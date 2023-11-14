// 페이지 로딩 시
$(document).ready(function() {
    var urlParams = new URLSearchParams(window.location.search);
    var keywordParam = urlParams.get('keyword');
    if (keywordParam) {
        $('.search-bar').val(decodeURIComponent(keywordParam)); // 검색어 입력란에 검색어 설정
    }
});

//  회원정보 삭제(비동기)
function deleteUser(userNumber) {

    let confirmDelete = confirm(userNumber+"번 회원정보를 삭제하시겠습니까?");

    if (confirmDelete) {
        console.log("번호 : " + userNumber);
        $.ajax({
            url: "/adUser/remove",
            type: "GET",
            data: {userNumber: userNumber},
            success: function (response) {
                if (response === "삭제 성공!") {
                    alert("회원정보 삭제 완료!");
                    location.reload();
                } else {
                    alert("회원정보 삭제 실패!");
                }
            },
            error: function () {
                alert("오류!");
            }
        });
    }else {
        alert("삭제를 취소하셨습니다");
    }
};