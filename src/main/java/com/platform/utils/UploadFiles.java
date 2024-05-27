package com.platform.utils;

/**
 * 文件类，需要的时候，可以和数据库进行关联
 * @author Administrator
 *
 */
public class UploadFiles {
    /**
     * 上传的文件名称
     */
    private String uploadFileName;
    /**
     * 类型
     */
    private String uploadContentType;
    /**
     * 保存的文件真实名称，UUID
     */
    private String uploadRealName;

    // 如果使用数据库的话，建议这三个字段都进行保存

    /**
     * 设置上传的文件
     * 
     * @return String 上传的文件
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * 设置上传文件名
     * 
     * @param uploadFileName
     *            文件名
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * 获取上传的文件内容类型
     * 
     * @return uploadContentType 文件内容类型
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * 设置上传的文件内容类型
     * 
     * @param uploadContentType 文件内容类型
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * 获取上传真实文件名
     * 
     * @return uploadRealName 真实文件名
     */
    public String getUploadRealName() {
        return uploadRealName;
    }

    /**
     * 设置上传真实文件名
     * 
     * @param uploadRealName 真实文件名
     */
    public void setUploadRealName(String uploadRealName) {
        this.uploadRealName = uploadRealName;
    }

}
