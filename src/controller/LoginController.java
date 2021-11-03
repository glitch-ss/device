package controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDAO;
import pojo.User;

@Controller
public class LoginController {
	private String refer_url = "/";
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("login");
		String refer = null;
		
		try {
			refer = request.getHeader("Referer");
			if (!refer.contains("login")) {
				String[] l = refer.split("Device");
				refer_url = l[1];
			}
		} catch(NumberFormatException e) {
			System.out.println(e);
		}
		
		UserDAO ud = new UserDAO();
		return mav;
		
	}
	
	@RequestMapping("/logincheck")
	public ModelAndView logincheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("login");
		
		String email = null;
		String password = null;
		String passwordInSql = null;
		
		try {
			email = request.getParameter("email");
			password = request.getParameter("password");
		} catch(NumberFormatException e) {
			System.out.println(e);
		}
		
		UserDAO ud = new UserDAO();
		if (password == null || email == null) {
			return mav;
		} else {
			passwordInSql = ud.getPasswordByEmail(email);
			if (passwordInSql.equals(password)) {
				User user = ud.get(email);
				String uuid = String.valueOf(UUID.randomUUID());
				String loginkey = uuid.substring(0, 8);
				user.loginkey = loginkey;
				ud.setLoginKey(user);
				session.setAttribute("loginkey", loginkey);
				session.setAttribute("id", user.id);
				ModelAndView newmav = new ModelAndView("redirect:" + refer_url);
		        return newmav;
			}else {
				String message = "Wrong password!!";
				mav.addObject("message", message);
				return mav;
			}
		}
		
	}
	
	@RequestMapping("/signin")
	public ModelAndView signin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("signin");
		
		String name = null;
		String email = null;
		String password = null;
		String passwordInSql = null;
		
		try {
			name =request.getParameter("name");
			email = request.getParameter("email");
			password = request.getParameter("password");
		} catch(NumberFormatException e) {
			
		}
		
		UserDAO ud = new UserDAO();
		System.out.println("name:" + name);
		if (password == null || email == null) {
			return mav;
		} else {
			passwordInSql = ud.getPasswordByEmail(email);
			if (passwordInSql == null) {
				User user = new User();
				user.name = name;
				user.email = email;
				user.password = password;
				ud.add(user);
				ModelAndView newmav = new ModelAndView("redirect:/login");
				return newmav;
			}else {
				String message = "email has been used, please check.";
				mav.addObject("message", message);
				return mav;
			}
		}
		
	}
}
