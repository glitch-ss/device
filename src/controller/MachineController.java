package controller;
import java.util.List;
import java.util.Map;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.PCIeCategoryDAO;
import pojo.PCIeCategory;

@Controller
public class MachineController {
	@RequestMapping("/machine")
	public ModelAndView machine(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String machine = null;
		try {
			machine = String.valueOf(request.getParameter("machine"));
		} catch(NumberFormatException e) {
			
		}
		//machine = "x5-2l-sqa-1";
		String path = "/QA/SQA/config/machine_config/";
		if (machine == null) {
			File dir = new File(path);
			String[] fileNames = dir.list();
			ModelAndView mav = new ModelAndView("machine");
			mav.addObject("machines", fileNames);
			return mav;
		}else {
			String filepath = path + machine;
			File file = new File(filepath);
			String label = machine.substring(0, 5);
			String page_file = "machine-" + label;
			ModelAndView mav = new ModelAndView(page_file);
			PCIeCategoryDAO pd = new PCIeCategoryDAO();
			String file1 = FileUtils.readFileToString(file);
			JSONObject machineJson = JSON.parseObject(file1);
			JSONObject pci = machineJson.getJSONObject("pcie");
			for (Map.Entry entry : pci.entrySet()) {
				String slot = String.valueOf(entry.getKey());
				JSONObject device =  (JSONObject) JSONObject.toJSON(entry.getValue());
				System.out.println("test");
				System.out.println(device);
				String partnumber = String.valueOf(device.get("partnumber"));
				PCIeCategory pciitem = pd.get(partnumber);
				System.out.println(pciitem);
				device.put("nickname", pciitem.nickname);
			}
			mav.addObject("name", machine);
			mav.addObject("machine", machineJson);
			return mav;
		}
	}
	
	@RequestMapping("/editmachine")
	public ModelAndView editmachine(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String machine = null;
		String config = null;
		JSONObject configJson = null;
		String tempValue = null;
		String key = null;
		JSONObject machineJson = null;
		try {
			machine = String.valueOf(request.getParameter("machine"));
			config = String.valueOf(request.getParameter("config"));
			configJson = JSONObject.parseObject(config);
		} catch(NumberFormatException e) {
			
		}
		//machine = "x5-2l-sqa-1";
		String path = "/QA/SQA/config/machine_config/";
		String filepath = path + machine;
		File file = new File(filepath);
		File dir = new File(path);
		String[] fileNames = dir.list();
		if (machine == null || config == null) {
			ModelAndView mav = new ModelAndView("machine");
			mav.addObject("machines", fileNames);
			return mav;
		}else {
			
			if (file.exists()) {
                file.delete();
            }else {;
            	ModelAndView mav = new ModelAndView("machine");
    			mav.addObject("machines", fileNames);
    			return mav;
            }
			file.createNewFile();
			String label = machine.substring(0, 5);
			String page_file = "machine-" + label;
			ModelAndView mav = new ModelAndView(page_file);
			PCIeCategoryDAO pd = new PCIeCategoryDAO();
			String file1 = FileUtils.readFileToString(file);
			machineJson = JSON.parseObject(file1);
			JSONObject pci = machineJson.getJSONObject("pcie");
			for (Map.Entry entry : pci.entrySet()) {
				String slot = String.valueOf(entry.getKey());
				JSONObject device =  (JSONObject) JSONObject.toJSON(entry.getValue());
				System.out.println("test");
				System.out.println(device);
				String partnumber = String.valueOf(device.get("partnumber"));
				PCIeCategory pciitem = pd.get(partnumber);
				System.out.println(pciitem);
				device.put("nickname", pciitem.nickname);
			}
		}
		for (Map.Entry entry : configJson.entrySet()) {
			key = String.valueOf(entry.getKey());
			tempValue = String.valueOf(entry.getValue());
			machineJson.put(key, tempValue);
		}
		machine = JSON.toJSONString(machineJson);
		FileUtils.writeStringToFile(file, machine, "UTF-8");
		ModelAndView mav = new ModelAndView("machine");
		mav.addObject("machines", fileNames);
		return mav;
	}
}
