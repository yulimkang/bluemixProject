package com.ibmMeeting.VO;

import java.io.Serializable;

public class Conference implements Serializable{

	private int confNo;
	private String confNm;
	private String confComp;
	private int confOrder;
	
	public Conference(int confNo, String confNm, String confComp, int confOrder) {
		super();
		this.confNo = confNo;
		this.confNm = confNm;
		this.confComp = confComp;
		this.confOrder = confOrder;
	}

	public Conference() {
		super();
	}

	public int getConfNo() {
		return confNo;
	}

	public void setConfNo(int confNo) {
		this.confNo = confNo;
	}

	public String getConfNm() {
		return confNm;
	}

	public void setConfNm(String confNm) {
		this.confNm = confNm;
	}

	public String getConfComp() {
		return confComp;
	}

	public void setConfComp(String confComp) {
		this.confComp = confComp;
	}

	public int getConfOrder() {
		return confOrder;
	}

	public void setConfOrder(int confOrder) {
		this.confOrder = confOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confComp == null) ? 0 : confComp.hashCode());
		result = prime * result + ((confNm == null) ? 0 : confNm.hashCode());
		result = prime * result + confNo;
		result = prime * result + confOrder;
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
		Conference other = (Conference) obj;
		if (confComp == null) {
			if (other.confComp != null)
				return false;
		} else if (!confComp.equals(other.confComp))
			return false;
		if (confNm == null) {
			if (other.confNm != null)
				return false;
		} else if (!confNm.equals(other.confNm))
			return false;
		if (confNo != other.confNo)
			return false;
		if (confOrder != other.confOrder)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conference [confNo=" + confNo + ", confNm=" + confNm
				+ ", confComp=" + confComp + ", confOrder=" + confOrder + "]";
	}
	
}
