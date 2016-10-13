/**
* @author Adrian Lopez Quesada
* @version v0.1.1-alpha
*/

angular
    .module("myApp", [])
    .controller("myCtrl", Control);


/**
* @name Control
* @desc the main controller of the index
* @param {$scope} needed since the use of 'this' was generating problems
*/
function Control($scope) {
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

    vid.load();

    $scope.connect = connect;
    $scope.send = send;
    $scope.moveBar = moveBar;


    /**
    * @name connect
    * @desc connects to the websocket of the Server
    * @param {String} pEndMessage Message to send at the end of transfer, tellign which action to take
    * @param {String} pIdFileInput string id of the input whoch contains the file, its changes by process or groundtruth
    */
    function connect(pEndMessage, pIdFileInput) {
        // oprn the connection if one does not exist
        if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
            return;
        }
        // Create a websocket
        webSocket = new WebSocket("ws://localhost:8080/vidUpload");
        endMessage = pEndMessage;
        idFileInput = pIdFileInput;

        webSocket.onopen = onopen;
        webSocket.onmessage = onmessage;
        webSocket.onclose = onclose;

        /**
        * @name onopen
        * @desc executed when the connecions opens
        * @returns {String}
        */
        function onopen() {
            vid.pause();
            $scope.buttonsAvailable = false;
            $scope.send();
        }

        /**
        * @name onmessage
        * @desc executed when the connection receives a message
        * @param {Event} event object sent from the server containing data
        */
        function onmessage(event) {
            if (event.data === "transfer") {
                $scope.loading = false;
                $scope.moveBar();
                $scope.$digest();
            } else {
                if (event.data === "groundTruth") {
                    webSocket.close();
                } else {
                    $scope.downloadLink = event.data;
                    $scope.displayVideo = "http://localhost:8080/" + event.data;
                    webSocket.close();
                }
            }
        }

        /**
        * @name onclose
        * @desc executed when the connection closes
        */
        function onclose() {
            $scope.videoProcess = false;
            $scope.buttonsAvailable = true;
            width = 0;
            $scope.$digest();
            vid.load();
        }
    }

    /**
    * @name send
    * @desc method used to upload the video to the server
    */
    function send() {
        $scope.videoProcess = true;
        $scope.loading = true;
        $scope.$digest();
        var file = document.getElementById(idFileInput).files[0];
        if (file === "undefined") {
            webSocket.close();
        } else {
            webSocket.send("filename:" + file.name);
            var reader = new FileReader();
            var rawData = new ArrayBuffer();

            reader.onload = function (e) {
                $scope.loading = true;
                rawData = e.target.result;
                webSocket.send(rawData);
                webSocket.send(endMessage);
            };
            reader.readAsArrayBuffer(file);
        }
    }

    /**
    * @name moveBar
    * @desc updates the process bar by one
    */
    function moveBar() {
        width = width + 1;
        elem.style.width = width + "%";
        document.getElementById("label").innerHTML = width + "%";
    }
}