package com.dffan.volunter.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dffan.volunter.domain.Admin;
/**
 * 后台管理员页面过滤
 * @author admin
 *
 */
public class LoginFilter implements Filter {
	
	private String excludeUrl;
	
	@Override
	public void init(FilterConfig fconfig) throws ServletException {
		System.out.println("过滤器初始化---");
		excludeUrl = fconfig.getInitParameter("exclude");
		System.out.println("excludeUrl:"+excludeUrl);

	}



	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String servletPath = request.getServletPath();
		System.out.println("servletPath:"+servletPath);
		String [] url = excludeUrl.split(",");
		List l = Arrays.asList(url);
		for(Object s : l){
			System.out.print(s);
		}
        if(admin == null){
        	//不包含排除的url
           if(!l.contains(servletPath)){
        	   System.out.println("暂未登录---");
        	   response.sendRedirect(request.getContextPath()+"/adminLogin.jsp");
           }else{
            	filterChain.doFilter(request, response);
           }
        }else{
        	filterChain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {
     System.out.println("过滤器销毁---");
	}

}
