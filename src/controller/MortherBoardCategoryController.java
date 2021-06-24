package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.MortherBoardCategoryDAO;
import pojo.MortherBoardCategory;

@Controller
public class MortherBoardCategoryController {
	@RequestMapping("/mbcategorylist")
	public ModelAndView mbcategorylist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("mbcategorylist");
		int start = 0;
		int count = 5;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new MortherBoardCategoryDAO().getTotal();
		
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
		
		List<MortherBoardCategory> mbs = new MortherBoardCategoryDAO().list(start, count);
		mav.addObject("mbs", mbs);
		return mav;
	}
	
	@RequestMapping("/addmbcategory")
	public ModelAndView addmbcategory(MortherBoardCategory mb) throws Exception {
		int id = 0;
		try {
			id = mb.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			ModelAndView mav = new ModelAndView("addmbcategory");
			return mav;
		} else {
			MortherBoardCategoryDAO cd = new MortherBoardCategoryDAO();
			cd.add(mb);
			ModelAndView mav = new ModelAndView("redirect:/mbcategorylist");
	        return mav;
		}
	}
	@RequestMapping("/editmbcategory")
	public ModelAndView editmbcategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/mbcategorylist");
	        return mav;
		} else {
			MortherBoardCategoryDAO cd = new MortherBoardCategoryDAO();
			MortherBoardCategory mb = cd.get(Id);
			ModelAndView mav = new ModelAndView("editmbcategory");
			mav.addObject("mb", mb);
	        return mav;
		}
	}
	
	@RequestMapping("/updatembcategoryaction")
	public ModelAndView updatembcategoryaction(MortherBoardCategory mb) throws Exception {
		int id = 0;
		try {
			id = mb.id;
			System.out.println("id: " + id);
			
		} catch(NumberFormatException e) {
			
		}
		if (id == 0) {
			System.out.println("add: " + id);
		} else {
			System.out.println("add: " + id);
			MortherBoardCategoryDAO cd = new MortherBoardCategoryDAO();
			cd.update(mb);
		}
		ModelAndView mav = new ModelAndView("redirect:/mbcategorylist");
        return mav;
	}
	
	@RequestMapping("/deletembcategory")
	public ModelAndView deletembcategory(int id) throws Exception {
		int Id = 0;
		try {
			Id = id;
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			System.out.println("delete: " + Id);
		} else {
			MortherBoardCategoryDAO cd = new MortherBoardCategoryDAO();
			cd.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/mbcategorylist");
        return mav;
	}
}
