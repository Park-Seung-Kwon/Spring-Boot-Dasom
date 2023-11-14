// 페이지 로딩 시
$(document).ready(function() {
    var urlParams = new URLSearchParams(window.location.search);
    var keywordParam = urlParams.get('keyword');
    if (keywordParam) {
        $('.search-bar').val(decodeURIComponent(keywordParam)); // 검색어 입력란에 검색어 설정
    }
});

//  후원 글 삭제(비동기)
function deleteDonation(donateWriteNumber) {

    let confirmDelete = confirm(donateWriteNumber+"번 후원모집글을 삭제하시겠습니까?");

    if (confirmDelete) {
        console.log("번호 : " + donateWriteNumber);
        $.ajax({
            url: "/donateWrite/remove",
            type: "GET",
            data: {donateWriteNumber: donateWriteNumber},
            success: function (response) {
                if (response === "삭제 성공!") {
                    alert("후원글 삭제 완료!");
                    location.reload();
                } else {
                    alert("후원글 삭제 실패!");
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

//  후원 글 모집완료(비동기)
function recruitDonation(donateWriteNumber) {
    let confirmRecruit = confirm(donateWriteNumber+"번 후원모집을 완료하시겠습니까?");
    if (confirmRecruit) {
        console.log("번호 : " + donateWriteNumber);
        $.ajax({
            url: "/donateWrite/recruit",
            type: "GET",
            data: {donateWriteNumber: donateWriteNumber},
            success: function (response) {
                // Handle the success response here
                if (response === "모집완료 성공!") {
                    alert("후원 모집 완료!");
                    location.reload();
                } else {
                    alert("후원 모집완료 실패!");
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
    let modal = document.querySelector(".donate-user-modal");
    let xBox = document.querySelector('.modal-close');
    let campaignLinks = document.querySelectorAll('.campaign-btn');

    campaignLinks.forEach(function(campaignLink) {
        campaignLink.addEventListener('click', function(event) {
            event.preventDefault();

            // 클릭된 링크의 데이터를 가져와서 csWriteNumber 설정
            let donateWriteNumber = $(this).data('number');

            modal.style.display = "block";

            function getList(donateWriteNumber, callback){
                $.ajax({
                    url : `/adDonateRest/find/${donateWriteNumber}`,
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

            getList(donateWriteNumber, showDonateUser);

            function showDonateUser(result){
                console.log(result);

                let text ='';

                result.forEach( r => {

                    text += `
                    <ul class="donate-cate">
                        <li class="donate-user-num">${r.donateNumber}</li>
                        <li class="donate-user-id">${r.userId}</li>
                        <li class="donate-user-name">${r.userName}</li>
                        <li class="donate-user-amount">${r.donateAmount}원</li>
                        <li class="donate-user-date">${r.donateDate}</li>
                    </ul>

                  `;
                });

                $('.donate-cate-bottom').html(text);
            }

            function getAmount(donateWriteNumber, callback) {
                $.ajax({
                    url: `/adDonateRest/findAmount/${donateWriteNumber}`,
                    type: 'get',
                    dataType: 'json',
                    success: function (result) {
                        if (callback) {
                            callback(result);
                        }
                    },
                    error: function (a, b, c) {
                        console.error(c);
                    }
                });
            }

            getAmount(donateWriteNumber, showAmount);

            function showAmount(result) {
                console.log(result);

                let text = `
                    <div class="donate-write-amountCount">총모금액 : ${result.formattedAmount}원</div>
                    `;

                $('.donate-write_amount').html(text);
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

        var donateWriteTitle = btn.textContent;

        var donateTitle = document.querySelector(".donate-user-title");
        donateTitle.textContent = "[후원모금] " + donateWriteTitle + " 후원 목록";
    });
});