package todoapp.services;

import todoapp.models.Todo;

import java.util.List;

public interface ITodoService {

	public List<Todo> fetchAllTodos();
	
	public Todo saveTodo(Todo todo);
	
	public Todo fetchTodoById(String id);
	
	public Todo updateTodoById(Todo todo);
	
	public void deleteTodoById(String id);
	
	public Todo markTodoAsCompleted(Todo todo);
	
}
