package com.icia.classup;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

public interface IClassUpDao {

	boolean insertclassupload(ClassUpBean cb);

	List<ClassUpBean> classList(String id);

	
	
	@Update("UPDATE CL SET CL_CT='1' WHERE CL_IDNUM=#{idnum}")
	int updateGoAdmin(String idnum);

	
	
	@Select("SELECT CL_CT FROM CL WHERE CL_IDNUM=#{idnum}")
	String selectCt(String idnum);

	
	@Select("SELECT * FROM CLPC WHERE CL_ID=#{cl_id} AND CL_CT=#{cl_ct}")
	List<ClassUpBean> getClassList(@Param("cl_id")String cl_id, @Param("cl_ct")String cl_ct);


}
