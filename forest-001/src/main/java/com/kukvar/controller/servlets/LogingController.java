package com.kukvar.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kukvar.hibernate.DAO.UsersDAO;
import com.kukvar.hibernate.entity.User;
import com.kukvar.model.SignedUser;

import java.io.IOException;

@WebServlet("/login")
public class LogingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String action = request.getParameter("_action");
		switch (action) {
		case "logout" :
			logout(request,response);
			break;
		case "login" :
			response.sendRedirect("login.jsp");
			break;
		case "register" :
			//String button = request.getParameter("email");
			//System.out.println("value : "+button);
			response.sendRedirect("register.jsp");
			break;
		case "sign_with_google": {
			response.sendRedirect("login.jsp");
			break;
		}
		default:
			response.sendRedirect("index.jsp");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Logout handler... new session...as guest...");
		request.getSession().invalidate();	
		HttpSession newSession = request.getSession(true);
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action_");
		switch (action) {
		case "register": {
			register(request,response);
			break;
		}		
		case "signin": {
			signin(request,response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("new Customer : "+email);
		User user = new User( email, password, "1112223334");
			try {
				new UsersDAO().registerUser(name, name, null, null, null, email, password, null);
				signin(request,response);
			} catch (Exception e) {
				System.out.println(e.toString());
				response.sendRedirect("login.jsp");
			}
	}

	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("email: "+email);
		System.out.println("password : "+password);
		
		if(! new UsersDAO().isExisted(email)) {
			response.sendRedirect("login.jsp");
		} else {
			//Invalidating session if any
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
			newSession.setMaxInactiveInterval(300);
			//newSession.setAttribute("username", username);
			newSession.setAttribute("email", email);
			SignedUser user = new SignedUser(email);

			newSession.setAttribute("SignedUser",user);
			response.sendRedirect("about.jsp");		
		}		
	}
	
	
}




