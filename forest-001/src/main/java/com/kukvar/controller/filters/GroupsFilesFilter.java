package com.kukvar.controller.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class GroupsFilter
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST,
				DispatcherType.FORWARD
				}
					, 
		urlPatterns = {
				"/classes",
				"/FilesHandler"
		}, 
		servletNames = { "GroupsHandler","FilesHandler" })
public class GroupsFilesFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		System.out.println("You are watched by GroupsFilter: "+req.getRequestURI());
		System.out.println("I see Query String: "+req.getQueryString());
		System.out.println("I see Query Parameter action: "+req.getParameter("action"));
		if (req.getParameter("action").equals("viewGroups")) {
			chain.doFilter(request, response);
		} else {
			if ((req.getSession(false) == null) || req.getSession(false).getAttribute("SignedUser") == null) {
			System.out.println("You are not validated to such action.Please sign in.");
			req.getSession().setAttribute("message", "");
			resp.sendRedirect("login.jsp");
			}
		}
			
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
