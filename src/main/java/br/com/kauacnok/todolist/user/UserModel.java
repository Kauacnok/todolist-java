package br.com.kauacnok.todolist.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserModel {

	private String username;
	private String name;
	private String password;

}