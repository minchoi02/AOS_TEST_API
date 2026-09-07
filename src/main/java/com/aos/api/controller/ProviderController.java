package com.aos.api.controller;

import com.aos.api.client.ProviderApiClient;
import com.aos.api.dto.ApiResponse;
import com.aos.api.dto.AosEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/provider")
@Tag(name = "Provider Integration API", description = "보험개발원/외부 제공기관 API 연계")
public class ProviderController {

    private final ProviderApiClient client;

    public ProviderController(ProviderApiClient client) {
        this.client = client;
    }

    @PostMapping("/events")
    @Operation(summary = "외부 제공기관으로 데이터 전송(POST)",
            description = "설정된 aos.provider.base-url의 /api/events로 JSON을 전송합니다.")
    public ApiResponse<String> send(@Valid @RequestBody AosEventRequest request) {
        return ApiResponse.ok("외부 API POST 전송 완료", client.postEvent(request));
    }

    @GetMapping("/events/{receiptNo}")
    @Operation(summary = "외부 제공기관 데이터 추출(GET)",
            description = "설정된 aos.provider.base-url의 /api/events/{receiptNo}에서 원문 응답을 가져옵니다.")
    public ApiResponse<String> extract(@PathVariable String receiptNo) {
        return ApiResponse.ok("외부 API GET 추출 완료", client.getEvent(receiptNo));
    }
}
