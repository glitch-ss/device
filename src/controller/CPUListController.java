package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.CPU;
import pojo.CPUdetail;
import dao.CPUDAO;

@Controller
public class CPUListController {
	@RequestMapping("/cpulist")
	public ModelAndView cpuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("cpulist");
		int start = 0;
		int count = 5;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new CPUDAO().getTotal();
		
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
		
		List<CPUdetail> cpus = new CPUDAO().listDetail(start, count);
		//request.setAttribute("cpus", cpus);
		//request.getRequestDispatcher("cpu.jsp").forward(request, response);
		mav.addObject("cpus", cpus);
		return mav;
	}
}