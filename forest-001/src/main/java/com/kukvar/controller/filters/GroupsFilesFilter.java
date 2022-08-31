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
				//"/team",
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if ((req.getSession(false) == null) 
				|| (req.getSession(false).getAttribute("SignedTeamUser") == null) ) {
				resp.sendRedirect("loginTeam.jsp");	
		}	else {
				chain.doFilter(request, response);
		} 
			
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
