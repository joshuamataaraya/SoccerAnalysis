var app = angular.module('serviceDemoApp');
app.controller('serviceCtrl',['DemoService','$scope',function(DemoService, $scope){
       //calling service 
	   $scope.downloadLink = '/BocaAnalytics/rest/UserService/test';
	   console.log($scope.downloadLink);
       DemoService.get(function(DemoService){
              $scope.response=DemoService;
console.log($scope.response);
       })}]);



