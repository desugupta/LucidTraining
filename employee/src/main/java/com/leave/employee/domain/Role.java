package com.leave.employee.domain;

import org.springframework.data.annotation.Id;

public class Role extends BaseDomain{
	
	@Id
	private String id;
	private String roleId;
	private String roleName;
	private String roleStatus;


}
