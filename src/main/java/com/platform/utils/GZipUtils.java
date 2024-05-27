package com.platform.utils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩
 * 
 * @author Administrator
 *
 */
public class GZipUtils {
    /**
     * 缓冲区大小
     */
    public static final int BUFFER = 1024;
    /**
     * 扩展名
     */
    public static final String EXT = ".gz";

    /**
     * 数据压缩
     * 
     * @param data
     *            原数据
     * @return byte[] 压缩后数据
     * @throws IOException
     *             异常
     */
    public static byte[] compress(byte[] data) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return output;
    }

    /**
     * 文件压缩
     * 
     * @param file
     *            文件名
     * @throws Exception
     *             异常
     */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /**
     * 文件压缩
     * 
     * @param file
     *            文件名
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     *             异常
     */
    public static void compress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);

        compress(fis, fos);

        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            file.delete();
        }
    }

    /**
     * 数据压缩
     * 
     * @param is
     *            InputStream
     * @param os
     *            OutputStream
     * @throws IOException
     *             IOException
     */
    public static void compress(InputStream is, OutputStream os) throws IOException {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte[] data = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }

        gos.finish();

        gos.flush();
        gos.close();
    }

    /**
     * 文件压缩
     * 
     * @param path
     *            路径
     * @throws Exception
     *             异学点
     */
    public static void compress(String path) throws Exception {
        compress(path, true);
    }

    /**
     * 文件压缩
     * 
     * @param path
     *            路径
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     *             Exception
     */
    public static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 数据解压缩
     * 
     * @param data
     *            压缩源数据
     * @return byte[] 压缩后数据
     * @throws Exception
     *             Exception
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 解压缩

        decompress(bais, baos);
        data = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return data;
    }

    /**
     * 文件解压缩
     * 
     * @param file
     *            文件
     * @throws Exception
     *             异常
     */
    public static void decompress(File file) throws Exception {
        decompress(file, true);
    }

    /**
     * 文件解压缩
     * 
     * @param file
     *            文件
     * @param delete
     *            是否删除原始文件
     * @throws Exception Exception
     */
    public static void decompress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT, ""));
        decompress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            file.delete();
        }
    }

    /**
     * 数据解压缩
     * 
     * @param is
     *            InputStream
     * @param os
     *            OutputStream
     * @throws Exception
     *             Exception
     */
    public static void decompress(InputStream is, OutputStream os) throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }

        gis.close();
    }

    /**
     * 文件解压缩
     * 
     * @param path 目录
     * @throws Exception Exception
     */
    public static void decompress(String path) throws Exception {
        decompress(path, true);
    }

    /**
     * 文件解压缩
     * 
     * @param path 目录
     * @param delete 
     *            是否删除原始文件
     * @throws Exception Exception
     */
    public static void decompress(String path, boolean delete) throws Exception {
        File file = new File(path);
        decompress(file, delete);
    }

}
