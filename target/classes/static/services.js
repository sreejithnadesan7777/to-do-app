app.factory('todoService', function($http) {
    var todoService = {};
    var url = "/api"
    todoService.saveTodo = function(toDo) {
        return $http.post(url+"/todos",toDo)
            .then(function(response) {
                return response.data;
            });
    };

    todoService.fetchAllTodos = function () {
        return $http.get(url+"/todo/all")
            .then(function (response) {
                return response.data;
            });
    };

    todoService.deletedo = function (id) {

        return $http.delete(url+"/todos/"+id)
            .then(function (response) {
                return response.data;
            });
    };


    return todoService;
});