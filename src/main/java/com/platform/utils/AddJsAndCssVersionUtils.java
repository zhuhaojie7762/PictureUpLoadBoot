package com.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddJsAndCssVersionUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String path;

    private String version = "1.0.1";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void execute() {
        addVersionTo(path, version);
    }

    private void addVersionTo(String path, String version) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addVersionTo(files[i].getAbsolutePath(), version);
            } else {
                boolean isUpdate = false;
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                logger.info("-->file::{}", strFileName);
                //log.debug("get file {}", strFileName);
                // 如果是符合条件的文件，则添加版本信息
                if (strFileName.endsWith(".html") || strFileName.endsWith(".jsp")) {
                    // RandomAccessFile raf = null;
                    InputStream is = null;
                    OutputStream os = null;
                    List contentList = new ArrayList();
                    // 读文件
                    try {
                        is = new FileInputStream(files[i]);
                        Reader r = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(r);
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            String modLine = getModLine(line, version);
                            if (modLine != null) {
                                logger.info("-->modi-1:{}", line);
                                logger.info("-->modi-2:{}", modLine);
                                line = modLine;
                                isUpdate = true;
                            }
                            line = line + "\n";
                            contentList.add(line);
                        }
                        // 关闭流
                        br.close();
                        r.close();
                    } catch (Exception e) {
                        System.out.println("读文件失败");
                        e.printStackTrace();
                    } finally {
                        if (null != is) {
                            try {
                                is.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if ( !isUpdate ) {
                        continue;
                    }
                    // 写文件
                    try {
                        os = new FileOutputStream(files[i]);
                        Writer w = new OutputStreamWriter(os);
                        BufferedWriter bw = new BufferedWriter(w);
                        int size = contentList.size();
                        for (Iterator it = contentList.iterator(); it.hasNext();) {
                            String line = (String) it.next();
                            size = size--;
                            if ( size == 0 ) {
                                line = line.replace("\n", "");
                            }
                            bw.write(line);
                        }
                        // 更新到文件
                        bw.flush();
                        // 关闭流
                        bw.close();
                        w.close();
                    } catch (Exception e) {
                        System.out.println("写文件失败");
                        e.printStackTrace();
                    } finally {
                        if (null != os) {
                            try {
                                os.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int matcher(String content, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content); // 获取 matcher 对象
        int count = 0;
        int end = -1;
        while (m.find()) {
            count++;
            end = m.end();
        }
        if (count == 1) {
            return end;
        }
        return -1;
    }

    // href="${ctxStatic }/css/
    private String getModLine(String line, String version) {
        // 增加js版本
        line.trim();
        //String pattern = "<script(.*)src(.*)\\.(js|css)";
        //String pattern = "<script(.*)src(.*)(/css|js/)(.*)\\.(js|css)";
        String pattern = "<script(.*)src(.*)}/(css|js)/(.*)\\.(js|css)";
        int end = matcher(line, pattern);
        if (end == -1) {
            return null;
        }
        String replaceAs = "?version=" + version;
        int index = line.indexOf("version");
        StringBuffer sb = new StringBuffer(line);
        if (index == -1) {
            sb.insert(end, replaceAs);
        } else {
            int start = end;
            end = line.lastIndexOf("\"");
            sb.replace(start, end, replaceAs);
        }
        return sb.toString();
    }

    public void testReplace() {
        String content;
        content = "<script src=\"../css/task/taskList.css\">";
        //content = "<script src=\"../resource/js/task/taskList.js?version=0.0.0\">";
        //content = "<script src=\"../resource/css/task/taskList.css?version=0.0.0\">";
        String repl = getModLine(content, version);
        logger.info("-->modi-1:{}", content);
        logger.info("-->modi-2:{}", repl);
        System.out.println("repl:" + repl);
    }

    public static void main(String[] args) {
//        String path = "D:\\temp\\webapp\\jspview";
//        path="D:\\amap\\trunk\\02_Code\\hdmap\\manage\\src\\main\\webapp\\jspview";
//        String version = "${version}";
        AddJsAndCssVersionUtils utils = new AddJsAndCssVersionUtils();
//        utils.setPath(path);
//        utils.setVersion(version);
//        utils.execute();
        utils.testReplace();
    }
}
