package excelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("deprecation")
public class WriteExcel {

	File fileName = new File("C:\\workspace\\MSTRDesktopTest\\src\\test\\resources\\results\\MSTR10.11.0003_Win10_Paypal_Desktop_B.xlsx");

	public void copyExcel(File source, File dest) throws IOException {
	    FileUtils.copyFile(source, dest);
	}

	public void createWorkbook(File fileName) throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			XSSFWorkbook  workbook = new XSSFWorkbook();            

			//	        XSSFSheet sheet = workbook.createSheet(fileName.toString().trim().substring(56, 60));  

			//	        Row row = sheet.createRow(0);   
			//	        Cell cell0 = row.createCell(0);
			//	        cell0.setCellValue("Nav Value");
			//
			//	        Cell cell1 = row.createCell(1);
			//
			//	        cell1.setCellValue("Amount Change");       
			//
			//	        Cell cell2 = row.createCell(2);
			//	        cell2.setCellValue("Percent Change");

			workbook.write(fos);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void copySheets(XSSFSheet newSheet, XSSFSheet sheet){
		copySheets(newSheet, sheet, true);
	}
	public void copySheets(XSSFSheet newSheet, XSSFSheet sheet, boolean copyStyle){
		int maxColumnNum = 0;
		Map<Integer, XSSFCellStyle> styleMap = (copyStyle)
				? new HashMap<Integer, XSSFCellStyle>() : null;

				for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
					XSSFRow srcRow = sheet.getRow(i);
					XSSFRow destRow = newSheet.createRow(i);
					if (srcRow != null) {
						System.out.println("here");
						copyRow(sheet, newSheet, srcRow, destRow, styleMap);
						if (srcRow.getLastCellNum() > maxColumnNum) {
							maxColumnNum = srcRow.getLastCellNum();
						}
					}
				}
				for (int i = 0; i <= maxColumnNum; i++) {
					newSheet.setColumnWidth(i, sheet.getColumnWidth(i));
				}
	}

	public void copyRow(XSSFSheet srcSheet, XSSFSheet destSheet, XSSFRow srcRow, XSSFRow destRow, Map<Integer, XSSFCellStyle> styleMap) {
		Set mergedRegions = new TreeSet();
		destRow.setHeight(srcRow.getHeight());
		for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
			XSSFCell oldCell = srcRow.getCell(j);
			XSSFCell newCell = destRow.getCell(j);
			if (oldCell != null) {
				if (newCell == null) {
					newCell = destRow.createCell(j);
				}
				System.out.println("here2");
				copyCell(oldCell, newCell, styleMap);
				//                CellRangeAddress mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(), oldCell.getColumnIndex());
				//                if (mergedRegion != null) {
				////                    Region newMergedRegion = new Region( destRow.getRowNum(), mergedRegion.getColumnFrom(),
				////                            destRow.getRowNum() + mergedRegion.getRowTo() - mergedRegion.getRowFrom(), mergedRegion.getColumnTo() );
				//                	CellRangeAddress newMergedRegion = new CellRangeAddress(mergedRegion.getFirstRow(), mergedRegion.getFirstColumn(),
				//                            mergedRegion.getLastRow(), mergedRegion.getLastColumn());
				//                    if (isNewMergedRegion(newMergedRegion, mergedRegions)) {
				//                        mergedRegions.add(newMergedRegion);
				//                        destSheet.addMergedRegion(newMergedRegion);
			}
		}
	}
	//        }

	//   }
	public void copyCell(XSSFCell oldCell, XSSFCell newCell, Map<Integer, XSSFCellStyle> styleMap) {
		if(styleMap != null) {
			if(oldCell.getSheet().getWorkbook() == newCell.getSheet().getWorkbook()){
				newCell.setCellStyle(oldCell.getCellStyle());
			} else{
				int stHashCode = oldCell.getCellStyle().hashCode();
				XSSFCellStyle newCellStyle = styleMap.get(stHashCode);
				if(newCellStyle == null){
					newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();
					newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
					styleMap.put(stHashCode, newCellStyle);
					System.out.println("here3");
				}
				newCell.setCellStyle(newCellStyle);
			}
		}
		switch(oldCell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			newCell.setCellValue(oldCell.getStringCellValue());
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			newCell.setCellValue(oldCell.getNumericCellValue());
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			newCell.setCellType(oldCell.CELL_TYPE_BLANK);
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			newCell.setCellValue(oldCell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_ERROR:
			newCell.setCellErrorValue(oldCell.getErrorCellValue());
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			newCell.setCellFormula(oldCell.getCellFormula());
			break;
		default:
			break;
		}

	}
	public CellRangeAddress getMergedRegion(XSSFSheet sheet, int rowNum, int cellNum) {
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress merged = sheet.getMergedRegion(i);
			if (merged.isInRange(rowNum, cellNum)) {
				return merged;
			}
		}
		return null;
	}

	private boolean isNewMergedRegion(CellRangeAddress region, Collection mergedRegions) {
		return !mergedRegions.contains(region);
	}

	public void writeXLSXFile(String excel_file, int row, int col, String value) throws IOException {
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\jadmin\\Desktop\\MSTR_Desktop_Images_Final\\Xlsx_Files\\"+excel_file);

			XSSFWorkbook worbook = new XSSFWorkbook(file);
			XSSFSheet sheet = worbook.getSheetAt(0);
			Cell cell = null;

			//Retrieve the row and check for null
			XSSFRow sheetrow = sheet.getRow(row);
			if(sheetrow == null){
				sheetrow = sheet.createRow(row);
			}
			//Update the value of cell
			cell = sheetrow.getCell(col);
			if(cell == null){
				cell = sheetrow.createCell(col);
			}
			//cell.setCellValue("Pass");
			cell.setCellValue(value);


			file.close();

			FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\jadmin\\Desktop\\MSTR_Desktop_Images_Final\\Xlsx_Files\\"+excel_file));
			worbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int count(String excel_file) throws IOException
	{
		FileInputStream file = new FileInputStream("C:\\Users\\jadmin\\Desktop\\MSTR_Desktop_Images_Final\\Xlsx_Files\\"+excel_file);

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Cell cell = null;
		XSSFRow sheetrow = sheet.getRow(0);
		int colNum = sheetrow.getLastCellNum();
		int rowNum = sheet.getLastRowNum();
		System.out.println("Total columns"+colNum);
		System.out.println("Total Rows"+rowNum);

		return rowNum;
	}

}
