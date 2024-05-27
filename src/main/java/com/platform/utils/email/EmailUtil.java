package com.platform.utils.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.*;

/**
 * 新邮件工具类
 ** 
 * @author：zhuhaojie
 * @time：2017年6月23日 下午5:14:35
 */
public class EmailUtil {

    /**
     * 模板中要替换内容的开始标识
     */
    private static final String pre = "${";
    /**
     * 模板中要替换内容的结束标识
     */
    private static final String end = "}";

    /**
     * Logger日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * 
     * @author：zhuhaojie
     * @time：2017年6月23日 下午5:15:26
     */
    private EmailUtil() {
    }

    /**
     * 读取html模板并将其转换成String返回
     * 
     * @author zhuhaojie
     * @time 2016年12月12日 上午11:46:54
     * @param path
     *            html模板绝对路径
     * @return 读取到的html内容
     * @throws IOException
     *             读取文件发生错误时抛出异常
     */
    public static String readHtmlMailString(String path) throws IOException {

        StringBuffer buff = new StringBuffer();
        InputStreamReader in = null;
        BufferedReader br = null;

        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("文件:" + path + "不存在！");
        } else if (!file.canRead()) {
            throw new RuntimeException("文件:" + path + "不可读！");
        }
        try {
            in = new InputStreamReader(new FileInputStream(file), "UTF-8");
            br = new BufferedReader(in);
            String line = null;
            while ((line = br.readLine()) != null) {
                buff.append(line).append("\n");
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buff.toString();
    }

    /**
     * 
     * @author zhuhaojie
     * @time 2016年12月12日 下午12:46:01
     * @param filepath
     *            html模板绝对路径
     * @param map
     *            模板中要替换的key,value对，key是模板中的以特殊字符包括的字符串 value 是要替换的真正值
     * @throws IOException
     *             当读取模板失败时抛出
     * @return 转换后的html生成的字符串
     */
    public static String setTemplate(String filepath, Map<String, String> map) throws IOException {

        try {
            String content = readHtmlMailString(filepath);
            // 替换标识
            content = replace(content, map);

            return content;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("解析html模板失败: e" + e);
            throw e;
        }

    }

    /**
     * 将html模板中的开始和结束标签值，转换成map中对应的value 并返回替换后的字符串
     * 
     * @author zhuhaojie
     * @time 2016年12月12日 下午1:09:37
     * @param content
     *            替换前模板内容
     * @param map
     *            准备替换的map值
     * @return String 替换后的模板内容
     */
    public static String replace(String content, Map<String, String> map) {
        if (map == null || map.values().size() == 0)
            return content;
        Collection<String> cl = map.keySet();
        for (String key : cl) {
            String temp = pre + key + end;
            content = content.replace(temp, map.get(key));
        }
        return content;
    }

    /**
     * 
     * @author zhuhaojie
     * @time 2016年12月12日 下午1:22:21
     * @param smtpFromMail
     *            发件箱地址
     * @param host
     *            发件箱主机地址
     * @param userName
     *            发件方用户名
     * @param pwd
     *            发件箱密码
     * @param port
     *            发件箱端口号
     * @param emailTitle
     *            邮件标题
     * @param toMail
     *            收件箱地址
     * @param templatePath
     *            模板绝对路径
     * @param map
     *            模板中含有pre和end标识的内容要替换的map value
     * @param files
     *            附件列表
     * 
     * @throws IOException
     *             解析模板时可能抛出的异常
     * @throws MessagingException
     *             构建邮件时可能抛出的异常
     */
    public static void sendMailByTemplete(String smtpFromMail, String host, String userName,
            String pwd, int port, String emailTitle, String[] toMail, String templatePath, Map<String, String> map,
            String[] files) throws IOException, MessagingException {
        String content = setTemplate(templatePath, map);
        sendMail(smtpFromMail, host,userName, pwd, port, emailTitle, toMail, content, files);
    }

    /**
     * 
     * @author zhuhaojie
     * @time 2016年12月8日 下午7:07:39
     * @param smtpFromMail
     *            发件箱地址
     * @param host
     *            发件箱主机地址
     * @param userName
     *            发件箱用户名
     * @param pwd
     *            发件箱密码
     * @param port
     *            发件箱端口号
     * @param emailTitle
     *            邮件标题
     * @param toMail
     *            收件箱地址
     * @param content
     *            邮件内容
     * @param fileNames
     *            附件列表
     * @throws MessagingException
     *             构建邮件信息可能抛出的异常
     * @throws UnsupportedEncodingException
     *             设置邮件编码异常
     * 
     */
    public static void sendMail(String smtpFromMail, String host, String userName, String pwd,
            int port, String emailTitle, String[] toMail, String content, String[] fileNames)
                    throws UnsupportedEncodingException, MessagingException {

        if(userName!=null){
            userName=userName.trim();
        }
        if("".equals(userName)){
            userName=null;
        }
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        // 设置认证方式
        props.put("mail.smtp.auth.mechanisms", "NTLM");
        Session sendMailSession = Session.getInstance(props);
        // 设置成调试模式
        sendMailSession.setDebug(true);

        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        Address from = new InternetAddress(smtpFromMail);

        // 创建邮件的接收者地址，并设置到邮件消息中
        int toLength = toMail.length;
        InternetAddress[] ToAddress = new InternetAddress[toLength];
        for (int i = 0; i < toLength; i++) {
            ToAddress[i] = new InternetAddress(toMail[i]);
        }
        mailMessage.setRecipients(Message.RecipientType.TO, ToAddress);
        // 设置邮件消息的发送者
        mailMessage.setFrom(from);
        // 设置邮件消息的主题
        mailMessage.setSubject(emailTitle);
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        mailMessage.addHeader("charset", "UTF-8");
        /* 添加正文内容 */
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
     // // 设置邮件消息的主要内容
        contentPart.setContent(content, "text/html;charset=utf-8");
        multipart.addBodyPart(contentPart);
        if (fileNames != null && fileNames.length > 0) {
            File[] files = toFile(fileNames);
            if (files != null && files.length > 0) {
                for (File file : files) {
                    MimeBodyPart fileBody = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    fileBody.setDataHandler(new DataHandler(source));
                    fileBody.setFileName(file.getName());
                    multipart.addBodyPart(fileBody);
                }

            }
        }
        mailMessage.setContent(multipart);
        mailMessage.saveChanges();
        Transport transport = sendMailSession.getTransport("smtp");
        // transport.connect(host, (null == domainUser) ? username : domainUser,
        // password);
        transport.connect(host, (null == userName) ? smtpFromMail : userName, pwd);
        // transport.connect(host,smtpFromMail, pwd);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    /**
     * 以字节List形式发送带附件的邮件
     * 
     * @param smtpFromMail
     *            发件箱地址
     * @param host
     *            发件箱服务器地址
     * @param userName
     *            发件箱用户名
     * @param pwd
     *            发件箱密码
     * @param port
     *            发件邮箱服务器端口号
     * @param emailTitle
     *            邮件标题
     * @param toMail[]
     *            收件邮箱地址
     * @param content
     *            邮件内容
     * @param list
     *            附件列表
     * @throws UnsupportedEncodingException
     *             编码异常
     * @throws MessagingException
     *             发送信息异常
     * @author：zhuhaojie
     * @time：2017年6月26日 下午1:28:27
     */
    public static void sendMail(String smtpFromMail, String host, String userName, String pwd,
            int port, String emailTitle, String[] toMail, String content, LinkedList<byte[]> list)
                    throws UnsupportedEncodingException, MessagingException {
        
       
        if(userName!=null){
            userName=userName.trim();
        }
        if("".equals(userName)){
            userName=null;
        }
           

        Properties props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        // 设置认证方式
        props.put("mail.smtp.auth.mechanisms", "NTLM");
        Session sendMailSession = Session.getInstance(props);
        // 设置成调试模式
        sendMailSession.setDebug(true);

        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);

        // 创建邮件发送者地址
        Address from = new InternetAddress(smtpFromMail);
        // 设置邮件消息的发送者
        mailMessage.setFrom(from);

        // 创建邮件的接收者地址，并设置到邮件消息中

        int toLength = toMail.length;
        InternetAddress[] Toaddress = new InternetAddress[toLength];
        for (int i = 0; i < toLength; i++) {
            Toaddress[i] = new InternetAddress(toMail[i]);
        }

        mailMessage.setRecipients(Message.RecipientType.TO, Toaddress);
        // 设置邮件消息的主题
        mailMessage.setSubject(emailTitle);
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        mailMessage.addHeader("charset", "UTF-8");

        // 设置邮件消息的主要内容
        String mailContent = content;
        // mailMessage.setText(mailContent);
        /* 添加正文内容 */
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setText(mailContent);
        contentPart.setHeader("Content-Type", "text/html; charset=UTF-8");
        multipart.addBodyPart(contentPart);

        if (list != null && list.size() > 0) {
            for (byte[] bytes : list) {
                MimeBodyPart fileBody = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(bytes, "application/octet-stream");
                fileBody.setDataHandler(new DataHandler(source));
                fileBody.setFileName(System.currentTimeMillis() + ".xls");
                multipart.addBodyPart(fileBody);
            }
        }
        mailMessage.setContent(multipart);
        mailMessage.saveChanges();
        Transport transport = sendMailSession.getTransport("smtp");
        transport.connect(host, (null == userName) ? smtpFromMail : userName, pwd);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
       
        String smtpFromMail = "liuxiangzong@chinasoftinc.com";
       
        String host = "smtp.chinasoftinc.com";
       
        String userName = "liuxiangzong@chinasoftinc.com";
        String domainUser = "liuxiangzong";
        System.out.println("domainUser:" + domainUser);
        if ("".equals(domainUser))
            domainUser = null;

       

        String pwd = "#240056qaz";
        int port = 25;
        // int port = Integer.valueOf(args[4]);
        String emailTitle = "邮箱标题";
        // String emailTitle = args[1];
        String toMail = "zhuhaojie@chinasoftinc.com";
        // String toMail = args[0];
        String content = "这是邮件内容";
        // String content =args[2];
        String[] files = new String[] { "d:/6.jpg", "d:/7.jpg" };
        // String[] files = null;
        LinkedList<byte[]> list = new LinkedList<byte[]>();
        /**
         * EmailUtilSecond mail=new EmailUtilSecond(host, "true", domainUser,
         * username, password); mail.send(new String[] {to}, null, null,
         * "tititle", "<h3>test</h3>"); System.out.println("send ok.");
         */
        sendMail(smtpFromMail, host, userName, pwd, port, emailTitle, new String[] { toMail }, content,
                list);
    }

    /**
     * @param filePaths
     *            要上传文件的地址列表
     * @return File[] 转换后的文件数组
     * @author：zhuhaojie
     * @time：2017年6月26日 下午12:32:28
     */
    private static File[] toFile(String[] filePaths) {
        List<File> list = new ArrayList<File>();
        for (String fileName : filePaths) {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new RuntimeException("文件:" + file.getName() + "不存在");
            }
            if (!file.canRead()) {
                throw new RuntimeException("文件:" + file.getName() + "不可读");
            }
            list.add(file);
        }
        return list.toArray(new File[1]);
    }
}