var app = angular.module('myApp',[]);
app.controller('myCtrl', function($scope) {
	
   	$scope.connect = function() {
		// oprn the connection if one does not exist
		if (webSocket !== undefined
				&& webSocket.readyState !== WebSocket.CLOSED) {
			return;
		}
		// Create a websocket
		webSocket = new WebSocket("ws://localhost:8080/toUpper");

		webSocket.onopen = function(event) {
			$scope.updateOutput("Connected!");
			connectBtn.disabled = true;
			sendBtn.disabled = false;

		};

		webSocket.onmessage = function(event) {
			$scope.updateOutput(event.data);
		};

		webSocket.onclose = function(event) {
			$scope.updateOutput("Connection Closed");
			connectBtn.disabled = false;
			sendBtn.disabled = true;
		};
	}

	$scope.send = function(){
		var text = document.getElementById("input").value;
		webSocket.send(text);
	}

	$scope.closeSocket = function() {
		webSocket.close();
	}

	$scope.updateOutput = function(text) {
		output.innerHTML += "<br/>" + text;
	}
	
});