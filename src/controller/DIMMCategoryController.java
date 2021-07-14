package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.DIMMCategory;
import dao.DIMMCategoryDAO;

@Controller
public class DIMMCategoryController {
	@RequestMapping("/dimmcategorylist")
	public ModelAndView dimmcategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("dimmcategorylist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new DIMMCategoryDAO().getTotal();
		
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
		
		List<DIMMCategory> dimms = new DIMMCategoryDAO().list(start, count);
		mav.addObject("dimms", dimms);
		return mav;
	}
	
	@RequestMapping("/adddimmcategory")
	public ModelAndView adddimmcategory(DIMMCategory dimm) throws Exception {
		int id = 0;
		try {
			id = dimm.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			ModelAndView mav = new ModelAndView("adddimmcategory");
			return mav;
		} else {
			DIMMCategoryDAO cd = new DIMMCategoryDAO();
			cd.add(dimm);
			ModelAndView mav = new ModelAndView("redirect:/dimmcategorylist");
	        return mav;
		}
	}
	@RequestMapping("/editdimmcategory")
	public ModelAndView editdimmcategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/dimmcategorylist");
	        return mav;
		} else {
			DIMMCategoryDAO cd = new DIMMCategoryDAO();
			DIMMCategory dimm = cd.get(Id);
			ModelAndView mav = new ModelAndView("editdimmcategory");
			mav.addObject("dimm", dimm);
	        return mav;
		}
	}
	
	@RequestMapping("/updatedimmcategoryaction")
	public ModelAndView updatedimmcategoryaction(DIMMCategory dimm) throws Exception {
		int id = 0;
		try {
			id = dimm.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			System.out.println("add: " + id);
		} else {
			System.out.println("add: " + id);
			DIMMCategoryDAO cd = new DIMMCategoryDAO();
			cd.update(dimm);
		}
		ModelAndView mav = new ModelAndView("redirect:/dimmcategorylist");
        return mav;
	}
	
	@RequestMapping("/deletedimmcategory")
	public ModelAndView deletedimmcategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			DIMMCategoryDAO cd = new DIMMCategoryDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/dimmcategorylist");
        return mav;
	}
}
