package org.study.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	// 전송(forwarding)할 JSP 타입을 return 타입으로 가짐
	String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;

}
