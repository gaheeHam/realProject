package com.icia.planClass;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.icia.classup.ClassUpBean;

@Controller
public class PlanClassController {

	
	@Autowired
	PlanClassMM pcm;
	
	@RequestMapping(value = "/prof/insertplanclasspage", method = RequestMethod.GET)
	public String insertlecturepage(HttpSession session, String idNum, String cl_name, String cl_lcnum, String cl_lv,HttpServletRequest request) {
		request.setAttribute("cl_name", cl_name);
		request.setAttribute("idNum", idNum);
		request.setAttribute("cl_lcnum", cl_lcnum);
		request.setAttribute("cl_lv", cl_lv);
		return "insertplanclasspage";
		
	}
	
	@RequestMapping(value = "/prof/insertplanclass", method = RequestMethod.POST)
	public ModelAndView insertplanclass(HttpSession session, HttpServletRequest request,ClassUpBean cb,RedirectAttributes attr) {
		ModelAndView mav =null;
		mav=pcm.insertplanclass(cb,request,attr);
		return mav;
		
	}
	
	@RequestMapping(value = "/prof/updateplanclasspage")
	public ModelAndView updateplanclasspage(String obj) {
		System.out.println(obj);
		ModelAndView mav = new ModelAndView();
		mav.addObject("upPlanClass",obj);
		mav.setViewName("teacher/gahee/updatePlanClassPage");
		
//		JsonParser parser = new JsonParser();
//		Object obj2 = parser.parse( obj ); 
//		JsonObject jsonObj = (JsonObject) obj2;
//		System.out.println("name: "+jsonObj.get("clname").toString());
		
		return mav;	
	} 
	@RequestMapping(value = "/prof/updateplanclass")
	public ModelAndView updateplanclass(ClassUpBean cub , RedirectAttributes attr) {	
		ModelAndView mav = pcm.updateplanclass(cub,attr);
		return mav;	
	} 
	
	
	
	
	
	
	
}
