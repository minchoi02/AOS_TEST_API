package com.aos.api.controller;

import com.aos.api.dto.kakao.KakaoSkillRequest;
import com.aos.api.dto.kakao.KakaoSkillResponse;
import com.aos.api.service.KakaoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kakao")
public class KakaoController {

    private static final Logger log =
            LoggerFactory.getLogger(KakaoController.class);

    private final KakaoService kakaoService;


    // =========================================================
    // Constructor
    // =========================================================

    public KakaoController(
            KakaoService kakaoService
    ) {

        this.kakaoService =
                kakaoService;
    }


    // =========================================================
    // Kakao Skill API
    // =========================================================

    /**
     * Kakao Skill API
     *
     * POST /api/v1/kakao/skill
     */
    @PostMapping(
            value = "/skill",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<KakaoSkillResponse> skill(
            @RequestBody KakaoSkillRequest request
    ) {

        log.info(
                "================================================="
        );

        log.info(
                "Kakao Skill 요청 시작"
        );


        try {

            // =================================================
            // 1. Request 확인
            // =================================================

            if (request == null) {

                log.warn(
                        "Kakao request가 null입니다."
                );

                return ResponseEntity.ok(
                        KakaoSkillResponse.text(
                                "요청 정보를 확인할 수 없습니다."
                        )
                );
            }


            // =================================================
            // 2. Action 확인
            // =================================================

            if (request.getAction() == null) {

                log.warn(
                        "Kakao action이 null입니다."
                );

                return ResponseEntity.ok(
                        KakaoSkillResponse.text(
                                "카카오 Action 정보를 확인할 수 없습니다."
                        )
                );
            }


            // =================================================
            // 3. Action 정보
            // =================================================

            log.info(
                    "Action Name = {}",
                    request.getAction().getName()
            );

            log.info(
                    "Action ID = {}",
                    request.getAction().getId()
            );


            // =================================================
            // 4. Params
            // =================================================

            log.info(
                    "Action Params = {}",
                    request.getAction().getParams()
            );


            // =================================================
            // 5. Detail Params
            // =================================================

            log.info(
                    "Action DetailParams = {}",
                    request.getAction().getDetailParams()
            );


            // =================================================
            // 6. User 정보
            // =================================================
            //
            // 기존 코드:
            // request.getUserRequest()
            //
            // 현재 KakaoSkillRequest에는
            // getUserRequest()가 없으므로 getUser() 사용
            // =================================================

            if (request.getUser() != null) {

                log.info(
                        "Kakao User ID = {}",
                        request.getUser().getId()
                );

                log.info(
                        "Kakao User Type = {}",
                        request.getUser().getType()
                );


                if (request.getUser().getProperties() != null) {

                    log.info(
                            "Kakao plusfriendUserKey = {}",
                            request.getUser()
                                    .getProperties()
                                    .getPlusfriendUserKey()
                    );

                    log.info(
                            "Kakao appUserId = {}",
                            request.getUser()
                                    .getProperties()
                                    .getAppUserId()
                    );
                }
            }


            // =================================================
            // 7. Bot 정보
            // =================================================

            if (request.getBot() != null) {

                log.info(
                        "Kakao Bot ID = {}",
                        request.getBot().getId()
                );

                log.info(
                        "Kakao Bot Name = {}",
                        request.getBot().getName()
                );
            }


            // =================================================
            // 8. Timezone
            // =================================================

            log.info(
                    "Kakao Timezone = {}",
                    request.getTimezone()
            );


            // =================================================
            // 9. KakaoService 실행
            // =================================================

            log.info(
                    "KakaoService.processSkill() 실행 시작"
            );


            KakaoSkillResponse response =
                    kakaoService.processSkill(
                            request
                    );


            // =================================================
            // 10. Service Response 확인
            // =================================================

            if (response == null) {

                log.error(
                        "KakaoService 응답이 null입니다."
                );

                return ResponseEntity.ok(
                        KakaoSkillResponse.text(
                                "접수 처리 결과를 생성하지 못했습니다."
                        )
                );
            }


            // =================================================
            // 11. 정상 종료
            // =================================================

            log.info(
                    "KakaoService.processSkill() 실행 완료"
            );

            log.info(
                    "Kakao Skill 정상 종료"
            );

            log.info(
                    "================================================="
            );


            return ResponseEntity.ok(
                    response
            );


        } catch (Exception e) {

            // =================================================
            // Exception 처리
            // =================================================

            log.error(
                    "================================================="
            );

            log.error(
                    "Kakao Skill 처리 중 예외 발생"
            );

            /*
             * 중요
             *
             * e.printStackTrace() 대신
             * Logger에 Exception 객체를 전달합니다.
             *
             * 이렇게 하면 Exception Message와
             * 전체 StackTrace가 서버 로그에 출력됩니다.
             */
            log.error(
                    "Exception Message = " + e.getMessage(),
                    e
            );

            log.error(
                    "================================================="
            );


            return ResponseEntity.ok(
                    KakaoSkillResponse.text(
                            "접수 처리 중 오류가 발생했습니다.\n"
                                    + "잠시 후 다시 시도해주세요."
                    )
            );
        }
    }


    // =========================================================
    // Controller 전역 Exception 처리
    // =========================================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<KakaoSkillResponse> handleException(
            Exception e
    ) {

        log.error(
                "================================================="
        );

        log.error(
                "Kakao Controller 전역 예외 발생"
        );

        log.error(
                "Exception Message = " + e.getMessage(),
                e
        );

        log.error(
                "================================================="
        );


        return ResponseEntity.ok(
                KakaoSkillResponse.text(
                        "접수 처리 중 오류가 발생했습니다."
                )
        );
    }
}