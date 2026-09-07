package com.aos.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "AOS 최소필드 이벤트 수신 요청")
public class AosEventRequest {

    @NotBlank
    @Schema(example = "AOS-000184", description = "보험접수번호")
    private String receiptNo;

    @NotNull
    @Schema(example = "2026-08-13T15:42:00", description = "전송일시")
    private LocalDateTime transmittedAt;

    @NotBlank
    @Schema(example = "00가70000", description = "차량번호. 실제 운영에서는 마스킹 정책 적용")
    private String vehicleNo;

    @Schema(example = "현대")
    private String vehicleManufacturer;

    @Schema(example = "그랜저")
    private String vehicleModel;

    @Schema(example = "검정")
    private String vehicleColor;

    @NotBlank
    @Schema(example = "휠/헤드램프, 교환")
    private String partWorkType;

    @NotBlank
    @Schema(example = "92101-AB000")
    private String oemPartNo;

    @NotBlank
    @Schema(example = "좌측")
    private String mountPosition;

    @NotNull
    @Min(1)
    @Schema(example = "1")
    private Integer quantity;

    @Schema(example = "https://example.com/photos/AOS-000184.jpg")
    private String photoUrl;

    @NotBlank
    @Schema(example = "RS-1042")
    private String shopCode;

    @NotBlank
    @Schema(example = "OO공업사")
    private String shopName;

    @NotBlank
    @Schema(example = "대전광역시 OO구")
    private String shopLocation;

    @NotBlank
    @Schema(example = "000-0000-0000")
    private String contact;

    public String getReceiptNo() { return receiptNo; }
    public void setReceiptNo(String v) { receiptNo = v; }
    public LocalDateTime getTransmittedAt() { return transmittedAt; }
    public void setTransmittedAt(LocalDateTime v) { transmittedAt = v; }
    public String getVehicleNo() { return vehicleNo; }
    public void setVehicleNo(String v) { vehicleNo = v; }
    public String getVehicleManufacturer() { return vehicleManufacturer; }
    public void setVehicleManufacturer(String v) { vehicleManufacturer = v; }
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String v) { vehicleModel = v; }
    public String getVehicleColor() { return vehicleColor; }
    public void setVehicleColor(String v) { vehicleColor = v; }
    public String getPartWorkType() { return partWorkType; }
    public void setPartWorkType(String v) { partWorkType = v; }
    public String getOemPartNo() { return oemPartNo; }
    public void setOemPartNo(String v) { oemPartNo = v; }
    public String getMountPosition() { return mountPosition; }
    public void setMountPosition(String v) { mountPosition = v; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer v) { quantity = v; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String v) { photoUrl = v; }
    public String getShopCode() { return shopCode; }
    public void setShopCode(String v) { shopCode = v; }
    public String getShopName() { return shopName; }
    public void setShopName(String v) { shopName = v; }
    public String getShopLocation() { return shopLocation; }
    public void setShopLocation(String v) { shopLocation = v; }
    public String getContact() { return contact; }
    public void setContact(String v) { contact = v; }
}
