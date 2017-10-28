package com.ibmMeeting.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ibmMeeting.VO.Member;
import com.ibmMeeting.VO.Reservation;

@Mapper
public interface MemberDao {

	List<Member> getMemInfoByPn(String memPn);
	
	void registMember(Member member);
	
	int checkExistMemInfo(String memPn);
	
	void modifyMember(Member member);
}
