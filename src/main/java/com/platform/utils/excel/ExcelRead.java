package com.platform.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel读取类，将外部Excel读取到程序
 * 
 * @author zhuhaojie 创建时间 2016年7月20日 下午1:58:24
 */
public class ExcelRead {

	/**
	 * 传送路径
	 */
	public static String path;

	/** 构造方法 */
	public ExcelRead() {
	};

	/**
	 * 错误信息
	 */
	String errorInfo;

	/**
	 * 返回错误信息
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午3:11:40
	 * @return String 错误信息
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * 重设错误信息
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午3:12:49
	 * @param errorInfo
	 *            重设的错误信息
	 * 
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * 文件后缀检查 看是否是Excel后缀
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午3:13:25
	 * @param filePath
	 *            文件路径
	 * @return boolean
	 */
	public static boolean validateExcel(String filePath) {
		if (filePath != null) {
			filePath = filePath.trim();
			if (!"".equals(filePath)) {
				int index = filePath.lastIndexOf(".");
				int length = filePath.length();
				if (index != -1 && index < (length - 1)) {
					// 截取
					String s = filePath.substring(index + 1);
					if ("xls".equalsIgnoreCase(s) || "xlsx".equalsIgnoreCase(s)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @date 2016年7月20日上午9:19:26
	 * @return List<List<List<String>>>
	 * @return
	 * @throws FileNotFoundException :指定的文件不存在抛出此异常
	 */
	public static List<List<List<String>>> readExcel() throws FileNotFoundException {
		FileInputStream in = getIn(ExcelRead.path);
		if (in != null) {
			List<List<List<String>>> data = readExcel2(in);
			return data;
		} else {
			throw new RuntimeException("文件" + ExcelRead.path + "不存在，或者不是有效的excel文件");
		}
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午3:27:57
	 * @param filePath
	 *            指定的文件路径
	 * @throws FileNotFoundException
	 *             文件未找到时抛出此异常
	 * @return FileInputStream
	 */
	public static FileInputStream getIn(String filePath) throws FileNotFoundException {
		if (filePath != null) {
			filePath = filePath.trim();
			if (!filePath.equals("")) {
				if (validateExcel(filePath)) {
					File file = new File(filePath);
					if (file != null) {
						if (file.exists()) {
							if (file.canRead()) {
								FileInputStream in = new FileInputStream(file);
								return in;
							}
						}
					}
				}
			}
		}
        return null;
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午3:31:37
	 * @param inputStream
	 *            输入流对象
	 * @return List<List<List<String>>> list集合对象
	 */
	public static List<List<List<String>>> readExcel2(InputStream inputStream) {
		if (inputStream != null) {
			Workbook wb = null;
			try {
				wb = new HSSFWorkbook(inputStream); // 先用2003的读取
			} catch (Exception e) {
				try {
					String path = ExcelRead.path; // 获取当前对象设置好的路径
					inputStream = getIn(path);
					if (inputStream != null) {
						wb = new XSSFWorkbook(inputStream); // 再用2007的读取
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					if (inputStream != null) {
						try {
							inputStream.close();
							inputStream = null;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			List<List<List<String>>> result = readNew(wb);
			return result;
		}
		return null;
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @date 2016年7月20日上午9:12:54
	 * @return List<List<List<String>>>
	 * @param wb
	 *            要读取的工作表对象
	 * @return
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
	 * @date 2016年7月20日上午9:12:46
	 * @return String
	 * @param cell
	 *            代表Excel的每一个格子
	 * @return
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

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午3:36:24
	 * @param args
	 *            字符串数组
	 * @throws Exception
	 *             抛出异常
	 */
	public static void main(String[] args) throws Exception {

		// 指定要导入文件路径
		ExcelRead.path = "C://Users//Administrator//Desktop//1.xls";
		// 读取文件
		List<List<List<String>>> newlist = readExcel();
		if (newlist != null) {
			int size = newlist.size(); // 工作表个数
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					System.out.println("第" + (i + 1) + "个工作表数据:");
					List<List<String>> sheet = newlist.get(i);
					if (sheet != null) {
						int sheetsize = sheet.size();
						if (sheetsize > 0) {
							// 循环工作表
							for (int j = 0; j < sheetsize; j++) {
								// 获取每一行数据
								List<String> row = sheet.get(j);
								if (row != null) {
									int rowsize = row.size();
									if (rowsize > 0) {
										for (int k = 0; k < rowsize; k++) {
											System.out.print(row.get(k) + " ");
										}
									}
								}
								System.out.println("");
							}

						}
					}
				}
				System.out.println("----------------");
			}
		}

	}

}
