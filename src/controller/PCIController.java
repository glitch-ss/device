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

import dao.PCIeCategoryDAO;
import dao.PCIeDAO;
import pojo.PCIe;
import pojo.PCIeCategory;
import pojo.PCIeDetail;

@Controller
public class PCIController {
	@RequestMapping("/pcilist")
	public ModelAndView cpuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("pcilist");
		int start = 0;
		int count = 10;
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			
		}
		
		int next = start + count;
		int pre = start - count;
		
		int total = new PCIeDAO().getTotal();
		
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
		
		List<PCIeDetail> pcis = new PCIeDAO().listDetail(start, count);
		mav.addObject("pcis", pcis);
		return mav;
	}
	
	@RequestMapping("/addpci")
	public ModelAndView addpci(PCIe pci) throws Exception {
		int categoryId = 0;
		try {
			categoryId = pci.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			ModelAndView mav = new ModelAndView("addcpu");
			List<PCIeCategory> pciscategory = new PCIeCategoryDAO().list();
			mav.addObject("pcis", pciscategory);
			return mav;
		} else {
			System.out.println("add: " + categoryId);
			PCIeDAO cp = new PCIeDAO();
			cp.add(pci);
			ModelAndView mav = new ModelAndView("redirect:/pcilist");
	        return mav;
		}
	}
	@RequestMapping("/editpci")
	public ModelAndView editpci(int id) throws Exception {
		int Id = 0;
		try {
			Id = Integer.valueOf(id);
			System.out.println("id: " + Id);
			
		} catch(NumberFormatException e) {
			
		}
		if (Id == 0) {
			ModelAndView mav = new ModelAndView("redirect:/pcilist");
	        return mav;
		} else {
			System.out.println("add: " + Id);
			PCIeDAO cp = new PCIeDAO();
			PCIe pci = cp.get(Id);
			List<PCIeCategory> pciscategory = new PCIeCategoryDAO().list();
			ModelAndView mav = new ModelAndView("editpci");
			mav.addObject("pci", pci);
			mav.addObject("pciscategory", pciscategory);
	        return mav;
		}
	}
	
	@RequestMapping("/updatepciaction")
	public ModelAndView updatecpuaction(PCIe pci) throws Exception {
		int categoryId = 0;
		try {
			categoryId = pci.categoryId;
			System.out.println("id: " + categoryId);
			
		} catch(NumberFormatException e) {
			
		}
		if (categoryId == 0) {
			System.out.println("add: " + categoryId);
		} else {
			System.out.println("add: " + categoryId);
			PCIeDAO cp = new PCIeDAO();
			System.out.println("serial: " + pci.serialnumber);
			cp.update(pci);
		}
		ModelAndView mav = new ModelAndView("redirect:/pcilist");
        return mav;
	}
	
	@RequestMapping("/deletepci")
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
			PCIeDAO cp = new PCIeDAO();
			cp.delete(Id);
		}
		ModelAndView mav = new ModelAndView("redirect:/pcilist");
        return mav;
	}

	@RequestMapping(value="/getpci", method=RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, PCIeDetail> getcpu(@RequestBody String req) throws Exception {
		String location = null;
		String owner = null;
		String serialnumber = null;
		PCIeDAO cp = new PCIeDAO();
		PCIeDetail pcid = new PCIeDetail();
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
			pcid = cp.GetByLocation(owner, location);
		} else if (serialnumber != null){
			pcid = cp.GetBySerialnumber(serialnumber);
		} else {
			pcid = null;
		}
		Map<String, PCIeDetail> modelMap = new HashMap<String, PCIeDetail>(1);
	    
		modelMap.put("pci", pcid);
		return modelMap;
	}
}
