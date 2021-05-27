package com.leave.employee.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeUser foundedUser = userRepository.findByUserName(username);
		if (foundedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
			}
		String name = foundedUser.getUserName();
		String password = foundedUser.getPassword();
		return new User(name, password, getAuthority(foundedUser));
	}
	
	private Set getAuthority(EmployeeUser user) {
        Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByUserName(username);
        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));
    }
    */

}

/*
 * @Service(value = "userService") public class UserServiceImpl implements
 * UserDetailsService, UserService {
 * 
 * @Autowired private UserDao userDao;
 * 
 * @Autowired private BCryptPasswordEncoder bcryptEncoder;
 * 
 * public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { User user = userDao.findByUsername(username);
 * if(user == null){ throw new
 * UsernameNotFoundException("Invalid username or password."); } return new
 * org.springframework.security.core.userdetails.User(user.getUsername(),
 * user.getPassword(), getAuthority(user)); }
 * 
 * private Set getAuthority(User user) { Set authorities = new HashSet<>();
 * user.getRoles().forEach(role -> { authorities.add(new
 * SimpleGrantedAuthority("ROLE_" + role.getName())); }); return authorities; }
 * 
 * public List findAll() { List list = new ArrayList<>();
 * userDao.findAll().iterator().forEachRemaining(list::add); return list; }
 * 
 * @Override public void delete(long id) { userDao.deleteById(id); }
 * 
 * @Override public User findOne(String username) { return
 * userDao.findByUsername(username); }
 * 
 * @Override public User findById(Long id) { return userDao.findById(id).get();
 * }
 * 
 * @Override public User save(UserDto user) { User newUser = new User();
 * newUser.setUsername(user.getUsername());
 * newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
 * newUser.setAge(user.getAge()); newUser.setSalary(user.getSalary()); return
 * userDao.save(newUser); } }
 */
