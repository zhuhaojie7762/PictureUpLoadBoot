package com.platform.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件下载工具类
 ** 
 * @author：zhuhaojie
 * @time：2017年4月19日 下午12:44:45
 */
public class FileDownLoadUtil {
    /**
     * 文件的MIME类型
     */
    private static final String CONTENT_TYPE = "multipart/form-data";

    /**
     * 从本机下载文件
     * 
     * @author：zhuhaojie
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @throws Exception
     *             下载文件时可能抛出的异常
     * @time：2017年4月19日 下午5:29:59
     */
    public static void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(CONTENT_TYPE);
        // 得到下载文件的名字
        // String filename=request.getParameter("filename");
        String dataDir = request.getSession().getServletContext().getRealPath("/");
        dataDir = dataDir + "resource" + File.separator + "downLoadFile" + File.separator;
        String filename = request.getParameter("filename");

        String filePath = dataDir + filename;
        // 创建file对象
        File file = new File(filePath);

        // 设置response的编码方式
        // response.setContentType("application/x-msdownload");

        // 写明要下载的文件的大小
        response.setContentLength((int) file.length());
        // 解决中文乱码
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(filename.getBytes("utf-8"), "iso-8859-1"));

        // 读出文件到i/o流
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream buff = new BufferedInputStream(fis);

        byte[] b = new byte[1024]; // 相当于我们的缓存

        long k = 0; // 该值用于计算当前实际下载了多少字节

        // 从response对象中得到输出流,准备下载

        OutputStream out = response.getOutputStream();

        // 开始循环下载
        long total = file.length();

        while (k < total) {

            int j = buff.read(b, 0, 1024);
            k += j;

            // 将b中的数据写到客户端的内存
            out.write(b, 0, j);

        }

        // 将写入到客户端的内存的数据,刷新到磁盘
        out.flush();
        out.close();
        buff.close();

    }

    /**
     * @param response
     *            相应对象
     * @param urlAddress
     *            文件url地址
     * @throws Exception
     *             下载文件时可能抛出的异常
     * @author：zhuhaojie
     * @time：2017年4月19日 下午1:12:47
     */
    public static void downloadNet(HttpServletResponse response, String urlAddress) throws Exception {
        response.setContentType(CONTENT_TYPE);
        urlAddress = urlAddress.trim();
        int length = urlAddress.length();
        int index = urlAddress.lastIndexOf(".");
        String suffix = "";
        String fileName = "";
        if (index != -1) {
            suffix = urlAddress.substring(index, length);

            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + suffix;
        }
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        URL url = new URL(urlAddress); // 构造url对象
        // URLConnection conn = url.openConnection();// 开启连接对象
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(50000); // 设置超时时间
        int code = conn.getResponseCode();
        if (code == 200) {
            InputStream in = conn.getInputStream(); // 获取输入流对象
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream(); // 获取输出流对象
            byte[] buffer = new byte[1024]; // 准备缓冲区
            // 循环从输入流中读取数据到缓冲区
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;

                // 将缓冲区的数据写出到输出流
                out.write(buffer, 0, byteread);
            }
            System.out.println("总字节数:" + bytesum);
            out.flush();
            out.close();
            in.close();
        }
    }
}
