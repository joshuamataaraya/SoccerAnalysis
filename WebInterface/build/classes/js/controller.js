var app = angular.module('serviceDemoApp');


app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);

 app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
       var fd = new FormData();
       fd.append('file', file);
       
       console.log(fd);
       
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
    
       .success(function(){
       })
    
       .error(function(){
       });
    }
 }]);

app.controller('serviceCtrl',['fileUpload','$scope',function(fileUpload, $scope){
	
	 $scope.uploadFile = function(){
	       var file = $scope.myFile;
	       
	       console.log('file is ' );
	       console.dir(file);
	       
	       var uploadUrl = "/fileUpload";
	       fileUpload.uploadFileToUrl(file, uploadUrl);
	    };
	
       //calling service 
	   /*$scope.downloadLink = '/WebInterface/rest/UserService/test';
	   console.log($scope.downloadLink);
       DemoService.get(function(DemoService){
              $scope.response=DemoService[0];
console.log($scope.response);
       }) */
       
       }]);



