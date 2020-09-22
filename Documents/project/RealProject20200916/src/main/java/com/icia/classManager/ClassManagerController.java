package com.icia.classManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.icia.course.CourseBean;


@Controller
public class ClassManagerController {
	
	@Autowired
	private ClassManagerMM cmm;

	
	@RequestMapping(value = "/prof/classuploadmanagerpage", method = RequestMethod.GET)
	public ModelAndView classuploadManagerPage(HttpSession session) {
		ModelAndView mav=new ModelAndView();
			mav.addObject("manager_classList" ,new Gson().toJson(cmm.selectClass(session)));
			mav.setViewName("teacher/gahee/classUploadManagerPage");
		return mav;
	}
	
	@RequestMapping(value = "/prof/selectmanagercoursehomeworkpage", method = RequestMethod.POST)
	public ModelAndView selectManagerCourseHomeworkPage(HttpSession session, CourseBean cb) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("courseList", new Gson().toJson(cmm.selectCourse(cb)));
		mav.setViewName("teacher/gahee/selectManagerCourseHomeworkPage");
		return mav;
	}
	
	


	

	
	
	
	
}
