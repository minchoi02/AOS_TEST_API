package com.aos.api.service;

import com.aos.api.dto.kakao.KakaoSkillRequest;
import com.aos.api.dto.kakao.KakaoSkillResponse;
import com.aos.api.entity.AosEvent;
import com.aos.api.repository.AosEventRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Service
public class KakaoService {

    private static final Logger log =
            LoggerFactory.getLogger(KakaoService.class);

    private final AosEventRepository repository;


    // =========================================================
    // Constructor
    // =========================================================

    public KakaoService(
            AosEventRepository repository
    ) {
        this.repository = repository;
    }


    // =========================================================
    // Kakao Skill
    // =========================================================

    @Transactional
    public KakaoSkillResponse processSkill(
            KakaoSkillRequest request
    ) {

        log.info("=================================================");
        log.info("KakaoService.processSkill() START");
        log.info("=================================================");


        try {

            // =================================================
            // 1. Request 확인
            // =================================================

            if (request == null) {

                log.warn("Kakao request == null");

                return KakaoSkillResponse.text(
                        "카카오 요청 정보를 확인할 수 없습니다."
                );
            }


            // =================================================
            // 2. Action 확인
            // =================================================

            KakaoSkillRequest.Action action =
                    request.getAction();


            if (action == null) {

                log.warn("Kakao action == null");

                return KakaoSkillResponse.text(
                        "접수 요청 정보를 확인할 수 없습니다."
                );
            }


            // =================================================
            // 3. Action 정보
            // =================================================

            log.info(
                    "Action ID   = [{}]",
                    action.getId()
            );

            log.info(
                    "Action Name = [{}]",
                    action.getName()
            );


            // =================================================
            // 4. Params 전체 확인
            // =================================================

            Map<String, Object> params =
                    action.getParams();


            Map<String, KakaoSkillRequest.DetailParam>
                    detailParams =
                    action.getDetailParams();


            log.info(
                    "Action Params = {}",
                    params
            );

            log.info(
                    "Action DetailParams = {}",
                    detailParams
            );


            // =================================================
            // 5. 파라미터 조회
            // =================================================

            String vehicleNo =
                    getParam(
                            action,
                            "vehicleNo"
                    );


            String vehicleManufacturer =
                    getParam(
                            action,
                            "vehicleManufacturer"
                    );


            String vehicleModel =
                    getParam(
                            action,
                            "vehicleModel"
                    );


            String vehicleColor =
                    getParam(
                            action,
                            "vehicleColor"
                    );


            String partWorkType =
                    getParam(
                            action,
                            "partWorkType"
                    );


            String shopCode =
                    getParam(
                            action,
                            "shopCode"
                    );


            String shopName =
                    getParam(
                            action,
                            "shopName"
                    );


            String imageUrl1 =
                    getParam(
                            action,
                            "imageUrl1"
                    );


            String imageUrl2 =
                    getParam(
                            action,
                            "imageUrl2"
                    );


            String imageUrl3 =
                    getParam(
                            action,
                            "imageUrl3"
                    );


            // =================================================
            // 6. 최종 파라미터 로그
            // =================================================

            log.info("-------------------------------------------------");
            log.info("Kakao Parameter 최종값");

            log.info(
                    "vehicleNo           = [{}]",
                    vehicleNo
            );

            log.info(
                    "vehicleManufacturer = [{}]",
                    vehicleManufacturer
            );

            log.info(
                    "vehicleModel        = [{}]",
                    vehicleModel
            );

            log.info(
                    "vehicleColor        = [{}]",
                    vehicleColor
            );

            log.info(
                    "partWorkType        = [{}]",
                    partWorkType
            );

            log.info(
                    "shopCode            = [{}]",
                    shopCode
            );

            log.info(
                    "shopName            = [{}]",
                    shopName
            );

            log.info(
                    "imageUrl1           = [{}]",
                    imageUrl1
            );

            log.info(
                    "imageUrl2           = [{}]",
                    imageUrl2
            );

            log.info(
                    "imageUrl3           = [{}]",
                    imageUrl3
            );

            log.info("-------------------------------------------------");


            // =================================================
            // 7. 차량번호
            // =================================================

            if (isBlank(vehicleNo)) {

                log.warn(
                        "차량번호가 없습니다."
                );

                return KakaoSkillResponse.text(
                        "차량번호를 입력해주세요."
                );
            }


            vehicleNo =
                    vehicleNo.trim();


            // =================================================
            // 8. 차량번호 형식
            // =================================================

            if (vehicleNo.length() < 4) {

                log.warn(
                        "차량번호가 너무 짧습니다. vehicleNo=[{}]",
                        vehicleNo
                );

                return KakaoSkillResponse.text(
                        "차량번호를 정확하게 입력해주세요."
                );
            }


            // =================================================
            // 9. 제조사
            // =================================================

            if (isBlank(vehicleManufacturer)) {

                log.warn(
                        "vehicleManufacturer가 없습니다."
                );

                return KakaoSkillResponse.text(
                        "차량 제조사를 선택해주세요."
                );
            }


            vehicleManufacturer =
                    vehicleManufacturer.trim();


            // =================================================
            // 10. 차종
            // =================================================

            if (isBlank(vehicleModel)) {

                log.warn(
                        "vehicleModel이 없습니다."
                );

                return KakaoSkillResponse.text(
                        "차종을 선택해주세요."
                );
            }


            vehicleModel =
                    vehicleModel.trim();


            // =================================================
            // 11. 차량 색상
            // =================================================

            if (isBlank(vehicleColor)) {

                log.warn(
                        "vehicleColor가 없습니다."
                );

                return KakaoSkillResponse.text(
                        "차량 색상을 선택해주세요."
                );
            }


            vehicleColor =
                    vehicleColor.trim();


            // =================================================
            // 12. 작업구분
            // =================================================

            if (isBlank(partWorkType)) {

                log.warn(
                        "partWorkType가 없습니다."
                );

                return KakaoSkillResponse.text(
                        "접수하실 작업을 선택해주세요."
                );
            }


            partWorkType =
                    partWorkType.trim();


            // =================================================
            // 13. 작업구분 정규화
            // =================================================

            partWorkType =
                    normalizePartWorkType(
                            partWorkType
                    );


            log.info(
                    "정규화된 partWorkType = [{}]",
                    partWorkType
            );


            // =================================================
            // 14. 작업구분 유효성
            // =================================================

            if (!isValidPartWorkType(
                    partWorkType
            )) {

                log.warn(
                        "지원하지 않는 작업구분 = [{}]",
                        partWorkType
                );

                return KakaoSkillResponse.text(
                        "접수하실 작업을 다시 선택해주세요.\n\n"
                                + "휠 복원/재제조\n"
                                + "헤드램프 재제조"
                );
            }


            // =================================================
            // 15. 공업사
            // =================================================

            if (isBlank(shopCode)) {

                shopCode =
                        "SHOP001";

            } else {

                shopCode =
                        shopCode.trim();
            }


            if (isBlank(shopName)) {

                shopName =
                        "서울공업사";

            } else {

                shopName =
                        shopName.trim();
            }


            // =================================================
            // 16. 사진 URL
            // =================================================

            String photoUrl =
                    combinePhotoUrls(
                            imageUrl1,
                            imageUrl2,
                            imageUrl3
                    );


            // =================================================
            // 17. 접수번호 생성
            // =================================================

            String receiptNo =
                    generateReceiptNo();


            log.info(
                    "생성된 접수번호 = [{}]",
                    receiptNo
            );


            // =================================================
            // 18. AosEvent 생성
            // =================================================

            AosEvent event =
                    new AosEvent();


            // =================================================
            // 19. 접수번호
            // =================================================

            event.setReceiptNo(
                    receiptNo
            );


            // =================================================
            // 20. 전송일시
            // =================================================

            event.setTransmittedAt(
                    LocalDateTime.now()
            );


            // =================================================
            // 21. 차량번호
            // =================================================

            event.setVehicleNo(
                    vehicleNo
            );


            // =================================================
            // 22. 제조사
            // =================================================

            event.setVehicleManufacturer(
                    vehicleManufacturer
            );


            // =================================================
            // 23. 차종
            // =================================================

            event.setVehicleModel(
                    vehicleModel
            );


            // =================================================
            // 24. 차량 색상
            // =================================================

            event.setVehicleColor(
                    vehicleColor
            );


            // =================================================
            // 25. 작업구분
            // =================================================

            event.setPartWorkType(
                    partWorkType
            );


            // =================================================
            // 26. OEM 부품번호
            // =================================================

            event.setOemPartNo(
                    "미입력"
            );


            // =================================================
            // 27. 장착 위치
            // =================================================

            event.setMountPosition(
                    "미입력"
            );


            // =================================================
            // 28. 수량
            // =================================================

            event.setQuantity(
                    1
            );


            // =================================================
            // 29. 사진
            // =================================================

            event.setPhotoUrl(
                    photoUrl
            );


            // =================================================
            // 30. 공업사 코드
            // =================================================

            event.setShopCode(
                    shopCode
            );


            // =================================================
            // 31. 공업사명
            // =================================================

            event.setShopName(
                    shopName
            );


            // =================================================
            // 32. 공업사 위치
            // =================================================

            event.setShopLocation(
                    "미등록"
            );


            // =================================================
            // 33. 연락처
            // =================================================

            event.setContact(
                    "미등록"
            );


            // =================================================
            // 34. 상태
            // =================================================

            event.setStatus(
                    "RECEIVED"
            );


            // =================================================
            // 35. DB 저장 전 최종 확인
            // =================================================

            log.info("=================================================");
            log.info("PostgreSQL 저장 시작");

            log.info(
                    "receiptNo       = [{}]",
                    event.getReceiptNo()
            );

            log.info(
                    "vehicleNo       = [{}]",
                    event.getVehicleNo()
            );

            log.info(
                    "manufacturer    = [{}]",
                    event.getVehicleManufacturer()
            );

            log.info(
                    "vehicleModel    = [{}]",
                    event.getVehicleModel()
            );

            log.info(
                    "vehicleColor    = [{}]",
                    event.getVehicleColor()
            );

            log.info(
                    "partWorkType    = [{}]",
                    event.getPartWorkType()
            );

            log.info(
                    "oemPartNo       = [{}]",
                    event.getOemPartNo()
            );

            log.info(
                    "mountPosition   = [{}]",
                    event.getMountPosition()
            );

            log.info(
                    "quantity        = [{}]",
                    event.getQuantity()
            );

            log.info(
                    "photoUrl        = [{}]",
                    event.getPhotoUrl()
            );

            log.info(
                    "shopCode        = [{}]",
                    event.getShopCode()
            );

            log.info(
                    "shopName        = [{}]",
                    event.getShopName()
            );

            log.info(
                    "shopLocation    = [{}]",
                    event.getShopLocation()
            );

            log.info(
                    "contact         = [{}]",
                    event.getContact()
            );

            log.info(
                    "status          = [{}]",
                    event.getStatus()
            );

            log.info("=================================================");


            // =================================================
            // 36. PostgreSQL 저장
            //
            // saveAndFlush()를 사용하여 DB INSERT 시점의
            // 실제 오류를 즉시 확인
            // =================================================

            log.info(
                    "repository.saveAndFlush() 실행"
            );


            AosEvent savedEvent =
                    repository.saveAndFlush(
                            event
                    );


            // =================================================
            // 37. 저장 결과
            // =================================================

            if (savedEvent == null) {

                log.error(
                        "repository.saveAndFlush() 결과가 null입니다."
                );

                return KakaoSkillResponse.text(
                        "접수정보 저장에 실패했습니다."
                );
            }


            log.info(
                    "repository.saveAndFlush() 정상 완료"
            );


            log.info(
                    "PostgreSQL 저장 완료"
            );


            log.info(
                    "savedEvent.id = [{}]",
                    savedEvent.getId()
            );


            log.info(
                    "savedEvent.receiptNo = [{}]",
                    savedEvent.getReceiptNo()
            );


            // =================================================
            // 38. 최종 응답 메시지
            // =================================================

            String message =

                    "AOS 접수가 완료되었습니다."
                            + "\n\n"

                            + "[접수번호]"
                            + "\n"
                            + safeString(
                            savedEvent.getReceiptNo()
                    )
                            + "\n\n"

                            + "[공업사]"
                            + "\n"
                            + safeString(
                            savedEvent.getShopName()
                    )
                            + "\n"
                            + "코드 : "
                            + safeString(
                            savedEvent.getShopCode()
                    )
                            + "\n\n"

                            + "[차량정보]"
                            + "\n"
                            + "차량번호 : "
                            + safeString(
                            savedEvent.getVehicleNo()
                    )
                            + "\n"
                            + "제조사 : "
                            + safeString(
                            savedEvent.getVehicleManufacturer()
                    )
                            + "\n"
                            + "차종 : "
                            + safeString(
                            savedEvent.getVehicleModel()
                    )
                            + "\n"
                            + "색상 : "
                            + safeString(
                            savedEvent.getVehicleColor()
                    )
                            + "\n\n"

                            + "[접수정보]"
                            + "\n"
                            + "작업구분 : "
                            + safeString(
                            savedEvent.getPartWorkType()
                    )
                            + "\n\n"

                            + "접수정보가 정상적으로 저장되었습니다.";


            // =================================================
            // 39. Kakao Response
            // =================================================

            KakaoSkillResponse response =
                    KakaoSkillResponse.text(
                            message
                    );


            // =================================================
            // 40. 정상 종료
            // =================================================

            log.info("=================================================");
            log.info("KakaoService.processSkill() 정상 종료");
            log.info(
                    "접수번호 = [{}]",
                    savedEvent.getReceiptNo()
            );
            log.info("=================================================");


            return response;


        } catch (Exception e) {

            // =================================================
            // 실제 오류 확인
            // =================================================

            log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            log.error(
                    "KakaoService.processSkill() 예외 발생"
            );

            log.error(
                    "Exception Class = [{}]",
                    e.getClass().getName()
            );

            log.error(
                    "Exception Message = [{}]",
                    e.getMessage()
            );

            log.error(
                    "StackTrace",
                    e
            );

            log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


            return KakaoSkillResponse.text(
                    "접수 처리 중 오류가 발생했습니다.\n"
                            + "잠시 후 다시 시도해주세요."
            );
        }
    }


    // =========================================================
    // Parameter 조회
    // =========================================================

    private String getParam(
            KakaoSkillRequest.Action action,
            String key
    ) {

        if (action == null) {

            return null;
        }


        // =================================================
        // 1. params
        // =================================================

        Map<String, Object> params =
                action.getParams();


        if (params != null
                && !params.isEmpty()) {

            Object value =
                    params.get(key);


            if (value != null) {

                String result =
                        convertToString(
                                value
                        );


                if (!isBlank(result)) {

                    log.info(
                            "PARAM [{}] = [{}]",
                            key,
                            result
                    );

                    return result.trim();
                }
            }
        }


        // =================================================
        // 2. detailParams
        // =================================================

        Map<String, KakaoSkillRequest.DetailParam>
                detailParams =
                action.getDetailParams();


        if (detailParams != null
                && !detailParams.isEmpty()) {

            KakaoSkillRequest.DetailParam detailParam =
                    detailParams.get(key);


            if (detailParam != null) {

                // -----------------------------------------
                // value
                // -----------------------------------------

                if (!isBlank(
                        detailParam.getValue()
                )) {

                    String value =
                            detailParam
                                    .getValue()
                                    .trim();


                    log.info(
                            "DETAIL PARAM VALUE [{}] = [{}]",
                            key,
                            value
                    );


                    return value;
                }


                // -----------------------------------------
                // origin
                // -----------------------------------------

                if (!isBlank(
                        detailParam.getOrigin()
                )) {

                    String origin =
                            detailParam
                                    .getOrigin()
                                    .trim();


                    log.info(
                            "DETAIL PARAM ORIGIN [{}] = [{}]",
                            key,
                            origin
                    );


                    return origin;
                }
            }
        }


        // =================================================
        // 3. 찾지 못함
        // =================================================

        log.info(
                "PARAM NOT FOUND = [{}]",
                key
        );


        return null;
    }


    // =========================================================
    // Object → String
    // =========================================================

    private String convertToString(
            Object value
    ) {

        if (value == null) {

            return null;
        }


        if (value instanceof String) {

            return (String) value;
        }


        if (value instanceof Number) {

            return String.valueOf(
                    value
            );
        }


        if (value instanceof Boolean) {

            return String.valueOf(
                    value
            );
        }


        return String.valueOf(
                value
        );
    }


    // =========================================================
    // 작업구분 정규화
    // =========================================================

    private String normalizePartWorkType(
            String value
    ) {

        if (isBlank(value)) {

            return null;
        }


        String workType =
                value.trim();


        // =================================================
        // 휠 복원/재제조
        // =================================================

        if (
                workType.equals("휠")
                        || workType.equals("휠 복원")
                        || workType.equals("휠복원")
                        || workType.equals("휠 복원/재제조")
                        || workType.equals("휠복원/재제조")
                        || workType.equals("휠 복원 / 재제조")
                        || workType.equals("휠복원 / 재제조")
                        || workType.equals("휠 복원 및 재제조")
        ) {

            return "휠 복원/재제조";
        }


        // =================================================
        // 헤드램프 재제조
        // =================================================

        if (
                workType.equals("헤드램프")
                        || workType.equals("헤드램프 재제조")
                        || workType.equals("헤드램프재제조")
                        || workType.equals("헤드램프 재 제조")
                        || workType.equals("헤드 램프")
                        || workType.equals("헤드 램프 재제조")
        ) {

            return "헤드램프 재제조";
        }


        return workType;
    }


    // =========================================================
    // 작업구분 유효성
    // =========================================================

    private boolean isValidPartWorkType(
            String value
    ) {

        if (isBlank(value)) {

            return false;
        }


        return
                "휠 복원/재제조".equals(
                        value
                )
                        ||
                        "헤드램프 재제조".equals(
                                value
                        );
    }


    // =========================================================
    // 사진 URL 결합
    // =========================================================

    private String combinePhotoUrls(
            String imageUrl1,
            String imageUrl2,
            String imageUrl3
    ) {

        StringBuilder builder =
                new StringBuilder();


        appendPhoto(
                builder,
                imageUrl1
        );


        appendPhoto(
                builder,
                imageUrl2
        );


        appendPhoto(
                builder,
                imageUrl3
        );


        if (builder.length() == 0) {

            return null;
        }


        return builder.toString();
    }


    // =========================================================
    // 사진 추가
    // =========================================================

    private void appendPhoto(
            StringBuilder builder,
            String photo
    ) {

        if (isBlank(photo)) {

            return;
        }


        if (builder.length() > 0) {

            builder.append(",");
        }


        builder.append(
                photo.trim()
        );
    }


    // =========================================================
    // 접수번호 생성
    // =========================================================

    private String generateReceiptNo() {

        String date =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyyMMdd"
                                )
                        );


        String random =
                UUID.randomUUID()
                        .toString()
                        .replace(
                                "-",
                                ""
                        )
                        .substring(
                                0,
                                6
                        )
                        .toUpperCase();


        return
                "AOS-"
                        + date
                        + "-"
                        + random;
    }


    // =========================================================
    // Null 안전 문자열
    // =========================================================

    private String safeString(
            String value
    ) {

        if (value == null) {

            return "";
        }


        return value;
    }


    // =========================================================
    // Blank 확인
    // =========================================================

    private boolean isBlank(
            String value
    ) {

        return value == null
                || value.trim().isEmpty();
    }
}