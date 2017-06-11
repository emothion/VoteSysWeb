package com.votesys.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportInExcelUtil {

	/**
	 * @Function com.votesys.tools.ExportInExcelUtil::readWriter
	 * @Description 读入Excel模板并写入数据
	 * @param templatePath
	 * @param topicID
	 * @param params
	 *            数据库获取的数据
	 * @return
	 * @throws WriteException
	 */
	public static boolean readWriter(String templatePath, String topicID, List<String[]> params) throws WriteException {
		WritableWorkbook wwb = null;
		WritableSheet wws = null;
		FileOutputStream out = null;
		jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#0");// 设置数字类型的格式
		jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);// 设置表单格式
		wcfN.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);// 设置单元格全边框，实线，黑色
		// 获取要读取的EXCEL表格模板
		File is = new File(templatePath + File.separator + "ExcelTemplate.xls");
		String filename = templatePath + "\\export-excel\\";
		// 写入到新的表格里
		File f = new File(filename, topicID + ".xls");
		try {
			// 创建新文件
			f.createNewFile();
			out = new FileOutputStream(f);
			// 获取工作簿对象
			Workbook wb = Workbook.getWorkbook(is);
			// 创建可写入的工作簿对象
			wwb = Workbook.createWorkbook(out, wb);
			// 根据工作表名获取WritableSheet对象
			wws = wwb.getSheet("Vote");
			Label label = null;
			int num = 0;
			for (int i = 0; i < params.size(); i++) {
				num = num + Integer.parseInt(params.get(i)[2]);
			}
			for (int i = 1; i <= params.size(); i++) {
				for (int j = 0; j < 4; j++) {
					// 创建label对象设置value值j相当于是X轴I是Y轴位置
					if (j == 1) {
						label = new Label(j, i, params.get(i - 1)[j], wcfN);// 插入文本类型
						wws.addCell(label);// 添加到工作薄中
					} else if(j == 2) {
						jxl.write.Number labelNF = new Number(j, i, Double.valueOf(params.get(i - 1)[j]), wcfN);// 插入数字类型
						wws.addCell(labelNF);
					}
					if (j==3) {
						double part = (Double.parseDouble(params.get(i - 1)[j-1])/num)*100;
						label = new Label(j, i, part+"%", wcfN);// 插入文本类型
						wws.addCell(label);
					}
				}
			}
			// 将新建立的工作薄写入到磁盘
			wwb.write();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// 关闭流
			try {
				wwb.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}