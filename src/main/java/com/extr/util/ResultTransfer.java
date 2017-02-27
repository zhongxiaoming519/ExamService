package com.extr.util;

public class ResultTransfer<T> {	

	public int geterrcode() {
		return errcode;
	}
	public void seterrcode(int errorCode) {
		this.errcode = errorCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	private int errcode;
	private T data;
}
