package com.icia.member;

import java.util.List;
import java.util.Map;

import com.icia.email.Email;


public interface IMemberDao {

	boolean insertMemberJoin(Member mb);

	String securityPwd(String mb_id);

	void insertConcern(Member mb);

	void insertAuthority(Member mb);

	List<String> selectConcern(Member mb);

	int selectIdclick(String id);

	List<String> idChecknumConfirm(Email em);

	List<String> pwChecknumConfirm(Email em);

	boolean PwUpdate(Member mb);
}

