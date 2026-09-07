package com.aos.api.controller;

import com.aos.api.dto.*;
import com.aos.api.service.AosEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "AOS Event API", description = "AOS 최소필드 수신 및 조회 API")
public class AosEventController {

    private final AosEventService service;

    public AosEventController(AosEventService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "이벤트 데이터 수신(POST)",
            description = "보험접수번호, 전송일시, 차량, 부품, 공업사 최소필드를 JSON으로 수신하고 저장합니다. 동일 접수번호는 중복 저장하지 않고 갱신합니다.")
    public ApiResponse<AosEventResponse> receive(@Valid @RequestBody AosEventRequest request) {
        return ApiResponse.ok("이벤트 수신 및 저장 완료", service.receive(request));
    }

    @GetMapping("/{receiptNo}")
    @Operation(summary = "접수번호 상세 조회(GET)")
    public ApiResponse<AosEventResponse> detail(
            @Parameter(description = "보험접수번호", example = "AOS-000184")
            @PathVariable String receiptNo) {
        return ApiResponse.ok(service.getByReceiptNo(receiptNo));
    }

    @GetMapping
    @Operation(summary = "이벤트 목록 조회/검색(GET)",
            description = "날짜, 접수번호, 차량번호, 공업사 코드로 검색합니다.")
    public ApiResponse<PageResponse<AosEventResponse>> search(
            @Parameter(description = "전송일 기준 날짜", example = "2026-08-13")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,

            @Parameter(description = "접수번호", example = "AOS-000184")
            @RequestParam(required = false) String receiptNo,

            @Parameter(description = "차량번호", example = "00가70000")
            @RequestParam(required = false) String vehicleNo,

            @Parameter(description = "공업사 코드", example = "RS-1042")
            @RequestParam(required = false) String shopCode,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return ApiResponse.ok(service.search(date, receiptNo, vehicleNo, shopCode, page, size));
    }
}
