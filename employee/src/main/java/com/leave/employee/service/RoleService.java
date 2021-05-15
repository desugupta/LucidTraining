package com.leave.employee.service;

import java.util.List;

import com.leave.employee.domain.Role;

public interface RoleService {

	public Role saveRole(Role role);

	public Role updateRole(Role role);

	public Role getRole(String roleId);

	public List<Role> getAllRoles();

}
