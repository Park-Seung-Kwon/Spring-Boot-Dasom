let $input = $('#post-image');
let $imgList = $('.img-list');
// console.log($input);

// file change이벤트로 미리보기 갱신하기
$input.on('change', function () {
    let files = this.files;
    //   console.log(files);

    // 길이 체크함수 (files, 원하는 길이)
    let newFiles = checkLength(files, 1);

    appendImg(newFiles);
});

// 클릭 이벤트로 이미지 지우고 미리보기 갱신하기
$imgList.on('click', function (e) {
    let name = $(e.target).data('name');
    removeImg(name);
    appendImg($input[0].files);
});

//미리보기 삭제 함수
function removeImg(name) {
    let dt = new DataTransfer();

    let files = $input[0].files;

    for (let i = 0; i < files.length; i++) {
        if (files[i].name !== name) {
            dt.items.add(files[i]);
        }
    }
    $input[0].files = dt.files;
}

//파일 길이 체크 함수(체크할 files객체, 제한할 길이)
function checkLength(files, num) {
    if (files.length > num) {
        alert(`파일은 최대 ${num}개까지만 첨부 가능합니다.`);
        // 최대 수를 넘으면 비어있는 files객체 반환
        return new DataTransfer().files;
    }
    return files;
}

// 이미지 미리보기 처리 함수
// 이미지 수가 4개보다 적으면 기본이미지로 대체함
function appendImg(files) {
    for (let i = 0; i < 1; i++) {
        if (i < files.length) {
            let src = URL.createObjectURL(files[i]);

            $imgList.eq(i).css('background-image', `url(${src})`).css('background-size', 'cover').data('name', `${files[i].name}`);

            $imgList.eq(i).addClass('x-box');
        } else {
            $imgList
                .eq(i)
                .css(
                    'background',
                    'url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIzNiIgaGVpZ2h0PSIzNiI+PGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48ZyBzdHJva2U9IiNCNUI1QjUiIHN0cm9rZS13aWR0aD0iMS41IiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg0IDQpIj48cmVjdCB3aWR0aD0iMjgiIGhlaWdodD0iMjgiIHJ4PSIzLjUiLz48Y2lyY2xlIGN4PSI4LjU1NiIgY3k9IjguNTU2IiByPSIyLjMzMyIvPjxwYXRoIGQ9Ik0yOCAxOC42NjdsLTcuNzc3LTcuNzc4TDMuMTExIDI4Ii8+PC9nPjxwYXRoIGQ9Ik0wIDBoMzZ2MzZIMHoiLz48L2c+PC9zdmc+) no-repeat 50% #f2f2f2'
                )
                .data('name', null);
            $imgList.eq(i).removeClass('x-box');
        }
    }
}

// 이미지 띄우기 처리
displayAjax();

function displayAjax() {
    let donateWriteNumber = $('.board-num').val();
    console.log("donateWriteNumber : " + donateWriteNumber);
    $.ajax({
        url: '/donateFile/imgList',
        type: 'get',
        data: { donateWriteNumber: donateWriteNumber },
        success: function (fileList) {
            console.log("fileList : " + fileList);
            let text = '';

            let fileName = fileList.donateFileUploadPath + '/' + fileList.donateFileUuid + '_' + fileList.donateFileName;
            console.log("fileName : " + fileName);

            text += `<img src="/donateFile/display?fileName=${fileName}" data-number=${fileList.donateFileNumber} class="post-image2" />`;

            $('.post-images').html(text);

            // 이미지가 생성된 후에 클릭 이벤트 처리기를 추가
            let bigImgWrap = $('.big-img-wrap');

            // 이미지가 생성된 후에 클릭 이벤트 처리기를 추가
            let testImg = document.getElementsByClassName('post-image2');

            for (let i = 0; i < testImg.length; i++) {
                testImg[i].addEventListener("click", function () {
                    let src = this.getAttribute('src');
                    console.log(src);
                    console.log(this);

                    let bigImg = document.querySelector('.big-img');
                    bigImg.setAttribute('src', src);

                    // 해당 이미지와 관련된 bigImgWrap을 찾아서 표시
                    let parentBigImgWrap = document.querySelector('.big-img-wrap'); // 변경된 부분
                    parentBigImgWrap.style.display = 'flex';
                });
            }

            // 모든 bigImgWrap 요소에 클릭 이벤트를 주기 위해서 반복문 사용
            for (let i = 0; i < bigImgWrap.length; i++) {
                bigImgWrap[i].addEventListener('click', function () {
                    this.style.display = 'none';
                });
            }
        }
    });
}