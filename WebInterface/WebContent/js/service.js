var app=angular.module('serviceDemoApp',['ngResource']);
 
app.factory('DemoService',function($resource){

       return $resource('/BocaAnalytics/rest/UserService/users',{},{ get: { method: "GET", isArray: false } });
});