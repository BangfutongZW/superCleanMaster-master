package com.yzy.supercleanmaster.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 */
public class HttpTool implements Runnable{

	private String url = "";
	private String body = "";
	private int timeout = 0;
	private Handler handler = null;

	/**
	 * @param url
	 */
	public HttpTool(String url) {
		super();
		this.url = url;
		this.setTimeout(10000);
	}
	
	private String httpPost()
	{
		String result = "";
		BufferedReader responseReader = null;
		HttpURLConnection httpConn = null;
		try {
			URL url = new URL(this.url);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			
			httpConn.setConnectTimeout(this.timeout);
			httpConn.setReadTimeout(timeout);

			httpConn.setRequestProperty("Content-length",""+this.body.getBytes("utf-8").length);
			httpConn.setRequestProperty("Content-type", "text/html");
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(this.body.getBytes("utf-8"));
			outputStream.close();

			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {// ���ӳɹ�

				StringBuffer sb = new StringBuffer();
				String readLine;

				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					//sb.append(readLine).append("\n");
					sb.append(readLine);
				}
				
				responseReader.close();
				
				Log.e("RESULT", sb.toString());
				result = sb.toString().trim();
			}
		} catch (IOException e) {

		}finally{
			if(httpConn != null)
				httpConn.disconnect();
		}
		
		return result;
	}
	
	@Override
	public void run() {
		
		Message msg = new Message();
		msg.what = 0x123;
		msg.obj = "";
		String result = "";
		result = httpPost();
		
		msg.obj = result.trim();
		msg.what = 1;
		handler.sendMessage(msg);
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public int getTimeout() {
		return timeout;
	}


	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}


	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
