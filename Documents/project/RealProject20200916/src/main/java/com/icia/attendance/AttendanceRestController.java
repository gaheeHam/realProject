package com.icia.attendance;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@org.springframework.web.bind.annotation.RestController
public class AttendanceRestController {
	@Autowired
	private AttendanceMM atm;
	
	
	@RequestMapping(value = "/prof/rest/countAt")
	public Integer countAt(@RequestParam("at_id")String at_id,@RequestParam("at_idnum")String at_idnum,@RequestParam("at_lv")String at_lv) {
		Integer countAt = atm.countAt(at_id ,at_idnum, at_lv);
		return countAt;
		
	}

	
		
		
		

}
