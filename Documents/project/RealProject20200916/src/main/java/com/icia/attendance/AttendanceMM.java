package com.icia.attendance;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class AttendanceMM {
	@Autowired 
	private IAttendanceDao atd;

	public Integer countAt(String at_id, String at_idnum, String at_lv) {
		Integer countAt = atd.countAt(at_id,at_idnum,at_lv);
		return countAt;
	}




}
