package com.ibmMeeting.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibmMeeting.Service.MemberService;
import com.ibmMeeting.VO.Member;

@Controller
@RequestMapping("/Member")
public class MemberController {

	@Autowired
	MemberService memberService;

	/**
	 * 작성자 : 박세연
	 * 전화번호 입력시 회원 이름과 이메일 자동입력을 위한 data 가져오기
	 * @param memPn
	 * @return
	 */
	@RequestMapping("GetMemInfoByPn")
	@ResponseBody
	public List<Member> getMemInfoByPn(@RequestParam String memPn){
		
		List<Member> list = memberService.getMemInfoByPn(memPn);
		
		return list;
	}
	
	
}
