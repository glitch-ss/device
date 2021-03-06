package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.MortherBoardCategoryDAO;
import dao.MortherBoardDAO;
import pojo.MortherBoard;
import pojo.MortherBoardCategory;
import pojo.MortherBoardDetail;


@Controller
public class MortherBoardController {
	@RequestMapping("/mortherboardlist")
	public ModelAndView mortherboardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("mortherboardlist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new MortherBoardDAO().getTotal();
		
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
		
		List<MortherBoardDetail> mortherboards = new MortherBoardDAO().listDetail(start, count);
		mav.addObject("mortherboards", mortherboards);
		return mav;
	}
	
	@RequestMapping("/addmortherboard")
	public ModelAndView addmortherboard(MortherBoard mortherboard) throws Exception {
		int categoryId = 0;
		try {
			categoryId = mortherboard.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			ModelAndView mav = new ModelAndView("addmortherboard");
			List<MortherBoardCategory> mortherboardscategory = new MortherBoardCategoryDAO().list();
			mav.addObject("mortherboards", mortherboardscategory);
			return mav;
		} else {
			System.out.println("add: " + categoryId);
			MortherBoardDAO cd = new MortherBoardDAO();
			cd.add(mortherboard);
			ModelAndView mav = new ModelAndView("redirect:/mortherboardlist");
	        return mav;
		}
	}
	@RequestMapping("/editmortherboard")
	public ModelAndView editmortherboard(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/mortherboardlist");
	        return mav;
		} else {
			System.out.println("add: " + Id);
			MortherBoardDAO cd = new MortherBoardDAO();
			MortherBoard mortherboard = cd.get(Id);
			List<MortherBoardCategory> mortherboardscategory = new MortherBoardCategoryDAO().list();
			ModelAndView mav = new ModelAndView("editmortherboard");
			mav.addObject("mortherboard", mortherboard);
			mav.addObject("mortherboardscategory", mortherboardscategory);
	        return mav;
		}
	}
	
	@RequestMapping("/updatemortherboardaction")
	public ModelAndView updatemortherboardaction(MortherBoard mortherboard) throws Exception {
		int categoryId = 0;
		try {
			categoryId = mortherboard.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			System.out.println("add: " + categoryId);
		} else {
			System.out.println("add: " + categoryId);
			MortherBoardDAO cd = new MortherBoardDAO();
			System.out.println("serial: " + mortherboard.serialnumber);
			cd.update(mortherboard);
		}
		ModelAndView mav = new ModelAndView("redirect:/mortherboardlist");
        return mav;
	}
	
	@RequestMapping("/deletemortherboard")
	public ModelAndView deletemortherboard(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			MortherBoardDAO cd = new MortherBoardDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/mortherboardlist");
        return mav;
	}
	
	@RequestMapping(value="/getMB", method=RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, MortherBoardDetail> getmb(@RequestBody String req) throws Exception {
		String owner = null;
		String serialnumber = null;
		MortherBoardDAO md = new MortherBoardDAO();
		MortherBoardDetail mbd = new MortherBoardDetail();
		for(String va: req.split("&")) {
			String val[] = va.split("=");
			switch(val[0]) {
			case "owner":
				owner = val[1];
				break;
			case "serialnumber":
				serialnumber = val[1];
				break;
			default:
				System.out.println("value: " + val[0]);
			}
		}
		if (owner != null) {
			mbd = md.GetByLocation(owner);
		} else if (serialnumber != null){
			mbd = md.GetBySerialnumber(serialnumber);
		} else {
			mbd = null;	
		}
		Map<String, MortherBoardDetail> modelMap = new HashMap<String, MortherBoardDetail>(1);
	    
		modelMap.put("mb", mbd);
		return modelMap;
	}
}
