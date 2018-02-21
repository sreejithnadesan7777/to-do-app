var app = angular.module("myApp",[]);
app.controller('myCtrl', ['$scope', '$http','todoService',function($scope, $http, todoService) {
$scope.newTodo = {};
$scope.todos = {};
$scope.customFilter = {};
$scope.editing = false;
//Fetching all todos when the Controller loading
todoService.fetchAllTodos().then(function (response) {
             $scope.todos = response;
         });
//Method for Saving todos
$scope.createTodo = function (todoForm) {
 if ($scope.ngForm.$valid) {
     $scope.newTodo.description = todoForm;
     todoService.saveTodo($scope.newTodo).then(function (response) {
             todoService.fetchAllTodos().then(function (response) {
                          $scope.todos = response;
                      });
         });
}
 }

 //Method for saving filters

 $scope.addFilter = function(filterType) {
    switch(filterType) {
           case 'all': {
              $scope.customFilter = {};
              break;
           }
           case 'completed': {
              $scope.customFilter.isCompleted = true;
              break;
           }
           case 'pending': {
              $scope.customFilter.isCompleted = false;
              break;
           }
           default: {
              //statements;
              break;
           }
        }
 }

//Method for editing todos
 $scope.editTodo = function(todo) {
 $scope.editing = true;
 todoService.saveTodo(todo).then(function (response) {
              $scope.categories = response;
          });

 }

//Method for todoos completion

    $scope.complete = function(todo) {
    $scope.editing = false;
     todoService.saveTodo(todo).then(function (response) {
                  $scope.categories = response;
              });

     }
 //Method for marking completed

 $scope.toggleCompleted = function(toDo) {
 toDo.isCompleted = !toDo.isCompleted;
 todoService.saveTodo(toDo).then(function (response) {
              $scope.categories = response;
          });
 }
//Method for deleting toodos
 $scope.deleteTodo = function(id) {
 todoService.deletedo(id).then(function (response) {
               todoService.fetchAllTodos().then(function (response) {
                            $scope.todos = response;
                        });
           });
 }

}]);
