package com.icia.attendance;



import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class AttendanceBean {
	String at_atmk;//1
	Integer at_num;
	String at_id; 
	String at_idnum; 
	Integer at_lv;   
	
	
}
