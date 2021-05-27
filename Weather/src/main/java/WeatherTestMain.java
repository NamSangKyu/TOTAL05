import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class WeatherTestMain {

	public static void main(String[] args) {
		//오늘 서울 날씨, 14시 기준으로 조회
		String nx, ny, baseTime, serviceKey, dataType, baseDate, numOfRows, url;
		nx="57";
		ny="127";
		url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		serviceKey = "hpOVfNem4MVro1QdBZTMTq%2FMZs%2B8yylSvxNQlqPiEQec%2Bo99WRRbIvrVqLltto5W0TmluoxR7uQHpHFNZ146qg%3D%3D";
		dataType = "json";
		numOfRows="10";
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
		System.out.println(baseDate + " " + baseTime);
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}














