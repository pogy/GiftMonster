package com.li5tao.data.collect.alimama;

import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*
 * 登录操作
 */
public class Login {
	
	private String _username = ""; //用户名
	private String _password = ""; //密码
	private String _passwordEncrypt = ""; //加密后的密码
	private final String url_Index = "http://www.alimama.com/index.htm";
	private final String url_loginaction = "http://www.alimama.com/member/minilogin_act.htm";
	private final String url_proxy = "http%3A%2F%2Fwww.alimama.com%2Fproxy.htm";
	
	public Login(String username, String password,String pwdEncrypt){
		this._username = username;
		this._password = password;
		this._passwordEncrypt = pwdEncrypt;
	}
	
	
	
	/*
	 * 登录方法
	 * return:返回登录成功后的Cookies
	 */
	public void LoginIn() throws Exception{
		Cookie[] cookiesret;
		String token = "";
		
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		try{
			HttpGet httpget = new HttpGet(url_Index);
			CloseableHttpResponse response = httpclient.execute(httpget);
			
			try{
				List<Cookie> cookies = cookieStore.getCookies();
				if(!cookies.isEmpty()){
					for(Cookie cookie : cookies){
						if(cookie.getName().equals("_tb_token_")){
							token = cookie.getValue();
							System.out.println(token);
						}
					}
				}
				
			}finally{
				response.close();
			}
			
			HttpUriRequest login = RequestBuilder.post()
					.setUri(new URI(url_loginaction))
					.addParameter("_tb_token_",token)
					.addParameter("style", "")
					.addParameter("redirect", "")
					.addParameter("proxy", url_proxy)
					.addParameter("logname", _username)
					.addParameter("originalLogpasswd", _password)
					.addParameter("logpasswd", _passwordEncrypt)
					.build();
			
			response = httpclient.execute(login);
			try{
				List<Cookie> cookies = cookieStore.getCookies();
				cookiesret = new Cookie[cookies.size()];
				int index = 0;
				boolean isSucceed = false;
				if(!cookies.isEmpty()){
					for(Cookie cookie : cookies){
						cookiesret[index] = cookie;
						if(cookie.getName().equals("alimamapw")){
							token = cookie.getValue();
							isSucceed = true;
							System.out.println(token);
						}
						index++;
					}
				}
				SessionInfo.setLogin(cookiesret, isSucceed);
				
			}finally{
				response.close();
			}
			
		}finally{
			httpclient.close();
		}
	}
	
	/*
	 * main method
	 */
	public static void main(String[] args){
		try{
			Login login = new Login("food0517@163.com","tao3230517","6c346857fa701323c6055b2d0787b3f6");
			login.LoginIn();
		}catch(Exception e){
			
		}
	}
}
