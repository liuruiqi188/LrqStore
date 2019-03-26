package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName LoginJson
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/22 16:22
 **/
public class LoginJson {
    public LoginData result;
    public String message;
    public String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginData getResult() {
        return result;
    }

    public void setResult(LoginData result) {
        this.result = result;
    }

    public class LoginData{
        private String headPic;
        private String nickName;
        private String phone;
        private String sessionId;
        private String sex;
        private String userId;

        @Override
        public String toString() {
            return "LoginData{" +
                    "headPic='" + headPic + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", sessionId='" + sessionId + '\'' +
                    ", sex='" + sex + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }

        public LoginData(String headPic, String nickName, String phone, String sessionId, String sex, String userId) {
            this.headPic = headPic;
            this.nickName = nickName;
            this.phone = phone;
            this.sessionId = sessionId;
            this.sex = sex;
            this.userId = userId;
        }

        public String getHeadPic() {

            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
