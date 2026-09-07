@echo off
curl -X POST "http://localhost:8080/api/v1/events" ^
  -H "Content-Type: application/json" ^
  -d "{\"receiptNo\":\"AOS-000184\",\"transmittedAt\":\"2026-08-13T15:42:00\",\"vehicleNo\":\"00가70000\",\"vehicleManufacturer\":\"현대\",\"vehicleModel\":\"그랜저\",\"vehicleColor\":\"검정\",\"partWorkType\":\"휠/헤드램프, 교환\",\"oemPartNo\":\"92101-AB000\",\"mountPosition\":\"좌측\",\"quantity\":1,\"photoUrl\":\"https://example.com/photos/AOS-000184.jpg\",\"shopCode\":\"RS-1042\",\"shopName\":\"OO공업사\",\"shopLocation\":\"대전광역시 OO구\",\"contact\":\"000-0000-0000\"}"

echo.
echo ===== GET LIST =====
curl "http://localhost:8080/api/v1/events?date=2026-08-13"

echo.
echo ===== GET DETAIL =====
curl "http://localhost:8080/api/v1/events/AOS-000184"
