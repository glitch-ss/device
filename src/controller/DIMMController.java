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

import dao.DIMMDAO;
import dao.DIMMCategoryDAO;
import pojo.DIMM;
import pojo.DIMMDetail;
import pojo.DIMMCategory;

@Controller
public class DIMMController {
	@RequestMapping("/dimmlist")
	public ModelAndView dimmList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("dimmlist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new DIMMDAO().getTotal();
		
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
		
		List<DIMMDetail> dimms = new DIMMDAO().listDetail(start, count);

		mav.addObject("dimms", dimms);
		return mav;
	}
	
	@RequestMapping("/adddimm")
	public ModelAndView adddimm(DIMM dimm) throws Exception {
		int categoryId = 0;
		try {
			categoryId = dimm.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			ModelAndView mav = new ModelAndView("adddimm");
			List<DIMMCategory> dimmscategory = new DIMMCategoryDAO().list();
			mav.addObject("dimms", dimmscategory);
			return mav;
		} else {
			System.out.println("add: " + categoryId);
			DIMMDAO cd = new DIMMDAO();
			cd.add(dimm);
			ModelAndView mav = new ModelAndView("redirect:/dimmlist");
	        return mav;
		}
	}
	@RequestMapping("/editdimm")
	public ModelAndView editdimm(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/dimmlist");
	        return mav;
		} else {
			DIMMDAO cd = new DIMMDAO();
			DIMM dimm = cd.get(Id);
			List<DIMMCategory> dimmscategory = new DIMMCategoryDAO().list();
			ModelAndView mav = new ModelAndView("editdimm");
			mav.addObject("dimm", dimm);
			mav.addObject("dimmscategory", dimmscategory);
	        return mav;
		}
	}
	
	@RequestMapping("/updatedimmaction")
	public ModelAndView updatedimmaction(DIMM dimm) throws Exception {
		int categoryId = 0;
		try {
			categoryId = dimm.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			System.out.println("add: " + categoryId);
		} else {
			System.out.println("categoryId: " + categoryId);
			DIMMDAO cd = new DIMMDAO();
			System.out.println("serial: " + dimm.serialnumber);
			cd.update(dimm);
		}
		ModelAndView mav = new ModelAndView("redirect:/dimmlist");
        return mav;
	}
	
	@RequestMapping("/deletedimm")
	public ModelAndView deletedimm(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			DIMMDAO cd = new DIMMDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/dimmlist");
        return mav;
	}
	
	@RequestMapping(value="/getdimm", method=RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, DIMMDetail> getcpu(@RequestBody String req) throws Exception {
		String location = null;
		String owner = null;
		String serialnumber = null;
		DIMMDAO dd = new DIMMDAO();
		DIMMDetail dimmd = new DIMMDetail();
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
			dimmd = dd.GetByLocation(owner, location);
		} else if (serialnumber != null){
			dimmd = dd.GetBySerialnumber(serialnumber);
		} else {
			dimmd = null;
		}
		Map<String, DIMMDetail> modelMap = new HashMap<String, DIMMDetail>(1);
	    
		modelMap.put("dimm", dimmd);
		return modelMap;
	}
	
}
