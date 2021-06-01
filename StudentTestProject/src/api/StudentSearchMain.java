package api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentSearchMain {

	public static void main(String[] args) {
				String search = null;
				String mode = null;
				Scanner sc = new Scanner(System.in);
				System.out.println("검색할 종류를 선택하세요");
				System.out.println("학번 - sno");
				System.out.println("이름 - name");
				System.out.println("학과 - major");
				System.out.println("명령어를 입력하세요>");
				mode = sc.nextLine();
				System.out.println("검색할 내용 일부분을 입력하세요>");
				search = sc.nextLine();
				
				try {
					search = URLEncoder.encode(search,"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				try {
					String apiUrl = "http://localhost:9999/search.do?mode="+mode +"&search="+search;
					URL url = new URL(apiUrl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String result = "";
					while(true) {
						String str = br.readLine();
						if(str == null) break;
						result += str;
					}
					System.out.println(result);
					JSONObject json = new JSONObject(result);
					if(json.getInt("code") == 500){
						throw new Exception(json.getInt("code") + "\t" + json.getString("message")+"\n");
					}
					
					JSONArray arr = json.getJSONArray("result");
					for(int i=0;i<arr.length();i++) {
						JSONObject obj = arr.getJSONObject(i);
						System.out.println(obj.getString("sno"));
						System.out.println(obj.getString("name"));
						System.out.println(obj.getString("major"));
						System.out.println(obj.getDouble("score"));
					}
					br.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					try {
						//true 추가모드, false 새파일로 생성
						FileOutputStream fos = new FileOutputStream("error.txt",true);
						PrintWriter pw = new PrintWriter(fos);
						//날짜 셋팅 에러발생한 시점
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
						Calendar today = Calendar.getInstance();
						//로그 내용 앞에 발생한 날짜
						String str = sdf.format(today.getTime())+ "\t" + e.getMessage();
						System.out.println(str);
						pw.write(str);
						pw.flush();
						pw.close();
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					e.printStackTrace();
				}
	}

}
