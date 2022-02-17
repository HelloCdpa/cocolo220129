    //express 를 모듈에서 불러옴
    const express = require("express");
    const http = require("http");
    //express의 실행 내용을 app에 담음
    const app = express();
    const path = require("path")
    //express가 http로 실행될 수 있도록
    const server = http.createServer(app);
    //소켓 사용하기
    const socketIO = require("socket.io");
    //변수에 서버 담기
    const io = socketIO(server);
    //모먼트 사용(시간)
    const moment = require("moment");
    //한국시간으로 모먼트 설정
    const cors = require('cors');
    app.use(cors());

    //서버가 실행이 되면 보여줄 폴더를 지정
    //path.join : 운영체제마다 경로를 나타내주는 기호가 다르기 때문에
    app.use(express.static(path.join(__dirname, "src")));

    //프로세스 환경에 포트가 지정되어 있으면 그것을 실행 아니면 5000실행
    const PORT = process.env.PORT || 5000;

    //연결이 되면 그것의 정보를 소켓에 담음
    //소켓의 정보에 접근
    io.on("connection", (socket) => {
    //"채팅아이디"
    socket.on("chatting", (data) => {
        const { name, msg } = data;
        io.emit("chatting", {
            name,
            msg,
            time : moment(new Date()).format("h:ss A")
        })
    })

});
    //app.listen(포트,명령) 포트라는 변수를 사용하기 위해서 백틱 사용
    server.listen(PORT, ()=> console.log(`server is running ${PORT}`));




