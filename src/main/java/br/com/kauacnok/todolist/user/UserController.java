package br.com.kauacnok.todolist.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.kauacnok.todolist.user.UserModel;

@RestController()
@RequestMapping("/user")
public class UserController {

	@PostMapping("/createUser")
	public void create(@RequestBody UserModel userModel) {
		System.out.println(userModel.name);
	}

}