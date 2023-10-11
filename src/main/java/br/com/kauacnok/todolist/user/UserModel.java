package br.com.kauacnok.todolist.user;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@Column(unique = true)
	private String username;
	private String name;
	private String password;

	@CreationTimestamp
	private LocalDateTime created_at;

}
