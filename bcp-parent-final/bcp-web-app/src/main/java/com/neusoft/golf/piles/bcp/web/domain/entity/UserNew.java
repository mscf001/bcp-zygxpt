package com.neusoft.golf.piles.bcp.web.domain.entity;

import com.neusoft.golf.piles.bcp.core.domain.entity.User;

/**
 * @author Administrator
 */
public class UserNew extends User {

    private String card;

    public static UserNew.UserNewBuilder builderNew() {
        return new UserNew.UserNewBuilder();
    }

    public UserNew(final String card) {
        this.card = card;
    }

    public UserNew() {
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        super.equals(o);
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        }else {
            UserNew other = (UserNew)o;
            if (!other.canEqual(this)) {
                return false;
            }else {
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

    @Override
    public int hashCode() {
        super.hashCode();
        boolean PRIME = true;
        int result = 1;
        Object $card = this.getCard();
        result = result * 59 + ($card == null ? 43 : $card.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UserNew(id=" + super.getId() + ", name=" + super.getName() + ", password=" + super.getPassword() + ", type=" + super.getType() + ", account=" + super.getAccount() + ", locked=" + super.getLocked() + ", lockedReason=" + super.getLockedReason() + ", card=" + this.getCard() +" )";
    }

    public static class UserNewBuilder {

        private String card;

        private String aa;

        UserNewBuilder() {
        }

        public UserNew.UserNewBuilder card(final String card) {
            this.card = card;
            return this;
        }

        public UserNew buildNew() {
            return new UserNew(this.card);
        }

        @Override
        public String toString() {
            return "UserNew.UserNewBuilder(card=" + this.card + ")";
        }
    }
}
