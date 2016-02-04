package com.joker.core.mvc.servlet.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.joker.core.mvc.context.ApplicationContext;
import com.joker.core.mvc.controller.IController;
import com.joker.core.mvc.servlet.context.HttpRequestContext;
import com.joker.core.mvc.view.View;

public class DispatcherCommand  extends AbstractCommand {

	@Override
	protected boolean exec(HttpRequestContext httpRequestContext) throws ServletException, IOException {
		ApplicationContext applicationContext= ApplicationContext.getInstance();
		IController controller =applicationContext.getIControllerInstance(httpRequestContext.getRequestUir());
		View view = null;
		if(controller!= null){
			view =controller.execute(httpRequestContext.getRequest(), httpRequestContext.getResponse(), httpRequestContext.getModel());
			httpRequestContext.setView(view);
		}
		return view == null ? false:true;
	}
}
