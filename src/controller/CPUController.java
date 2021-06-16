package controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import dao.CPUDAO;
import dao.CPUCategoryDAO;
import pojo.CPU;
import pojo.CPUdetail;
import pojo.CPUCategory;

@Controller
public class CPUController {
	@RequestMapping("/cpulist")
	public ModelAndView cpuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("cpulist");
		int start = 0;
		int count = 10;
		
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
			System.out.println("serial: " + cpu.serialnumber);
			cd.update(cpu);
		}
		ModelAndView mav = new ModelAndView("redirect:/cpulist");
        return mav;
	}
	
	@RequestMapping("/deletecpu")
	public ModelAndView deletecpu(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			CPUDAO cd = new CPUDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/cpulist");
        return mav;
	}

	@RequestMapping(value="/getcpu", method=RequestMethod.POST)
	public Map<String, CPUdetail> getcpu(@RequestBody Map<String, String> request) throws Exception {
		String location = null;
		String owner = null;
		String serialnumber = null;
		CPUDAO cd = new CPUDAO();
		CPUdetail cpud = new CPUdetail();

		try {
			owner = request.get("owner");
			location = request.get("location");
			serialnumber = request.get("serialnumber");
		} catch(NumberFormatException e) {
			
		}
		if (location != null && owner != null) {
			cpud = cd.GetByLocation(owner, location);
		} else if (serialnumber != null){
			cpud = cd.GetBySerialnumber(serialnumber);
		} else {
			cpud = null;
		}
		Map<String, CPUdetail> modelMap = new HashMap<String, CPUdetail>(1);
		modelMap.put("cpu", cpud);
		return modelMap;
	}
}
