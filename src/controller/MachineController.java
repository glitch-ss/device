package controller;
import java.util.List;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
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
		ModelAndView mav = new ModelAndView("machine");
		String machine = null;
		
		try {
			machine = String.valueOf(request.getParameter("machine"));
		} catch(NumberFormatException e) {
			
		}
		File file = new File(machine);

		String file1 = FileUtils.readFileToString(file);

		
		mav.addObject("cpus", cpus);
		return mav;
	}
}
