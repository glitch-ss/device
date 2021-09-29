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

import dao.CPUCategoryDAO;
import dao.HDCategoryDAO;
import pojo.CPUCategory;
import pojo.HDCategory;

@Controller
public class HDCategoryController {
	@RequestMapping("/hdcategorylist")
	public ModelAndView hdcategorylist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("hdcategorylist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new HDCategoryDAO().getTotal();
		
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
		
		List<HDCategory> hdcs = new HDCategoryDAO().list(start, count);
		mav.addObject("hdcs", hdcs);
		return mav;
	}
	
	@RequestMapping("/addhdcategory")
	public ModelAndView addhdcategory(HDCategory hdc) throws Exception {
		int id = -1;
		try {
			id = hdc.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == -1) {
			ModelAndView mav = new ModelAndView("addhdcategory");
			return mav;
		} else {
			HDCategoryDAO cd = new HDCategoryDAO();
			cd.add(hdc);
			ModelAndView mav = new ModelAndView("redirect:/hdcategorylist");
	        return mav;
		}
	}
	@RequestMapping("/edithdcategory")
	public ModelAndView edithdcategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/hdcategorylist");
	        return mav;
		} else {
			HDCategoryDAO cd = new HDCategoryDAO();
			HDCategory hdc = cd.get(Id);
			ModelAndView mav = new ModelAndView("edithdcategory");
			mav.addObject("hd", hdc);
	        return mav;
		}
	}
	
	@RequestMapping("/updatehdcategoryaction")
	public ModelAndView updatepscategoryaction(HDCategory hdc) throws Exception {
		int id = 0;
		try {
			id = hdc.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			System.out.println("add: " + id);
		} else {
			System.out.println("add: " + id);
			HDCategoryDAO cd = new HDCategoryDAO();
			cd.update(hdc);
		}
		ModelAndView mav = new ModelAndView("redirect:/hdcategorylist");
        return mav;
	}
	
	@RequestMapping("/deletehdcategory")
	public ModelAndView deletehdcategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			HDCategoryDAO cd = new HDCategoryDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/hdcategorylist");
        return mav;
	}
	
	@RequestMapping(value="/gethdcategory", method=RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, HDCategory> gethdcategory(@RequestBody String req) throws Exception {
		String partnumber = null;
		HDCategory hdc = null;
		HDCategoryDAO hdcd = new HDCategoryDAO();
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
		if (partnumber != null) {
			hdc = hdcd.get(partnumber);
		}
		Map<String, HDCategory> modelMap = new HashMap<String, HDCategory>(1);
	    
		modelMap.put("hdc", hdc);
		return modelMap;
	}
}
