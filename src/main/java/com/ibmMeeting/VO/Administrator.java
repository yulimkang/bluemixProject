package com.ibmMeeting.VO;

import java.io.Serializable;

public class Administrator implements Serializable{

	private int adminNo;
	private String adminId;
	private String adminPwd;
	private String adminComp;
	
	public Administrator(int adminNo, String adminId, String adminPwd,
			String adminComp) {
		super();
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminComp = adminComp;
	}

	public Administrator() {
		super();
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminComp() {
		return adminComp;
	}

	public void setAdminComp(String adminComp) {
		this.adminComp = adminComp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adminComp == null) ? 0 : adminComp.hashCode());
		result = prime * result + ((adminId == null) ? 0 : adminId.hashCode());
		result = prime * result + adminNo;
		result = prime * result
				+ ((adminPwd == null) ? 0 : adminPwd.hashCode());
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
		Administrator other = (Administrator) obj;
		if (adminComp == null) {
			if (other.adminComp != null)
				return false;
		} else if (!adminComp.equals(other.adminComp))
			return false;
		if (adminId == null) {
			if (other.adminId != null)
				return false;
		} else if (!adminId.equals(other.adminId))
			return false;
		if (adminNo != other.adminNo)
			return false;
		if (adminPwd == null) {
			if (other.adminPwd != null)
				return false;
		} else if (!adminPwd.equals(other.adminPwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrator [adminNo=" + adminNo + ", adminId=" + adminId
				+ ", adminPwd=" + adminPwd + ", adminComp=" + adminComp + "]";
	}
	
}
