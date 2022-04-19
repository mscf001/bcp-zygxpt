package com.neusoft.golf.piles.bcp.web.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class UserNewDTO implements Serializable {
    private static final long serialVersionUID = -7461547951430357528L;
    private String account;
    private String password;
    private String name;
    private String messageInfo;
    private String messageType;
    private String id;
    private String userType;
    private boolean admin;
    private String role;
    private String card;

    public static UserNewDTO.UserNewDTOBuilder builder() {
        return new UserNewDTO.UserNewDTOBuilder(); }

    public UserNewDTO(final String account, final String password, final String name, final String messageInfo, final String messageType, final String id, final String userType, final boolean admin, final String role,final String card) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.messageInfo = messageInfo;
        this.messageType = messageType;
        this.id = id;
        this.userType = userType;
        this.admin = admin;
        this.role = role;
        this.card = card;
    }

    public UserNewDTO() {
    }

    public String getAccount() {
        return this.account;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getMessageInfo() {
        return this.messageInfo;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public String getId() {
        return this.id;
    }

    public String getUserType() {
        return this.userType;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public String getRole() {
        return this.role;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setMessageInfo(final String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }

    public void setAdmin(final boolean admin) {
        this.admin = admin;
    }

    public void setRole(final String role) {
        this.role = role;
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
        } else if (!(o instanceof UserDTO)) {
            return false;
        } else {
            UserNewDTO other = (UserNewDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label111: {
                    Object this$account = this.getAccount();
                    Object other$account = other.getAccount();
                    if (this$account == null) {
                        if (other$account == null) {
                            break label111;
                        }
                    } else if (this$account.equals(other$account)) {
                        break label111;
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

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                label90: {
                    Object this$messageInfo = this.getMessageInfo();
                    Object other$messageInfo = other.getMessageInfo();
                    if (this$messageInfo == null) {
                        if (other$messageInfo == null) {
                            break label90;
                        }
                    } else if (this$messageInfo.equals(other$messageInfo)) {
                        break label90;
                    }

                    return false;
                }

                label83: {
                    Object this$messageType = this.getMessageType();
                    Object other$messageType = other.getMessageType();
                    if (this$messageType == null) {
                        if (other$messageType == null) {
                            break label83;
                        }
                    } else if (this$messageType.equals(other$messageType)) {
                        break label83;
                    }

                    return false;
                }

                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$userType = this.getUserType();
                Object other$userType = other.getUserType();
                if (this$userType == null) {
                    if (other$userType != null) {
                        return false;
                    }
                } else if (!this$userType.equals(other$userType)) {
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

                if (this.isAdmin() != other.isAdmin()) {
                    return false;
                } else {
                    Object this$role = this.getRole();
                    Object other$role = other.getRole();
                    if (this$role == null) {
                        if (other$role != null) {
                            return false;
                        }
                    } else if (!this$role.equals(other$role)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserNewDTO;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $account = this.getAccount();
        result = result * 59 + ($account == null ? 43 : $account.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $messageInfo = this.getMessageInfo();
        result = result * 59 + ($messageInfo == null ? 43 : $messageInfo.hashCode());
        Object $messageType = this.getMessageType();
        result = result * 59 + ($messageType == null ? 43 : $messageType.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $userType = this.getUserType();
        result = result * 59 + ($userType == null ? 43 : $userType.hashCode());
        result = result * 59 + (this.isAdmin() ? 79 : 97);
        Object $role = this.getRole();
        result = result * 59 + ($role == null ? 43 : $role.hashCode());
        Object $card = this.getCard();
        result = result * 59 + ($card == null ? 43 : $card.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UserNewDTO(account=" + this.getAccount() + ", password=" + this.getPassword() + ", name=" + this.getName() + ", messageInfo=" + this.getMessageInfo() + ", messageType=" + this.getMessageType() + ", id=" + this.getId() + ", userType=" + this.getUserType() + ", admin=" + this.isAdmin() + ", role=" + this.getRole() + ", card=" + this.getCard() + ")";
    }

    public static class UserNewDTOBuilder {
        private String account;
        private String password;
        private String name;
        private String messageInfo;
        private String messageType;
        private String id;
        private String userType;
        private boolean admin;
        private String role;
        private String card;

        UserNewDTOBuilder() {
        }

        public UserNewDTO.UserNewDTOBuilder account(final String account) {
            this.account = account;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder messageInfo(final String messageInfo) {
            this.messageInfo = messageInfo;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder messageType(final String messageType) {
            this.messageType = messageType;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder userType(final String userType) {
            this.userType = userType;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder admin(final boolean admin) {
            this.admin = admin;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder role(final String role) {
            this.role = role;
            return this;
        }

        public UserNewDTO.UserNewDTOBuilder card(final String card) {
            this.card = card;
            return this;
        }

        public UserNewDTO build() {
            return new UserNewDTO(this.account, this.password, this.name, this.messageInfo, this.messageType, this.id, this.userType, this.admin, this.role, this.card);
        }

        @Override
        public String toString() {
            return "UserNewDTO.UserNewDTOBuilder(account=" + this.account + ", password=" + this.password + ", name=" + this.name + ", messageInfo=" + this.messageInfo + ", messageType=" + this.messageType + ", id=" + this.id + ", userType=" + this.userType + ", admin=" + this.admin + ", role=" + this.role + ",card=" + this.card + ")";
        }
    }
}
