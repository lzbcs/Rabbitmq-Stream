let stompClient = null;

function connect() {
    // 建立连接对象（还未发起连接）
    const socket = new SockJS('/websocket')
    // 获取 STOMP 子协议的客户端对象
    stompClient = Stomp.over(socket)

    let prevPriceValue = null
    // 向服务器发起websocket连接并发送CONNECT帧
    //client.connect(headers, connectCallback, errorCallback);
    //headers表示客户端的认证信息,若无需认证，直接使用空对象 “{}” 即可；
    stompClient.connect({},
        // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
        function connectCallback (frame) {
            console.log('Connected: ' + frame)

            //subscribe(destination url, callback[, headers])
            //订阅相应的URL，发送一个 SUBSCRIBE 帧，然后才能不断接收来自服务器的推送消息；
            stompClient.subscribe('/topic/prices', function (price) {
                const priceBody = JSON.parse(price.body)
                const priceValue = priceBody.value
                const priceTimestamp = priceBody.timestamp

                if (prevPriceValue == null) {
                    prevPriceValue = priceValue
                }
                const priceVar = priceValue - prevPriceValue
                prevPriceValue = priceValue

                $('#currentPrice').text(Number(priceValue).toFixed(2))
                $('#variation').text((priceVar > 0 ? "+" : "") + Number(priceVar).toFixed(2))

                const row = '<tr><td>'+Number(priceValue).toFixed(2)+'</td><td>'+moment(priceTimestamp).format('YYYY-MM-DD HH:mm:ss')+'</td></tr>'
                if ($('#priceList tr').length > 20) {
                    $('#priceList tr:last').remove()
                }
                $('#priceList').find('tbody').prepend(row)
            })

            stompClient.subscribe('/topic/comments', function (chatComment) {
                const chatCommentBody = JSON.parse(chatComment.body)
                const fromUser = chatCommentBody.fromUser
                const message = chatCommentBody.message
                const timestamp = chatCommentBody.timestamp

                const row = '<tr><td>['+moment(timestamp).format('YYYY-MM-DD HH:mm:ss')+'] '+fromUser+' to all: '+message+'</td></tr>'
                $('#chat').find('tbody').prepend(row)
            })

            stompClient.subscribe('/user/topic/comments', function (chatComment) {
                const chatCommentBody = JSON.parse(chatComment.body)
                const fromUser = chatCommentBody.fromUser
                const message = chatCommentBody.message
                const timestamp = chatCommentBody.timestamp

                const row = '<tr><td>['+moment(timestamp).format('YYYY-MM-DD HH:mm:ss')+'] '+fromUser+' to you: '+message+'</td></tr>'
                $('#chat').find('tbody').prepend(row)
            })
        },
        // 连接失败时
        // function() {
        //     console.log('Unable to connect to Websocket!')
        //     $('#websocketSwitch').prop('checked', false)
        // }
        function errorCallBack (error) {
            // 连接失败时（服务器响应 ERROR 帧）的回调方法
            console.log('Unable to connect to Websocket!')
            $('#websocketSwitch').prop('checked', false)
        }
     )
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

$(function () {
    //listen click event
    $('#websocketSwitch').click(function() {
        if ($(this).prop('checked')) {
            connect()
        } else {
            disconnect()
        }
    })
    //listen submit event
    $('#chatForm').submit(function(e) {
        e.preventDefault();

        const fromUserVal = $("#fromUser").val()
        const toUserVal = $("#toUser").val()
        const message = $("#message")
        const messageVal = message.val()

        if (fromUserVal.length !== 0 && messageVal.length !== 0) {
            //STOMP 帧的 body 必须是 string 类型,
            const comment = JSON.stringify({'fromUser': fromUserVal, 'toUser': toUserVal, 'message': messageVal})
            //send() 方法向服务器发送信息
            stompClient.send("/app/chat", {}, comment)
            message.val('')
        }
    })

    let height = window.innerHeight - 245
    $('#priceList').parent().css({"height": height, "max-height": height, "overflow-y": "auto"})

    height = window.innerHeight - 500
    $('#chat').parent().css({"height": height, "max-height": height, "overflow-y": "auto"})
})

connect()