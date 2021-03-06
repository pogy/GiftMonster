package com.li5tao.data.collect.alimama;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*
 * 抓取产品操作
 */
public class CollectProduct {
	
	public static void getProduct() {
		try{
			String token="";
			String t = "";
			
			SessionInfo sessionInfo = SessionManager.getSessionInfo();
			t = sessionInfo.get_t();
			token = sessionInfo.get_token();
			
			BasicCookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookies(sessionInfo.get_cookies());
			CloseableHttpClient httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			
			HttpGet httpget =  new HttpGet("http://pub.alimama.com/pubauc/searchAuctionList.json?spm=0.0.0.0.nBjKHW&q=%E6%89%8B%E6%9C%BA&t="+t+"&_tb_token_="+token+"&_input_charset=utf-8");
			CloseableHttpResponse response = httpclient.execute(httpget);
			try{
				HttpEntity entity = response.getEntity();
				BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
				   StringBuffer buffer = new StringBuffer();
				   String line = "";
				   while ((line = in.readLine()) != null){
				     buffer.append(line);
				   }
				   System.out.println(buffer.toString());
				
			}finally{
				response.close();
			}
		}catch(Exception e){
			
		}
	}
	
	public static void main(String[] args){
		getProduct();
	}
}
