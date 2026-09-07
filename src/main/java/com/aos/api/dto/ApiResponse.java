package com.aos.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공통 API 응답")
public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "정상 처리되었습니다.", data);
    }
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }
}
