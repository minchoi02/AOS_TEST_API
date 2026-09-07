package com.aos.api.dto.kakao;

import java.util.ArrayList;
import java.util.List;

public class KakaoEventResponse {

    private Template template;

    public KakaoEventResponse() {
    }

    public static KakaoEventResponse text(String message) {

        KakaoEventResponse response = new KakaoEventResponse();

        Template template = new Template();

        template.getOutputs().add(
                new SimpleText(
                        new Text(message)
                )
        );

        response.setTemplate(template);

        return response;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public static class Template {

        private List<Object> outputs = new ArrayList<>();

        public Template() {
        }

        public List<Object> getOutputs() {
            return outputs;
        }

        public void setOutputs(List<Object> outputs) {
            this.outputs = outputs;
        }
    }

    public static class SimpleText {

        private Text simpleText;

        public SimpleText() {
        }

        public SimpleText(Text simpleText) {
            this.simpleText = simpleText;
        }

        public Text getSimpleText() {
            return simpleText;
        }

        public void setSimpleText(Text simpleText) {
            this.simpleText = simpleText;
        }
    }

    public static class Text {

        private String text;

        public Text() {
        }

        public Text(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}