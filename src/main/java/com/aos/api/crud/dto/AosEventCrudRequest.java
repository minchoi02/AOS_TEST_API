package com.aos.api.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "자동차 AOS 연계 데이터 CRUD 요청")
public class AosEventCrudRequest {

    @NotBlank
    @Schema(description = "보험 접수번호", example = "AOS-20260907-0001")
    private String receiptNo;

    @Schema(description = "AOS 전송일시", example = "2026-09-07T09:30:00")
    private LocalDateTime transmittedAt;

    @NotBlank
    @Schema(description = "차량번호", example = "12가3456")
    private String vehicleNo;

    @Schema(description = "차량 제조사", example = "현대자동차")
    private String vehicleManufacturer;

    @Schema(description = "차량 모델", example = "그랜저")
    private String vehicleModel;

    @Schema(description = "차량 색상", example = "화이트")
    private String vehicleColor;

    @NotBlank
    @Schema(description = "작업 유형", example = "휠 복원/재제조")
    private String partWorkType;

    @Schema(description = "OEM 부품번호", example = "52910-G8000")
    private String oemPartNo;

    @Schema(description = "장착 위치", example = "FR")
    private String mountPosition;

    @NotNull
    @Min(1)
    @Schema(description = "수량", example = "1")
    private Integer quantity = 1;

    @Schema(description = "사진 URL", example = "https://example.com/aos/wheel001.jpg")
    private String photoUrl;

    @NotBlank
    @Schema(description = "공업사 코드", example = "SHOP001")
    private String shopCode;

    @NotBlank
    @Schema(description = "공업사명", example = "티벌컨 정비센터")
    private String shopName;

    @Schema(description = "공업사 위치", example = "서울특별시 금천구")
    private String shopLocation;

    @Schema(description = "연락처", example = "02-1234-5678")
    private String contact;

    @Schema(description = "처리 상태", example = "RECEIVED", defaultValue = "RECEIVED")
    private String status = "RECEIVED";

    public String getReceiptNo() { return receiptNo; }
    public void setReceiptNo(String receiptNo) { this.receiptNo = receiptNo; }
    public LocalDateTime getTransmittedAt() { return transmittedAt; }
    public void setTransmittedAt(LocalDateTime transmittedAt) { this.transmittedAt = transmittedAt; }
    public String getVehicleNo() { return vehicleNo; }
    public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }
    public String getVehicleManufacturer() { return vehicleManufacturer; }
    public void setVehicleManufacturer(String vehicleManufacturer) { this.vehicleManufacturer = vehicleManufacturer; }
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public String getVehicleColor() { return vehicleColor; }
    public void setVehicleColor(String vehicleColor) { this.vehicleColor = vehicleColor; }
    public String getPartWorkType() { return partWorkType; }
    public void setPartWorkType(String partWorkType) { this.partWorkType = partWorkType; }
    public String getOemPartNo() { return oemPartNo; }
    public void setOemPartNo(String oemPartNo) { this.oemPartNo = oemPartNo; }
    public String getMountPosition() { return mountPosition; }
    public void setMountPosition(String mountPosition) { this.mountPosition = mountPosition; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public String getShopCode() { return shopCode; }
    public void setShopCode(String shopCode) { this.shopCode = shopCode; }
    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }
    public String getShopLocation() { return shopLocation; }
    public void setShopLocation(String shopLocation) { this.shopLocation = shopLocation; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
