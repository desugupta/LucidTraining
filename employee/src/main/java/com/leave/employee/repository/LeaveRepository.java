package com.leave.employee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leave.employee.domain.LeavesDetails;

@Repository
public interface LeaveRepository extends MongoRepository<LeavesDetails,String> {
	
	
	public LeavesDetails findByManagerEmpId(Integer managerEmpId);

	public LeavesDetails findByManagerEmpIdAndLeaveStatus(Integer managerEmpId,String leaveStatus);
	

}
