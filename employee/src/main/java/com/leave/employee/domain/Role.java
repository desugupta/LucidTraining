package com.leave.employee.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role  {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Integer versionId;
	private String roleId;
	@NotEmpty(message = "should not be empty or null")
	private String roleName;
	@NotEmpty(message = "should not be empty or null")
	private String roleStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", versionId=" + versionId + ", roleId=" + roleId + ", roleName=" + roleName
				+ ", roleStatus=" + roleStatus + "]";
	}

}
