package com.icia.planClass;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.classup.ClassUpBean;

@Component
public class PlanClassMM {
	@Autowired 
	private IPlanClassDao pcd;

	
	public ModelAndView insertplanclass(ClassUpBean cb, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mav =new ModelAndView();
		System.out.println("idnum: "+cb.getPc_idnum());
		System.out.println("lv: "+cb.getPc_lv());
		System.out.println("title: "+cb.getPc_title());
		System.out.println("content: "+ cb.getPc_cont());
		
		boolean result = pcd.insertplanclass(cb);
		if(result) {
			System.out.println("planclassOOOOOOOOOOOOOO");
			request.setAttribute("insertplanclass", "강의계획서 등록 성공");
			attr.addFlashAttribute("goAjax","go");
			mav.setViewName("redirect:/goTeacherLoginFrm");
		}else {
			System.out.println("planclassXXXXXXXXXXXXx");
			request.setAttribute("insertplanclass", "강의계획서 등록 실패");
			mav.setViewName("teacher/gahee/insertplanclasspage");
		}
		
		return mav;
	}


	public ModelAndView updatePlanClassPage(String obj) {
//		JsonParser parser = new JsonParser();
//		Object obj2 = parser.parse( obj ); 
//		JsonObject jsonObj = (JsonObject) obj2;
//		System.out.println("name: "+jsonObj.get("clname").toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("updatePlanClass",obj);
		mav.setViewName("teacher/gahee/updatePlanClassPage");
		return mav;
	}


	public ModelAndView updateplanclass(ClassUpBean cub, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		boolean result = pcd.updateplanclass(cub);
		System.out.println("result:    "+result);
		if(result) {
			mav.addObject("falseMsg","수정성공");
			attr.addFlashAttribute("goAjax","go");
			mav.setViewName("redirect:/goTeacherLoginFrm");
		}else{
			mav.addObject("falseMsg","수정실패");
			mav.setViewName("./");
		}
		return mav;
	}



	




}
