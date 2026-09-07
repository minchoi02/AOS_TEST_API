package com.aos.api.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "자동차 AOS 연계 데이터 CRUD 응답")
public record AosEventCrudResponse(
        Long id,
        String receiptNo,
        LocalDateTime transmittedAt,
        String vehicleNo,
        String vehicleManufacturer,
        String vehicleModel,
        String vehicleColor,
        String partWorkType,
        String oemPartNo,
        String mountPosition,
        Integer quantity,
        String photoUrl,
        String shopCode,
        String shopName,
        String shopLocation,
        String contact,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
