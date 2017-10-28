package com.ibmMeeting.Validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ibmMeeting.VO.Reservation;

public class RegisterReservationValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Reservation.class);
	}

	public void validate(Object target, Errors errors) {

		/**에러에 따라 출력할 text적기*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rsvTitle", "rsvTitle", "제목을 입력하세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rsvDate", "rsvDate", "날짜를 선택하세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rsvDelPwd", "rsvDelPwd", "비밀번호를 입력하세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rsvMemPn", "rsvMemPn", "전화번호를 입력하세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rsvMemNm", "rsvMemNm", "이름을 입력하세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rsvMemEm", "rsvMemEm", "이메일을 입력하세요");
		
		
	}	
}
