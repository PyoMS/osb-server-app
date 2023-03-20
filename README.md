# OSB 검색서버

## [Jar File Download](/osb-server-app.jar)

### 1. 기본기능

a. 블로그 검색 기능

b. 인기 검색어 TOP 10 조회 기능


### 2. 추가 기능 라이브러리 항목
1. Swagger2 : RestfulAPI 명세서 제공 및 테스트 환경 제공 
2. QueryDSL : 동적 쿼리 제공 및 컴파일 단계에서 문법오류 확인


### 3. Config / Advice 항목
[1. JpaConfig](/osb-core/src/main/java/com/pms/osb/config/JpaConfig.java) : QueryDSL 라이브러리 사용을 위한 Config 설정

[2. SwaggerConfig](/osb-api/src/main/java/com/pms/osb/common/config/SwaggerConfig.java) : Swagger 환경 구축을 위한 Config

[3. RestTemplateConfig](/osb-api/src/main/java/com/pms/osb/api/service/externalapi/config/RestTemplateConfig.java) : RestTemplate 사용 시 Connection timeout 및 최대 연결 세션 설정. @LoadBalanced 사용

[4. ApiCommonAdvice](/osb-api/src/main/java/com/pms/osb/common/advice/ApiCommonAdvice.java) : Exception 처리 Advice

### 4. Module 분할

- osb-core : domain, repository, querydslConfig 클래스 
- osb-api : service, api, 외부api, web exception(ApiCommonAdvice)


### 5. [외부 API 5xx 장애 시 Naver API로 전환](/osb-api/src/main/java/com/pms/osb/api/service/osblog/ObsLogService.java)

---