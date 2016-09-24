var app=angular.module('serviceDemoApp',['ngResource']);
 
app.factory('DemoService',function($resource){

       return $resource('/WebInterface/rest/UserService/users',{},{ get: { method: "GET", isArray: true } });
});