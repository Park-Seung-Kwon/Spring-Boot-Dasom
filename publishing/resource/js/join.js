 // '출생 연도' 셀렉트 박스 option 목록 동적 생성
 const birthYear = document.querySelector('#birth-year')
 // option 목록 생성 여부 확인
 isYearOptionExisted = false;
 birthYear.addEventListener('focus', function () {
 // year 목록 생성되지 않았을 때 (최초 클릭 시)
 if(!isYearOptionExisted) {
 isYearOptionExisted = true
 for(var i = 1940; i <= 2022; i++) {
 // option element 생성
 const yearOption = document.createElement('option')
 yearOption.setAttribute('value', i)
 yearOption.innerText = i
 // birthYear의 자식 요소로 추가
 this.appendChild(yearOption);
      }
     }
 });

 // '출생 연도' 셀렉트 박스 option 목록 동적 생성
 const birthMonth = document.querySelector('#birth-month')
 // option 목록 생성 여부 확인
 isMonthOptionExisted = false;
 birthMonth.addEventListener('focus', function () {
 // month 목록 생성되지 않았을 때 (최초 클릭 시)
 if(!isMonthOptionExisted) {
 isMonthOptionExisted = true
 for(var i = 1; i <= 12; i++) {
 // option element 생성
 const monthOption = document.createElement('option')
 monthOption.setAttribute('value', i)
 monthOption.innerText = i
 // birthMonth의 자식 요소로 추가
 this.appendChild(monthOption);
      }
     }
 });

  // '출생 연도' 셀렉트 박스 option 목록 동적 생성
  const birthDay = document.querySelector('#birth-day')
 // option 목록 생성 여부 확인
 isDayOptionExisted = false;
 birthDay.addEventListener('focus', function () {
 // month 목록 생성되지 않았을 때 (최초 클릭 시)
 if(!isMonthOptionExisted) {
 isDayOptionExisted = true
 for(var i = 1; i <= 31; i++) {
 // option element 생성
 const dayOption = document.createElement('option')
 dayOption.setAttribute('value', i)
 dayOption.innerText = i
 // birthMonth의 자식 요소로 추가
 this.appendChild(dayOption);
      }
     }
 });

//주소찾기 api
 function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
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
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
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
        }
    }).open();
}

//개인정보처리 모달창으로 불러오기
$(document).ready(function () {
    $("#termOfUse").load("/include/termOfUse.html", function () {

        
    });
})

let termOfUse= document.querySelector(".join-termOfUse-modal");
let modalTermOfUse = document.querySelector(".modal-termOfUse");
let xBox = document.querySelector('.modal-termOfUse-close');
let section5 = document.querySelector('.main-section5');

termOfUse.addEventListener('click',function(event){
    event.preventDefault();
    modalTermOfUse.style.display="block";
    xBox.style.display="block";
    section5.style.display="flex";
});

xBox.addEventListener('click',function(){
    modalTermOfUse.style.display="none";
    xBox.style.display="none";
    section5.style.display="none";
});


//비밀번호 일치
$(document).ready(function(){
    $('.Confirm')
        .text("비밀번호를 입력해주세요")
        .css("color", "gray");
});

function pwCheck() {
    if($('.join-pw-input').val() ==="" &$('.join-pwck-input').val()==="" ){
        $('.Confirm')
        .text("비밀번호를 입력해주세요")
        .css("color", "gray");
    }else if ($('.join-pw-input').val() === $('.join-pwck-input').val()) {
      $('.Confirm')
        .text("비밀번호가 일치합니다")
        .css("color", "green");
    }else if($('.join-pw-input').val() !== $('.join-pwck-input').val()){
      $('.Confirm')
        .text("비밀번호가 일치하지 않습니다")
        .css("color", "red");
    }else{
        $('.Confirm')
        .text("비밀번호를 입력해주세요")
        .css("color", "gray");
    }
}

//인증시간 카운트 다운
var countdownInterval;
var secondsRemaining = 240; // 4분 = 240초

function startCountdown() {
    var phoneCheckDiv = document.querySelector(".phone-check");
    phoneCheckDiv.style.display = "flex";

    var countdownDiv = document.getElementById("countdown");
    countdownDiv.style.display = "block";

    countdownDiv.innerHTML = formatTime(secondsRemaining);

    countdownInterval = setInterval(function() {
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