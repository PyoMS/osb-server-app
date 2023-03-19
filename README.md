# OSB 검색서버

### 1. 기본기능

a. 블로그 검색 기능

b. 인기 검색어 TOP 10 조회 기능


### 2. 추가 기능 라이브러리 항목
1. Swagger2 : RestfulAPI 명세서 제공 및 테스트 환경 제공 
2. QueryDSL : 동적 쿼리 제공 및 컴파일 단계에서 문법오류 확인


### 3. Config / Advice 항목
[1. JpaConfig](/src/main/java/com/osb/osbserverapp/config/JpaConfig.java) : QueryDSL 라이브러리 사용을 위한 Config 설정

[2. SwaggerConfig](/src/main/java/com/osb/osbserverapp/config/SwaggerConfig.java) : Swagger 환경 구축을 위한 Config

[3. RestTemplateConfig](/src/main/java/com/osb/osbserverapp/externalapi/config/RestTemplateConfig.java) : RestTemplate 사용 시 Connection timeout 및 최대 연결 세션 설정. @LoadBalanced 사용

[4. ApiCommonAdvice](/src/main/java/com/osb/osbserverapp/common/advice/ApiCommonAdvice.java) : Exception 처리 Advice

---
### Jar Download

[osb-server-app](/osb-server-app.jar)