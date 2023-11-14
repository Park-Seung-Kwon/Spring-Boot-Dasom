

// 체크박스 요소와 로컬 스토리지 키를 설정합니다.
const checkBox = document.getElementById('chk');
const loginBtn = document.querySelector(".login-btn");
const loginForm = document.querySelector(".login-form");
const storageKey = 'savedUserId';

// 페이지가 로드될 때, 로컬 스토리지에서 아이디를 가져와서 체크박스를 업데이트합니다.
window.addEventListener('load', () => {
    const savedUserId = localStorage.getItem("userId");
    if (savedUserId) {
        checkBox.checked = true;
        document.getElementById('userId').value = savedUserId;
    }
});

loginBtn.addEventListener('click', function (){
    const userIdInput = document.getElementById('userId');

    if(checkBox.checked){
        const  userId = userIdInput.value;
        localStorage.setItem("userId", userId);
    }else {
        localStorage.removeItem("userId");
    }
    loginForm.submit();
});