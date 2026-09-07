package com.aos.api.repository;

import com.aos.api.entity.AosEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AosEventRepository
        extends JpaRepository<AosEvent, Long> {

    /**
     * 접수번호로 단건 조회
     *
     * 예:
     * AOS-20260825-ABC123
     */
    Optional<AosEvent> findByReceiptNo(
            String receiptNo
    );

    /**
     * 접수번호 중복 여부 확인
     */
    boolean existsByReceiptNo(
            String receiptNo
    );

    /**
     * AOS 접수 목록 조회
     *
     * 검색조건
     * - 전송일시
     * - 접수번호
     * - 차량번호
     * - 공업사 코드
     *
     * React 조회 화면의
     * 접수목록 / 검색 / 페이징에 사용
     */
    Page<AosEvent>
    findByTransmittedAtBetweenAndReceiptNoContainingAndVehicleNoContainingAndShopCodeContaining(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            String receiptNo,
            String vehicleNo,
            String shopCode,
            Pageable pageable
    );
}