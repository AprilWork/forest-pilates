package com.kukvar.controller.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kukvar.hibernate.DAO.FilesDAO;
import com.kukvar.hibernate.DAO.GroupsDAO;
import com.kukvar.hibernate.entity.Files;
import com.kukvar.hibernate.entity.Group;

/**
 * Servlet implementation class GroupsHandler
 */
@WebServlet("/classes")
public class GroupsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//public String path = "c:/images/"; 
	public final String assetsImagePath = "assets/img/uploaded/";
	public final String realPath = "C:/Users/irade/git/Booking/pilates/src/main/webapp/";
	public final String path = "C:/Users/irade/git/Booking/pilates/src/main/webapp/assets/img/uploaded/";

    public GroupsHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "viewGroups": {
			listingGroups(request, response);
			break;
		}
		case "createGroup": {
			createGroupForm(request, response);
			break;
		}		
		case "listingGroups": {
			listingGroupsForEditing(request, response);
			break;
		}
		case "filesUpload": {
			filesUpload(request, response);
			break;			
		}		
		case "deleteGroup": {
			deleteGroups(request, response);
			break;			
		}			
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	private void createGroupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("newGroup.jsp").forward(request, response);
	}

	private void deleteGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		Group file = new GroupsDAO().getGroup(groupId);
		new GroupsDAO().deleteGroup(groupId);	
		listingGroups(request, response);
	}

	private void listingGroupsForEditing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Group> groups = new GroupsDAO().listGroups();
		request.setAttribute("groups", groups);
		request.setAttribute("path", assetsImagePath);
		request.getRequestDispatcher("listEditedGroups.jsp").forward(request, response);		
		
	}

	private void listingGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Group> groups = new GroupsDAO().listGroups();
		request.setAttribute("groups", groups);
		request.setAttribute("path", assetsImagePath);
		request.getRequestDispatcher("listGroups.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "createGroup": {
			createGroup(request, response);
			break;
		}		
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	private void createGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int groupId = Integer.parseInt(request.getParameter("groupId"));
		String className = request.getParameter("className");
		String description = request.getParameter("description");
		String nameImageFile = request.getParameter("nameImageFile");
		if ((nameImageFile == null ) || (nameImageFile == "" ) ) {
			nameImageFile = "default.jpg";
		} else {
			
		}
		//Group group = new Group(className, description,nameImageFile);
		//new GroupsDAO().addGroupDetails(group);
		//response.sendRedirect("index.jsp");
		response.sendRedirect(request.getContextPath()+"/classes?action=listingGroups");
		//request.getRequestDispatcher("listEditedGroups.jsp").
		//listingGroupsForEditing(request, response);
	}
	
	/*
	 * Probably usefull method to upload image file from newGroup.jsp
	 * 
	 */	
	private void filesUpload1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nameImageFile = request.getParameter("nameImageFile");
		try {
			nameImageFile = nameImageFile.substring(nameImageFile.lastIndexOf("\\") + 1);
		} catch (Exception e) {
		}
		System.out.println(nameImageFile);
		File file = new File(path + nameImageFile);
		if (!file.exists()) {
			
			//new FilesDAO().addFileDetails(new Files(name));
			
			// image.write(file);
			System.out.println("new file: "+path+nameImageFile);
		}		
			
	}
	
	private void filesUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		try {
		List<FileItem> images = upload.parseRequest(request);
			for (FileItem image : images) {
				String name = image.getName();
				try {name = name.substring(name.lastIndexOf("\\")+1); 
				} catch (Exception e) {
				}
				System.out.println(name);
				File file = new File(path+name);
				if (!file.exists()) {
					//new FilesDAO().addFileDetails(new Files(name));
					//image.write(file);
				}
				
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
		//listingImages(request, response);
		
	}


}
