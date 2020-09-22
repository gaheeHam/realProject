package com.icia.homework;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.icia.classup.ClassUpBean;

@Controller
public class HomeworkController {	
	@Autowired
	HomeworkMM hm;
	
	@RequestMapping(value = "/prof/inserthomeworkpage", method = RequestMethod.POST)
	public ModelAndView insertHomeworkPage(HomeworkBean hb) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("hb", new Gson().toJson(hb));
		mav.setViewName("insertHomeworkPage");
		return mav;
		
	}
	@RequestMapping(value = "/prof/inserthomework", method = RequestMethod.POST)
	public ModelAndView insertHomework(HttpSession session, MultipartHttpServletRequest multi,RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		mav = hm.insertHomework(session, multi,attr);
		return mav;
		
	}
	
	@RequestMapping(value = "/prof/homeworkFiledown", method = RequestMethod.GET)
	public void homeworkFiledown(String sysFileName, HttpServletResponse response) {
	 hm.homeworkFiledown(sysFileName,response);

	}
	@RequestMapping(value = "/prof/selectHwResultPage", method = RequestMethod.GET)
	public ModelAndView selectHwResult(HomeworkBean hwb) {
		System.out.println("just do it plz");
		ModelAndView mav = new ModelAndView();
		mav = hm.selectHwResult(hwb);
		return mav;
		
	}

	
	
	
}
