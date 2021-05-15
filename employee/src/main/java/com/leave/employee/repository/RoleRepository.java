package com.leave.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leave.employee.domain.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	public Role findByRoleId(String roleId);

}
