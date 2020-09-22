package com.icia.lectureQuiz;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LectureQuizController {
	@Autowired
	LectureQuizMM lm;
	
	
	ModelAndView mav;
	
	@RequestMapping(value = "/goLectureQuizPage", method = RequestMethod.GET)
	public String gosearchidspage() {
		System.out.println("강의별 퀴즈관리 페이지");
		
		return "teacher/LectureQuiz";
	}
	
	@RequestMapping(value = "/goLectureTestPage", method = RequestMethod.GET)
	public String goLectureTestPage() {
		System.out.println("강의별 과정 시험관리 페이지");
		
		return "teacher/LectureTest";
	}
	
	@RequestMapping(value = "/goLectureQuizShowPage", method = RequestMethod.GET)
	public ModelAndView goLectureQuizShowPage(HttpSession session, String clname, String name, String lcnum) {
		System.out.println("강의별퀴즈 상세페이지");
		System.out.println(clname);
		System.out.println(name);
		System.out.println(lcnum);
		
		String id= session.getAttribute("id").toString();
		mav = lm.goLectureQuizShowPage(clname,name,lcnum,id);
		
		return mav;
	}
	
	@RequestMapping(value = "/goLectureTestShowPage", method = RequestMethod.POST)
	public ModelAndView goLectureTestShowPage(HttpSession session, LectureQuiz lq) {
		System.out.println("강의별시험 상세페이지");
		System.out.println(lq.getCl_idnum());
		System.out.println(lq.getCo_name());
		System.out.println(lq.getCl_lcnum());
		System.out.println(lq.getCl_clname());
		String a= lq.getCl_clname().replaceAll(",", " ");
		String b= lq.getCo_name().replaceAll(",", " ");
		System.out.println(a);
		System.out.println(b);
		lq.setCl_clname(a);
		lq.setCo_name(b);
		
		lq.setCl_id(session.getAttribute("id").toString());
		mav = lm.goLectureTestShowPage(lq);
		
		return mav;
	}
	
	@RequestMapping(value = "/goStudentCommentPage", method = RequestMethod.POST)
	public ModelAndView goStudentCommentPage(LectureQuiz lq) {
		System.out.println("강의별 시험 코멘트작성 페이지");
		System.out.println(lq.getCo_name());
		System.out.println(lq.getCo_lv());
		System.out.println(lq.getGr_id1());
		System.out.println(lq.getGr_score());
		System.out.println(lq.getCl_lcnum());
		System.out.println(lq.getCl_idnum());
		String a= lq.getCo_name().replaceAll(",", " ");
		lq.setCo_name(a);
		System.out.println(lq.getCo_name());
		
		mav = lm.goStudentCommentPage(lq);
		
		return mav;
	}
	
	@RequestMapping(value = "/studentCommentInsert", method = RequestMethod.POST)
	public ModelAndView studentCommentInsert(LectureQuiz lq) {
		System.out.println("강의별 시험 코멘트 인설트");
		System.out.println(lq.getCo_lv());
		System.out.println(lq.getGr_id1());
		System.out.println(lq.getCl_lcnum());
		System.out.println(lq.getCl_idnum());
		System.out.println(lq.getGpa_feedback());
		
		String a= lq.getGpa_feedback().replaceAll("\r\n","<br>");
		System.out.println(a);
		String b= a.replaceAll(" ","&nbsp;");
		System.out.println(b);
		lq.setGpa_feedback(b);
		
		mav = lm.studentCommentInsert(lq);
		
		return mav;
	}
}
