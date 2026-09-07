# AOS 최소필드 API 연계 플랫폼 - Spring Boot + Swagger

제공된 `보험개발원_AOS_최소필드_API연계_플랫폼_PPT`의 최소필드 정의와 아키텍처를 기준으로 만든 실행 가능한 기본 골격입니다.

## 1. 문서 기준 구현 범위

- Event Data
  - 보험접수번호
  - 전송일시
- Vehicle Data
  - 차량번호
  - 차량 기본정보(제조사/차종/연식 등 중 필요한 값)
- Part Data
  - 품목 및 작업구분
  - OEM 품번
  - 장착 위치 및 수량
  - 파손사진 URL(선택)
- Shop Data
  - 공업사명/코드
  - 공업사 위치
  - 연락처
- API 수신/저장
- 내부 GET 조회/검색
- 외부 API POST 전송
- 외부 API GET 추출
- Swagger/OpenAPI
- 접수번호 기준 중복 방지(upsert)
- 조회 응답의 차량번호/연락처 마스킹

## 2. 중요: 실제 보험개발원 API 사양

첨부 PPT에는 실제 운영 API의 URL, HTTP Header, 인증서/mTLS 상세, 요청/응답 JSON 전문, 오류코드, 서명 규격이 포함되어 있지 않습니다.

따라서 이 프로젝트의 `ProviderApiClient`는 아래 환경변수로 실제 연계 주소를 주입하도록 만들었습니다.

```text
AOS_PROVIDER_BASE_URL=https://실제-제공기관-API
AOS_PROVIDER_TOKEN=실제-토큰
```

실제 API 명세를 받으면 URI, Header, mTLS, 암호화, 응답 매핑까지 바로 교체할 수 있습니다.

## 3. 실행

### PostgreSQL

### Spring Boot
```bash
mvn spring-boot:run
```

또는

```bash
mvn clean package
java -jar target/aos-api-swagger-1.0.0.jar
```

## 4. Swagger

브라우저:

http://localhost:8080/swagger-ui.html

OpenAPI:

http://localhost:8080/v3/api-docs

## 5. 주요 API

### 내부 데이터 수신
POST `/api/v1/events`

### 내부 목록/검색
GET `/api/v1/events?date=2026-08-13&receiptNo=AOS-000184`

### 상세
GET `/api/v1/events/AOS-000184`

### 외부 제공기관 POST
POST `/api/v1/provider/events`

### 외부 제공기관 GET
GET `/api/v1/provider/events/AOS-000184`

## 6. POST 테스트 JSON

```json
{
  "receiptNo": "AOS-000184",
  "transmittedAt": "2026-08-13T15:42:00",
  "vehicleNo": "00가70000",
  "vehicleManufacturer": "현대",
  "vehicleModel": "그랜저",
  "vehicleColor": "검정",
  "partWorkType": "휠/헤드램프, 교환",
  "oemPartNo": "92101-AB000",
  "mountPosition": "좌측",
  "quantity": 1,
  "photoUrl": "https://example.com/photos/AOS-000184.jpg",
  "shopCode": "RS-1042",
  "shopName": "OO공업사",
  "shopLocation": "대전광역시 OO구",
  "contact": "000-0000-0000"
}
```

## 7. GET 테스트

```text
GET http://localhost:8080/api/v1/events?date=2026-08-13
GET http://localhost:8080/api/v1/events/AOS-000184
```

## 8. 운영 전 필수 보완

1. 보험개발원 실제 API URL/경로 확인
2. mTLS 또는 기관 인증 방식 적용
3. API Key/Bearer/전자서명 등 실제 인증 규격 적용
4. 실제 JSON Schema에 맞춰 DTO 확정
5. 오류코드/재전송 정책 확정
6. 요청 ID/Idempotency-Key 적용
7. 감사로그 및 전송이력 테이블 분리
8. 차량번호/연락처 등 개인정보 암호화·마스킹 정책 확정
9. 사진 URL의 접근권한 및 만료정책 적용
10. 운영 DB PostgreSQL 계정/권한 분리
