package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.CPUCategory;
import pojo.CPUdetail;
import dao.CPUCategoryDAO;

@Controller
public class CPUCategoryController {
	@RequestMapping("/cpucategorylist")
	public ModelAndView cpucategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("cpucategorylist");
		int start = 0;
		int count = 10;
		
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
		mav.addObject("cpus", cpus);
		return mav;
	}
	
	@RequestMapping("/addcpucategory")
	public ModelAndView addcpucategory(CPUCategory cpu) throws Exception {
		int id = 0;
		try {
			id = cpu.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			ModelAndView mav = new ModelAndView("addcpucategory");
			return mav;
		} else {
			CPUCategoryDAO cd = new CPUCategoryDAO();
			cd.add(cpu);
			ModelAndView mav = new ModelAndView("redirect:/cpucategorylist");
	        return mav;
		}
	}
	@RequestMapping("/editcpucategory")
	public ModelAndView editcpucategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/cpucategorylist");
	        return mav;
		} else {
			CPUCategoryDAO cd = new CPUCategoryDAO();
			CPUCategory cpu = cd.get(Id);
			ModelAndView mav = new ModelAndView("editcpucategory");
			mav.addObject("cpu", cpu);
	        return mav;
		}
	}
	
	@RequestMapping("/updatecpucategoryaction")
	public ModelAndView updatecpucategoryaction(CPUCategory cpu) throws Exception {
		int id = 0;
		try {
			id = cpu.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			System.out.println("add: " + id);
		} else {
			System.out.println("add: " + id);
			CPUCategoryDAO cd = new CPUCategoryDAO();
			cd.update(cpu);
		}
		ModelAndView mav = new ModelAndView("redirect:/cpucategorylist");
        return mav;
	}
	
	@RequestMapping("/deletecpucategory")
	public ModelAndView deletecpucategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			CPUCategoryDAO cd = new CPUCategoryDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/cpucategorylist");
        return mav;
	}
	
	@RequestMapping("/getcpucategory")
	public Map<String, CPUCategory> getcpucategory(@RequestBody String req) throws Exception {
		String partnumber = null;
		CPUCategory cpu = new CPUCategory();
		for(String va: req.split("&")) {
			String val[] = va.split("=");
			switch(val[0]) {
			case "partnumber":
				partnumber = val[1];
				break;
			default:
				System.out.println("value: " + val[0]);
			}
		}
		if (partnumber == null) {
			cpu = null;
		}else {
			CPUCategoryDAO cd = new CPUCategoryDAO();
			cpu = cd.get(partnumber);
		}
		Map<String, CPUCategory> modelMap = new HashMap<String, CPUCategory>(1);
	    
		modelMap.put("cpu", cpu);
		return modelMap;
	}
}