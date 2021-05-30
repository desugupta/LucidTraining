package com.leave.employee.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.leave.employee.domain.ResponseObject;
import com.leave.employee.domain.Role;
import com.leave.employee.service.RoleService;
import com.leave.employee.util.ResponseUtil;

@RequestMapping("/role")
@RestController
public class RolesRest {

	@Autowired
	private RoleService roleService;

	private final Logger logger = LoggerFactory.getLogger(RolesRest.class);

	/**
	 * @author rajasekhar.d
	 * @description To save the role
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveRole(@RequestBody Role role) {
		try {
			logger.info("+++++ Entry into saveRole() method in Rest +++++");
			Role roles = roleService.saveRole(role);
			logger.info("+++++ Exit from saveRole() method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(roles), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in saving the role:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to update the role
	 */
	@RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRole(@RequestBody Role role,@PathVariable("roleId") String roleId) {
		try {
			logger.info("+++++ Entry into updateRole method in Rest +++++");
			Role roles = roleService.updateRole(role,roleId);
			logger.info("+++++ Exit from updateRole method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(roles), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in updating the role:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to get the role by roleId
	 */
	@RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRole(@PathVariable("roleId") String roleId) {
		try {
			logger.info("+++++ Entry into getRole() method in Rest +++++");
			Role role = roleService.getRole(roleId);
			logger.info("+++++ Exit from getRole() method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(role), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting the role:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to get all roles available in the roles collection
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllRole() {
		try {
			logger.info("+++++ Entry into getAllRole() method in Rest +++++");
			List<Role> roles = roleService.getAllRoles();
			logger.info("+++++ Exit from getAllRole() method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(roles), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting all the roles:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
