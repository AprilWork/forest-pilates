package com.kukvar.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kukvar.hibernate.DAO.UsersDAO;
import com.kukvar.hibernate.entity.User;
import com.kukvar.model.SignedTeamUser;
import com.kukvar.model.SignedUser;

import java.io.IOException;

/**
 * Servlet implementation class MemberAreaController
 */
@WebServlet("/team")
public class TeamAreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeamAreaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null) {
			if (request.getSession().getAttribute("SignedTeamUser") != null) {
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("loginTeam.jsp");
			}
		} else {
			switch (action) {
			case "login":
				login(request,response);
				break;
			case "logout":
				logout(request,response);
				break;
			default:
				response.sendRedirect("loginTeam.jsp");
				break;
			}
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String teamEmail = null, sessionID = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("teamEmail")) {
					teamEmail = cookie.getValue();
				}
				if (cookie.getName().equals("JSESSIONID")) {
					sessionID = cookie.getValue();
				}
			}
		}
		if (sessionID == null || teamEmail == null) {
			response.sendRedirect("loginTeam.jsp");
		}		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {	
		case "signin": {
			signin(request,response);
			break;
		}	
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Logout handler... new session...as guest...");
		request.getSession().invalidate();	
		HttpSession newSession = request.getSession(true);
		response.sendRedirect("index.jsp");
	}	
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		if (validatePassword(email, request.getParameter("password"))) {
			//Invalidating session if any
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
			newSession.setMaxInactiveInterval(300);
			//newSession.setAttribute("email", email);
			SignedTeamUser signedTeamUser = new SignedTeamUser(email);
			newSession.setAttribute("SignedTeamUser",signedTeamUser);
			Cookie teamEmail = new Cookie("teamEmail", email);
	    response.addCookie(teamEmail);
			response.sendRedirect("index.jsp");	
		} else {
			response.sendRedirect("loginTeam.jsp");	
		}
	}
	
	private boolean validatePassword(String email,String password) {
		// TODO validate password for team member
		/*
		if (email == null) {
			return false;
		} else {
		return new UsersDAO().validatePassword(email, password);
		}
		*/
		return true;
	}


}
