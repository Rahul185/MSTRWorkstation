package excelUtils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {
	/* public static final Logger logger = LoggerFactory
	            .getLogger(ReadExcel.class);*/
	 
	 private Sheet dataSheet = null;
	 
	 public ReadExcel(String xlsfile, String sheetName) throws Exception {
	        // TODO Auto-generated constructor stub
	        InputStream inp = new FileInputStream(xlsfile);
	        dataSheet = WorkbookFactory.create(inp).getSheet(sheetName);

	    }
	 
	 public ReadExcel(String xlsfile) throws Exception {
	        // TODO Auto-generated constructor stub
	        this(xlsfile,1);
	    }
	 
	 /**
	     * Sheet number starts with 1
	     *
	     * @param xlsfile
	     * @param sheetNumber
	     * @throws IndexOutOfBoundsException
	     */
	 
	 public ReadExcel(String xlsfile, int sheetNumber) throws Exception {
	        // TODO Auto-generated constructor stub
	        InputStream inp = new FileInputStream(xlsfile);
	        dataSheet = WorkbookFactory.create(inp).getSheetAt(sheetNumber - 1);

	    }
	 
	 /**
	     * Returns the column data which row contains 'RowValue'
	     * @param RowValue
	     * @param ColumnName
	     */
	    public String getCellData(String RowValue, String ColumnName)
	            throws Exception {
	        int x = findColumnNumber(ColumnName);
	        if (x == -1)
	            return null;

	        for (Row row : dataSheet) {
	            if (getStringVal(row.getCell(0)).equalsIgnoreCase(RowValue)) {
	                return getStringVal(row.getCell(x));
	            }

	        }

	        return null;
	    }
	    
	    /**
	     * Returns the column data which row contains 'RowValue'
	     * @param RowValue
	     * @param ColumnIndex
	     */
	    public String getCellData(String RowValue, Integer ColumnIndex)
	            throws Exception {
	        for (Row row : dataSheet) {
	            if (getStringVal(row.getCell(0)).equalsIgnoreCase(RowValue)) {
	                return getStringVal(row.getCell(ColumnIndex));
	            }

	        }

	        return null;
	    }
	    
	    public int findColumnNumber(String val) {
	        Row row = dataSheet.getRow(0);
	        for (Cell cell : row) {
	            if (getStringVal(cell).equalsIgnoreCase(val))
	                return cell.getColumnIndex();

	        }
	        return -1;

	    }

	    public HashMap<String, String> getRowData(String RowValue) throws Exception {
	        HashMap<String, String> hm = null;
	        Row r2 = null;
	        for (Row row : dataSheet) {
	            for (Cell cell : row)
	                if (getStringVal(cell).equalsIgnoreCase(RowValue)) {
	                    r2 = row;
	                    break;
	                }
	        }
	        if (r2 == null)
	            return null;
	        else {
	            Row r1 = dataSheet.getRow(1);
	            hm = new HashMap<String, String>();

	            for (int i = 0; i <= r1.getLastCellNum() - 1; i++) {
	                hm.put(getStringVal(r1.getCell(i)),getStringVal(r2.getCell(i)));
	            }
	        }

	        return hm;

	    }
	    
	   
		public HashMap<String, String>[][] getTableToHashMapDoubleArray(int noOfRows)
	            throws Exception {
	        HashMap<String, String>[][] tabArray = null;
	        HashMap<String, String>[] tabData = null;
	        try {
	            tabArray = new HashMap[noOfRows-1][1];
	            tabData = getTableToHashMapArray(noOfRows);
	            for (int i = 0; i < noOfRows-1; i++) {
	                tabArray[i][0] = tabData[i];
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("error in getTableToHashMap()" + e.getMessage());
	            throw new Exception("Error is parsing Excel file" + e.getMessage());
	        }

	        return (tabArray);
	    }

	    public HashMap<String, String>[][] getTableToHashMapDoubleArray() throws Exception {
	        try {

	            return getTableToHashMapDoubleArray(dataSheet.getLastRowNum() + 1);
	        } catch (Exception e) {
	            e.printStackTrace();
	            //logger.error("error in getTableToHashMap()" + e.getMessage());
	            throw new Exception("Error is parsing Excel file" + e.getMessage());
	        }

	    }
	    
	   
		public HashMap<String, String>[] getTableToHashMapArray(int noOfRows)
	            throws Exception {
	        HashMap<String, String>[] tabArray = null;
	        int i=1;
	        try {
	            int endCol, ci;

	            endCol = dataSheet.getRow(0).getLastCellNum();
	            tabArray = new HashMap[noOfRows - 1];
	            ci = 0;
	            System.out.println("No of tests processing is "+noOfRows);
	            for (i = 1; i < noOfRows; i++, ci++) {
	                HashMap<String, String> hm = new HashMap<String, String>();
	                for (int j = 0; j < endCol; j++) {
	                    if(dataSheet.getRow(i)!=null)
	                        hm.put(getStringVal(dataSheet.getRow(0).getCell(j)),getStringVal(dataSheet.getRow(i).getCell(j)));
	                    else
	                        hm.put(getStringVal(dataSheet.getRow(0).getCell(j)),"");
	                }
	                tabArray[ci] = new HashMap<String, String>();
	                tabArray[ci].putAll(hm);
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	           // logger.debug("Failed for the row "+i);
	           // logger.error("error in getTableToHashMap()" + e.getMessage());
	            throw new Exception("Error is parsing Excel file" + e.getMessage());
	        }
	        return (tabArray);
	    }
	    
	    public boolean isRowContainColumnVals(Row row,String[] columnName,String[] ColVal)
	    {
	        for(int i=0;i<columnName.length;i++)
	            if(!getStringVal(row.getCell(findColumnNumber(columnName[i]))).equalsIgnoreCase(ColVal[i]))
	                return false;

	        return true;
	    }
	    
	    /**
	     * rowNumber starts with 1
	     *
	     * @param rowNumber
	     * @return
	     * @throws Exception
	     */
	    public HashMap<String,String> getRowData(int rowNumber) throws Exception {

	        HashMap<String, String> hm = null;
	        Row r1 = dataSheet.getRow(1);
	        Row r2 = dataSheet.getRow(rowNumber - 1);
	        hm = new HashMap<String, String>();

	        for (int i = 0; i <= r1.getLastCellNum() - 1; i++) {
	            hm.put(getStringVal(r1.getCell(i)),getStringVal(r2.getCell(i)));
	        }

	        return hm;

	    }
	    public String getStringVal(Cell c) {
	        if (c == null)
	            return "";
	        else {

	            if(c.getCellType()==Cell.CELL_TYPE_BLANK)
	                return "";
	            else if (c.getCellType()==Cell.CELL_TYPE_BOOLEAN)
	                return Boolean.toString(c.getBooleanCellValue());
	            else if(c.getCellType()==Cell.CELL_TYPE_NUMERIC)
	            {
	                if (HSSFDateUtil.isCellDateFormatted(c)) {
	                    return (c.getDateCellValue().getTime()/1000)+"";
	                }
	                else{
	                    double d =c.getNumericCellValue();
	                    return (long) d == d ? "" + (long) d : "" + d;
	                }
	            }
	            else
	                return c.getStringCellValue();
	        }

	    }
	    
	 
	 
	

}
