package com.aos.api.crud.controller;

import com.aos.api.crud.dto.AosEventCrudRequest;
import com.aos.api.crud.dto.AosEventCrudResponse;
import com.aos.api.crud.service.AosEventCrudService;
import com.aos.api.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "AOS Event API", description = "자동차 AOS 연계 데이터 CRUD API")
public class AosEventCrudController {

    private final AosEventCrudService service;

    public AosEventCrudController(AosEventCrudService service) {
        this.service = service;
    }

    @PutMapping("/{receiptNo}")
    @Operation(summary = "AOS 데이터 수정", description = "접수번호를 기준으로 자동차 AOS 연계 데이터를 수정합니다.")
    public ApiResponse<AosEventCrudResponse> update(
            @Parameter(description = "수정 대상 접수번호", example = "AOS-20260907-0001")
            @PathVariable String receiptNo,
            @Valid @RequestBody AosEventCrudRequest request) {
        return ApiResponse.ok("AOS 데이터 수정 완료", service.update(receiptNo, request));
    }

    @DeleteMapping("/{receiptNo}")
    @Operation(summary = "AOS 데이터 삭제", description = "접수번호를 기준으로 aos_event 데이터를 삭제합니다.")
    public ApiResponse<Void> delete(
            @Parameter(description = "삭제 대상 접수번호", example = "AOS-20260907-0001")
            @PathVariable String receiptNo) {
        service.delete(receiptNo);
        return ApiResponse.ok("AOS 데이터 삭제 완료", null);
    }
}
