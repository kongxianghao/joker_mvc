package com.joker.core.mvc.context;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joker.core.mvc.config.JokerConfig;
import com.joker.core.mvc.controller.IController;
import com.joker.core.mvc.utils.StringUtils;

public class ApplicationContext {
	protected final Log logger =LogFactory.getLog(getClass()) ;
	private static ApplicationContext applicationContext = new ApplicationContext();
	
	private Map<String,IController> cache = new Hashtable<String,IController>();
	
	private JokerConfig jokerConfig;
	
	private ServletContext servletContext;
	
	public JokerConfig getJokerConfig(){
		return jokerConfig;
	}

	public static ApplicationContext getInstance() {

		return applicationContext;
	}

	public void loadJokerConfig(JokerConfig jokerConfig) {
		if(jokerConfig!=null){
			if(logger.isInfoEnabled()){
				logger.info("Loading JokerConfig Controller started");
			}
			long startTime =System.currentTimeMillis();
			boolean suc =false;
			Iterator<String> iterator = jokerConfig.iteratoKey();
			
			String url = null;
			String classPath = null;
			while(iterator.hasNext()){
				suc =false;
				url =iterator.next();
				classPath =jokerConfig.getClassPath(url);
				Class class1 = null;
				IController controller = null;
				try{		
					class1 = Class.forName(classPath);
					controller =(IController)class1.newInstance();
					suc =true;
				}catch(ClassNotFoundException e1){
					this.logger.error("LOADING url:"+url+" class:"+classPath+" failed! Class not found :"+classPath+"",e1);
				}catch(InstantiationException e1){
					this.logger.error("LOADING url:"+url+" class:"+classPath+" failed! Class newInstance error :"+classPath+"",e1);
				}catch(IllegalAccessException e1){
					this.logger.error("LOADING url:"+url+" class:"+classPath+" failed! Class newInstance error :"+classPath+"",e1);
				}
				
				if(suc){
					if(logger.isInfoEnabled()){
						logger.info("Loading url:"+url+"class:"+classPath+" success!");
					}
					this.cache.put(classPath, controller);
				}
			}
			if(logger.isInfoEnabled()){
				long elapsedTime =System.currentTimeMillis() -startTime;
				logger.info("Loading JokerConfig Controller completed in"+elapsedTime+" ms!");
			}
		}
		this.jokerConfig=jokerConfig;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
		
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
	private ApplicationContext(){}
	
	public IController getIControllerInstance(String url){
		String classPath =this.jokerConfig.getClassPath(url);
		IController controller = null;
		if(!StringUtils.isNull(classPath)){
			if(cache.containsKey(classPath)){
				controller =cache.get(classPath);
			}else{
				try {
					Class class1 =Class.forName(classPath);
					controller =(IController) class1.newInstance();
					cache.put(classPath, controller);
				} catch (Exception e) {
					logger.error("Loading url:"+url+" class:"+classPath+" failed!",e);
				}
			}
		}
		return controller;
	}
	

}
