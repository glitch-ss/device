package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.PowerSupplyCategoryDAO;
import dao.PowerSupplyDAO;
import pojo.PowerSupply;
import pojo.PowerSupplyCategory;
import pojo.PowerSupplyDetail;

@Controller
public class PowerSupplyController {
	@RequestMapping("/powersupplylist")
	public ModelAndView powersupplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("powersupplylist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new PowerSupplyDAO().getTotal();
		
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
		
		List<PowerSupplyDetail> powersupplys = new PowerSupplyDAO().listDetail(start, count);
		mav.addObject("powersupplys", powersupplys);
		return mav;
	}
	
	@RequestMapping("/addpowersupply")
	public ModelAndView addpowersupply(PowerSupply powersupply) throws Exception {
		int categoryId = 0;
		try {
			categoryId = powersupply.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			ModelAndView mav = new ModelAndView("addpowersupply");
			List<PowerSupplyCategory> powersupplyscategory = new PowerSupplyCategoryDAO().list();
			mav.addObject("powersupplys", powersupplyscategory);
			return mav;
		} else {
			System.out.println("add: " + categoryId);
			PowerSupplyDAO cd = new PowerSupplyDAO();
			cd.add(powersupply);
			ModelAndView mav = new ModelAndView("redirect:/powersupplylist");
	        return mav;
		}
	}
	@RequestMapping("/editpowersupply")
	public ModelAndView editpowersupply(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/powersupplylist");
	        return mav;
		} else {
			System.out.println("add: " + Id);
			PowerSupplyDAO cd = new PowerSupplyDAO();
			PowerSupply powersupply = cd.get(Id);
			List<PowerSupplyCategory> powersupplyscategory = new PowerSupplyCategoryDAO().list();
			ModelAndView mav = new ModelAndView("editpowersupply");
			mav.addObject("powersupply", powersupply);
			mav.addObject("powersupplyscategory", powersupplyscategory);
	        return mav;
		}
	}
	
	@RequestMapping("/updatepowersupplyaction")
	public ModelAndView updatepowersupplyaction(PowerSupply powersupply) throws Exception {
		int categoryId = 0;
		try {
			categoryId = powersupply.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			System.out.println("add: " + categoryId);
		} else {
			System.out.println("add: " + categoryId);
			PowerSupplyDAO cd = new PowerSupplyDAO();
			System.out.println("serial: " + powersupply.serialnumber);
			cd.update(powersupply);
		}
		ModelAndView mav = new ModelAndView("redirect:/powersupplylist");
        return mav;
	}
	
	@RequestMapping("/deletepowersupply")
	public ModelAndView deletepowersupply(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			PowerSupplyDAO cd = new PowerSupplyDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/powersupplylist");
        return mav;
	}
}
