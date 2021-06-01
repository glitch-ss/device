package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.CPUCategory;
import dao.CPUCategoryDAO;

@Controller
public class IndexController {
	@RequestMapping("/cpucategory")
	public ModelAndView cpuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("cpucategory");
		int start = 0;
		int count = 5;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new CPUCategoryDAO().getTotal();
		
		int last;
		if (0 == total % count)
			last = total - count;
		else
			last = total - total % count;
		pre = pre < 0 ? 0 : pre;
		next = next > last ? last  : next;
		mav.addObject("next", next);
		mav.addObject("pre", pre);
		mav.addObject("last", last);
		
		List<CPUCategory> cpus = new CPUCategoryDAO().list(start, count);
		//request.setAttribute("cpus", cpus);
		//request.getRequestDispatcher("cpu.jsp").forward(request, response);
		mav.addObject("cpus", cpus);
		return mav;
	}
	
	
	@RequestMapping("/addcpu")
	public ModelAndView addcpu(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		try {
			String action = String.valueOf(request.getParameter("action"));
			System.out.println("action: " + action);
			ModelAndView mav = new ModelAndView("addcpu");
			List<CPUCategory> cpuscategory = new CPUCategoryDAO().list();
			mav.addObject("cpus", cpuscategory);
			return mav;
		} catch(NumberFormatException e) {
			ModelAndView mav = new ModelAndView("redirect:/cpucategory");
	        return mav;
		}
		
		
	}
}