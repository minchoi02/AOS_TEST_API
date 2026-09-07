package com.aos.api.crud.service;

import com.aos.api.crud.dto.AosEventCrudRequest;
import com.aos.api.crud.dto.AosEventCrudResponse;
import com.aos.api.entity.AosEvent;
import com.aos.api.repository.AosEventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AosEventCrudService {

    private final AosEventRepository repository;

    public AosEventCrudService(AosEventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AosEventCrudResponse create(AosEventCrudRequest request) {
        if (repository.existsByReceiptNo(request.getReceiptNo())) {
            throw new IllegalArgumentException("이미 존재하는 접수번호입니다: " + request.getReceiptNo());
        }

        AosEvent event = new AosEvent();
        apply(event, request);
        return toResponse(repository.save(event));
    }

    @Transactional(readOnly = true)
    public Page<AosEventCrudResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public AosEventCrudResponse findByReceiptNo(String receiptNo) {
        return repository.findByReceiptNo(receiptNo)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("접수번호를 찾을 수 없습니다: " + receiptNo));
    }

    @Transactional
    public AosEventCrudResponse update(String receiptNo, AosEventCrudRequest request) {
        AosEvent event = repository.findByReceiptNo(receiptNo)
                .orElseThrow(() -> new IllegalArgumentException("접수번호를 찾을 수 없습니다: " + receiptNo));

        // PUT에서는 경로의 receiptNo를 식별자로 사용하고 본문의 receiptNo는 변경하지 않습니다.
        apply(event, request);
        event.setReceiptNo(receiptNo);
        return toResponse(repository.save(event));
    }

    @Transactional
    public void delete(String receiptNo) {
        AosEvent event = repository.findByReceiptNo(receiptNo)
                .orElseThrow(() -> new IllegalArgumentException("접수번호를 찾을 수 없습니다: " + receiptNo));
        repository.delete(event);
    }

    private void apply(AosEvent event, AosEventCrudRequest request) {
        if (request.getTransmittedAt() != null) event.setTransmittedAt(request.getTransmittedAt());
        event.setVehicleNo(request.getVehicleNo());
        event.setVehicleManufacturer(request.getVehicleManufacturer());
        event.setVehicleModel(request.getVehicleModel());
        event.setVehicleColor(request.getVehicleColor());
        event.setPartWorkType(request.getPartWorkType());
        event.setOemPartNo(request.getOemPartNo());
        event.setMountPosition(request.getMountPosition());
        event.setQuantity(request.getQuantity());
        event.setPhotoUrl(request.getPhotoUrl());
        event.setShopCode(request.getShopCode());
        event.setShopName(request.getShopName());
        event.setShopLocation(request.getShopLocation());
        event.setContact(request.getContact());
        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            event.setStatus(request.getStatus());
        }
    }

    private AosEventCrudResponse toResponse(AosEvent event) {
        return new AosEventCrudResponse(
                event.getId(),
                event.getReceiptNo(),
                event.getTransmittedAt(),
                event.getVehicleNo(),
                event.getVehicleManufacturer(),
                event.getVehicleModel(),
                event.getVehicleColor(),
                event.getPartWorkType(),
                event.getOemPartNo(),
                event.getMountPosition(),
                event.getQuantity(),
                event.getPhotoUrl(),
                event.getShopCode(),
                event.getShopName(),
                event.getShopLocation(),
                event.getContact(),
                event.getStatus(),
                event.getCreatedAt(),
                event.getUpdatedAt()
        );
    }
}
