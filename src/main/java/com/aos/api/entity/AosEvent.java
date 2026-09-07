package com.aos.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "aos_event",
        indexes = {
                @Index(name = "idx_receipt_no", columnList = "receipt_no"),
                @Index(name = "idx_transmitted_at", columnList = "transmitted_at"),
                @Index(name = "idx_vehicle_no", columnList = "vehicle_no"),
                @Index(name = "idx_shop_code", columnList = "shop_code")
        }
)
public class AosEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_no", nullable = false, unique = true, length = 50)
    private String receiptNo;

    @Column(name = "transmitted_at")
    private LocalDateTime transmittedAt;

    @Column(name = "vehicle_no", nullable = false, length = 30)
    private String vehicleNo;

    @Column(name = "vehicle_manufacturer", length = 100)
    private String vehicleManufacturer;

    @Column(name = "vehicle_model", length = 100)
    private String vehicleModel;

    @Column(name = "vehicle_color", length = 50)
    private String vehicleColor;

    @Column(name = "part_work_type", nullable = false, length = 100)
    private String partWorkType;

    @Column(name = "oem_part_no", length = 100)
    private String oemPartNo;

    @Column(name = "mount_position", length = 50)
    private String mountPosition;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "photo_url", length = 1000)
    private String photoUrl;

    @Column(name = "shop_code", nullable = false, length = 50)
    private String shopCode;

    @Column(name = "shop_name", nullable = false, length = 200)
    private String shopName;

    @Column(name = "shop_location", length = 300)
    private String shopLocation;

    @Column(name = "contact", length = 50)
    private String contact;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {

        LocalDateTime now = LocalDateTime.now();

        if (createdAt == null) {
            createdAt = now;
        }

        if (updatedAt == null) {
            updatedAt = now;
        }

        if (transmittedAt == null) {
            transmittedAt = now;
        }

        if (quantity == null) {
            quantity = 1;
        }

        if (status == null) {
            status = "RECEIVED";
        }
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public LocalDateTime getTransmittedAt() {
        return transmittedAt;
    }

    public void setTransmittedAt(LocalDateTime transmittedAt) {
        this.transmittedAt = transmittedAt;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleManufacturer() {
        return vehicleManufacturer;
    }

    public void setVehicleManufacturer(String vehicleManufacturer) {
        this.vehicleManufacturer = vehicleManufacturer;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getPartWorkType() {
        return partWorkType;
    }

    public void setPartWorkType(String partWorkType) {
        this.partWorkType = partWorkType;
    }

    public String getOemPartNo() {
        return oemPartNo;
    }

    public void setOemPartNo(String oemPartNo) {
        this.oemPartNo = oemPartNo;
    }

    public String getMountPosition() {
        return mountPosition;
    }

    public void setMountPosition(String mountPosition) {
        this.mountPosition = mountPosition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}