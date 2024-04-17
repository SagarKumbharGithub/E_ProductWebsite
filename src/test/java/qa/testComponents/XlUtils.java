package qa.testComponents;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlUtils {

	public static Object[][]  getExcelData(String filePath, String sheetName) throws IOException
	{
		FileInputStream fis= new FileInputStream(filePath);
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet(sheetName);
		int  rowCount=sheet.getLastRowNum();
		int cellCount=sheet.getRow(0).getLastCellNum();
		
		Object [][] data =new Object [rowCount][cellCount];
		
		
		for (int i=0;i<rowCount;i++)
		{
			XSSFRow row= sheet.getRow(i+1);
			
			for(int j=0;j<cellCount;j++)
			{
				XSSFCell cell=row.getCell(j);
				DataFormatter formatter= new DataFormatter();
				 String cellData=formatter.formatCellValue(cell);
				 data[i][j]=cellData;
				//System.out.print(cell.toString() + "\t");
				//System.out.print(formattedCell + "\t");
				
			}
			
		}
		workbook.close();
		fis.close();		
		return data;		
		
	}
}
