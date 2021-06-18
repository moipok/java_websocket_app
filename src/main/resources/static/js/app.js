// ws = new WebSocket("ws://localhost:8080/game");
//
// alert("1221");
//
// ws.onopen = function () {
//     alert("conn ok");
// }
//
// ws.onmessage = function (ev)
// {
//     changeElem(ev);
// }
//
// function changeElem(ev) {
//     console.log(ev);
// }


// function send_col(id)
// {
//     let m;
//     console.log(id);
//     m = document.getElementById('collor').value;
//     ws.send(id + m);
// }

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function send_col(id) {
    stompClient.send("/app/hello", {}, JSON.stringify({
        'color': $("#collor").val(),
        'id' : id
    }))
    // stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    console.log(message);
    let arrayOfStrings = message.split("#");
    let id = arrayOfStrings[0];
    let color = "#" + arrayOfStrings[1];
    console.log(id + "    " + color);
    // $("#greetings").append("<tr><td>" + message + "</td></tr>");
    document.getElementById(id).style.backgroundColor = color;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

