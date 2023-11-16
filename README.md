# 팀명 : 다솜아띠

# 프로젝트 이름 : Dasom - Spring Boot Project

## 📚프로젝트 주제 

재난 재해 발생 시 후원을 할 수 있고 봉사 활동을 신청할 수 있는 사이트   
1. 후원하기
2. 봉사 신청하기
3. 내 주변 대피소 조회

## 목차
- [팀명 : 다솜아띠](#팀명--다솜아띠)
- [프로젝트 이름 : Dasom - Spring Boot Project](#프로젝트-이름--dasom---spring-boot-project)
  - [📚프로젝트 주제](#프로젝트-주제)
  - [목차](#목차)
  - [팀 구성](#팀-구성)
  - [ERD구성](#erd구성)
  - [Tools](#tools)
  - [나의 작업](#나의-작업)
    - [후원 게시판 리스트 기능소개 WIKI로 이동](#후원-게시판-리스트-기능소개-WIKI로-이동)
    - [후원 게시판 결제 페이지 WIKI로 이동](#후원-게시판-결제-페이지-기능소개-wiki로-이동)
    - [후원 완료 페이지 기능소개 WIKI로 이동](#후원-완료-페이지-기능소개-wiki로-이동)
    - [봉사 게시판 리스트 기능소개 WIKI로 이동](#봉사-게시판-리스트-기능소개-wiki로-이동)
    - [봉사 게시판 상세 페이지 기능소개 WIKI로 이동](#봉사-게시판-상세-페이지-기능소개-wiki로-이동)
    - [봉사 신청 완료 페이지 기능소개 WIKI로 이동](#봉사-신청-완료-페이지-기능소개-wiki로-이동)
    - [Interceptor 기능소개 WIKI로 이동](#Interceptor-기능소개-wiki로-이동)

## 팀 구성

|팀장|나예은|           
|부팀장|강진혁|   
|팀원|박승권|   
|팀원|이우진|   

## ERD구성

<details open>
<summary>ERD이미지</summary>

![erd](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/e33dd5c1-8ee5-46bd-90de-ba542acbc5c0)

</details>

 ## Tools
- **Java**
- **Spring Boot**
- **Intellij**
- **Mybatis**
- **Oracle**

## 나의 작업

### 후원 게시판   

#### 후원 게시판 리스트 [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/%ED%9B%84%EC%9B%90-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A6%AC%EC%8A%A4%ED%8A%B8)   
- 리스트에 보여줄 내용 불러오기 (비동기)   
- 진행중 캠페인/ 종료된 캠페인 구분하여 표시 (비동기)   
- 무한 스크롤 페이징 처리 (비동기)   

진행중 캠페인/ 종료된 캠페인   
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/0c9e52b6-291f-481e-9fec-a492e39cc1f8.gif" width="500">   
<br>
무한 스크롤 페이징    
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/1a76a139-f9c0-4aa5-8333-d6cce319b017.gif" width="500">     

<br>
<br>

#### 후원 게시판 결제 페이지 [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/%ED%9B%84%EC%9B%90-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EA%B2%B0%EC%A0%9C-%ED%8E%98%EC%9D%B4%EC%A7%80)   
- 캠페인 명, 후원자 명 불러오기   
- 금액을 입력하여 입력한 금액만큼 후원 결제   
- 카카오페이 API이용 결제(비동기)
   
카카오페이 결제   
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/0342df47-d225-4499-ba82-66370127ea20.gif" width="500">   
<br>
<br>

#### 후원 완료 페이지 [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/%ED%9B%84%EC%9B%90-%EC%99%84%EB%A3%8C-%ED%8E%98%EC%9D%B4%EC%A7%80) 
- 후원자 이름 불러와 감사 인사 출력   
- 메인페이지/ 마이페이지 후원 내역 페이지로 이동 가능한 버튼 배치
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/20c8b4f4-05d0-4173-afd3-25ee8853f9f5.gif" width="500">
<br>

### 봉사 게시판   
    
#### 봉사 게시판 리스트 [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/%EB%B4%89%EC%82%AC-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A6%AC%EC%8A%A4%ED%8A%B8)    
- 리스트에 보여줄 내용 불러오기 (비동기)   
- 진행중 캠페인/ 종료된 캠페인 구분하여 표시 (비동기)   
- 무한 스크롤 페이징 처리 (비동기)
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/7bcd2baa-9b29-43e6-9f1c-afac2fb18a13.gif" width="500">
<br>
<br>

#### 봉사 게시판 상세 페이지 [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/%EB%B4%89%EC%82%AC-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EC%83%81%EC%84%B8-%ED%8E%98%EC%9D%B4%EC%A7%80)   
- 캠페인 정보 불러와 출력
- 첨부한 캠페인 포스터 이미지 출력
- 봉사 참여 버튼 클릭하여 봉사 참여 신청
- 이미 신청한 봉사 중복 체크
  
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/e9da0b14-b77d-4c14-adad-dccfc338ed35.gif" width="500">   
<br>
<br>

이미 신청한 봉사   
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/5650a503-34f4-449a-a12f-46d0dddfe41a.gif" width="500">
<br>
<br>

#### 봉사 신청 완료 페이지 [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/%EB%B4%89%EC%82%AC-%EC%8B%A0%EC%B2%AD-%EC%99%84%EB%A3%8C%ED%8E%98%EC%9D%B4%EC%A7%80)      
- 봉사 신청자 이름 불러와 감사 인사 출력   
- 메인페이지/ 마이페이지 봉사 신청 내역 페이지로 이동 가능한 버튼 배치   
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/d671975c-dbad-4a60-b822-a03a20423440.gif" width="500">
<br>   
<br>

### Interceptor [기능소개 WIKI로 이동](https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/wiki/Interceptor)   
- 후원/봉사 게시물 열람 시 로그인 여부 확인
  
<img src="https://github.com/Park-Seung-Kwon/Spring-Boot-Dasom/assets/112665201/ed3fc747-034a-45c5-8014-b618550494cf.gif" width="500">
   

