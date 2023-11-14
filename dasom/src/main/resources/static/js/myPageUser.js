
//주소찾기 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();

            modifyCheck3();
        }
    }).open();
}
//
function btnBack(){
    history.go(0);
}
// 수정사항 체크
$(document).ready(function () {
    // 초기에 메시지를 " "로 설정 (테스트용)
    $('.modify-message').text("");
    // 수정 가능한 입력 필드에 대해서 keyup 이벤트 처리
    $('.user-email-input, .mypage-addr-detail').on('keyup', function () {
        modifyCheck();
    });
});

function modifyCheck() {
    // 수정사항이 존재할때
    if (storedEmail !== $('.user-email-input').val() ||  storedAddDetail !== $('.mypage-addr-detail').val()) {
        // "수정사항이 존재합니다" 라는 메시지를 빨간색으로 표시
        $('.modify-message')
            .text("수정사항이 존재합니다")
            .css("color", "red");
    } else {
        // 다시 메시지를 초기화 (테스트용)
        $('.modify-message')
            .text("")
    }
}

function modifyCheck2() {
    // 수정사항이 존재할때
    if (storedPh !== $('.user-phone-input-none').val() ) {
        // "수정사항이 존재합니다" 라는 메시지를 빨간색으로 표시
        $('.modify-message')
            .text("수정사항이 존재합니다")
            .css("color", "red");
    } else {
        // 다시 메시지를 초기화 (테스트용)
        $('.modify-message')
            .text("")
    }
}
function modifyCheck3() {
    // 수정사항이 존재할때
    if ( storedAddr !== $('.mypage-addr-addr').val() ) {
        // "수정사항이 존재합니다" 라는 메시지를 빨간색으로 표시
        $('.modify-message')
            .text("수정사항이 존재합니다")
            .css("color", "red");
    } else {
        // 다시 메시지를 초기화 (테스트용)
        $('.modify-message')
            .text("")
    }
}




//번호 변경창(모달)
let changeModalPh = document.querySelector(".user-phone-btn");
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
                document.querySelector("#userPhone").value = userPhone;
                PhoneCheck();
                modifyCheck2();
                closePh();


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

changeModal.addEventListener('click', function (event) {
    event.preventDefault();
    modal.style.display = "flex";
    pw.style.display = "flex";
    xBox.style.display = "flex";
    changeBtn.style.display = "block";
});

xBox.addEventListener('click', function () {
    modal.style.display = "none";
    xBox.style.display = "none";
    pw.style.display = "none";
    changeBtn.style.display = "none";
});


// const storedPassword = '[[${List.userPassword}]]';
const passwordInput = document.getElementById('passwordInput');
const passwordMismatchMessage = document.getElementById('passwordMismatchMessage');
const changeButton = document.querySelector('.user-change-btn');
const passwordInputs = document.querySelectorAll('.user-password-input2, .user-password-input3');
const confirmDiv = document.querySelector('.Confirm');

const handlePasswordInput = () => {
    passwordMismatchMessage.style.display = (passwordInput.value === storedPassword && passwordInput.value) ? 'none' : 'block';
    checkPasswordsMatch();
};

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
    if (passwordInput.value !== storedPassword || !passwordInput.value) {
        alert('현재 비밀번호를 입력하세요.');
        event.preventDefault();
    } else if (!passwordInputs[0].value || !passwordInputs[1].value) {
        alert('변경할 비밀번호를 입력하세요.');
        event.preventDefault();
    } else if (passwordInputs[0].value !== passwordInputs[1].value) {
        alert('비밀번호가 일치하지 않습니다.');
        event.preventDefault();
    } else {
        alert('비밀번호가 변경되었습니다.');
    }
};
passwordInput.addEventListener('input', handlePasswordInput);
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