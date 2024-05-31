const stompClient = new StompJs.Client({
    //web socket server 설정
    brokerURL: 'ws://localhost:8080/endpoint'
});

stompClient.activate();

//web socket 연결 해제
//stompClient.deactivate();

const myName = prompt('이름을 입력하세요.');

//stompClient가 서버에 연결됐을 때 호출되는 함수
stompClient.onConnect = (frame) => {
    console.log('Socket Connected: ' + frame);
    //구독 경로 설정
    stompClient.subscribe('/sub/chat/'+roomId, (message) => {
        showChat(JSON.parse(message.body));
    });
};

function publish() {
    //발행 경로 설정
    const destination = "/pub/chat/" + roomId;

    //메시지 내용 가져오기
    const text = document.getElementById("text").value;
    document.getElementById("text").value = "";// 발신 후 입력창 비우기

    //Chat
    const message = {
        senderName: myName,
        text: text,
        createdTime: new Date()
    };

    stompClient.publish({
        destination: destination,
        body: JSON.stringify(message)
    });
}

function showChat(message) {
    // 태그 생성
    const chatLog = document.getElementById("chatLog");
    const row = document.createElement("tr");
    const chat = document.createElement("td");

    // 전체 container
    const messageContainer = document.createElement("div");
    messageContainer.className = "message-container";
    if (message.senderName === myName) {
        messageContainer.classList.add("sent-by-me");
    }

    // 발신자 이름
    const senderNameDiv = document.createElement("div");
    senderNameDiv.className = "sender-name";
    senderNameDiv.textContent = message.senderName;

    // 메시지 내용
    const messageTextDiv = document.createElement("div");
    messageTextDiv.className = "message-text";
    messageTextDiv.textContent = message.text;

    // 발신 날짜
    const createdTimeDiv = document.createElement("div");
    createdTimeDiv.className = "created-time";
    createdTimeDiv.textContent = formatDateTime(message.createdTime);

    //container에 담기
    messageContainer.appendChild(senderNameDiv);
    messageContainer.appendChild(messageTextDiv);
    messageContainer.appendChild(createdTimeDiv);

    // table에 추가
    chat.appendChild(messageContainer);
    row.appendChild(chat);
    chatLog.appendChild(row);
}

function formatDateTime(dateTime) {
    // numeric or 2-digit :  숫자, long or short : 문자
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
    return new Date(dateTime).toLocaleDateString('ko-KR', options);
}

document.addEventListener("DOMContentLoaded", function () {
    // const form = document.querySelector("form");
    const form = document.getElementById("submit-form");
    const publishButton = document.getElementById("publish");

    // 새로고침 방지
    form.addEventListener("submit", function (e) {
        e.preventDefault();
    });

    publishButton.addEventListener("click", function () {
        publish();
    });
});
