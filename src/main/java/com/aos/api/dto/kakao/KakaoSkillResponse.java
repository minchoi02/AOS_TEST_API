package com.aos.api.dto.kakao;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================
 * Kakao Skill Response
 * =========================================================
 */
@Schema(description = "Kakao Skill 2.0 응답")
public class KakaoSkillResponse {

    @Schema(
            description = "Kakao 응답 버전",
            example = "2.0"
    )
    private String version;

    @Schema(description = "Kakao 응답 Template")
    private Template template;


    // =========================================================
    // Constructor
    // =========================================================

    public KakaoSkillResponse() {
    }


    public KakaoSkillResponse(
            String version,
            Template template
    ) {
        this.version = version;
        this.template = template;
    }


    // =========================================================
    // Getter / Setter
    // =========================================================

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }


    // =========================================================
    // Simple Text
    // =========================================================

    public static KakaoSkillResponse text(
            String message
    ) {

        if (message == null) {
            message = "";
        }


        SimpleText simpleText =
                new SimpleText();

        simpleText.setText(message);


        Output output =
                new Output();

        output.setSimpleText(simpleText);


        List<Output> outputs =
                new ArrayList<>();

        outputs.add(output);


        Template template =
                new Template();

        template.setOutputs(outputs);


        return new KakaoSkillResponse(
                "2.0",
                template
        );
    }


    // =========================================================
    // Template
    // =========================================================

    @Schema(description = "Kakao Template")
    public static class Template {

        private List<Output> outputs;


        public Template() {
        }


        public List<Output> getOutputs() {
            return outputs;
        }


        public void setOutputs(
                List<Output> outputs
        ) {
            this.outputs = outputs;
        }
    }


    // =========================================================
    // Output
    // =========================================================

    @Schema(description = "Kakao Output")
    public static class Output {

        private SimpleText simpleText;


        public Output() {
        }


        public SimpleText getSimpleText() {
            return simpleText;
        }


        public void setSimpleText(
                SimpleText simpleText
        ) {
            this.simpleText = simpleText;
        }
    }


    // =========================================================
    // SimpleText
    // =========================================================

    @Schema(description = "Kakao SimpleText")
    public static class SimpleText {

        private String text;


        public SimpleText() {
        }


        public String getText() {
            return text;
        }


        public void setText(String text) {
            this.text = text;
        }
    }
}