// 페이지 로드 시 실행
document.addEventListener('DOMContentLoaded', function () {
    // userNumber가 비어있으면 얼럿 창 표시
    if (!userNumber) {
        alert('조회할 수 없습니다. 다시 시도해주세요.');
        // 뒤로가기 이벤트 생성
        window.history.back();
    }
});

//번호 인증창(모달)
let changeModalPh = document.querySelector(".submit-btn");
let ph = document.querySelector(".change-ph");
let phModal = document.querySelector(".phone-change-modal");
let xBoxPh = document.querySelector('.phone-modal-close');
// let changeBtnPh = document.querySelector('.ph-change-btn');

changeModalPh.addEventListener('click', function (event) {
    event.preventDefault();
    phModal.style.display = "flex";
    ph.style.display = "flex";
    xBoxPh.style.display = "flex";
    // changeBtnPh.style.display="block";
});

xBoxPh.addEventListener('click', function () {
    phModal.style.display = "none";
    xBoxPh.style.display = "none";
    ph.style.display = "none";
    // changeBtnPh.style.display="none";
});

//인증시간 카운트 다운
var countdownInterval;
var secondsRemaining = 240; // 4분 = 240초

function startCountdown() {
    var phoneCheckDiv = document.querySelector(".phone-check");
    phoneCheckDiv.style.display = "flex";

    var countdownDiv = document.getElementById("countdown");
    countdownDiv.style.display = "block";

    countdownDiv.innerHTML = formatTime(secondsRemaining);

    countdownInterval = setInterval(function () {
        secondsRemaining--;

        if (secondsRemaining <= 0) {
            clearInterval(countdownInterval);
            countdownDiv.style.display = "none";
            showPhoneCheck();
        } else {
            countdownDiv.innerHTML = formatTime(secondsRemaining);
        }
    }, 1000);
}

function formatTime(seconds) {
    var minutes = Math.floor(seconds / 60);
    var remainingSeconds = seconds % 60;
    return minutes + "분 " + remainingSeconds + "초";
}

function showPhoneCheck() {
    var phoneCheckDiv = document.querySelector(".phone-check");
    phoneCheckDiv.style.display = "none";
}

function PhoneCheck() {
    // 새로운 카운트 다운 시작 (4분 = 240초)
    secondsRemaining = 240;

    clearInterval(countdownInterval); // 이전 카운트 다운을 초기화

    var countdownDiv = document.getElementById("countdown");
    countdownDiv.style.display = "none";
    countdownDiv.innerHTML = formatTime(secondsRemaining);

    var CheckDiv = document.querySelector(".phone-check");
    CheckDiv.style.display = "none";
}

// SMS를 보내는 함수
$('.user-phone-btn1').on('click', function sendSms() {
    const phoneNumber = document.getElementById("userPhoneCh").value;
    if (!phoneNumber) {
        alert("휴대전화 번호를 입력하세요.");
        return;
    }

    // 서버로 휴대전화 번호를 보내고 SMS를 전송합니다.
    fetch("/users/send", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({phoneNumber})
    })
        .then(response => response.json())
        .then(data => {
            if (data.error) {
                alert("SMS 전송에 실패했습니다.");
            } else {
                alert("SMS가 전송되었습니다.");
                startCountdown(); // SMS 전송 후 카운트다운 시작
            }
        })
        .catch(error => {
            console.error("SMS 전송 오류:", error);
        });
});

// SMS 인증번호 확인 함수
$('.user-check-btn').on('click', function verifySms() {
    const verificationCode = document.getElementById("verificationCode").value;
    if (!verificationCode) {
        alert("인증번호를 입력하세요.");
        return;
    }

    // 서버로 인증번호를 보내고 확인합니다.
    fetch("/users/check", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({checkNumber: verificationCode})
    })
        .then(response => response.json())
        .then(data => {
            if (data) {
                alert("인증이 완료되었습니다.");
                var userPhone = document.querySelector("#userPhoneCh").value;
                // document.querySelector("#userPhone").value = userPhone;
                PhoneCheck();
                closePh();
                showPw();



            } else {
                alert("인증번호가 일치하지 않습니다.");
            }
        })
        .catch(error => {

            console.error("인증 오류:", error);
        });
})

//비밀번호 변경창(모달)
let changeModal = document.querySelector(".change-pw-btn");
let pw = document.querySelector(".change-pw");
let modal = document.querySelector(".change-modal");
let xBox = document.querySelector('.modal-close');
let changeBtn = document.querySelector('.user-change-btn');

// changeModal.addEventListener('click', function (event) {
//     event.preventDefault();
//     modal.style.display = "flex";
//     pw.style.display = "flex";
//     xBox.style.display = "flex";
//     changeBtn.style.display = "block";
// });

xBox.addEventListener('click', function () {
    modal.style.display = "none";
    xBox.style.display = "none";
    pw.style.display = "none";
    changeBtn.style.display = "none";
});


const changeButton = document.querySelector('.user-change-btn');
const passwordInputs = document.querySelectorAll('.user-password-input2, .user-password-input3');
const confirmDiv = document.querySelector('.Confirm');



const checkPasswordsMatch = () => {
    if (passwordInputs[0].value !== passwordInputs[1].value) {
        confirmDiv.innerText = '비밀번호가 일치하지 않습니다';
        confirmDiv.style.color = 'red';
        confirmDiv.style.display = 'block';
        changeButton.disabled = true;
    } else {
        confirmDiv.innerText = ''; // 비밀번호 일치 메시지 초기화
        confirmDiv.style.display = 'none';
        changeButton.disabled = false;
    }
};

const handlePasswordChange = event => {
    if (!passwordInputs[0].value || !passwordInputs[1].value) {
        alert('변경할 비밀번호를 입력하세요.');
        event.preventDefault();
    } else if (passwordInputs[0].value !== passwordInputs[1].value) {
        alert('비밀번호가 일치하지 않습니다.');
        event.preventDefault();
    } else {
        alert('비밀번호가 변경되었습니다.');
    }
};
passwordInputs.forEach(input => input.addEventListener('input', checkPasswordsMatch));
changeButton.addEventListener('click', handlePasswordChange);

//비밀번호 일치
$(document).ready(function () {
    $('.Confirm')
        .text("비밀번호를 입력해주세요")
        .css("color", "gray");
});

function pwCheck() {

    if ($('.user-password-input2').val() === "" & $('.user-password-input3').val() === "") {
        $('.Confirm')
            .text("비밀번호를 입력해주세요")
            .css("color", "gray");
    } else if ($('.user-password-input2').val() === $('.user-password-input3').val()) {
        $('.Confirm')
            .text("비밀번호가 일치합니다")
            .css("color", "green");
    } else if ($('.user-password-input2').val() !== $('.user-password-input3').val()) {
        $('.Confirm')
            .text("비밀번호가 일치하지 않습니다")
            .css("color", "red");
    } else {
        $('.Confirm')
            .text("비밀번호를 입력해주세요")
            .css("color", "gray");
    }
}
function closePh(){
    phModal.style.display = "none";
    xBoxPh.style.display = "none";
    ph.style.display = "none";
    // changeBtnPh.style.display="none";
}
function showPw(){
    // event.preventDefault();
    modal.style.display = "flex";
    xBox.style.display = "flex";
    pw.style.display = "flex";
    changeBtn.style.display = "block";
}