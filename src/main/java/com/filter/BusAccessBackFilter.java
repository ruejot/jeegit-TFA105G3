package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class TestingLogInFilter
 */
// ALL Filter setup in web.xml
// please refer setting with web-site www.codejava.net/java-ee/servlet/webfilter-annotation-examples

// 廠商身分(BusUsing)的，才可以看到後台頁面。
// 判斷方式: 頁面的路徑。
public class BusAccessBackFilter implements Filter {

    /**
     * Default constructor. 
     */
//    public BusAccessBackFilter() {
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 
	 * Only Bus identity can access Background.
	 * In session, use "BusUsing" as key name.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		if (session.getAttribute("BusUsing") == null) {
			res.sendRedirect(req.getContextPath()+"/nest-frontend/show404Restriction.jsp");
		} else {
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
