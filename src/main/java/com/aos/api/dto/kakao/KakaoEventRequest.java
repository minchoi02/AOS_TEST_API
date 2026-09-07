package com.aos.api.dto.kakao;

/**
 * Kakao Event Request DTO
 *
 * 카카오에서 전달받은 접수정보를
 * AOS 이벤트 데이터로 전달하기 위한 DTO
 *
 * 주의:
 * KakaoSkillRequest와 별개의 DTO입니다.
 */
public class KakaoEventRequest {

    /**
     * 접수번호
     *
     * 예:
     * AOS-20260825-ABC123
     *
     * 서버에서 자동 생성하는 경우
     * 입력하지 않아도 됩니다.
     */
    private String receiptNo;

    /**
     * 차량번호
     */
    private String vehicleNo;

    /**
     * 차량 제조사
     */
    private String vehicleManufacturer;

    /**
     * 차량 차종
     */
    private String vehicleModel;

    /**
     * 차량 색상
     */
    private String vehicleColor;

    /**
     * 작업 구분
     *
     * 예:
     * 휠 복원/재제조
     * 헤드램프 재제조
     */
    private String partWorkType;

    /**
     * OEM 부품번호
     */
    private String oemPartNo;

    /**
     * 장착 위치
     */
    private String mountPosition;

    /**
     * 수량
     */
    private Integer quantity;

    /**
     * 사진 URL
     *
     * 여러 장인 경우
     * 콤마(,)로 구분할 수 있습니다.
     */
    private String photoUrl;

    /**
     * 공업사 코드
     */
    private String shopCode;

    /**
     * 공업사명
     */
    private String shopName;

    /**
     * 공업사 위치
     */
    private String shopLocation;

    /**
     * 담당자 연락처
     */
    private String contact;


    // ==================================================
    // Getter / Setter
    // ==================================================

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
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

    public void setVehicleManufacturer(
            String vehicleManufacturer
    ) {
        this.vehicleManufacturer =
                vehicleManufacturer;
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
}