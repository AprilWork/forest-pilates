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
	
		String action = request.getParameter("action");
		switch (action) {
		case "logout" :
			logout(request,response);
			break;
		case "login" :
			response.sendRedirect("login.jsp");
			break;			
		case "register" :
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
		String action = request.getParameter("action");
		switch (action) {
		case "registerin": {
			//System.out.println("doPost: register action");
			register(request,response);
			break;
		}		
		case "signin": {
			signin(request,response);
			break;
		}
		case "password": {
			password(request,response);
			break;
		}		
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
	}

	private void password(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//String emailFromRequest = request.getParameter("email");
		String emailFromAttribute = (String) request.getSession().getAttribute("email");
		//System.out.println("email from request parameter:"+emailFromRequest);
		System.out.println("email from attribute:"+emailFromAttribute);
		if (validatePassword(emailFromAttribute, request.getParameter("password"))) {
			//Invalidating session if any
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
			newSession.setMaxInactiveInterval(300);
			
			//newSession.setAttribute("username", username);
			newSession.setAttribute("email", emailFromAttribute);
			SignedUser signedUser = new SignedUser(emailFromAttribute);

			newSession.setAttribute("SignedUser",signedUser);
			response.sendRedirect("welcome_user.jsp");	
		} else {
			
		}
	}
	
	private boolean validatePassword(String email,String password) {
		if (email == null) {
			return false;
		} else {
		return new UsersDAO().validatePassword(email, password);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		System.out.println("Registering new Customer : "+email);
		User user = new User( email, password, phone);
		System.out.println("New Customer: "+user.toString());
			try {
				new UsersDAO().registerUser(name, name, null, null, null, email, password, phone);
				String message = "You are successfully registered. Please login in your account.";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("login.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("register.jsp");
			}
	}

	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");	
		if(! new UsersDAO().isExisted(email)) {
			String message = "We're sorry, the email you entered is incorrect. Please try again or reset your password.";
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		} else {
			String message = "Please enter password.";
			request.getSession().setAttribute("message", message);
			request.getSession().setAttribute("email", email);
			request.getRequestDispatcher("password.jsp").forward(request, response);
			//response.sendRedirect("password.jsp");		
		}		
	}
	
	
}




