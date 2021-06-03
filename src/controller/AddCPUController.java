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
import pojo.CPUdetail;
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
			System.out.println("add: " + categoryId);
			CPUDAO cd = new CPUDAO();
			cd.add(cpu);
			ModelAndView mav = new ModelAndView("redirect:/cpulist");
	        return mav;
		}
	}
	@RequestMapping("/editcpu")
	public ModelAndView editcpu(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/cpulist");
	        return mav;
		} else {
			System.out.println("add: " + Id);
			CPUDAO cd = new CPUDAO();
			CPU cpu = cd.get(Id);
			List<CPUCategory> cpuscategory = new CPUCategoryDAO().list();
			ModelAndView mav = new ModelAndView("editcpu");
			mav.addObject("cpu", cpu);
			mav.addObject("cpuscategory", cpuscategory);
	        return mav;
		}
	}
	@RequestMapping("/updatecpuaction")
	public ModelAndView updatecpuaction(CPU cpu) throws Exception {
		int categoryId = 0;
		try {
			categoryId = cpu.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			System.out.println("add: " + categoryId);
		} else {
			System.out.println("add: " + categoryId);
			CPUDAO cd = new CPUDAO();
			cd.update(cpu);
		}
		ModelAndView mav = new ModelAndView("redirect:/cpulist");
        return mav;
	}
}
