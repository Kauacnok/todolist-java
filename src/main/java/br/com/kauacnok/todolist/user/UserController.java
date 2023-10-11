package br.com.kauacnok.todolist.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.kauacnok.todolist.user.UserModel;
import br.com.kauacnok.todolist.user.IUserRepository;

@RestController()
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserRepository userRepository;

	@PostMapping("/createUser")
	public ResponseEntity create(@RequestBody UserModel userModel) {
		var user = this.userRepository.findByUsername(userModel.getUsername());

		if (user != null) {
			System.out.println("Usuário já existe");
			return ResponseEntity.status(400).body("Usuário já existe");
		}

		var userCreated = this.userRepository.save(userModel);
		return ResponseEntity.status(201).body(userCreated);
	}

}