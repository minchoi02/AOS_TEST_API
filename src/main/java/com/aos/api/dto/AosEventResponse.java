package com.aos.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "AOS 이벤트 조회 응답")
public record AosEventResponse(
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
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
