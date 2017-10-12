package com.ibmMeeting.VO;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reservation implements Serializable{

	private int rsvNo;
	private String rsvComp;
	private String rsvConfNm;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rsvDate;
	private Time rsvTotalTime;
	private Time rsvStartTime;
	private Time rsvEndTime;
	private String rsvTitle;
	private String rsvMemNm;
	private String rsvMemPn;
	private String rsvMemEm;
	private String rsvDelPwd;
	private Date rsvRegDate;
	private String rsvColor;
	private String rsvConfirmState;
	private int rsvRepeatSeq;
	private String rsvEmailCheck;
	
	private Conference conference;

	public Reservation(int rsvNo, String rsvComp, String rsvConfNm,
			Date rsvDate, Time rsvTotalTime, Time rsvStartTime,
			Time rsvEndTime, String rsvTitle, String rsvMemNm, String rsvMemPn,
			String rsvMemEm, String rsvDelPwd, Date rsvRegDate,
			String rsvColor, String rsvConfirmState, int rsvRepeatSeq,
			String rsvEmailCheck, Conference conference) {
		super();
		this.rsvNo = rsvNo;
		this.rsvComp = rsvComp;
		this.rsvConfNm = rsvConfNm;
		this.rsvDate = rsvDate;
		this.rsvTotalTime = rsvTotalTime;
		this.rsvStartTime = rsvStartTime;
		this.rsvEndTime = rsvEndTime;
		this.rsvTitle = rsvTitle;
		this.rsvMemNm = rsvMemNm;
		this.rsvMemPn = rsvMemPn;
		this.rsvMemEm = rsvMemEm;
		this.rsvDelPwd = rsvDelPwd;
		this.rsvRegDate = rsvRegDate;
		this.rsvColor = rsvColor;
		this.rsvConfirmState = rsvConfirmState;
		this.rsvRepeatSeq = rsvRepeatSeq;
		this.rsvEmailCheck = rsvEmailCheck;
		this.conference = conference;
	}

	public Reservation(int rsvNo, String rsvComp, String rsvConfNm,
			Date rsvDate, Time rsvTotalTime, Time rsvStartTime,
			Time rsvEndTime, String rsvTitle, String rsvMemNm, String rsvMemPn,
			String rsvMemEm, String rsvDelPwd, Date rsvRegDate,
			String rsvColor, String rsvConfirmState, int rsvRepeatSeq,
			String rsvEmailCheck) {
		super();
		this.rsvNo = rsvNo;
		this.rsvComp = rsvComp;
		this.rsvConfNm = rsvConfNm;
		this.rsvDate = rsvDate;
		this.rsvTotalTime = rsvTotalTime;
		this.rsvStartTime = rsvStartTime;
		this.rsvEndTime = rsvEndTime;
		this.rsvTitle = rsvTitle;
		this.rsvMemNm = rsvMemNm;
		this.rsvMemPn = rsvMemPn;
		this.rsvMemEm = rsvMemEm;
		this.rsvDelPwd = rsvDelPwd;
		this.rsvRegDate = rsvRegDate;
		this.rsvColor = rsvColor;
		this.rsvConfirmState = rsvConfirmState;
		this.rsvRepeatSeq = rsvRepeatSeq;
		this.rsvEmailCheck = rsvEmailCheck;
	}

	public Reservation() {
		super();
	}

	public int getRsvNo() {
		return rsvNo;
	}

	public void setRsvNo(int rsvNo) {
		this.rsvNo = rsvNo;
	}

	public String getRsvComp() {
		return rsvComp;
	}

	public void setRsvComp(String rsvComp) {
		this.rsvComp = rsvComp;
	}

	public String getRsvConfNm() {
		return rsvConfNm;
	}

	public void setRsvConfNm(String rsvConfNm) {
		this.rsvConfNm = rsvConfNm;
	}

	public Date getRsvDate() {
		return rsvDate;
	}

	public void setRsvDate(Date rsvDate) {
		this.rsvDate = rsvDate;
	}

	public Time getRsvTotalTime() {
		return rsvTotalTime;
	}

	public void setRsvTotalTime(Time rsvTotalTime) {
		this.rsvTotalTime = rsvTotalTime;
	}

	public Time getRsvStartTime() {
		return rsvStartTime;
	}

	public void setRsvStartTime(Time rsvStartTime) {
		this.rsvStartTime = rsvStartTime;
	}

	public Time getRsvEndTime() {
		return rsvEndTime;
	}

	public void setRsvEndTime(Time rsvEndTime) {
		this.rsvEndTime = rsvEndTime;
	}

	public String getRsvTitle() {
		return rsvTitle;
	}

	public void setRsvTitle(String rsvTitle) {
		this.rsvTitle = rsvTitle;
	}

	public String getRsvMemNm() {
		return rsvMemNm;
	}

	public void setRsvMemNm(String rsvMemNm) {
		this.rsvMemNm = rsvMemNm;
	}

	public String getRsvMemPn() {
		return rsvMemPn;
	}

	public void setRsvMemPn(String rsvMemPn) {
		this.rsvMemPn = rsvMemPn;
	}

	public String getRsvMemEm() {
		return rsvMemEm;
	}

	public void setRsvMemEm(String rsvMemEm) {
		this.rsvMemEm = rsvMemEm;
	}

	public String getRsvDelPwd() {
		return rsvDelPwd;
	}

	public void setRsvDelPwd(String rsvDelPwd) {
		this.rsvDelPwd = rsvDelPwd;
	}

	public Date getRsvRegDate() {
		return rsvRegDate;
	}

	public void setRsvRegDate(Date rsvRegDate) {
		this.rsvRegDate = rsvRegDate;
	}

	public String getRsvColor() {
		return rsvColor;
	}

	public void setRsvColor(String rsvColor) {
		this.rsvColor = rsvColor;
	}

	public String getRsvConfirmState() {
		return rsvConfirmState;
	}

	public void setRsvConfirmState(String rsvConfirmState) {
		this.rsvConfirmState = rsvConfirmState;
	}

	public int getRsvRepeatSeq() {
		return rsvRepeatSeq;
	}

	public void setRsvRepeatSeq(int rsvRepeatSeq) {
		this.rsvRepeatSeq = rsvRepeatSeq;
	}

	public String getRsvEmailCheck() {
		return rsvEmailCheck;
	}

	public void setRsvEmailCheck(String rsvEmailCheck) {
		this.rsvEmailCheck = rsvEmailCheck;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conference == null) ? 0 : conference.hashCode());
		result = prime * result
				+ ((rsvColor == null) ? 0 : rsvColor.hashCode());
		result = prime * result + ((rsvComp == null) ? 0 : rsvComp.hashCode());
		result = prime * result
				+ ((rsvConfNm == null) ? 0 : rsvConfNm.hashCode());
		result = prime * result
				+ ((rsvConfirmState == null) ? 0 : rsvConfirmState.hashCode());
		result = prime * result + ((rsvDate == null) ? 0 : rsvDate.hashCode());
		result = prime * result
				+ ((rsvDelPwd == null) ? 0 : rsvDelPwd.hashCode());
		result = prime * result
				+ ((rsvEmailCheck == null) ? 0 : rsvEmailCheck.hashCode());
		result = prime * result
				+ ((rsvEndTime == null) ? 0 : rsvEndTime.hashCode());
		result = prime * result
				+ ((rsvMemEm == null) ? 0 : rsvMemEm.hashCode());
		result = prime * result
				+ ((rsvMemNm == null) ? 0 : rsvMemNm.hashCode());
		result = prime * result
				+ ((rsvMemPn == null) ? 0 : rsvMemPn.hashCode());
		result = prime * result + rsvNo;
		result = prime * result
				+ ((rsvRegDate == null) ? 0 : rsvRegDate.hashCode());
		result = prime * result + rsvRepeatSeq;
		result = prime * result
				+ ((rsvStartTime == null) ? 0 : rsvStartTime.hashCode());
		result = prime * result
				+ ((rsvTitle == null) ? 0 : rsvTitle.hashCode());
		result = prime * result
				+ ((rsvTotalTime == null) ? 0 : rsvTotalTime.hashCode());
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
		Reservation other = (Reservation) obj;
		if (conference == null) {
			if (other.conference != null)
				return false;
		} else if (!conference.equals(other.conference))
			return false;
		if (rsvColor == null) {
			if (other.rsvColor != null)
				return false;
		} else if (!rsvColor.equals(other.rsvColor))
			return false;
		if (rsvComp == null) {
			if (other.rsvComp != null)
				return false;
		} else if (!rsvComp.equals(other.rsvComp))
			return false;
		if (rsvConfNm == null) {
			if (other.rsvConfNm != null)
				return false;
		} else if (!rsvConfNm.equals(other.rsvConfNm))
			return false;
		if (rsvConfirmState == null) {
			if (other.rsvConfirmState != null)
				return false;
		} else if (!rsvConfirmState.equals(other.rsvConfirmState))
			return false;
		if (rsvDate == null) {
			if (other.rsvDate != null)
				return false;
		} else if (!rsvDate.equals(other.rsvDate))
			return false;
		if (rsvDelPwd == null) {
			if (other.rsvDelPwd != null)
				return false;
		} else if (!rsvDelPwd.equals(other.rsvDelPwd))
			return false;
		if (rsvEmailCheck == null) {
			if (other.rsvEmailCheck != null)
				return false;
		} else if (!rsvEmailCheck.equals(other.rsvEmailCheck))
			return false;
		if (rsvEndTime == null) {
			if (other.rsvEndTime != null)
				return false;
		} else if (!rsvEndTime.equals(other.rsvEndTime))
			return false;
		if (rsvMemEm == null) {
			if (other.rsvMemEm != null)
				return false;
		} else if (!rsvMemEm.equals(other.rsvMemEm))
			return false;
		if (rsvMemNm == null) {
			if (other.rsvMemNm != null)
				return false;
		} else if (!rsvMemNm.equals(other.rsvMemNm))
			return false;
		if (rsvMemPn == null) {
			if (other.rsvMemPn != null)
				return false;
		} else if (!rsvMemPn.equals(other.rsvMemPn))
			return false;
		if (rsvNo != other.rsvNo)
			return false;
		if (rsvRegDate == null) {
			if (other.rsvRegDate != null)
				return false;
		} else if (!rsvRegDate.equals(other.rsvRegDate))
			return false;
		if (rsvRepeatSeq != other.rsvRepeatSeq)
			return false;
		if (rsvStartTime == null) {
			if (other.rsvStartTime != null)
				return false;
		} else if (!rsvStartTime.equals(other.rsvStartTime))
			return false;
		if (rsvTitle == null) {
			if (other.rsvTitle != null)
				return false;
		} else if (!rsvTitle.equals(other.rsvTitle))
			return false;
		if (rsvTotalTime == null) {
			if (other.rsvTotalTime != null)
				return false;
		} else if (!rsvTotalTime.equals(other.rsvTotalTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reservation [rsvNo=" + rsvNo + ", rsvComp=" + rsvComp
				+ ", rsvConfNm=" + rsvConfNm + ", rsvDate=" + rsvDate
				+ ", rsvTotalTime=" + rsvTotalTime + ", rsvStartTime="
				+ rsvStartTime + ", rsvEndTime=" + rsvEndTime + ", rsvTitle="
				+ rsvTitle + ", rsvMemNm=" + rsvMemNm + ", rsvMemPn="
				+ rsvMemPn + ", rsvMemEm=" + rsvMemEm + ", rsvDelPwd="
				+ rsvDelPwd + ", rsvRegDate=" + rsvRegDate + ", rsvColor="
				+ rsvColor + ", rsvConfirmState=" + rsvConfirmState
				+ ", rsvRepeatSeq=" + rsvRepeatSeq + ", rsvEmailCheck="
				+ rsvEmailCheck + ", conference=" + conference + "]";
	}
	
}
