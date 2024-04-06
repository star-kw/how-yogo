-----
### Folder Structure
-----
< 파일 구조 설명 >
1. Controller : URL에 따라 요청을 제일 먼저 받아서 처리하는 Servlet 파일 보관
2. Service : Controller와 DAO 사이에서 비즈니스 로직(데이터를 가져와서 2차적으로 가공)을 작성하는 클래스 파일 보관
3. DAO : DB와 통신하여 데이터를 가져오고 DTO 객체로 저장하여 서비스 클래스로 전달
4. DTO
```
  - Request DTO : 요청 객체로 사용자가 서버쪽으로 데이터를 전달할 때 받을 DTO 선언
  - Response DTO : 응답 객체로 DB의 데이터를 해당 DTO로 받아서 최종적으로 사용자에게 반환할 DTO 선언
```

< Java >
```
src/main 
       ├─java
          ├─ Member (회원)
          │      ├─ Controller
          │      ├─ Service
          │      ├─ Filter
          │      ├─ DAO
          │      ├─ DTO
          │           ├─ Request
          │           ├─ Response
          │
          │
          ├─ Board (게시판)
          │      ├─ Notice (공지사항)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │        
          │      │  
          │      ├─ QNA (질의사항)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │  
          │      ├─ Recipe (레시피 게시판)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │  
          │      ├─ Together (같이 요리 게시판)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │     
          │      ├─ RequestRecipe (요리 신청 게시판)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │                 
          │      ├─ Magazine (매거진 게시판)
          │              ├─ Controller
          │              ├─ Service
          │      │      ├─ Filter
          │              ├─ DAO
          │              ├─ DTO
          │                   ├─ Request
          │                   ├─ Response
          │
          │
          ├─ Comment (댓글 관련)
          │      ├─ Notice (공지사항)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │        
          │      │  
          │      ├─ QNA (질의사항)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │  
          │      ├─ Recipe (레시피 게시판)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │  
          │      ├─ Together (같이 요리 게시판)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │     
          │      ├─ RequestRecipe (요리 신청 게시판)
          │      │      ├─ Controller
          │      │      ├─ Service
          │      │      ├─ Filter
          │      │      ├─ DAO
          │      │      ├─ DTO
          │      │           ├─ Request
          │      │           ├─ Response
          │      │                 
          │      ├─ Magazine (매거진 게시판)
          │              ├─ Controller
          │              ├─ Service
          │      │      ├─ Filter
          │              ├─ DAO
          │              ├─ DTO
          │                   ├─ Request
          │                   ├─ Response
          │
          │
          ├─ Common (프로젝트 전체에서 공유할 파일(Header, Footer 등)
          │
          ├─ Config (환경 설정 파일 보관)
```

< JSP / Web >
```
       ├─webapp 
          ├─ Resource (CSS, Image, JS 파일 보관)
          │          ├─ CSS
          │          │    ├─ Member
          │          │    ├─ Board
          │          │    ├─ Comment
          │          │    ├─ Common
          │          │
          │          │
          │          ├─ Image
          │          │    ├─ Member
          │          │    ├─ Board
          │          │    ├─ Comment
          │          │    ├─ Common
          │          │
          │          │
          │          ├─ JS
          │               ├─ Member
          │               ├─ Board
          │               ├─ Comment
          │               ├─ Common
          │
          │
          │
          │
          │
          │
          ├─ WEB-INF
          │      ├─ lib
          │      ├─ web.xml
          │      ├─ Views (JSP Page 파일 보관)
          │     	├─ member
          │      	├─ board
          │      		├─ Magazine
          │      		├─ Notice
          │      		├─ QNA
          │      		├─ Recipe
          │      		├─ RequestRecipe
          │      		├─ Together
          │      	├─ comment
          │      	├─ common
          │
          │
          ├─ META-INF
```
