package com.icia.course;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.icia.basic.userClass.FileManager;
import com.icia.classup.ClassUpBean;

@Component
public class CourseMM {
	@Autowired 
	private ICourseDao cod;
	@Autowired
	FileManager fm;


	public List<CourseBean> courseList(HttpSession session, String co_idnum) {
		List <CourseBean> myCourseList = cod.courseList(co_idnum);
		
		return myCourseList;
	}

	public ModelAndView selectCourse(HttpSession session, Integer co_num, String co_idnum, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		String num =co_num.toString();
		
		List<CourseBean> myCoList = cod.myCoList(num,co_idnum);
		System.out.println("co_cont::   "+myCoList.get(0).co_cont);
		System.out.println(myCoList.get(0).getFbList().size());
		if(myCoList.size()!=0) {
			mav.addObject("myCoList", new Gson().toJson(myCoList));
			mav.setViewName("teacher/gahee/selectcoursepage");
		}else {
			attr.addFlashAttribute("falseMsg","강의를 불러오지 못했습니다.");
			 mav.setViewName("redirect:/goTeacherLoginFrm");
		}
		return mav;
	}
	
	@Transactional
	public ModelAndView insertCourse(MultipartHttpServletRequest multi, HttpSession session, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		String idnum = multi.getParameter("co_idnum");
		List<ClassUpBean> lv = cod.selectLv(idnum);
		if(lv.size()!=0) {
		CourseBean cb =new CourseBean();
		cb.setCo_lv(lv.get(0).getCl_lv());
		cb.setCo_cont(multi.getParameter("co_cont"));
		cb.setCo_idnum(idnum);
		cb.setCo_name(multi.getParameter("co_name"));
		cb.setCo_num(multi.getParameter("co_num"));
		Boolean coResult = cod.insertCourse(cb);
		System.out.println("result:   "+coResult);
		if(coResult) {
			CourseFilesBean cfb =new CourseFilesBean();
			cfb.setFl_num(Integer.parseInt(multi.getParameter("fl_num")));
			cfb.setFl_subvd("course");
			cfb.setFl_id(session.getAttribute("mb_id").toString());
			cfb.setFl_idnum(idnum);
			cfb.setFl_lv(lv.get(0).getCl_lv());
			boolean fiResult = fm.fileUp(multi, cfb);
			if(fiResult) {
				System.out.println("성공::   "+fiResult);
				attr.addFlashAttribute("goAjax","go");
				mav.setViewName("redirect:/goTeacherLoginFrm");
			}
			System.out.println("file에러");
		}
		System.out.println("co에러");
		}
		System.out.println("sel에러");

		return mav;
	}

	@Transactional
	public ModelAndView deleteCourse(HttpSession session, CourseBean cb, CourseFilesBean cfb, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		cfb.setFl_idnum(cb.getCo_idnum());
		cfb.setFl_lv(cb.getCo_lv());
		cfb.setFl_num(Integer.parseInt(cb.getCo_num()));
		List<CourseFilesBean>delFileList = cod.selectDelFile(cfb);
		
		boolean delFilesResult = cod.deleteCourseFiles(cfb);
		
		if(delFilesResult) {
			System.out.println("파일삭제성공");
			boolean delCoResult = cod.deleteCourse(cb);
			if(delCoResult) {
				fm.delete(delFileList);
				System.out.println("강좌삭제 성공");
				mav.addObject("falseMsg","강좌를 삭제하였습니다.");
				attr.addFlashAttribute("goAjax","go");
				 mav.setViewName("redirect:/goTeacherLoginFrm");
			}
		}else {
			System.out.println("트렌제션");
			mav.addObject("falseMsg","강좌삭제에 실패");
			mav.setViewName("new");
		}
		
		 
		
		return mav;
	}

	@Transactional
	public ModelAndView updateCourse(MultipartHttpServletRequest multi, HttpSession session, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();

		CourseBean cb = new CourseBean();
		cb.setCo_idnum(multi.getParameter("co_idnum"));
		cb.setCo_lv(Integer.parseInt(multi.getParameter("co_lv")));
		cb.setCo_num(multi.getParameter("co_num"));
		cb.setCo_cont(multi.getParameter("co_cont"));
		cb.setCo_name(multi.getParameter("co_name"));
		boolean upCo = cod.updateCourse(cb);
		if(upCo) {
			CourseFilesBean cfb = new CourseFilesBean();
			cfb.setFl_idnum(multi.getParameter("co_idnum"));
			cfb.setFl_lv(Integer.parseInt(multi.getParameter("co_lv")));
			cfb.setFl_num(Integer.parseInt(multi.getParameter("co_num")));
			boolean fiResult = fm.fileUpdate(multi, cfb);
			if(fiResult) {
				mav.addObject("falseMsg","수정성공");
				attr.addFlashAttribute("goAjax","go");
				 mav.setViewName("redirect:/goTeacherLoginFrm");
			}
			
		}else {
			mav.addObject("falseMsg","수정실패");
			mav.setViewName("new");
		}
		
		return mav;
		


	}


	


}
