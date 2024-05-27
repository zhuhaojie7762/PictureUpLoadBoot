package com.platform.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Excel 读取类
 * 
 * @author zhuhaojie 创建时间 2016年7月20日 下午1:57:34
 */
public class ReadExcelByPOI {

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:47:58
	 * @param filePath
	 *            文件路径
	 * 
	 */
	public static void readExcel(String filePath) {

		try {
			// 代表一页Execl
			Workbook workBook = null;
			FileInputStream in = new FileInputStream(filePath);
			try {
				workBook = WorkbookFactory.create(in); // 支持2007
			} catch (Exception ex) {
				workBook = new HSSFWorkbook(in); // 支持2003及以前
			}
			int numberSheet = workBook.getNumberOfSheets();
			// 获得Excel中工作表个数
			System.out.println("工作表个数 :" + numberSheet);

			// 循环每个工作表
			for (int i = 0; i < numberSheet; i++) {

				// 获取第一个工作表
				Sheet sheet = workBook.getSheetAt(i);
				// 获取工作表共有多少行，这里行是指有数据的行
				int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
				if (rows > 0) {
					sheet.getMargin(Sheet.TopMargin);
					for (int r = 0; r < rows; r++) { // 行循环
						Row row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getLastCellNum(); // 获得列数
							for (short c = 0; c < cells; c++) { // 列循环
								Cell cell = row.getCell(c);

								if (cell != null) {
									String value = getValue(cell);
									if (value != null) {
										value = value.trim();
										if (!"".equals(value)) {
											// System.out.print("第" + (r+1) + "行
											// " + "第" + (c+1)
											// + "列：" + value);
											System.out.print(value + " ");
										}
									}
								}
							}
							// 每一行解析完成，换行
							System.out.println();
						}
					}
				}

				// new FileInputStream(filePath).close();
				int numbers = sheet.getNumMergedRegions();
				// 查询合并的单元格
				for (int j = 0; j < numbers; j++) {
					System.out.println("第" + j + "个合并单元格");
					CellRangeAddress region = sheet.getMergedRegion(j);
					int row = region.getLastRow() - region.getFirstRow() + 1;
					int col = region.getLastColumn() - region.getFirstColumn() + 1;
					System.out.println("起始行:" + region.getFirstRow());
					System.out.println("起始列:" + region.getFirstColumn());
					System.out.println("所占行:" + row);
					System.out.println("所占列:" + col);
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:48:40
	 * @param cell
	 *            Excel单个单元格对象
	 * @return String 单元格中的值
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

	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年7月20日下午6:49:23
	 * @param args
	 *            字符串型数组
	 * 
	 */
	public static void main(String[] args) {
		// ReadExcelByPOI execlpoi = new ReadExcelByPOI();
		readExcel("G://手机号码.xlsx");
		readExcel("G://列表.xls");
		// execlpoi.readExcel("I://列表.xls");
		// execlpoi.readExcel("I://手机号码.xlsx");
		// System.exit(-1);
	}
}
