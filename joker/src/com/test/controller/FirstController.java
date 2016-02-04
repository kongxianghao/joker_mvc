package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.joker.core.mvc.controller.IController;
import com.joker.core.mvc.model.Model;
import com.joker.core.mvc.view.View;
public class FirstController implements IController {
	public View execute(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("---------in FirstController------");
		model.put("name", "Joker");
		model.put("age", "1");
		return new View("/test/first.jsp");
	}

}
