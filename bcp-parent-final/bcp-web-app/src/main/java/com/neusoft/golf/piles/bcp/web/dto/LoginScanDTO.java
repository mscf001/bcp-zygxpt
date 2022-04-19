package com.neusoft.golf.piles.bcp.web.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class LoginScanDTO implements Serializable {
    private static final long serialVersionUID = -7261162227031203215L;
    private String username;
    private String password;
    private String captchaId;
    private String captchaWord;
    private String card;

    public static LoginScanDTO.LoginScanDTOBuilder builder() {
        return new LoginScanDTO.LoginScanDTOBuilder();
    }

    public LoginScanDTO(final String username, final String password, final String captchaId, final String captchaWord,final String card) {
        this.username = username;
        this.password = password;
        this.captchaId = captchaId;
        this.captchaWord = captchaWord;
        this.card = card;
    }

    public LoginScanDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaWord() {
        return captchaWord;
    }

    public void setCaptchaWord(String captchaWord) {
        this.captchaWord = captchaWord;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LoginDTO)) {
            return false;
        } else {
            LoginScanDTO other = (LoginScanDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username == null) {
                            break label59;
                        }
                    } else if (this$username.equals(other$username)) {
                        break label59;
                    }

                    return false;
                }

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }

                Object this$captchaId = this.getCaptchaId();
                Object other$captchaId = other.getCaptchaId();
                if (this$captchaId == null) {
                    if (other$captchaId != null) {
                        return false;
                    }
                } else if (!this$captchaId.equals(other$captchaId)) {
                    return false;
                }

                Object this$captchaWord = this.getCaptchaWord();
                Object other$captchaWord = other.getCaptchaWord();
                if (this$captchaWord == null) {
                    if (other$captchaWord != null) {
                        return false;
                    }
                } else if (!this$captchaWord.equals(other$captchaWord)) {
                    return false;
                }

                Object this$card = this.getCard();
                Object other$card = other.getCard();
                if (this$card == null) {
                    if (other$card != null) {
                        return false;
                    }
                } else if (!this$card.equals(other$card)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoginScanDTO;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $captchaId = this.getCaptchaId();
        result = result * 59 + ($captchaId == null ? 43 : $captchaId.hashCode());
        Object $captchaWord = this.getCaptchaWord();
        result = result * 59 + ($captchaWord == null ? 43 : $captchaWord.hashCode());
        Object $card = this.getCard();
        result = result * 59 + ($card == null ? 43 : $card.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LoginScanDTO(username=" + this.getUsername() + ", password=" + this.getPassword() + ", captchaId=" + this.getCaptchaId() + ", captchaWord=" + this.getCaptchaWord() + ", card=" + this.getCard() + ")";
    }

    public static class LoginScanDTOBuilder {
        private String username;
        private String password;
        private String captchaId;
        private String captchaWord;
        private String card;

        LoginScanDTOBuilder() {
        }

        public LoginScanDTO.LoginScanDTOBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public LoginScanDTO.LoginScanDTOBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public LoginScanDTO.LoginScanDTOBuilder captchaId(final String captchaId) {
            this.captchaId = captchaId;
            return this;
        }

        public LoginScanDTO.LoginScanDTOBuilder captchaWord(final String captchaWord) {
            this.captchaWord = captchaWord;
            return this;
        }

        public LoginScanDTO.LoginScanDTOBuilder card(final String card) {
            this.card = card;
            return this;
        }

        public LoginScanDTO build() {
            return new LoginScanDTO(this.username, this.password, this.captchaId, this.captchaWord,this.card);
        }

        @Override
        public String toString() {
            return "LoginScanDTO.LoginScanDTOBuilder(username=" + this.username + ", password=" + this.password + ", captchaId=" + this.captchaId + ", captchaWord=" + this.captchaWord + ", card=" + this.card + ")";
        }
    }
}

