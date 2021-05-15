package com.leave.employee.impl;

import java.util.List;
import java.util.Random;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.employee.domain.Role;
import com.leave.employee.repository.RoleRepository;
import com.leave.employee.service.RoleService;

@Service
public class Rolesimpl implements RoleService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Role saveRole(Role role) {
		try {
			Role roleObj = new Role();
			String roleId = Integer.toString(generateRandomnumber());
			if (role.getRoleName() != null && !role.getRoleName().isEmpty()) {
				roleObj.setRoleName(role.getRoleName());
			}
			if (role.getRoleStatus() != null && !role.getRoleStatus().isEmpty()) {
				roleObj.setRoleStatus(role.getRoleStatus());
			}
			roleObj.setRoleId(roleId);
			roleObj = roleRepository.save(roleObj);
			roleObj = modelMapper.map(roleObj, Role.class);
			return roleObj;
		} catch (Exception e) {
			logger.error("Error occurred while saving the role:", e);
			throw e;
		}
	}

	public int generateRandomnumber() {
		int threeDigitNumber = new Random().nextInt(1000);
		return threeDigitNumber;
	}

	@Override
	public Role updateRole(Role role) {
		try {
			Role roleObj = roleRepository.findByRoleId(role.getRoleId());
			if (roleObj.getRoleId() != null && !roleObj.getRoleId().isEmpty()) {
				roleObj.setRoleId(role.getRoleId());
			}
			if (roleObj.getRoleName() != null && !roleObj.getRoleName().isEmpty()) {
				roleObj.setRoleName(role.getRoleName());
			}
			if (roleObj.getRoleStatus() != null && !roleObj.getRoleStatus().isEmpty()) {
				roleObj.setRoleStatus(role.getRoleStatus());
			}
			roleObj = roleRepository.save(roleObj);
			return roleObj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while updating the role:", e);
			throw e;
		}
	}

	@Override
	public List<Role> getAllRoles() {
		try {
			List<Role> roles = roleRepository.findAll();
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while getting all roles:", e);
			throw e;
		}
	}

	@Override
	public Role getRole(String roleId) {
		try {
			Role role = null;
			if (roleId != null && !roleId.isEmpty()) {
				role = roleRepository.findByRoleId(roleId);
			}
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while getting the role:", e);
			throw e;
		}
	}

}
