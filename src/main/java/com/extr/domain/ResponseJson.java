package com.extr.domain;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import net.sf.json.JSONObject;

public class ResponseJson implements Serializable {

	/**
	 * 返回响应JSON的模型
	 */
	private static final long serialVersionUID = -2631925256176282881L;
	
	JSONObject retJson = null;

	public ResponseJson(Object data, int errcode, String errmsg) {
		
		if (errcode == 0) {
			this.retJson = new JSONObject();
			if (data != null) {
				retJson.put("data", data);
			} else {
				retJson.put("data", "");
			}
			
			retJson.put("errcode", errcode);
			retJson.put("errmsg", errmsg);
		} 
		else if (errcode == -1) {
			this.retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", errcode);
			retJson.put("errmsg", errmsg);
		}
		
		
	}
	
public ResponseJson(Object data, int errcode, String errmsg,int total) {
		
		if (errcode == 0) {
			this.retJson = new JSONObject();
			if (data != null) {
				retJson.put("data", data);
			} else {
				retJson.put("data", "");
			}
			retJson.put("total", total);
			retJson.put("errcode", errcode);
			retJson.put("errmsg", errmsg);
		} 
		else if (errcode == -1) {
			this.retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", errcode);
			retJson.put("errmsg", errmsg);
		}
		
		
	}
	
	public String getResponseStr() {
		return this.retJson.toString();
		
	}
	
	
}
