package controller;
import java.util.List;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MachineController {
	@RequestMapping("/machine")
	public ModelAndView machine(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String machine = null;
		try {
			machine = String.valueOf(request.getParameter("machine"));
		} catch(NumberFormatException e) {
			
		}
		machine = "x5-2l-sqa-1";
		String label = machine.substring(0, 5);
		String page_file = "machine-" + label;
		ModelAndView mav = new ModelAndView(page_file);
		
		
		
		String path = "/QA/SQA/config/machine_config/" + machine;
		File file = new File(path);

		String file1 = FileUtils.readFileToString(file);
		JSONObject jsonobject = JSON.parseObject(file1);
		System.out.println(jsonobject);		
		mav.addObject("machine", jsonobject);
		return mav;
	}
}
