var app = angular.module('serviceDemoApp');
app.controller('serviceCtrl',['DemoService','$scope',function(DemoService, $scope){
       //calling service 
       DemoService.get(function(DemoService){
              $scope.response=DemoService[0];
console.log($scope.response);
       })}]);