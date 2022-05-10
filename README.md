### Spring Features

- 스프링 MVC - 기본 기능 - 프로젝트 생성
- 스프링 MVC - 기본 기능 - 로깅 간단히 알아보기
- 스프링 MVC - 기본 기능 - 요청 매핑
- 스프링 MVC - 기본 기능 - 요청 매핑 - API 예시
- 스프링 MVC - 기본 기능 - HTTP 요청 - 기본, 헤더 조회
- 스프링 MVC - 기본 기능 - HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form 6. 스프링 MVC - 기본 기능 - HTTP 요청 파라미터 - @RequestParam
- 스프링 MVC - 기본 기능 - HTTP 요청 파라미터 - @ModelAttribute
- 스프링 MVC - 기본 기능 - HTTP 요청 메시지 - 단순 텍스트
- 스프링 MVC - 기본 기능 - HTTP 요청 메시지 - JSON
- 스프링 MVC - 기본 기능 - HTTP 응답 - 정적 리소스, 뷰 템플릿
- 스프링 MVC - 기본 기능 - HTTP 응답 - HTTP API, 메시지 바디에 직접 입력 6. 스프링 MVC - 기본 기능 - HTTP 메시지 컨버터
- 스프링 MVC - 기본 기능 - 요청 매핑 헨들러 어뎁터 구조
- 스프링 MVC - 기본 기능 - 정리

#### HttpEntity

- HTTP header, body 정보를 편리하게 조회
    - 메시지 바디 정보를 직접 조회
    - 요청 파라미터를 조회하는 기능과 관계 없음 - @RequestParam X @ModelAttribute X

- `HttpEntity`는 응답에도 사용 가능
    - 메시지 바디 정보 직접 변환
    - 헤더 정보 포함 가능
    - view 조회 X

- `HttpEntity`를 상속받은 다음 객체들도 같은 기능을 제공한다
- `RequestEntity`
    - `HttpMethod`, url 정보가 추가, 요청에서 사용
- `ResponseEntity`
    - HTTP 상태 코드 설정 가능, 응답에서 사용
    - `return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED)`