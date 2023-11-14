// 페이지 로딩 시
$(document).ready(function() {
    var urlParams = new URLSearchParams(window.location.search);
    var keywordParam = urlParams.get('keyword');
    if (keywordParam) {
        $('.search-bar').val(decodeURIComponent(keywordParam)); // 검색어 입력란에 검색어 설정
    }
});

// 봉사글 삭제(비동기)
function deleteCs(csWriteNumber) {

    let confirmDelete = confirm(csWriteNumber+"번 봉사모집글을 삭제하시겠습니까?");

    if (confirmDelete) {
        console.log("번호 : " + csWriteNumber);
        $.ajax({
            url: "/csWrite/remove",
            type: "GET",
            data: { csWriteNumber: csWriteNumber },
            success: function(response) {
                if (response === "삭제 성공!") {
                    alert("봉사글 삭제 완료!");
                    location.reload();
                } else {
                    alert("봉사글 삭제 실패!");
                }
            },
            error: function() {
                alert("오류!");
            }
        });
    }else {
        alert("삭제를 취소하셨습니다");
    }
}

//  봉사 모집완료(비동기)
function recruitCs(csWriteNumber) {

    let confirmRecruit = confirm(csWriteNumber+"번 봉사모집을 완료하시겠습니까?");
    if (confirmRecruit) {
        console.log("번호 : " + csWriteNumber);
        $.ajax({
            url: "/csWrite/recruit",
            type: "GET",
            data: {csWriteNumber: csWriteNumber},
            success: function (response) {
                if (response === "모집완료 성공!") {
                    alert("봉사 모집 완료!");
                    location.reload();
                } else {
                    alert("봉사 모집 완료 실패!");
                }
            },
            error: function () {
                alert("오류!");
            }
        });
    }else {
        alert("모집완료를 취소하셨습니다");
    }
};

//봉사자 리스트 모달
document.addEventListener('DOMContentLoaded', function() {
    let modal = document.querySelector(".cs-user-modal");
    let xBox = document.querySelector('.modal-close');
    let campaignLinks = document.querySelectorAll('.campaign-btn');
    let csUserTitle = document.querySelector('.cs-user-title');
    let csWriteNumber = document.querySelector('.csNum');

    campaignLinks.forEach(function(campaignLink) {
        campaignLink.addEventListener('click', function(event) {
            event.preventDefault();

            // 클릭된 링크의 데이터를 가져와서 csWriteNumber 설정
            let csWriteNumber = $(this).data('number');

            modal.style.display = "block";

            function getList(csWriteNumber, callback){
                $.ajax({
                    url : `/adCsRest/find/${csWriteNumber}`,
                    type : 'get',
                    dataType : 'json',
                    success : function (result) {
                        if(callback){
                            callback(result);
                        }
                    },
                    error : function(a, b, c) {
                        console.error(c);
                    }
                });
            }

            getList(csWriteNumber, showCsUser);

            function showCsUser(result){
                console.log(result);

                let text ='';

                result.forEach( r => {

                    text += `
                    <ul class="cs-cate">
                        <li class="cs-user-num">${r.userNumber}</li>
                        <li class="cs-user-id">${r.userId}</li>
                        <li class="cs-user-name">${r.userName}</li>
                        <li class="cs-user-phone">${r.userPhone}</li>
                        <li class="cs-user-email">${r.userEmail}</li>
                        <li class="cs-user-date">${r.csApplyDate}</li>
                    </ul>

                  `;
                });

                $('.cs-cate-bottom').html(text);
            }
        });
    });

    xBox.addEventListener('click', function() {
        modal.style.display = "none";
    });
});


// 봉사지원자 리스트에 봉사글 제목 가져오기
var campaignBtns = document.querySelectorAll(".campaign-btn");

campaignBtns.forEach(function(btn) {
    btn.addEventListener("click", function(event) {

        var csWriteTitle = btn.textContent;

        var csUserTitle = document.querySelector(".cs-user-title");
        csUserTitle.textContent = "[봉사모집] " + csWriteTitle + " 지원자 목록";
    });
});


