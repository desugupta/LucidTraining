package com.leave.employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.impl.EmployeeImpl;
import com.leave.employee.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeApplicationTests {	

	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeImpl employeeImpl;

	@Test
	void contextLoads() {
	}

	@Test
	public void addUser() {
		EmployeeUser empUser = new EmployeeUser();
		empUser.setUserName("raja");
		when(employeeRepository.save(empUser)).thenReturn(empUser);
	//	EmployeeUser userResponse = employeeImpl.saveEmployee(empUser);
		assertEquals(empUser, employeeImpl.saveEmployee(empUser));
	}

	@Test
	public void updateUser() throws Exception {
		EmployeeUser empUser=new EmployeeUser();
		empUser.setEmployeeId(25);
		empUser.setEmailId("desugupta@gmail.com");
		when(employeeRepository.findByEmployeeId(empUser.getEmployeeId())).thenReturn(empUser);
		assertEquals(empUser.getEmailId(), employeeImpl.updateEmployee(empUser).getEmailId());		
	}
	
	
	/*
	 * @Test public void getEmployee() { Integer employeeId = 23;
	 * when(employeeRepository.findByEmployeeId(employeeId))
	 * .thenReturn(Stream.of(new
	 * EmployeeUser("Raja",23,"fsdf")).collect(Collectors.toList())); try {
	 * assertEquals(1, employeeImpl.getEmployee(employeeId)); } catch (Exception e)
	 * { e.printStackTrace(); } }
	 */
	
	/*
	 * @Test public void getUserbyAddressTest() { String address = "Bangalore";
	 * when(repository.findByAddress(address)) .thenReturn(Stream.of(new User(376,
	 * "Danile", 31, "USA")).collect(Collectors.toList())); assertEquals(1,
	 * service.getUserbyAddress(address).size()); }
	 */
	
	
	/*
	 * @Test public void getUserbyAddressTest() { String address = "Bangalore";
	 * when(repository.findByAddress(address)) .thenReturn(Stream.of(new User(376,
	 * "Danile", 31, "USA")).collect(Collectors.toList())); assertEquals(1,
	 * service.getUserbyAddress(address).size()); }
	 */

}