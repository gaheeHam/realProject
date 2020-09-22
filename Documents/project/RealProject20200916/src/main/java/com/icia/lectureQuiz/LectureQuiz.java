package com.icia.lectureQuiz;

import java.util.List;

import org.apache.ibatis.type.Alias;


import lombok.Data;
import lombok.experimental.Accessors;

@Alias("lecturequiz")
@Data
@Accessors(chain = true)
public class LectureQuiz {
	String cl_idnum;
	String cl_clname;
	String co_name;
	Integer cl_lcnum;
	Integer co_lv;
	String gr_id1;
	int gr_id;
	int gr_score;
	String cl_id;
	String gpa_feedback;
}
