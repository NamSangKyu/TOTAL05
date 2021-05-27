import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMain {

	public static void main(String[] args) {
		ZipSecureFile.setMinInflateRatio(0);
		try {
			FileInputStream fis = new FileInputStream("excel.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);//엑셀시트 번호로 읽어옴
			System.out.println("전체 행 개수 : "+sheet.getPhysicalNumberOfRows());
			System.out.println("해당 라인의 컬럼수 : "+sheet.getRow(0).getPhysicalNumberOfCells());
			System.out.println("4행 6열에 있는 셀값 : "+sheet.getRow(3).getCell(5));
			//엑셀파일의 전체 내용을 출력
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {//첫행부터 마지막 행까지
				String row = "";
				for(int j=0;j<sheet.getRow(i).getPhysicalNumberOfCells();j++) {//첫번째 칸부터 마지막 칸까지
					row += sheet.getRow(i).getCell(j) + "\t";
				}
				System.out.println(row);				
			}
			
			//서울특별시 마포구, 강원도 화천군 ---> nx ny 좌표값을 검색을 해서 출력 
			String address = JOptionPane.showInputDialog("시도 시군구 형식으로 입력하세요");
			String addr[] = address.split(" ");
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {//첫행부터 마지막 행까지
				if(!sheet.getRow(i).getCell(4).getStringCellValue().equals("")) continue;
				if(sheet.getRow(i).getCell(2).getStringCellValue().equals(addr[0])) {
					if(sheet.getRow(i).getCell(3).getStringCellValue().equals(addr[1]))
					System.out.println(sheet.getRow(i).getCell(2)+ " "+sheet.getRow(i).getCell(3)+ " " + sheet.getRow(i).getCell(5)+ " "+sheet.getRow(i).getCell(6));
				}
					
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}




