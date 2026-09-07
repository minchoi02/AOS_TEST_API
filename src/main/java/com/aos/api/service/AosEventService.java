package com.aos.api.service;

import com.aos.api.dto.AosEventRequest;
import com.aos.api.dto.AosEventResponse;
import com.aos.api.dto.PageResponse;
import com.aos.api.entity.AosEvent;
import com.aos.api.repository.AosEventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AosEventService {

    private final AosEventRepository repository;

    public AosEventService(AosEventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AosEventResponse receive(AosEventRequest req) {
        AosEvent event = repository.findByReceiptNo(req.getReceiptNo()).orElseGet(AosEvent::new);

        event.setReceiptNo(req.getReceiptNo());
        event.setTransmittedAt(req.getTransmittedAt());
        event.setVehicleNo(req.getVehicleNo());
        event.setVehicleManufacturer(req.getVehicleManufacturer());
        event.setVehicleModel(req.getVehicleModel());
        event.setVehicleColor(req.getVehicleColor());
        event.setPartWorkType(req.getPartWorkType());
        event.setOemPartNo(req.getOemPartNo());
        event.setMountPosition(req.getMountPosition());
        event.setQuantity(req.getQuantity());
        event.setPhotoUrl(req.getPhotoUrl());
        event.setShopCode(req.getShopCode());
        event.setShopName(req.getShopName());
        event.setShopLocation(req.getShopLocation());
        event.setContact(req.getContact());

        return toResponse(repository.save(event));
    }

    @Transactional(readOnly = true)
    public AosEventResponse getByReceiptNo(String receiptNo) {
        return repository.findByReceiptNo(receiptNo)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("접수번호를 찾을 수 없습니다: " + receiptNo));
    }

    @Transactional(readOnly = true)
    public PageResponse<AosEventResponse> search(
            LocalDate date,
            String receiptNo,
            String vehicleNo,
            String shopCode,
            int page,
            int size) {

        LocalDate target = date == null ? LocalDate.now() : date;
        LocalDateTime from = target.atStartOfDay();
        LocalDateTime to = target.plusDays(1).atStartOfDay().minusNanos(1);

        Page<AosEvent> result = repository
                .findByTransmittedAtBetweenAndReceiptNoContainingAndVehicleNoContainingAndShopCodeContaining(
                        from,
                        to,
                        safe(receiptNo),
                        safe(vehicleNo),
                        safe(shopCode),
                        PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 100),
                                Sort.by(Sort.Direction.DESC, "transmittedAt")));

        return new PageResponse<>(
                result.map(this::toResponse).getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages());
    }

    private String safe(String value) {
        return StringUtils.hasText(value) ? value : "";
    }

    private AosEventResponse toResponse(AosEvent e) {
        return new AosEventResponse(
                e.getId(),
                e.getReceiptNo(),
                e.getTransmittedAt(),
                maskVehicleNo(e.getVehicleNo()),
                e.getVehicleManufacturer(),
                e.getVehicleModel(),
                e.getVehicleColor(),
                e.getPartWorkType(),
                e.getOemPartNo(),
                e.getMountPosition(),
                e.getQuantity(),
                e.getPhotoUrl(),
                e.getShopCode(),
                e.getShopName(),
                e.getShopLocation(),
                maskContact(e.getContact()),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }

    private String maskVehicleNo(String v) {
        if (!StringUtils.hasText(v) || v.length() <= 4) return v;
        return v.substring(0, Math.min(2, v.length())) + "****" + v.substring(v.length() - 2);
    }

    private String maskContact(String v) {
        if (!StringUtils.hasText(v)) return v;
        String digits = v.replaceAll("[^0-9]", "");
        if (digits.length() >= 8) {
            return digits.substring(0, 3) + "-****-" + digits.substring(digits.length() - 4);
        }
        return "****";
    }
}
