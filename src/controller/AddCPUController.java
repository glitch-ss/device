package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.CPUDAO;
import dao.CPUCategoryDAO;
import pojo.CPU;
import pojo.CPUCategory;

@Controller
public class AddCPUController {
	@RequestMapping("/addcpu")
	public ModelAndView addcpu(CPU cpu) throws Exception {
		int categoryId = 0;
		try {
			categoryId = cpu.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			ModelAndView mav = new ModelAndView("addcpu");
			List<CPUCategory> cpuscategory = new CPUCategoryDAO().list();
			mav.addObject("cpus", cpuscategory);
			return mav;
		} else {
			CPUDAO cd = new CPUDAO();
			cd.add(cpu);
			ModelAndView mav = new ModelAndView("redirect:/cpulist");
	        return mav;
		}
		
		
		
		
	}
}
