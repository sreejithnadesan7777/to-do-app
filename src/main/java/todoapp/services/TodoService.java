package todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoapp.models.Todo;
import todoapp.repositories.ITodoRepository;

import java.util.Date;
import java.util.List;

@Service
public class TodoService implements ITodoService{
	
	@Autowired
	ITodoRepository todoRepository;

	@Override
	public List<Todo> fetchAllTodos(){
		return todoRepository.findAll();
	}

	@Override
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo fetchTodoById(String id) {
		return todoRepository.findOne(id);
	}

	@Override
	public Todo updateTodoById(Todo todo) {
		Todo todoData = fetchTodoById(todo.getId());
		todoData.setDescription(todo.getDescription());
		todoData.setUpdatedAt(new Date());
		return todoRepository.save(todoData);
	}

	@Override
	public void deleteTodoById(String id) {
		todoRepository.delete(id);
	}

	@Override
	public Todo markTodoAsCompleted(Todo todo) {
		Todo todoData = fetchTodoById(todo.getId());
		todoData.setIsCompleted(todo.getIsCompleted());
		todoData.setUpdatedAt(new Date());
		return todoRepository.save(todoData);
	}
	
}
