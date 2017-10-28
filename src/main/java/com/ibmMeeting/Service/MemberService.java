package com.ibmMeeting.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.MemberDao;
import com.ibmMeeting.VO.Member;
import com.ibmMeeting.VO.Reservation;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;
	
	/**
	 * 작성자 : 박세연
	 * 전화번호 이용해 회원 정보 가져오기
	 * @param memPn
	 * @return
	 */
	public List<Member> getMemInfoByPn(String memPn){
		
		List<Member> list = memberDao.getMemInfoByPn(memPn);
	
		return list;
	}
	
	/**
	 * 작성자 : 박세연
	 * 회원 등록하기
	 * @param reservation
	 */
	public void registMember(Reservation reservation){
		
		Member member =  new Member();
		Date date = new Date();
		
		member.setMemName(reservation.getRsvMemNm());
		member.setMemPn(reservation.getRsvMemPn());
		member.setMemEm(reservation.getRsvMemEm());
		member.setMemComp(ConstantCode.COMPANY_NAME);
		member.setMemRegDate(date);
		member.setMemState("정상");
		member.setCountWarn(ConstantCode.ZERO);
		member.setMemBanday("N");
		
		memberDao.registMember(member);
		
	}
	
	/**
	 * 작성자 : 박세연
	 * 등록된 회원인지 확인
	 * @param memPn
	 * @return
	 */
	public int checkExistMemInfo(String memPn){
		
		int exist = memberDao.checkExistMemInfo(memPn);
		
		return exist;
	}
	
	/**
	 * 작성자 : 박세연
	 * 회원정보 수정하기
	 * @param reservation
	 */
	public void modifyMember(Reservation reservation){
	
		Member member =  new Member();
		
		member.setMemName(reservation.getRsvMemNm());
		member.setMemPn(reservation.getRsvMemPn());
		member.setMemEm(reservation.getRsvMemEm());
		
		memberDao.modifyMember(member);
	}
}
