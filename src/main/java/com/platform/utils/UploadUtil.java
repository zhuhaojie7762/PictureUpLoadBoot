package com.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



/**
 * 文件上传
 * 
 * @author: jinlei
 * @version: 2016年12月30日 下午4:55:14
 */
public class UploadUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(UploadUtil.class);

	/**
	 * 上传文件
	 * 
	 * @param files
	 *            file
	 * @param url
	 *            服务器地址
	 * @return map
	 */
	public static Map<String, Object> upload(MultipartFile[] files, String url) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		boolean flg = false;
		LOGGER.debug("files:" + Arrays.toString(files));
		// 上传文件为空 返回
		if (files == null || files.length == 0) {
			paramMap.put("msg","文件为空");
			paramMap.put("flg", flg);
			return paramMap;
		}
		try {
			for (MultipartFile file : files) {
				// 文件名称
				String originalFilename = file.getOriginalFilename();
				// 绝对目录
				String absFileName = url + File.separator + originalFilename;
				LOGGER.debug("绝对路径:" + absFileName);
				File fileObjcet = new File(absFileName);
				LOGGER.debug("目录是否存在:" + new File(url).exists());
				// 路径不存在
				if (!new File(url).exists()) {
					paramMap.put("msg", "路径不存在");
					paramMap.put("flg", flg);
					return paramMap;
				}
				file.transferTo(fileObjcet);
				paramMap.put("absFileName", absFileName);
			}
		} catch (Exception e) {
			LOGGER.error("上传失败：", e);
			paramMap.put("msg","上传失败");
			paramMap.put("flg", flg);
		}
		flg = true;
		paramMap.put("flg", flg);
		return paramMap;
	}

	/**
	 * 删除文件
	 * 
	 * @param absFileName
	 *            绝对路径
	 */
	public static void delUpload(String absFileName) {
		File fileObjcet = new File(absFileName);
		if (fileObjcet.exists()) {
			fileObjcet.delete();
		}
	}

}
