package batch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob  implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			FileReader fr = new FileReader("error.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String str = br.readLine();//파일에서 로그 한줄 읽어옴
				if(str == null) break;
				sendLog(str.split("\t"));// 서버로 전송
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void sendLog(String[] arr) {
		String queryString = "";
		String[] paramArr = {"log_date","code_number","content"};
		try {
			for(int i=0;i<arr.length;i++) {
					queryString += paramArr[i] + "=" + URLEncoder.encode(arr[i],"utf-8") + "&";
			}
			System.out.println("queryString : " + queryString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			String apiUrl = "http://localhost:9999/sendLog.do?"+queryString;
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = br.readLine();
			System.out.println(result);
			br.close();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
