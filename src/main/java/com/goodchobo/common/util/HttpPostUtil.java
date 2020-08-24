package com.goodchobo.common.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

public class HttpPostUtil {

	private static final Logger log = Logger.getLogger(HttpPostUtil.class);

/**
 * post방식으로 url호출
 * @param url
 * @param param
 * @return
 * @throws Exception
 */
	public static Map<String, Object> urlBufferedReaderPOST(String url, String param) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> returnMap = new HashMap<String, Object>();
		StringBuffer response = new StringBuffer();

		URL obj = null;
		URLConnection conn = null;
		OutputStreamWriter wr = null;
		BufferedReader in = null;
		try{
			obj = new URL(url);
			conn = obj.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(param);
			wr.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf8"));
			String inputLine="";
			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
			}
			log.debug("### urlBufferedReaderPOST result : " + response.toString());
			//유니코드 개행문자
			returnMap.put("result", StringEscapeUtils.unescapeJava(response.toString()) );
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null)in.close();
			if(wr!=null)wr.close();
		}

		return returnMap;
	}

	/**
	 * get방식으로 url호출
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> urlBufferedReaderGET(String url) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> returnMap = new HashMap<String, Object>();
		StringBuffer response = new StringBuffer();

		URL obj = null;
		HttpURLConnection  conn = null;
		BufferedReader in = null;
		try{
			obj = new URL(url);
			log.debug("### urlBufferedReaderGET URL : " + url);
			conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf8"));
			String inputLine;
			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
			}
			log.debug("### urlBufferedReaderGET RESULT : " + response.toString());
			//유니코드 개행문자
			returnMap.put("result", StringEscapeUtils.unescapeJava(response.toString()));

		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(in !=null) {
				in.close();
			}
		}

		return returnMap;
	}
}
