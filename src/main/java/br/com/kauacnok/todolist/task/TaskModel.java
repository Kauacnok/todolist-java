package br.com.kauacnok.todolist.task;

import java.util.UUID;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import java.lang.Exception;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	private String description;

	@Column(length = 50)
	private String title;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private String priority;

	@CreationTimestamp
	private LocalDateTime createdAt;

	private UUID idUser;

	public void setTitle(String title) throws Exception {
		if (title.length() > 50) {
			throw new Exception("O campo title deve conter no máximo 50 caracteres");
		}
		this.title = title;
	}

}