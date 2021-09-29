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

import dao.HDCategoryDAO;
import dao.HDDAO;
import pojo.HD;
import pojo.HDCategory;
import pojo.HDDetail;

@Controller
public class HDController {
	@RequestMapping("/hdlist")
	public ModelAndView hdList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("hdlist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new HDDAO().getTotal();
		
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
		
		List<HDDetail> hds = new HDDAO().listDetail(start, count);
		mav.addObject("hds", hds);
		return mav;
	}
	
	@RequestMapping("/addhd")
	public ModelAndView addpowersupply(HD hd) throws Exception {
		int categoryId = -1;
		try {
			categoryId = hd.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == -1) {
			ModelAndView mav = new ModelAndView("addhd");
			List<HDCategory> hdcs = new HDCategoryDAO().list();
			mav.addObject("hds", hdcs);
			return mav;
		} else {
			System.out.println("add: " + categoryId);
			HDDAO cd = new HDDAO();
			cd.add(hd);
			ModelAndView mav = new ModelAndView("redirect:/hdlist");
	        return mav;
		}
	}
	@RequestMapping("/edithd")
	public ModelAndView edithd(int id) throws Exception {
		int Id = -1;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == -1) {
			ModelAndView mav = new ModelAndView("redirect:/hdlist");
	        return mav;
		} else {
			System.out.println("add: " + Id);
			HDDAO cd = new HDDAO();
			HD hd = cd.get(Id);
			List<HDCategory> hdcs = new HDCategoryDAO().list();
			ModelAndView mav = new ModelAndView("edithd");
			mav.addObject("hd", hd);
			mav.addObject("hdcs", hdcs);
	        return mav;
		}
	}
	
	@RequestMapping("/updatehdaction")
	public ModelAndView updatehdaction(HD hd) throws Exception {
		int categoryId = -1;
		try {
			categoryId = hd.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == -1) {
			System.out.println("add: " + categoryId);
		} else {
			System.out.println("add: " + categoryId);
			HDDAO cd = new HDDAO();
			System.out.println("serial: " + hd.serialnumber);
			cd.update(hd);
		}
		ModelAndView mav = new ModelAndView("redirect:/hdlist");
        return mav;
	}
	
	@RequestMapping("/deletehd")
	public ModelAndView deletehd(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			HDDAO cd = new HDDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/hdlist");
        return mav;
	}
	
	@RequestMapping(value="/getHD", method=RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, HDDetail> getps(@RequestBody String req) throws Exception {
		String location = null;
		String owner = null;
		String serialnumber = null;
		HDDAO dd = new HDDAO();
		HDDetail hdd = new HDDetail();
		for(String va: req.split("&")) {
			String val[] = va.split("=");
			switch(val[0]) {
			case "owner":
				owner = val[1];
				break;
			case "location":
				location = val[1];
				break;
			case "serialnumber":
				serialnumber = val[1];
				break;
			default:
				System.out.println("value: " + val[0]);
			}
		}
		if (location != null && owner != null) {
			hdd = dd.GetByLocation(owner, location);
		} else if (serialnumber != null){
			hdd = dd.GetBySerialnumber(serialnumber);
		} else {
			hdd = null;
		}
		Map<String, HDDetail> modelMap = new HashMap<String, HDDetail>(1);
	    
		modelMap.put("hd", hdd);
		return modelMap;
	}
}
