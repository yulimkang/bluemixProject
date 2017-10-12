package com.ibmMeeting.VO;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable{

	private int memNo;
	private String memName;
	private String memPn;
	private String memEm;
	private String memComp;
	private Date memRegDate;
	private String memState;
	private int countWarn;
	
	public Member(int memNo, String memName, String memPn, String memEm,
			String memComp, Date memRegDate, String memState, int countWarn) {
		super();
		this.memNo = memNo;
		this.memName = memName;
		this.memPn = memPn;
		this.memEm = memEm;
		this.memComp = memComp;
		this.memRegDate = memRegDate;
		this.memState = memState;
		this.countWarn = countWarn;
	}

	public Member() {
		super();
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPn() {
		return memPn;
	}

	public void setMemPn(String memPn) {
		this.memPn = memPn;
	}

	public String getMemEm() {
		return memEm;
	}

	public void setMemEm(String memEm) {
		this.memEm = memEm;
	}

	public String getMemComp() {
		return memComp;
	}

	public void setMemComp(String memComp) {
		this.memComp = memComp;
	}

	public Date getMemRegDate() {
		return memRegDate;
	}

	public void setMemRegDate(Date memRegDate) {
		this.memRegDate = memRegDate;
	}

	public String getMemState() {
		return memState;
	}

	public void setMemState(String memState) {
		this.memState = memState;
	}

	public int getCountWarn() {
		return countWarn;
	}

	public void setCountWarn(int countWarn) {
		this.countWarn = countWarn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countWarn;
		result = prime * result + ((memComp == null) ? 0 : memComp.hashCode());
		result = prime * result + ((memEm == null) ? 0 : memEm.hashCode());
		result = prime * result + ((memName == null) ? 0 : memName.hashCode());
		result = prime * result + memNo;
		result = prime * result + ((memPn == null) ? 0 : memPn.hashCode());
		result = prime * result
				+ ((memRegDate == null) ? 0 : memRegDate.hashCode());
		result = prime * result
				+ ((memState == null) ? 0 : memState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (countWarn != other.countWarn)
			return false;
		if (memComp == null) {
			if (other.memComp != null)
				return false;
		} else if (!memComp.equals(other.memComp))
			return false;
		if (memEm == null) {
			if (other.memEm != null)
				return false;
		} else if (!memEm.equals(other.memEm))
			return false;
		if (memName == null) {
			if (other.memName != null)
				return false;
		} else if (!memName.equals(other.memName))
			return false;
		if (memNo != other.memNo)
			return false;
		if (memPn == null) {
			if (other.memPn != null)
				return false;
		} else if (!memPn.equals(other.memPn))
			return false;
		if (memRegDate == null) {
			if (other.memRegDate != null)
				return false;
		} else if (!memRegDate.equals(other.memRegDate))
			return false;
		if (memState == null) {
			if (other.memState != null)
				return false;
		} else if (!memState.equals(other.memState))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memName=" + memName + ", memPn="
				+ memPn + ", memEm=" + memEm + ", memComp=" + memComp
				+ ", memRegDate=" + memRegDate + ", memState=" + memState
				+ ", countWarn=" + countWarn + "]";
	}
	
}
