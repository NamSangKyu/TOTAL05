import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherExelMain {

	public static void main(String[] args) {
		FileInputStream fis;
		try {
			ZipSecureFile.setMinInflateRatio(0);
			fis = new FileInputStream("excel.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Scanner sc = new Scanner(System.in);
			System.out.println("조회할 주소를 정확하게 서울특별시 강서구  <--- 형식으로 입력하세요");
			String address = sc.nextLine();
			String addr[] = address.split(" ");
			String nx = null, ny=null;
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				if(sheet.getRow(i).getCell(2).getStringCellValue().equals(addr[0])) {
					if(sheet.getRow(i).getCell(3).getStringCellValue().equals(addr[1])) {
						nx = sheet.getRow(i).getCell(5).toString();
						ny = sheet.getRow(i).getCell(6).toString();
					}
				}
			}
			System.out.println(nx + " "+ ny);
			String baseTime, serviceKey, dataType, baseDate, pageNo, numOfRows, url;
			int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			int[] arr = new int[] {2,5,8,11,14,17,20,23};
			int i;
			for(i=0;i<arr.length;i++) {
				if(arr[i] > time) break;
			}
			if(i > 0) { //23시 이하
				i = i-1;
			}else {// 02시 미만
				i = arr.length-1;
				cal.add(Calendar.DATE, -1);
			}
			baseDate = sdf.format(cal.getTime());
			baseTime = arr[i] + "00";
			numOfRows = "10";
			serviceKey = "hpOVfNem4MVro1QdBZTMTq%2FMZs%2B8yylSvxNQlqPiEQec%2Bo99WRRbIvrVqLltto5W0TmluoxR7uQHpHFNZ146qg%3D%3D";
			pageNo = "1";
			dataType = "json";
			url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"+"?serviceKey="+serviceKey+"&base_date="+baseDate+"&base_time="+baseTime+"&nx="+nx+"&ny="+ny+"&numOfRows="+numOfRows+"&pageNo=1&dataType="+dataType;
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			String result = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while(true) {
				String str = br.readLine();
				if(str==null) break;
				result += str;
			}
			System.out.println(result);
			JSONObject json = new JSONObject(result); 
			JSONArray jarr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			for(int j=0;j<jarr.length();j++) {
				String str = jarr.getJSONObject(j).getString("category");
//				System.out.println(jarr.getJSONObject(j).getString("category"));
				switch(str) { 
				case "T3H":
					System.out.println("현재온도 : "+ jarr.getJSONObject(j).getString("fcstValue"));
					break;
				case "TMX":
					System.out.println("최대기온 : "+ jarr.getJSONObject(j).getString("fcstValue"));
					break;
				case "TMN":
					System.out.println("최저기온 : "+ jarr.getJSONObject(j).getString("fcstValue"));
					break;
				case "SKY":
					System.out.println("하늘상태 : "+ jarr.getJSONObject(j).getString("fcstValue"));
					break;
					
				}
				
			}
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}











