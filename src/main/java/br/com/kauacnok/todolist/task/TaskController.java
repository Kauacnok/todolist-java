package br.com.kauacnok.todolist.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.kauacnok.todolist.task.TaskModel;
import br.com.kauacnok.todolist.task.ITaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private ITaskRepository taskRepository;

	@PostMapping("/create")
	public TaskModel create(@RequestBody TaskModel taskModel) {
		var task = this.taskRepository.save(taskModel);

		return task;
	}
}