package com.joker.core.mvc.servlet.command;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.joker.core.mvc.model.Model;
import com.joker.core.mvc.servlet.context.HttpRequestContext;

public class ModelDealCommand  extends AbstractCommand{

	@Override
	protected boolean exec(HttpRequestContext httpRequestContext) throws ServletException, IOException {
		HttpServletRequest request =httpRequestContext.getRequest();
		Model model =httpRequestContext.getModel();
		Iterator<String>  iterator =model.iteratorKey();
		String key = null;
		while(iterator.hasNext()){
			key =iterator.next();
			request.setAttribute(key, model.get(key));
		}
		return true;
	}



}
