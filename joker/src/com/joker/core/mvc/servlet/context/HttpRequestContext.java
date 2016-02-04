package com.joker.core.mvc.servlet.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.joker.core.mvc.model.Model;
import com.joker.core.mvc.view.View;

public class HttpRequestContext {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String requestUir;
	private Model model;
	private View view;
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getRequestUir() {
		return requestUir;
	}
	public void setRequestUir(String requestUir) {
		this.requestUir = requestUir;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

}
