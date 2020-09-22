package com.icia.classup;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;


@Alias("classup")
@Data
@Accessors(chain = true)
public class ClassUpBean {
	
	String cl_idnum;
	int cl_lv;
	String cl_clname;
	String cl_id;
	String cl_stday;
	String cl_fnday;
	int cl_pt;
	int cl_lcnum;
	String cl_cc;
	String cl_ct;
	
	
	String pc_idnum;
	int pc_lv;
	String pc_title;
	String pc_cont;
	
	
}
