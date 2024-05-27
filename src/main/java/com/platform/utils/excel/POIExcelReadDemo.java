package com.platform.utils.excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel数据读取工具类
 * 
 * @author zhuhaojie 创建时间 2016年7月20日 下午1:56:25
 */
public class POIExcelReadDemo {

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:50:38
	 * @param args
	 *            字符串数组
	 *
	 */
	public static void main(String[] args) {
		try {
			List<List<List<String>>> result = readExcel("G:\\列表.xls");
			if (result != null) {
				int length = result.size();
				if (length > 0) {
					for (int i = 0; i < length; i++) {
						List<List<String>> result2 = result.get(i);
						int length2 = result2.size();
						if (length2 > 0) {
							for (int j = 0; j < length2; j++) {
								List<String> result3 = result2.get(j);
								if (result3 != null) {
									int length3 = result3.size();
									if (length3 > 0) {
										for (int k = 0; k < length3; k++) {
											System.out.print(result3.get(k) + " ");
										}
									}
								}
								System.out.println();
							}
						}
						System.out.println("---------");
					}
				}
			}
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:53:31
	 * @param path
	 *            外部Excel文件路径
	 * @return List<List<List<String>>> 读取到的Excel数据
	 * @throws EncryptedDocumentException 异常
	 * @throws InvalidFormatException
	 *             无效格式异常
	 * @throws IOException
	 *             读取文件异常
	 */
	public static List<List<List<String>>> readExcel(String path)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		if (path != null) {
			path = path.trim();
			if (!path.equals("")) {
				File file = new File(path);
				if (file != null) {
					if (file.exists() && file.canRead()) {
						InputStream inp = new FileInputStream(file);
						Workbook wb = WorkbookFactory.create(inp);
						List<List<List<String>>> result = readNew(wb);
						return result;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:57:53
	 * @param wb
	 *            工作表格代表外部的Excel文件
	 * @return List<List<List<String>>> 从Excel获取到的数据
	 */
	private static List<List<List<String>>> readNew(Workbook wb) {
		if (wb != null) {
			// 获取文件中工作表个数
			int numberSheet = wb.getNumberOfSheets();
			if (numberSheet > 0) {
				// 存储所有数据
				List<List<List<String>>> data = new ArrayList<List<List<String>>>(numberSheet);
				for (int i = 0; i < numberSheet; i++) {
					// 循环获取每一个工作表
					Sheet sheet = wb.getSheetAt(i);
					if (sheet != null) {
						// 获取此工作表的有效数据行数
						int line = sheet.getPhysicalNumberOfRows();
						if (line > 0) {
							// 存储每一个工作表
							List<List<String>> sheetdata = new ArrayList<List<String>>(line);
							// 循环每一行
							for (int r = 0; r < line; r++) {
								// 获取每一行数据
								Row row = sheet.getRow(r);
								if (row == null) {
									continue;
								}
								// 获取此行有多少有效单元格
								int number = row.getPhysicalNumberOfCells();
								if (number > 0) {
									List<String> rowLst = new ArrayList<String>(number);
									// 循环每一行数据
									for (int c = 0; c < number; c++) {
										// 获取一个格子
										Cell cell = row.getCell(c);
										if (cell != null) {
											String value = getValue(cell);
											rowLst.add(value);
										}
									}
									sheetdata.add(rowLst);
								}

							}
							data.add(sheetdata);
						}
					}
				}
				return data;
			}
		}
		return null;
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:57:02
	 * @param cell
	 *            单个单元格
	 * @return String 从单元格中获取的数据
	 */
	public static String getValue(Cell cell) {

		String value = "";
		switch (cell.getCellType()) {

		    case Cell.CELL_TYPE_NUMERIC: // 数值型

			    if (DateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				    value = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
			    } else { // 纯数字
				    NumberFormat f = new DecimalFormat("############");
				    f.setMaximumFractionDigits(3);
				    value = f.format(cell.getNumericCellValue());

			    }
			    break;
		/* 此行表示单元格的内容为string类型 */
		    case Cell.CELL_TYPE_STRING: // 字符串型
			    value = cell.getRichStringCellValue().toString();
			    break;
		    case Cell.CELL_TYPE_FORMULA:// 公式型
			// 读公式计算值
			    value = String.valueOf(cell.getNumericCellValue());
			    if (value.equals("NaN")) { // 如果获取的数据值为非法值,则转换为获取字符串
				    value = cell.getRichStringCellValue().toString();
			    }
			    // cell.getCellFormula();读公式
			    break;
		    case Cell.CELL_TYPE_BOOLEAN:// 布尔
			    value = " " + cell.getBooleanCellValue();
			    break;
		    /* 此行表示该单元格值为空 */
		    case Cell.CELL_TYPE_BLANK: // 空值
			    value = "";
			    break;
		    case Cell.CELL_TYPE_ERROR: // 故障
			    value = "";
			    break;
		    default:
			    value = cell.getRichStringCellValue().toString();
		}

		return value;
	}

}
