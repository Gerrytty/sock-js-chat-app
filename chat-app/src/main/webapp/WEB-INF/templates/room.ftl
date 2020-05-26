<!doctype html>
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
            sendMessage("Login",  ${userId}, ${room.roomId}, "Login");
            console.log('connection opened');
        };

        sock.onmessage = function (response) {
            var json = JSON.parse(response.data);
            $('#messagesList').first().after("<li>" + json['from'] + ': ' + json['text'] + "</li>");
        };

        sock.onclose = function () {
            console.log('connection closed');
        };

        function sendMessage(text, from, room, payload) {

            let message = {
                "payload": payload,
                "text": text,
                "from": from, //user id
                "room": room
            };

            sock.send(JSON.stringify(message));
            console.log('text: ' + text + ' sent')
        }

    </script>

</head>
<body>

<h1>${room.roomName}</h1>

<div>

    <h3>Add user to this room by username</h3>
    <form method="post" action="/room/${room.roomId}/addUser">

        <input type="text" name="username" placeholder="Enter username">
        <input type="submit" value="add">

    </form>

</div>

<div>
    <h3>Send message</h3>
    <input id="message" placeholder="Input you'r message here">
    <button onclick="sendMessage($('#message').val(), ${userId}, ${room.roomId}, 'message')">Send</button>
</div>

<div>
    <h3>Messages</h3>
</div>

<div>
    <ul id="messagesList">

    </ul>
</div>

<div>

    <#list messages as message>

        <ul>
            #{message.userId}: ${message.text}
        </ul>

    </#list>

</div>

</body>
</html>