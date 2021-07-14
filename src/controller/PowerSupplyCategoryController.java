package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.PowerSupplyCategoryDAO;
import pojo.PowerSupplyCategory;

@Controller
public class PowerSupplyCategoryController {
	@RequestMapping("/pscategorylist")
	public ModelAndView pscategorylist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("pscategorylist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new PowerSupplyCategoryDAO().getTotal();
		
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
		
		List<PowerSupplyCategory> cpus = new PowerSupplyCategoryDAO().list(start, count);
		mav.addObject("cpus", cpus);
		return mav;
	}
	
	@RequestMapping("/addpscategory")
	public ModelAndView addcpucategory(PowerSupplyCategory ps) throws Exception {
		int id = 0;
		try {
			id = ps.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			ModelAndView mav = new ModelAndView("addpscategory");
			return mav;
		} else {
			PowerSupplyCategoryDAO cd = new PowerSupplyCategoryDAO();
			cd.add(ps);
			ModelAndView mav = new ModelAndView("redirect:/pscategorylist");
	        return mav;
		}
	}
	@RequestMapping("/editpscategory")
	public ModelAndView editpscategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/pscategorylist");
	        return mav;
		} else {
			PowerSupplyCategoryDAO cd = new PowerSupplyCategoryDAO();
			PowerSupplyCategory ps = cd.get(Id);
			ModelAndView mav = new ModelAndView("editpscategory");
			mav.addObject("ps", ps);
	        return mav;
		}
	}
	
	@RequestMapping("/updatepscategoryaction")
	public ModelAndView updatepscategoryaction(PowerSupplyCategory ps) throws Exception {
		int id = 0;
		try {
			id = ps.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			System.out.println("add: " + id);
		} else {
			System.out.println("add: " + id);
			PowerSupplyCategoryDAO cd = new PowerSupplyCategoryDAO();
			cd.update(ps);
		}
		ModelAndView mav = new ModelAndView("redirect:/pscategorylist");
        return mav;
	}
	
	@RequestMapping("/deletepscategory")
	public ModelAndView deletepscategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			PowerSupplyCategoryDAO cd = new PowerSupplyCategoryDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/pscategorylist");
        return mav;
	}
}
