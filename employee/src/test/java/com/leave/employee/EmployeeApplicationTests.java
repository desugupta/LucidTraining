package com.leave.employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.impl.EmployeeImpl;
import com.leave.employee.repository.EmployeeRepository;
import com.leave.employee.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeApplicationTests {

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;;

	@Autowired
	private EmployeeImpl employeeImpl;

	@Autowired
	private ModelMapper modelMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void addUser() {

		EmployeeUser empUser = new EmployeeUser();

		empUser.setUserName("raja");
		when(employeeRepository.save(empUser)).thenReturn(empUser);
		EmployeeUser userResponse = employeeImpl.saveEmployee(empUser);
		assertEquals(empUser, employeeImpl.saveEmployee(empUser));
	}

	/*
	 * @Test public void getEmployee() {
	 * 
	 * Integer employeeId=10;
	 * 
	 * when(employeeRepository.findByEmployeeId(employeeId)).thenReturn((
	 * EmployeeUser)Stream.of(new
	 * EmployeeUser("raja","rdds")).collect(Collectors.toList()));
	 * assertEquals(1,employeeImpl.getEmployee(employeeId)); }
	 */

	/*
	 * @Test public void getUserbyAddressTest() { String address = "Bangalore";
	 * when(repository.findByAddress(address)) .thenReturn(Stream.of(new User(376,
	 * "Danile", 31, "USA")).collect(Collectors.toList())); assertEquals(1,
	 * service.getUserbyAddress(address).size()); }
	 */

}