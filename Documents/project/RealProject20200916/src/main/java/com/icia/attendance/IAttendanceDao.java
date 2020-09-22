package com.icia.attendance;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAttendanceDao {
	
	
	
	
	@Select("SELECT COUNT (*) FROM AT WHERE AT_ATMK='1' AND AT_ID=#{at_id} AND AT_LV=#{at_lv} AND AT_IDNUM=#{at_idnum}")
	Integer countAt(@Param("at_id")String at_id, @Param("at_idnum") String at_idnum, @Param("at_lv") String at_lv);



}
