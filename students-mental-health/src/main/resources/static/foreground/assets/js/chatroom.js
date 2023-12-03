var stompClient = null;
$(function () {
    connect();

    /**
     * 上传图片发送
     */
    $("#sendImage").bind("change", function () {
        if (this.files.length != 0){
            $.ajax({
                url: $("#uploadUrl").val(),
                type: 'POST',
                cache: false,
                data: new FormData($('#sendImageForm')[0]),
                processData: false,
                contentType: false
            }).done(function(res) {
                console.log(res);
            }).fail(function(res) {
                console.log(res);
            });
        }
    });
    initEmoji();
    $("#sendImageBtn").click(function () {
        $("#sendImage").trigger("click");
    })

});
/**
 * 预加载emoji图片
 */
function initEmoji() {
    var emojiContainer = $("#emojiWrapper");
    var documentFragment = document.createDocumentFragment();
    for (var i = 69; i > 0; i--){
        var emojiItem = document.createElement("img");
        emojiItem.src = $("#emojiBaseUri").val().trim() + i + ".gif";
        emojiItem.title = i;
        documentFragment.appendChild(emojiItem);
    }
    emojiContainer.append(documentFragment);

    $("#emoji").click(function (event) {
        emojiContainer.css("display", "block");
        event.stopPropagation(); //阻止事件的传递，防止body监听到
    });

    $("body").click(function (event) {
        if (event.target != emojiContainer){
            emojiContainer.css("display", "none");
        }
    });
    
    $("#emojiWrapper").click(function (event) {
        var target = event.target;
        if (target.nodeName.toLowerCase() == "img"){
            var messageInput = $("#messageInput");
            messageInput.val(messageInput.val() + "[EMOJI:" + target.title + "]");
            messageInput.focus();
        }
    })
    
}
/**
 * 客户端连接服务端websocket
 * 并且订阅一系列频道，用来接收不同种类的消息
 * /app/chat/participants ：当前在线人数的消息，只会接收一次
 * /topic/login ： 新登录用户的消息
 * /topic/chat/message ： 聊天内容消息
 * /topic/logout : 用户离线的消息
 * 服务器发回json实例{"userName":"chris","sendDate":1494664021793,"content":"hello","messageType":"text"}
 * messageType分为：text与image
 */
function connect() {
    var socket = new SockJS($("#websocketUrl").val().trim());
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame){
        stompClient.subscribe("/app/chat/participants", function (message) {
            showActiveUserNumber(message.body);
            var user = "系统消息";
            var date = null;
            var msg = $("#myName").val() + "加入聊天！";
            showNewMessage(user, date, msg);
        });
        stompClient.subscribe("/topic/login", function (message) {
            showNewUser(message.body);
        });
        stompClient.subscribe("/topic/chat/message", function (message) {
            var json = JSON.parse(message.body);
            var messageType = json.messageType;
            var user = json.userName;
            var date = json.sendDate;
            var msg = json.content;
            if (messageType == "text"){
                showNewMessage(user, date, msg);
            }else if (messageType == "image"){
                showNewImage(user, date, msg);
            }

        })
        stompClient.subscribe("/topic/logout", function (message) {
            showUserLogout(message.body);
        })

    });
}

/**
 * 显示新消息
 * @param user 发消息的用户或者‘系统消息’
 * @param date 发消息的时间（未格式化）
 * @param msg  消息内容
 */
function showNewMessage(user, date, msg) {
    var container = document.getElementById("historyMsg");
    var msgToDisplay = document.createElement('p');
    if (user == "系统消息"){
        msgToDisplay.style.color = 'red';
    }
    var dateTime = formatDate(date);
    msg = showEmoji(msg);
    msgToDisplay.innerHTML = '<span class="timespan">' + dateTime + '</span><br/>[' + user + "] : " + msg;
    container.append(msgToDisplay);
    container.scrollTop = container.scrollHeight;
}
/**
 * 正则表达式显示消息中的emoji图片
 * @param message
 * @returns {*} 返回添加emoji图片标签后的消息
 */
function showEmoji(message) {
    var result = message,
        regrex = /\[EMOJI:\d+\]/g,
        match;
    while (match = regrex.exec(message)){
        var emojiIndex = match[0].slice(7, -1);
        var emojiUrl = $("#emojiBaseUri").val().trim() + emojiIndex + ".gif";
        result = result.replace(match[0], '<img src="' + emojiUrl + '"/>');
    }
    return result;
}

/**
 * 显示用户发送的图片
 * @param user 用户名称
 * @param date 用户发送的时间（未格式化）
 * @param url 图片url
 */
function showNewImage(user, date, url) {
    var container = document.getElementById("historyMsg");
    var msgToDisplay = document.createElement('p');
    var dateTime = formatDate(date);
    msgToDisplay.innerHTML = '<span class="timespan">' + dateTime + '</span><br/>[' + user + '] : <br/>' +
        '<img class="img-thumbnail" src="' + url + '"/>';
    container.append(msgToDisplay);
    container.scrollTop = container.scrollHeight;
}

