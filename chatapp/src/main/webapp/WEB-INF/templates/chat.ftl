<#--<!doctype html>-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chat</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <script>
        var sock = new SockJS('http://localhost:9999/ws');

        sock.onopen = function () {
            console.log('connection opened');
        };

        sock.onmessage = function (response) {
            var json = JSON.parse(response.data);
            $('#messagesList').first().after("<li>" + json['from'] + ': ' + json['text'] + "</li>");
        };

        sock.onclose = function () {
            console.log('connection closed');
        };

        function sendMessage(text) {

            let message = {
                "text": text,
                "from": 1 // pageId
            };

            sock.send(JSON.stringify(message));
            console.log('text: ' + text + ' sent')
        }

    </script>

</head>
<body>
<div>
    <input id="message" placeholder="Input you'r message here">
    <button onclick="sendMessage($('#message').val())">Send</button>
</div>
<div>
    <ul id="messagesList">

    </ul>
</div>
</body>
</html>