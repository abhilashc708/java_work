package com.testcode.springbootrestapi.service;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

	import com.testcode.springbootrestapi.model.User;
	import com.testcode.springbootrestapi.repository.UserRepository;

	@Service
	public class UserService {
		
		@Autowired
		UserRepository userRepository;
		
		public User save(User user) {
			return userRepository.save(user);
		}
		
		public List<User> findAll(){
			return userRepository.findAll();
		}
		
		public User findOne(Long id) {
			return userRepository.findOne(id);
		}
		
		public void delete(User user) {
			 userRepository.delete(user);
		}

	}



