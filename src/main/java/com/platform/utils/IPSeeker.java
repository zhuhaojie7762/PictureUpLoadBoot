/**
 * 
 */
package com.platform.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * * 用来读取QQwry.dat文件，以根据ip获得好友位置，QQwry.dat的格式是 一. 文件头，共8字节 1. 第一个起始IP的绝对偏移， 4字节
 * 2. 最后一个起始IP的绝对偏移， 4字节 二. "结束地址/国家/区域"记录区 四字节ip地址后跟的每一条记录分成两个部分 1. 国家记录 2.
 * 地区记录 但是地区记录是不一定有的。而且国家记录和地区记录都有两种形式 1. 以0结束的字符串 2. 4个字节，一个字节可能为0x1或0x2 a.
 * 为0x1时，表示在绝对偏移后还跟着一个区域的记录，注意是绝对偏移之后，而不是这四个字节之后 b. 为0x2时，表示在绝对偏移后没有区域记录
 * 不管为0x1还是0x2，后三个字节都是实际国家名的文件内绝对偏移
 * 如果是地区记录，0x1和0x2的含义不明，但是如果出现这两个字节，也肯定是跟着3个字节偏移，如果不是 则为0结尾字符串 三.
 * "起始地址/结束地址偏移"记录区 1. 每条记录7字节，按照起始地址从小到大排列 a. 起始IP地址，4字节 b. 结束ip地址的绝对偏移，3字节
 *
 * 注意，这个文件里的ip地址和所有的偏移量均采用little-endian格式，而java是采用 big-endian格式的，要注意转换
 *
 *
 * @author 马若劼
 *
 *         改进，IP地址热部署 啊赶
 */
public class IPSeeker {
    /**
     * 日志
     */
    private Log log = LogFactory.getLog(IPSeeker.class);
    /**
     * 记录数
     */
    private static final int IP_RECORD_LENGTH = 7;
    /**
     * 
     */
    private static final byte AREA_FOLLOWED = 0x01;
    /**
     * 
     */
    private static final byte NO_AREA = 0x2;
    /**
     * 内存映射文件,提高IO 读取效率
     */
    private MappedByteBuffer buffer;

    /**
     * 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
     */
    private HashMap<String, IPLocation> cache = new HashMap<String, IPLocation>();

    /**
     * 开始位置
     */
    private int ipBegin;
    /**
     * 结束位置
     */
    private int ipEnd;

    /**
     * IPSeeker
     * 
     * @param file
     *            文件对像
     * @throws Exception
     *             异常
     */
    public IPSeeker(File file) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        buffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());

        if (buffer.order().toString().equals(ByteOrder.BIG_ENDIAN.toString())) {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        ipBegin = readInt(0);
        ipEnd = readInt(4);

        if (ipBegin == -1 || ipEnd == -1) {
            randomAccessFile.close();
            throw new IOException("IP地址信息文件格式有错误，IP显示功能将无法使用");
        }
        log.debug("使用IP地址库:" + file.getAbsolutePath());
        randomAccessFile.close();
    }

    /**
     * 给定一个ip 得到一个 ip地址信息
     *
     * @param ip
     *            IP地址
     * @return 得到一个 ip地址信息
     */
    public String getAddress(String ip) {
        return getCountry(ip) + " " + getArea(ip);
    }

    /**
     * 根据IP得到国家名
     *
     * @param ip
     *            IP的字符串形式
     * @return 国家名字符串
     */
    public String getCountry(String ip) {
        IPLocation cache = getIpLocation(ip);
        return cache.getCountry();
    }

    /**
     * 根据IP得到地区名
     *
     * @param ip
     *            IP的字符串形式
     * @return 地区名字符串
     */
    public String getArea(String ip) {
        IPLocation cache = getIpLocation(ip);
        return cache.getArea();
    }

    /**
     * 获得一个IP地址信息
     *
     * @param ip
     *            IP地址
     * @return IPLocation IPLocation
     */
    public IPLocation getIpLocation(String ip) {
        IPLocation ipLocation = null;
        try {
            if (cache.get(ip) != null) {
                return cache.get(ip);
            }
            ipLocation = getIPLocation(getIpByteArrayFromString(ip));
            if (ipLocation != null) {
                cache.put(ip, ipLocation);
            }
        } catch (Exception e) {
            log.error(e);
        }
        if (ipLocation == null) {
            ipLocation = new IPLocation();
            ipLocation.setCountry("未知国家");
            ipLocation.setArea("未知地区");
        }
        return ipLocation;
    }

    /**
     * 给定一个地点的不完全名字，得到一系列包含s子串的IP范围记录
     *
     * @param s
     *            地点子串
     * @return 包含IPEntry类型的List
     */
    public List<IPEntry> getIPEntries(String s) {
        List<IPEntry> ret = new ArrayList<IPEntry>();
        byte[] b4 = new byte[4];
        int endOffset = ipEnd + 4;
        for (int offset = ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
            // 读取结束IP偏移
            int temp = readInt3(offset);
            // 如果temp不等于-1，读取IP的地点信息
            if (temp != -1) {
                IPLocation loc = getIPLocation(temp);
                // 判断是否这个地点里面包含了s子串，如果包含了，添加这个记录到List中，如果没有，继续
                if (loc.country.indexOf(s) != -1 || loc.area.indexOf(s) != -1) {
                    IPEntry entry = new IPEntry();
                    entry.country = loc.country;
                    entry.area = loc.area;
                    // 得到起始IP
                    readIP(offset - 4, b4);
                    entry.beginIp = getIpStringFromBytes(b4);
                    // 得到结束IP
                    readIP(temp, b4);
                    entry.endIp = getIpStringFromBytes(b4);
                    // 添加该记录
                    ret.add(entry);
                }
            }
        }
        return ret;
    }

    /**
     * 根据ip搜索ip信息文件，得到IPLocation结构，所搜索的ip参数从类成员ip中得到
     *
     * @param ip
     *            要查询的IP
     * @return IPLocation结构
     */
    private IPLocation getIPLocation(byte[] ip) {
        IPLocation info = null;
        int offset = locateIP(ip);
        if (offset != -1) {
            info = getIPLocation(offset);
        }
        return info;
    }

    // -----------------以下为内部方法

    /**
     * 读取4个字节
     *
     * @param offset
     *            位置
     * @return int 4个字节
     */
    private int readInt(int offset) {
        buffer.position(offset);
        return buffer.getInt();
    }

    /**
     * 读取个字节
     *
     * @param offset
     *            位置
     * @return int 4个字节
     */
    private int readInt3(int offset) {
        buffer.position(offset);
        return buffer.getInt() & 0x00FFFFFF;
    }

    /**
     * 从内存映射文件的offset位置得到一个0结尾字符串
     *
     * @param offset
     *            位置
     * @return String 字符串
     */
    private String readString(int offset) {
        try {
            byte[] buf = new byte[100];
            buffer.position(offset);
            int i;
            for (i = 0, buf[i] = buffer.get(); buf[i] != 0; buf[++i] = buffer.get()) {
            }
            if (i != 0) {
                return getString(buf, 0, i, "GBK");
            }
        } catch (IllegalArgumentException e) {
            log.error(e);
        }
        return "";
    }

    /**
     * 从offset位置读取四个字节的ip地址放入ip数组中，读取后的ip为big-endian格式，但是
     * 文件中是little-endian形式，将会进行转换
     *
     * @param offset
     *            位置
     * @param ip
     *            ip
     */
    private void readIP(int offset, byte[] ip) {
        buffer.position(offset);
        buffer.get(ip);
        byte temp = ip[0];
        ip[0] = ip[3];
        ip[3] = temp;
        temp = ip[1];
        ip[1] = ip[2];
        ip[2] = temp;
    }

    /**
     * 把类成员ip和beginIp比较，注意这个beginIp是big-endian的
     *
     * @param ip
     *            要查询的IP
     * @param beginIp
     *            和被查询IP相比较的IP
     * @return 相等返回0，ip大于beginIp则返回1，小于返回-1。
     */
    private int compareIP(byte[] ip, byte[] beginIp) {
        for (int i = 0; i < 4; i++) {
            int r = compareByte(ip[i], beginIp[i]);
            if (r != 0) {
                return r;
            }
        }
        return 0;
    }

    /**
     * 把两个byte当作无符号数进行比较
     *
     * @param b1
     *            字节
     * @param b2
     *            字节
     * @return 若b1大于b2则返回1，相等返回0，小于返回-1
     */
    private int compareByte(byte b1, byte b2) {
        if ((b1 & 0xFF) > (b2 & 0xFF)) { // 比较是否大于
            return 1;
        } else if ((b1 ^ b2) == 0) { // 判断是否相等
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 这个方法将根据ip的内容，定位到包含这个ip国家地区的记录处，返回一个绝对偏移 方法使用二分法查找。
     *
     * @param ip
     *            要查询的IP
     * @return 如果找到了，返回结束IP的偏移，如果没有找到，返回-1
     */
    private int locateIP(byte[] ip) {
        int m = 0;
        int r;
        byte[] b4 = new byte[4];
        // 比较第一个ip项
        readIP(ipBegin, b4);
        r = compareIP(ip, b4);
        if (r == 0) {
            return ipBegin;
        } else if (r < 0) {
            return -1;
        }
        // 开始二分搜索
        for (int i = ipBegin, j = ipEnd; i < j;) {
            m = getMiddleOffset(i, j);
            readIP(m, b4);
            r = compareIP(ip, b4);
            // log.debug(Utils.getIpStringFromBytes(b));
            if (r > 0) {
                i = m;
            } else if (r < 0) {
                if (m == j) {
                    j -= IP_RECORD_LENGTH;
                    m = j;
                } else {
                    j = m;
                }
            } else {
                return readInt3(m + 4);
            }
        }
        // 如果循环结束了，那么i和j必定是相等的，这个记录为最可能的记录，但是并非
        // 肯定就是，还要检查一下，如果是，就返回结束地址区的绝对偏移
        m = readInt3(m + 4);
        readIP(m, b4);
        r = compareIP(ip, b4);
        if (r <= 0) {
            return m;
        } else {
            return -1;
        }
    }

    /**
     * 得到begin偏移和end偏移中间位置记录的偏移
     *
     * @param begin 开始
     * @param end   结束
     * @return int 
     */
    private int getMiddleOffset(int begin, int end) {
        int records = (end - begin) / IP_RECORD_LENGTH;
        records >>= 1;
        if (records == 0) {
            records = 1;
        }
        return begin + records * IP_RECORD_LENGTH;
    }

    /**
     * @param offset
     *            位置
     * @return IPLocation
     */
    private IPLocation getIPLocation(int offset) {
        IPLocation loc = new IPLocation();
        // 跳过4字节ip
        buffer.position(offset + 4);
        // 读取第一个字节判断是否标志字节
        byte b = buffer.get();
        if (b == AREA_FOLLOWED) {
            // 读取国家偏移
            int countryOffset = readInt3();
            // 跳转至偏移处
            buffer.position(countryOffset);
            // 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
            b = buffer.get();
            if (b == NO_AREA) {
                loc.country = readString(readInt3());
                buffer.position(countryOffset + 4);
            } else {
                loc.country = readString(countryOffset);
            }
            // 读取地区标志
            loc.area = readArea(buffer.position());
        } else if (b == NO_AREA) {
            loc.country = readString(readInt3());
            loc.area = readArea(offset + 8);
        } else {
            loc.country = readString(buffer.position() - 1);
            loc.area = readArea(buffer.position());
        }
        return loc;
    }

    /**
     * @param offset
     *            位置
     * @return String
     */
    private String readArea(int offset) {
        buffer.position(offset);
        byte b = buffer.get();
        if (b == 0x01 || b == 0x02) {
            int areaOffset = readInt3();
            if (areaOffset == 0) {
                return "未知地区";
            } else {
                return readString(areaOffset);
            }
        } else {
            return readString(offset);
        }
    }

    /**
     * 从内存映射文件的当前位置开始的3个字节读取一个int
     *
     * @return int 整数
     */
    private int readInt3() {
        return buffer.getInt() & 0x00FFFFFF;
    }

    /**
     * 从ip的字符串形式得到字节数组形式
     * 
     * @param ip
     *            字符串形式的ip
     * @return byte[] 字节数组形式的ip
     * @throws Exception
     *             异常
     */
    private static byte[] getIpByteArrayFromString(String ip) throws Exception {
        byte[] ret = new byte[4];
        java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
        try {
            ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            throw e;
        }
        return ret;
    }

    /**
     * 根据某种编码方式将字节数组转换成字符串
     *
     * @param b
     *            字节数组
     * @param offset
     *            要转换的起始位置
     * @param len
     *            要转换的长度
     * @param encoding
     *            编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    private static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }

    /**
     * @param ip
     *            ip的字节数组形式
     * @return 字符串形式的ip
     */
    private static String getIpStringFromBytes(byte[] ip) {
        StringBuffer sb = new StringBuffer();
        sb.append(ip[0] & 0xFF);
        sb.append('.');
        sb.append(ip[1] & 0xFF);
        sb.append('.');
        sb.append(ip[2] & 0xFF);
        sb.append('.');
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }

    /**
     * 测试main
     * 
     * @param args
     *            参数
     */
    public static void main(String[] args) {
        System.out.println(getCountryByIp("221.208.247.139"));
        /*
         * String address="湖南省长沙市"; int shengIndex=address.indexOf("省"); int
         * shiIndex=address.lastIndexOf("市");
         * System.out.println(address.substring(shengIndex+1, shiIndex));
         */
    }

    /**
     * IPLocation类
     * 
     * @author Administrator
     *
     */
    public class IPLocation {
        /**
         * 所在国家
         */
        private String country;
        /**
         * 所在地区
         */
        private String area;

        /**
         * 构造函数
         */
        public IPLocation() {
        }

        /**
         * 构造函数
         * 
         * @param country
         *            country
         * @param area
         *            area
         */
        public IPLocation(String country, String area) {
            this.country = country;
            this.area = area;
        }

        /**
         * 获取IPLocation的copy
         * 
         * @return IPLocation IPLocation
         */
        public IPLocation getCopy() {
            return new IPLocation(country, area);
        }

        /**
         * 
         * @return String String
         */
        public String getArea() {
            return " CZ88.NET".equals(area) ? "" : area;
        }

        /**
         * 设置地区
         * 
         * @param area
         *            area
         */
        public void setArea(String area) {
            this.area = area;
        }

        /**
         * 获取国家
         * 
         * @return String 国家
         */
        public String getCountry() {
            return " CZ88.NET".equals(country) ? "" : country;
        }

        /**
         * 设置国家
         * 
         * @param country 国家
         */
        public void setCountry(String country) {
            this.country = country;
        }
    }

    /**
     * * 一条IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP *
     *
     * @author 马若劼
     */
    public class IPEntry {
        /**
         * 开始
         */
        public String beginIp;
        /**
         * 结束
         */
        public String endIp;
        /**
         * 国家
         */
        public String country;
        /**
         * 地区
         */
        public String area;

        /**
         * 构造函数
         */
        public IPEntry() {
        }

        @Override
        public String toString() {
            return new StringBuilder(this.area).append(";").append(this.country).append(";").append("IP范围:")
                    .append(beginIp).append("-").append(endIp).toString();
        }
    }

    /**
     * 根据IP得到所在省市
     * 
     * @param ip IP
     * @return String 国家
     */
    public static String getCountryByIp(String ip) {
        String ss = IPSeeker.class.getClassLoader().getResource("com/chinasoft/core/util/qqwry.dat").getFile();
        try {
            IPSeeker ipSeeker = new IPSeeker(new File(ss));
            return ipSeeker.getCountry(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "全国";
    }

    /**
     * 根据区域全名解析出城市名
     * 
     * @param address  地址
     * @return  String 地址
     */
    public static String getCityName(String address) {
        if (StringUtils.isNotBlank(address)) {
            int shengIndex = address.indexOf("省");
            int shiIndex = address.lastIndexOf("市");
            int xjIndex = address.lastIndexOf("新疆");
            int nmgIndex = address.lastIndexOf("内蒙古");
            int xzIndex = address.lastIndexOf("西藏");
            int gxIndex = address.lastIndexOf("广西");
            if (shiIndex > 0) {
                if (shengIndex > 0) {
                    return address.substring(shengIndex + 1, shiIndex);
                } else if (xjIndex != -1) {
                    return address.substring(xjIndex + 2, shiIndex);
                } else if (nmgIndex != -1) {
                    return address.substring(nmgIndex + 3, shiIndex);
                } else if (xzIndex != -1) {
                    return address.substring(xzIndex + 2, shiIndex);
                } else if (gxIndex != -1) {
                    return address.substring(gxIndex + 2, shiIndex);
                } else { //直辖市
                    return address.substring(0, shiIndex);
                }
            } else {
                System.out.println(address + " 无法解析到城市");
            }
        }
        return "全国";
    }
}
