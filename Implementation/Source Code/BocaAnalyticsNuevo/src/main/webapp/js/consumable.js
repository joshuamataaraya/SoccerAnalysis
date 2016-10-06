var app = angular.module('myApp',[]);
app.controller('myCtrl', function($scope) {
   
    var webSocket;

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
			$scope.loading = false;
			$scope.$digest();
			console.log($scope.loading);
		};

		webSocket.onclose = function(event) {
			$scope.updateOutput("Connection Closed");
			connectBtn.disabled = false;
			sendBtn.disabled = true;
		};
	}

	$scope.send = function(){
		console.log("here");
		$scope.loading = true;
		console.log($scope.loading);
        var file = document.getElementById('filename').files[0];
        webSocket.send('filename:'+file.name);
        var reader = new FileReader();
        var rawData = new ArrayBuffer();            
        //alert(file.name);

        reader.loadend = function() {

        }
        reader.onload = function(e) {
        	$scope.loading = true;
            rawData = e.target.result;
            webSocket.send(rawData);
            webSocket.send('end');
        }

        reader.readAsArrayBuffer(file);
	}

	$scope.closeSocket = function() {
		webSocket.close();
	}

	$scope.updateOutput = function(text) {
		output.innerHTML += "<br/>" + text;
	}

    function connectChatServer() {
        webSocket = new WebSocket(
                "ws://localhost:8080/toUpper");

        webSocket.binaryType = "arraybuffer";
        webSocket.onopen = function() {
            alert("Connected.")
        };

        webSocket.onmessage = function(evt) {
            alert(evt.msg);
        };

        webSocket.onclose = function() {
            alert("Connection is closed...");
        };
        webSocket.onerror = function(e) {
            alert(e.msg);
        }

    }

    $scope.sendFile=function(){
    	console.log("here");
    	connectChatServer();
        var file = document.getElementById('filename').files[0];
        webSocket.send('filename:'+file.name);
        var reader = new FileReader();
        var rawData = new ArrayBuffer(); 
        $scope.loading = true;
        //alert(file.name);

        reader.loadend = function() {

        }
        reader.onload = function(e) {
            rawData = e.target.result;
            webSocket.send(rawData);
            webSocket.send('end');
        }

        reader.readAsArrayBuffer(file);

    }
	
});