package com.kukvar.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kukvar.hibernate.DAO.GroupsDAO;
import com.kukvar.hibernate.entity.Group;

/**
 * Servlet implementation class SiteController
 */
@WebServlet("/home")
public class SiteController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1947945359429925496L;
	//public String path = "c:/images/"; 
	public final String assetsImagePath = "assets/img/uploaded/";
	public final String realPath = "C:/Users/irade/git/Booking/pilates/src/main/webapp/";
	public final String path = "C:/Users/irade/git/Booking/pilates/src/main/webapp/assets/img/uploaded/";	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "about": {
			request.getRequestDispatcher("about.jsp");
			break;
		}		
		case "viewGroups": {
			listingGroups(request, response);
			break;
		}		
		default:
			request.getRequestDispatcher("index.jsp");
			break;
		}
	}
	
	private void listingGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Group> groups = new GroupsDAO().listGroups();
		request.setAttribute("groups", groups);
		request.setAttribute("path", assetsImagePath);
		request.getRequestDispatcher("listGroups.jsp").forward(request, response);
	}	

}
