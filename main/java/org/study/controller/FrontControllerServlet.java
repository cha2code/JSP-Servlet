package org.study.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.command.Command;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Command> commandMap = new HashMap<>();
	
	// view name의 접두어, 접미어
	String prifix = "";
	String surfix = "";
	
	// 생성자로 command 생성
    public FrontControllerServlet() {

    	commandMap.put("/list.do", null);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI(); // project + 파일 경로를 가져옴 (ex> /project/list.jsp)
		String contextPath = request.getContextPath(); // project의 path만 가져옴 (ex> /project)
		
		// command로 어떤 작업을 할 지 찾음
		// .subString(시작) : 시작~끝을 잘라낸 새로운 문자열 리턴
		// path의 길이 이후 부터 끝
		String command = requestURI.substring(contextPath.length());
		
		// 요청할 URI의 command를 저장
		Command cmd = commandMap.get(command);
		String view; // jsp의 경로
		
		// cmd가 null일 경우 : 잘못된 경로를 입력했을 때 = 404 error
		if(cmd != null) {
			
			cmd.execute(request, response);
			view = cmd.execute(request, response); // 이동할 jsp의 경로
		}
		
		else {
			
			view = "404"; // 에러를 개발자 측에서 처리
		}
		
		// 경로를 접두어(저장 폴더명) + view(저장 파일명) + 접미어(확장자명)으로 구성
		String target = prifix + view + surfix;
		
		// view로 forwarding(전송)
		RequestDispatcher dis = request.getRequestDispatcher(target);
		dis.forward(request, response);
	}

}
