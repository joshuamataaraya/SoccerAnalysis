var app = angular.module('myApp',[]);
app.controller('myCtrl', function($scope) {
   
    var webSocket;
    var width = 0;
    var elem = document.getElementById("myBar");
    var vid = document.getElementById("myVideo");
    var endMessage;
    var idFileInput;
    $scope.loading = false;
    $scope.videoProcess = false;
    $scope.buttonsAvailable = true;
    $scope.downloadLink = "/video.mp4";
    $scope.displayVideo = "http://localhost:8080/video.mp4";

	$scope.connect = function(pEndMessage, pIdFileInput) {
		// oprn the connection if one does not exist
		if (webSocket !== undefined
				&& webSocket.readyState !== WebSocket.CLOSED) {
			return;
		}
		// Create a websocket
		webSocket = new WebSocket("ws://localhost:8080/vidUpload");
		
		endMessage = pEndMessage;
		idFileInput = pIdFileInput;

		webSocket.onopen = function(event) {
			vid.pause();
			$scope.buttonsAvailable = false;
			console.log("here?");
			$scope.send();

		};

		webSocket.onmessage = function(event) {
			if(event.data == "transfer"){
				$scope.loading = false;
				$scope.moveBar();
				$scope.$digest();
				
			}else{
				if(event.data == "groundTruth"){
					webSocket.close();
				}else{
					$scope.downloadLink= event.data;
					$scope.displayVideo = "http://localhost:8080/"+ event.data;
					console.log($scope.displayVideo);
					console.log(event.data);
					webSocket.close();
				}
			}
		};

		webSocket.onclose = function(event) {
			$scope.videoProcess = false;
			$scope.buttonsAvailable = true;
			width = 0;
			$scope.$digest();
			vid.load();
		};
	}

	$scope.send = function(){
		console.log("here");
		$scope.videoProcess = true;
		$scope.loading = true;
		$scope.$digest();
		console.log($scope.loading);
        var file = document.getElementById(idFileInput).files[0];
        if (typeof(file) == 'undefined') {
        	  webSocket.close();
        }else{
	        webSocket.send('filename:'+file.name);
	        var reader = new FileReader();
	        var rawData = new ArrayBuffer();            
	
	        reader.loadend = function() {
	
	        }
	        reader.onload = function(e) {
	        	$scope.loading = true;
	            rawData = e.target.result;
	            webSocket.send(rawData);
	            webSocket.send(endMessage);
	        }
	
	        reader.readAsArrayBuffer(file);
        }
	}
	
	$scope.moveBar= function() {
	    width++; 
        elem.style.width = width + '%'; 
        document.getElementById("label").innerHTML = width + '%';

	 }
});