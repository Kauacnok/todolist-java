package br.com.kauacnok.todolist.task;

import br.com.kauacnok.todolist.utils.Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import br.com.kauacnok.todolist.task.TaskModel;
import br.com.kauacnok.todolist.task.ITaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private ITaskRepository taskRepository;

	@GetMapping("/get-user-tasks")
	public List<TaskModel> list(HttpServletRequest request) {
		var tasks = this.taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));

		return tasks;
	}

	@PostMapping("/create")
	public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
		taskModel.setIdUser((UUID) request.getAttribute("idUser"));
		var task = this.taskRepository.save(taskModel);

		var currentDate = LocalDateTime.now();
		if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
			return ResponseEntity.status(400).body("A data de início e/ou a data de término deve ser maior que a data atual");
		}
		if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
			return ResponseEntity.status(400).body("A data de início deve ser menor que a data de término");
		}

		return ResponseEntity.status(201).body(task);
	}

	@PutMapping("/update-task/{id}")
	public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
		var task = this.taskRepository.findById(id).orElse(null);

		Utils.copyNonNullProperties(taskModel, task);

		return this.taskRepository.save(task);
	}

}