package com.platform.servlet;

import com.platform.entity.NewProductBase;
import com.platform.service.NewProductBaseService;
import com.platform.utils.DateUtils;
import com.platform.utils.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet(name = "up", urlPatterns = {"/toUp"})
public class FileUpLoad extends HttpServlet {
    /**
     * http://47.110.131.165:9905/dmp/anyrt/file/201812/98554b2c-b411-42ed-9b32-
     * ecb8b6a0dd20.png
     */
    private static final long serialVersionUID = 1L;

    /**
     * 先查询对应数据库中是否有对应的款号 如果没有提示。 如果存在就开始解析批量新增 批量上传成功后更新
     */

    private NewProductBaseService newProductBaseService;

    private String dataDir;
    private String sourceDataDir;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder pubUrl = new StringBuilder();
        List<String> hasData = new ArrayList<>();
        List<String> noData = new ArrayList<>();
        List<String> allItemNoList = new ArrayList<>();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String date = DateUtils.dateToStr(new Date());
        String[] dates = date.split("-");
        StringBuilder mulu = new StringBuilder();
        mulu.append(dates[0]).append(dates[1]);
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter out = response.getWriter();
        try {
            List<FileItem> itemList = upload.parseRequest(request);
            if (itemList != null && itemList.size() > 0) {
                StringBuilder basePath = new StringBuilder();
                String path = request.getContextPath();

                pubUrl.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
                        .append("8080/static/images");

                basePath.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
                        .append(request.getServerPort()).append(path).append("/upload/");
                Iterator<FileItem> it = itemList.iterator();
                List<String> pics = new ArrayList<>();
                while (it.hasNext()) {
                    FileItem item = it.next();
                    if (item != null) {
                        if (!item.isFormField()) {
                            String name = item.getName();
                            if (StringUtil.isNotEmpty(name)) {
                                int index = name.lastIndexOf(".");
                                if (index != -1) {
                                    String name1 = name.substring(0, index);
                                    allItemNoList.add(name1);
                                }
                            }
                        }
                    }
                }
                Map<String, NewProductBase> map = newProductBaseService.getNewProductBaseByItemNo(allItemNoList);
                for (String itemNo : allItemNoList) {
                    if (map.containsKey(itemNo)) {
                        hasData.add(itemNo);
                    } else {
                        noData.add(itemNo);
                    }
                }
                it = itemList.iterator();
                Map<String, String> itemNoPicture = new HashMap<>(itemList.size());
                while (it.hasNext()) {

                    FileItem item = it.next();
                    if (item != null) {
                        if (item.isFormField()) {
                            String name = item.getFieldName();
                            name = deal(name);
                            String str = item.getString();
                            str = deal(str);
                            request.setAttribute(name, str);
                            System.out.println("FormField: " + name + " = " + str);
                        } else {
                            String name = item.getName();
                            System.out.println("File: " + name);
                            if (name != null && !"".equals(name.trim())) {
                                name = name.trim();
                                String name1 = "";
                                int indexOne = name.lastIndexOf(".");
                                if (indexOne != -1) {
                                    name1 = name.substring(0, indexOne);
                                    if (map.containsKey(name1)) {
                                        continue;
                                    }
                                }

                                String fileName = name;
                                int index = fileName.lastIndexOf("\\");
                                if (index != -1) {
                                    fileName = fileName.substring(index);
                                }
                                upLoadFileToPath(dataDir, item, mulu, fileName);
                                System.out.println("dataDir:" + dataDir);
                                //upLoadFileToPath(sourceDataDir,item,mulu,fileName);
                                String url = dataDir + File.separator + mulu + File.separator + fileName;
                                System.out.println("url:" + url);
                                String url2 = pubUrl + File.separator + mulu + File.separator + fileName;
                                System.out.println("url2:" + url2);
                                itemNoPicture.put(name1, url2);
                                pics.add(url2);
                            }
                        }
                    }
                }

                /**
                 * 开始更新数据库
                 */
                if (itemNoPicture.size() > 0) {
                    if (hasData.size() > 0) {
                        List<NewProductBase> list = newProductBaseService.listByItemNo(hasData);
                        if (list != null && list.size() > 0) {
                            for (NewProductBase newProductBase : list) {
                                String itemNo = newProductBase.getItemNo();
                                String url = itemNoPicture.get(itemNo);
                                newProductBase.setPicture(url);
                            }
                        }
                        newProductBaseService.updateBatch(list);
                    }
                    if (noData.size() > 0) {
                        List<NewProductBase> list = new ArrayList<>(noData.size());
                        for (String noDatum : noData) {
                            NewProductBase newProductBase = new NewProductBase();
                            //newProductBase.setPicture(noDatum);
                            newProductBase.setItemNo(noDatum);
                            newProductBase.setPicture(itemNoPicture.get(noDatum));
                            newProductBase.setCreater("SYSTEM");
                            list.add(newProductBase);
                        }
                        newProductBaseService.saveOrUpdateBatch(list);
                    }

                }
                String stringBuffer = "上传完成的图片地址:" + pics;
                out.write(stringBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("上传发生错误：" + e.getMessage());
        }
    }


    public void upLoadFileToPath(String path, FileItem item, StringBuilder mulu, String fileName) throws Exception {
        String dataDirOne = path + File.separator + mulu + File.separator + fileName;
        File saved = new File(dataDirOne);
        boolean f = saved.getParentFile().exists();
        boolean k = saved.isDirectory();
        if (!f && !k) {
            File fileP = saved.getParentFile();
            boolean t = fileP.mkdirs();
            if (!t) {
                throw new Exception("创建父路径：" + fileP.getName() + "失败");
            }

        }
        if (saved.exists()) {
            boolean t = saved.delete();
            if (t) {
                boolean m = saved.createNewFile();
                if (!m) {
                    throw new Exception("创建新文件失败");
                }
            } else {
                throw new Exception("删除原始有的文件失败");
            }
        }
        InputStream ins = item.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(ins);
        OutputStream ous = new FileOutputStream(saved);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(ous);
        byte[] tmp = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(tmp)) != -1) {
            //ous.write(tmp, 0, len);
            bufferedOutputStream.write(tmp, 0, len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
//        ous.flush();
//        ous.close();
    }


    public String deal(String name) {

        if (name != null) {
            name = name.trim();
            if (!"".equals(name)) {
                try {
                    name = new String(name.getBytes("iso8859-1"), StandardCharsets.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return name;
            } else {
                return "";
            }
        }
        return null;
    }

    public void init() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        try {
            ServletContext servletContext = this.getServletContext();
            WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            assert ctx != null;
            newProductBaseService = (NewProductBaseService) ctx.getBean("newProductBaseService");
            //相对路径
            //dataDir = servletContext.getContextPath()+File.separator+"static"+File.separator+"images";
            //绝对路径
            //dataDir = servletContext.getRealPath("/")+"static"+File.separator+"images";
            ClassLoader classLoader = FileUpLoad.class.getClassLoader();
            URL resource = classLoader.getResource("static/images");
            dataDir = resource.getPath();
            dataDir = URLDecoder.decode(dataDir, "UTF-8");
            System.out.println("dataDir:" + dataDir);

            /**
             * request.getServletContext().getRealPath("")
             * request.getServletContext().getRealPath("/")
             * FileUpLoad.class.getResource("").getPath()
             * FileUpLoad.class.getResource(“/”).getPath()
             * FileUpLoad.class.getClassLoader().getResource("").getPath()
             * FileUpLoad.getURL("classpath:").getPath()
             *
             */
            logger.info("dataDir:" + dataDir);

        } catch (Exception e) {
            logger.error("servlet获取spring中的bean异常---------" + e);
            e.printStackTrace();
        }
    }
}