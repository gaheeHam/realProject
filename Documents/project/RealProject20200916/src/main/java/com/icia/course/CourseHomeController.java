package com.icia.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

@Controller
public class CourseHomeController {
	
	@Autowired
	private CourseMM com;

	
	@RequestMapping(value = "/prof/coursePage", method = RequestMethod.GET)
	public ModelAndView insertCoursePage(HttpServletRequest request, Integer co_lv, String co_idnum , Integer co_lcnum){
		ModelAndView mav=new ModelAndView();
		request.setAttribute("co_lv", co_lv);
		request.setAttribute("co_idnum", co_idnum);
		request.setAttribute("co_lcnum", co_lcnum);
		mav.setViewName("teacher/gahee/coursePage");	
		return mav;
	}
	
	@RequestMapping(value = "/prof/selectCoursePage", method = RequestMethod.GET)
	public ModelAndView selectCoursePage(HttpSession session, @RequestParam("co_idnum")String co_idnum, Integer co_num, RedirectAttributes attr){
		ModelAndView mav=new ModelAndView();
		System.out.println("idNum:               "+co_idnum);
		mav = com.selectCourse(session, co_num, co_idnum,attr);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/prof/insertcoursePage" , method =  RequestMethod.GET)
	public String insertcoursePage(HttpSession session, @RequestParam("co_idnum")String co_idnum, Integer co_num,HttpServletRequest request){
		System.out.println("여기 왜 못찾아?????????????????????????????");
		System.out.println("idNum:  0"+ co_idnum);
		System.out.println("Num:  0"+ co_num);
		request.setAttribute("co_idnum", co_idnum);
		request.setAttribute("co_num", co_num);
		return "teacher/gahee/insertcoursepage";
	}

	@RequestMapping(value = "/prof/insertCourse", method =RequestMethod.POST)
	public ModelAndView insertCourse(MultipartHttpServletRequest multi, HttpSession session, RedirectAttributes attr){
		ModelAndView mav = new ModelAndView();
		mav = com.insertCourse(multi, session, attr);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/prof/deletecourse")
	public ModelAndView deleteCourse(HttpSession session, CourseBean cb, CourseFilesBean cfb,RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		System.out.println("이거cb야: ㅍ     " + cb.getCo_idnum());
		System.out.println("이거cb야: ㅍ     " + cfb.getFl_sysname());
		mav = com.deleteCourse(session, cb, cfb,attr);
		return mav;
	}
	
	@RequestMapping(value = "/prof/updatecoursepage")
	public ModelAndView updatecoursePage(HttpSession session, CourseBean cb, CourseFilesBean cfb) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cb", new Gson().toJson(cb));
		mav.addObject("cfb",new Gson().toJson(cfb));
		mav.setViewName("teacher/gahee/updatecoursePage");
		return mav;
	}
	
	@RequestMapping(value = "/prof/updatecourse")
	public ModelAndView updateCourse(MultipartHttpServletRequest multi, HttpSession session, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		mav = com.updateCourse(multi, session,attr);
		return mav;
	}
	
	
	
	
}
