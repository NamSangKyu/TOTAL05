import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;


public class WeatherTodayMain {

	public static void main(String[] args) {
		String nx, ny, baseTime, serviceKey, dataType, baseDate, pageNo, numOfRows, url;
		nx="57";
		ny="127";
		url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		serviceKey = "hpOVfNem4MVro1QdBZTMTq%2FMZs%2B8yylSvxNQlqPiEQec%2Bo99WRRbIvrVqLltto5W0TmluoxR7uQHpHFNZ146qg%3D%3D";
		dataType = "json";
		pageNo ="1";
		numOfRows="88";
		baseTime = "0200";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		baseDate = sdf.format(cal.getTime());
		String apiUrl = url + "?serviceKey="+serviceKey+"&base_date="+baseDate+"&base_time="+baseTime+"&nx="+nx+"&ny="+ny+"&numOfRows="+numOfRows+"&pageNo=1&dataType="+dataType;
		System.out.println(apiUrl);
		try {
			URL u = new URL(apiUrl);
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
			JSONArray arr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			for(int i=0;i<arr.length();i++) {
				String str = arr.getJSONObject(i).getString("category");
				switch(str) { 
				case "T3H":
					System.out.println("T3H : "+ arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("fcstTime"));
					break;
				case "TMX":
					System.out.println("TMX : "+ arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("fcstTime"));
					break;
				case "TMN":
					System.out.println("TMN : "+ arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("fcstTime"));
					break;
				}
				
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}














