package com.aos.api.dto.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * =========================================================
 * Kakao Skill Request
 * =========================================================
 *
 * Kakao i Open Builder Skill 2.0 Request
 *
 * 주요 구조
 *
 * {
 *   "bot": {},
 *   "intent": {},
 *   "action": {
 *      "id": "",
 *      "name": "",
 *      "params": {},
 *      "detailParams": {}
 *   },
 *   "userRequest": {
 *      "block": {},
 *      "utterance": "",
 *      "user": {}
 *   },
 *   "contexts": [],
 *   "timezone": ""
 * }
 *
 * =========================================================
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoSkillRequest {

    private Bot bot;

    private User user;

    private Intent intent;

    private Action action;

    private UserRequest userRequest;

    private Context context;

    private String timezone;


    // =========================================================
    // Getter / Setter
    // =========================================================

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }


    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }


    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


    // =========================================================
    // Bot
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bot {

        private String id;

        private String name;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    // =========================================================
    // Intent
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Intent {

        private String id;

        private String name;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    // =========================================================
    // User
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {

        private String id;

        private String type;

        private Properties properties;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }
    }


    // =========================================================
    // User Properties
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {

        private String plusfriendUserKey;

        private String appUserId;


        public String getPlusfriendUserKey() {
            return plusfriendUserKey;
        }

        public void setPlusfriendUserKey(
                String plusfriendUserKey
        ) {
            this.plusfriendUserKey = plusfriendUserKey;
        }


        public String getAppUserId() {
            return appUserId;
        }

        public void setAppUserId(
                String appUserId
        ) {
            this.appUserId = appUserId;
        }
    }


    // =========================================================
    // Action
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Action {

        private String id;

        private String name;

        private Map<String, Object> params =
                new LinkedHashMap<>();

        private Map<String, DetailParam> detailParams =
                new LinkedHashMap<>();


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public Map<String, Object> getParams() {
            return params;
        }

        public void setParams(
                Map<String, Object> params
        ) {
            this.params =
                    params != null
                            ? params
                            : new LinkedHashMap<>();
        }


        public Map<String, DetailParam> getDetailParams() {
            return detailParams;
        }

        public void setDetailParams(
                Map<String, DetailParam> detailParams
        ) {
            this.detailParams =
                    detailParams != null
                            ? detailParams
                            : new LinkedHashMap<>();
        }
    }


    // =========================================================
    // DetailParam
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailParam {

        private String origin;

        private String value;


        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    // =========================================================
    // UserRequest
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserRequest {

        private Block block;

        private String utterance;

        private User user;


        public Block getBlock() {
            return block;
        }

        public void setBlock(Block block) {
            this.block = block;
        }


        public String getUtterance() {
            return utterance;
        }

        public void setUtterance(String utterance) {
            this.utterance = utterance;
        }


        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }


    // =========================================================
    // UserRequest Block
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Block {

        private String id;

        private String name;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    // =========================================================
    // Context
    // =========================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Context {

        private String id;

        private String values;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getValues() {
            return values;
        }

        public void setValues(String values) {
            this.values = values;
        }
    }
}