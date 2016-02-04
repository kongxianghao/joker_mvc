package com.joker.core.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import com.joker.core.mvc.config.JokerConfig;
import com.joker.core.mvc.context.ApplicationContext;
import com.joker.core.mvc.servlet.command.DispatcherCommand;
import com.joker.core.mvc.servlet.command.ForwardCommand;
import com.joker.core.mvc.servlet.command.ICommand;
import com.joker.core.mvc.servlet.command.ModelDealCommand;
import com.joker.core.mvc.servlet.context.HttpRequestContext;

public class JokerDispatcherServlet extends BaseDispatcherServlet {
	private ICommand command;

	@Override
	protected void process(HttpRequestContext httpRequestContext)
			throws ServletException, IOException {
		ApplicationContext applicationContext = ApplicationContext
				.getInstance();

		JokerConfig jokerConfig = applicationContext.getJokerConfig();

		if (jokerConfig != null) {
			httpRequestContext.getResponse().setCharacterEncoding(
					jokerConfig.getEncode());
			httpRequestContext.getRequest().setCharacterEncoding(
					jokerConfig.getEncode());
		}
		if (command != null) {
			try {
				command.process(httpRequestContext);
			} catch (ServletException e) {
				this.logger.error("Process failed", e);
				throw e;
			} catch (IOException e) {
				this.logger.error("Process failed", e);
				throw e;
			}
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("DispatcherCommand is not exist");
			}
		}
	}

	protected void initServlet() throws ServletException {
		command = new DispatcherCommand();
		command.setNextCommand(new ModelDealCommand()).setNextCommand(
				new ForwardCommand());
		if (logger.isInfoEnabled()) {
			logger
					.info("JokerDispatcherServlet         Initializing  Dispatcher  Chain  Success  ! ");
		}
	}
}
