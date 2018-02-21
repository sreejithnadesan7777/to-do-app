package todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todoapp.models.Todo;
import todoapp.services.ITodoService;
import todoapp.utils.RequestURI;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by A-7451 on 2/19/18.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TodoAppController {
    @Autowired
    ITodoService todoService;

    @RequestMapping(value = RequestURI.GET_ALL_TODOS, method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.fetchAllTodos();
        return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
    }

    @RequestMapping(value = RequestURI.TODOS, method = RequestMethod.POST)
    public ResponseEntity<Todo> saveTodos(@RequestBody Todo todo) {
        todo.setIsCompleted(false);
        todo.setCreatedAt(new Date());
        todo.setUpdatedAt(new Date());
        Todo newTodo = todoService.saveTodo(todo);
        return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);
    }

    @RequestMapping(value = RequestURI.TODOS, method = RequestMethod.PUT)
    public ResponseEntity<Todo> updateTodos(@RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodoById(todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    @RequestMapping(value = RequestURI.DELETE_TODOS, method = RequestMethod.DELETE)
    public ResponseEntity<Todo> deleteTodos(@PathVariable String id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = RequestURI.MARK_AS_COMPLETED, method = RequestMethod.PUT)
    public ResponseEntity<Todo> markAsCompleted(@RequestBody Todo todo) {
        Todo updatedTodo = todoService.markTodoAsCompleted(todo);
        return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
    }

}
