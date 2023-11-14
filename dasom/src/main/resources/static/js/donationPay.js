$("#check_module").click(function () {
    event.preventDefault();

    // 생략가능
    var IMP = window.IMP;
    // 가맹점 식별코드 입력
    IMP.init('imp44070752');

    // input 값 받을 변수 선언
    var payAmount = $('#pay-amount').val();
    var campaign = $('#campaign').val();
    var name = $('#name').val();


    // PG상점아이디 (CID) 입력력
    IMP.request_pay({
        pg: 'kakaopay.TC0ONETIME',
        pay_method: 'card',
        merchant_uid: 'merchant_' + new Date().getTime(),

        // 결제창에서 보여질 이름
        name: '캠페인 명 : ' + campaign,
        // 후원금
        amount: '후원 금액 : ' + parseInt(payAmount),
        // 후원자 명
        vol_name: '후원자 명 :' + name,

        vol_postcode: '123-456',
    }, function (rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '결제 금액 : ' + rsp.paid_amount;
            // $('.form').submit();
            // 결제 성공 시 정보를 넘겨줘야한다면 body에 form을 만든 뒤 위의 코드를 사용하는 방법이 있습니다.
            // 자세한 설명은 구글링으로 보시는게 좋습니다.
            $('.pay-form').submit();
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
    console.log(IMP.request_pay);
});