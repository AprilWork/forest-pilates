package com.kukvar.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kukvar.hibernate.DAO.UsersDAO;
import com.kukvar.hibernate.entity.Address;
import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.entity.UserInfo;
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "registerin": {
			register(request,response);
			break;
		}	
		case "registerinfo": {
			registerinfo(request,response);
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
	
	
	private void registerinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int customerId = (int) request.getSession().getAttribute("customerId");
		String email = request.getParameter("email");		
		String phone = request.getParameter("phone");
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");	
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");	
		String zipcode = request.getParameter("zipcode");	
		
		boolean sameaddress = request.getParameter("sameaddress") != null;
		
		try {
			Address homeaddress = new Address(street, city, zipcode);
			if (sameaddress) {
				new UsersDAO().updateUserInformation(customerId, firstname, lastname, null, homeaddress, homeaddress);
			} else {
				// TODO different billing address
				new UsersDAO().updateUserInformation(customerId, firstname, lastname, null, homeaddress, null);
			}
			request.getSession().setAttribute("firstname", firstname);
			String message = "You are successfully registered. Please login in your account.";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("registerinfo.jsp");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Logout handler... new session...as guest...");
		request.getSession().invalidate();	
		HttpSession newSession = request.getSession(true);
		response.sendRedirect("index.jsp");
	}

	private void password(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("email");
		//System.out.println("email from attribute:"+email);
		int id = validatePassword(email, request.getParameter("password"));
		
		if (id > 0) {
			SignedUser signedUser = new UsersDAO().getSignedUserFromUserInfo(id);
			//Invalidating session if any
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
			newSession.setMaxInactiveInterval(300);
			newSession.setAttribute("SignedUser",signedUser);
			response.sendRedirect("welcome_user.jsp");	
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
	}
	
	private int validatePassword(String email,String password) {
		if (email == null) {
			return -1;
		} else {
		return new UsersDAO().validatePassword(email, password);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		System.out.println("Registering new Customer : "+email);
		User user = new User( email, password, phone);
		System.out.println("New Customer: "+user.toString());
			try {
				int customerId = new UsersDAO().registerUser(null, null, null, null, null, email, password, phone);
				request.getSession().setAttribute("customerId", customerId);
				String message = "Please complete all the required fields.";
				request.getSession().setAttribute("message", message);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("phone", phone);
				response.sendRedirect("registerinfo.jsp");				
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




