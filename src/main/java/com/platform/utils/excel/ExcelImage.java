package com.platform.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类提供从外部读取图片到内存 将内存中的图片写出到外部Excel功能
 * 
 * @author zhuhaojie 创建时间 2016年7月20日 下午2:50:39
 */
public class ExcelImage {

	/**
	 * 根据图片路径，读取图片到内存
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午2:16:53
	 * @param imagesPath
	 *            要读取的图片绝路径数组
	 * @return List<BufferedImage> 图片集合对象
	 * @throws IOException 可能的IO异常
	 */
	public static List<BufferedImage> readImages(String[] imagesPath) throws IOException {
		if (imagesPath != null) {
			int length = imagesPath.length;
			if (length > 0) {
				List<BufferedImage> images = new ArrayList<BufferedImage>(length);
				for (int i = 0; i < length; i++) {
					String imagePath = imagesPath[i];
					if (imagePath != null) {
						imagePath = imagePath.trim();
						if (!imagePath.equals("")) {
							File file = new File(imagePath);
							if (file != null && file.exists() && file.canRead()) {
								BufferedImage image = ImageIO.read(file);
								if (image != null) {
									images.add(image);
								}
							}
						}
					}
				}
				return images;
			}
		}
		return null;
	}

	/**
	 * 将图片导出到Excel
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午2:29:05
	 * @param list
	 *            要导出的图片集合
	 * @param exportPath
	 *            导出图片路径
	 * @throws IOException
	 *             ImageIO的write方法抛出的此异常
	 */
	public static void exportImagesToExcel(List<BufferedImage> list, String exportPath) throws IOException {
		if (list != null) {
			int size = list.size();
			if (size > 0) {
				// 创建一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				// 在此文件里面创建一个Sheet
				HSSFSheet sheet1 = wb.createSheet("new sheet");
				// 在此Sheet对象里面创建一个画图的对象
				HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
				short i = 0;
				for (BufferedImage image : list) {
					// 字节数组输出流对象
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
					// 将此图片写入此对象
					ImageIO.write(image, "jpg", byteArrayOut);
					// 创建图片位置对象
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1 + i, (short) 2, 2 + i);
					anchor.setAnchorType(0);
					// 插入图片
					patriarch.createPicture(anchor,
							wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
					i++;
				}

				// 获取输出流对象
				FileOutputStream fileOut = null;
				try {
					fileOut = ExcelUtil.getFile(exportPath);
					if (fileOut != null) {
						// 写入excel文件
						wb.write(fileOut);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (fileOut != null) {
						fileOut.close();
						fileOut = null;
					}
				}
			}
		}

	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午2:56:36
	 * @param args
	 *            字符串数组参数
	 */
	public static void main(String[] args) {
		String[] picPaths = { "G:\\p\\1.jpg", "G:\\p\\2.jpg", "G:\\p\\3.jpg", "G:\\p\\5.jpg", };
		try {
			List<BufferedImage> list = readImages(picPaths); // 读取图片
			exportImagesToExcel(list, "G:\\newp\\picture.xlsx"); // 导出图片
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}