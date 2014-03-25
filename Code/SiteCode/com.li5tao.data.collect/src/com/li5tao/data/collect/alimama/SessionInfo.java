package com.li5tao.data.collect.alimama;

import org.apache.http.cookie.Cookie;

/*
 * 抓取中临时会话信息保存实体
 */
public class SessionInfo {
	/*
	 * 登录成功后得到的cookie。
	 */
	private Cookie[] _cookies;
	
	/*
	 * 抓取信息时所用的token
	 */
	private String _token = "";
	
	/*
	 * 抓取信息时所用的t参数
	 */
	private String _t = "";
	
	public Cookie[] get_cookies() {
		return _cookies;
	}

	public void set_cookies(Cookie[] _cookies) {
		this._cookies = _cookies;
	}

	public String get_token() {
		return _token;
	}

	public void set_token(String _token) {
		this._token = _token;
	}
	
	public String get_t() {
		return _t;
	}

	public void set_t(String _t) {
		this._t = _t;
	}

	
}
