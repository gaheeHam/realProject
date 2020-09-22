package com.icia.classup;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class ClassUpMM {
	@Autowired 
	private IClassUpDao cd;

	public ModelAndView insertclassupload(ClassUpBean cb, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		System.out.println("cb.getCl_id(): "+cb.getCl_id());
		System.out.println("cb.getCl_clname(): "+cb.getCl_clname());
		String [] fnsp = cb.getCl_fnday().split("-");
		String fnSpSum ="";
		for(int i=0; i<fnsp.length; i++) {
			fnSpSum += fnsp[i];
		}
		
		cb.setCl_idnum(""+cb.getCl_id()+cb.getCl_cc()+cb.getCl_lcnum()+fnSpSum);
		System.out.println("일련번호 입니다:  "+ cb.getCl_idnum());
		boolean result = cd.insertclassupload(cb);
		if(result) {
			System.out.println("oooooooo");
			mav.addObject("insertclassupload","강의등록 성공");
			attr.addFlashAttribute("goAjax","go");
			 mav.setViewName("redirect:/goTeacherLoginFrm");
			
		}else {
			System.out.println("xxxxxxxxxxxx");
			mav.addObject("insertclassupload","강의등록 실패");
			mav.setViewName("teacher/gahee/classUploadPage");
		}
		return mav;
	}

	public List<ClassUpBean> classList(HttpSession session) {
		String id = session.getAttribute("id").toString();
		System.out.println("hamq1"+id);
		List <ClassUpBean> myClassList = cd.classList(id);
		System.out.println("LISTFILE "+myClassList.get(0).getPc_title());
		return myClassList;
		
	}

	public int updateGoAdmin(String idnum) {
		int result = cd.updateGoAdmin(idnum);
		return result;
	}

	public String selectCt(String idnum) {
		String selectCt = cd.selectCt(idnum);
		System.out.println("CL_IDNUM=#{idnum}:         "+selectCt);
		return selectCt;
	}

	public List<ClassUpBean> getClassList(String cl_ct, HttpSession session) {
		String cl_id = session.getAttribute("id").toString();
		//System.out.println("cl_id: "+cl_id);
		List <ClassUpBean> classListVal = cd.getClassList(cl_id, cl_ct);
		System.out.println("종류별강의 "+classListVal.toString());
		return classListVal;
		
	}

}
