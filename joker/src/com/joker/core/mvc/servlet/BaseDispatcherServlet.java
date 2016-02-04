package com.joker.core.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joker.core.mvc.config.JokerConfig;
import com.joker.core.mvc.context.ApplicationContext;
import com.joker.core.mvc.model.Model;
import com.joker.core.mvc.servlet.context.HttpRequestContext;
import com.joker.core.mvc.utils.StringUtils;

public abstract class BaseDispatcherServlet extends HttpServlet {
	protected final Log logger =LogFactory.getLog(getClass()) ;


	public BaseDispatcherServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}
	protected abstract void process(HttpRequestContext httpRequestContext)throws ServletException, IOException;


	protected void doService(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String reqUri = request.getRequestURI();
		String contextUri =  request.getContextPath();
		reqUri = reqUri.substring(contextUri.length());
		HttpRequestContext requestContext = new HttpRequestContext();
		requestContext.setRequest(request);
		requestContext.setResponse(response);
		requestContext.setRequestUir(reqUri);
		requestContext.setModel(Model.getModelInstance());
		process(requestContext);
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService( request,response);


	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doService( request,response);
	}

	protected  void initServlet() throws ServletException {

	}


	public final void init() throws ServletException {
		getServletContext().log("Initializing                jokerBaseDispatcherServlet '"+getServletName() +"'");
		if(this.logger.isInfoEnabled()){
			this.logger.info("BaseDispatcherServlet '"+getServletName()+"':Initialization started");
		}
		long startTime =System.currentTimeMillis();
		String configFileName =this.getServletContext().getRealPath("/")+this.getServletConfig().getInitParameter("config");
		String encode =this.getServletConfig().getInitParameter("encode");
		JokerConfig jokerConfig = new JokerConfig(configFileName);
		if(!StringUtils.isNull(encode)){
			jokerConfig.setEncode(encode);
		}
		jokerConfig.parse();
		ApplicationContext applicationContext = ApplicationContext.getInstance();
		applicationContext.loadJokerConfig(jokerConfig);
		applicationContext.setServletContext(getServletContext());
		initServlet();
		if(this.logger.isInfoEnabled()){
			long elapsedTime =System.currentTimeMillis()-startTime;
			this.logger.info("BaseDispatcherServlet '"+getServletName()+"':Initialization completed in"+elapsedTime+" ms");
		}

	}

}
