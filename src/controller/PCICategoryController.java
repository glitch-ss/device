package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.PCIeCategoryDAO;
import pojo.PCIeCategory;


public class PCICategoryController {
	@RequestMapping("/pcicategorylist")
	public ModelAndView pcicategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("pcicategorylist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new PCIeCategoryDAO().getTotal();
		
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
		
		List<PCIeCategory> pcis = new PCIeCategoryDAO().list(start, count);
		mav.addObject("pcis", pcis);
		return mav;
	}
	
	@RequestMapping("/addpcicategory")
	public ModelAndView addpcicategory(PCIeCategory pci) throws Exception {
		int id = 0;
		try {
			id = pci.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			ModelAndView mav = new ModelAndView("addpcicategory");
			return mav;
		} else {
			System.out.println("add: " + id);
			PCIeCategoryDAO pd = new PCIeCategoryDAO();
			pd.add(pci);
			ModelAndView mav = new ModelAndView("redirect:/pcicategorylist");
	        return mav;
		}
	}
	@RequestMapping("/editpcicategory")
	public ModelAndView editpcicategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/pcicategorylist");
	        return mav;
		} else {
			PCIeCategoryDAO pd = new PCIeCategoryDAO();
			PCIeCategory pci = pd.get(Id);
			ModelAndView mav = new ModelAndView("editpcicategory");
			mav.addObject("pci", pci);
	        return mav;
		}
	}
	
	@RequestMapping("/updatepcicategoryaction")
	public ModelAndView updatepcicategoryaction(PCIeCategory pci) throws Exception {
		int id = 0;
		try {
			id = pci.id;
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			System.out.println("add: " + id);
		} else {
			System.out.println("update: " + id);
			PCIeCategoryDAO pd = new PCIeCategoryDAO();
			pd.update(pci);
		}
		ModelAndView mav = new ModelAndView("redirect:/pcicategorylist");
        return mav;
	}
	
	@RequestMapping("/deletepcicategory")
	public ModelAndView deletepcicategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			PCIeCategoryDAO cd = new PCIeCategoryDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/pcicategorylist");
        return mav;
	}

}
