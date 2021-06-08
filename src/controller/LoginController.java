package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDAO;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("login");
		
		String email = null;
		String password = null;
		String passwordInSql = null;
		
		try {
			email = String.valueOf(request.getParameter("email"));
			password = String.valueOf(request.getParameter("password"));
		} catch(NumberFormatException e) {
			
		}
		
		UserDAO ud = new UserDAO();
		
		if (password == null || email == null) {
			return mav;
		} else {
			passwordInSql = ud.getPasswordByEmail(email);
			if (passwordInSql != password) {
				String message = "Wrong password!!";
				mav.addObject("message", message);
				return mav;
			}else {
				String status = "login";
				session.setAttribute("status", status);
				ModelAndView newmav = new ModelAndView("redirect:/cpulist");
		        return newmav;
			}
		}
		
	}
}
