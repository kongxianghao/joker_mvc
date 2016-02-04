package com.joker.core.mvc.servlet.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.joker.core.mvc.servlet.context.HttpRequestContext;

public interface ICommand {
	public void process(HttpRequestContext httpRequestContext)throws ServletException, IOException;
	
	public ICommand setNextCommand(ICommand command);

}
