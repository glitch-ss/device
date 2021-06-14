package interceptor;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import dao.UserDAO;
import pojo.User;

public class UnloginInterceptor extends HandlerInterceptorAdapter {  

    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
    	
    	
    	String loginkey = String.valueOf(request.getSession().getAttribute("loginkey"));
    	String tempid = String.valueOf(request.getSession().getAttribute("id"));
    	if (loginkey.equals("null")) {
    		response.sendRedirect(request.getContextPath() + "/login");
        	return false;
    	}
    	int id = Integer.parseInt(tempid);
    	UserDAO ud = new UserDAO();
    	User user = ud.get(id);
    	System.out.println("Userid:"+id);
    	System.out.println("loginkey:" + user.loginkey);
    	if (user.loginkey.equals(loginkey)) {
    		return true;
    	}else {
    		System.out.println("loginkey:" + loginkey);
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
    	}     
  
    }  
    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {  
    	
    }  

    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)  
    throws Exception {  
          

    }  
      
} 