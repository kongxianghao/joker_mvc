package com.joker.core.mvc.servlet.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.joker.core.mvc.servlet.context.HttpRequestContext;

public abstract class AbstractCommand implements ICommand {
	
	
	protected ICommand command;

	public final void process(HttpRequestContext httpRequestContext)
			throws ServletException, IOException {
		boolean b = exec(httpRequestContext);
		if(b&&this.command!=null){
			this.command.process(httpRequestContext);
		}
	}
	protected abstract boolean exec(HttpRequestContext httpRequestContext)throws ServletException, IOException;

	public ICommand setNextCommand(ICommand command) {
		this.command=command;
		return command;
	}
	

}
