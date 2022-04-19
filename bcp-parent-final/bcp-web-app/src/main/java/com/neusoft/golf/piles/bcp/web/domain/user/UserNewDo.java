package com.neusoft.golf.piles.bcp.web.domain.user;

import com.neusoft.golf.piles.bcp.web.domain.role.RoleDO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 */
@Entity
@Access(AccessType.FIELD)
@Table(
        name = "T_USER"
)
public class UserNewDo implements Serializable {
    private static final long serialVersionUID = 8909095416953337168L;
    @Id
    @GeneratedValue(
            generator = "system-uuid"
    )
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid"
    )
    private String id;
    private String name;
    private String password;
    private String type;
    private String account;
    private Boolean locked;
    private String card;

    @Column(
            name = "locked_reason"
    )
    private String lockedReason;
    @Column(
            name = "password_expire"
    )
    private Long passwordExpire;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "T_USER_ROLE",
            joinColumns = {@JoinColumn(
                    name = "user_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id"
            )}
    )
    private Set<RoleDO> roles = new HashSet();

    public Boolean isAdmin() {
        Set<RoleDO> roleSet = this.getRoles();
        if (null != roleSet && !roleSet.isEmpty()) {
            Iterator iterator = roleSet.iterator();

            RoleDO role;
            do {
                if (!iterator.hasNext()) {
                    return false;
                }

                role = (RoleDO)iterator.next();
            } while(!role.getName().equalsIgnoreCase("ROLE_ADMIN"));

            return true;
        } else {
            return false;
        }
    }

    public void lockUser(String lockedReason) {
        this.setLocked(true);
        this.setLockedReason(lockedReason);
    }

    public void unlocked() {
        this.setLocked(false);
        this.setLockedReason("");
    }

    public static UserNewDo.UserNewDOBuilder builder() {
        return new UserNewDo.UserNewDOBuilder();
    }

    public UserNewDo(final String id, final String name, final String password, final String type, final String account, final Boolean locked, final String lockedReason, final Long passwordExpire, final Set<RoleDO> roles,final String card) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.account = account;
        this.locked = locked;
        this.lockedReason = lockedReason;
        this.passwordExpire = passwordExpire;
        this.roles = roles;
        this.card = card;
    }

    public UserNewDo() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType() {
        return this.type;
    }

    public String getAccount() {
        return this.account;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public String getLockedReason() {
        return this.lockedReason;
    }

    public Long getPasswordExpire() {
        return this.passwordExpire;
    }

    public Set<RoleDO> getRoles() {
        return this.roles;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setLocked(final Boolean locked) {
        this.locked = locked;
    }

    public void setLockedReason(final String lockedReason) {
        this.lockedReason = lockedReason;
    }

    public void setPasswordExpire(final Long passwordExpire) {
        this.passwordExpire = passwordExpire;
    }

    public void setRoles(final Set<RoleDO> roles) {
        this.roles = roles;
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
        } else if (!(o instanceof UserDO)) {
            return false;
        } else {
            UserNewDo other = (UserNewDo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label119: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label119;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label119;
                    }

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

                Object this$card = this.getCard();
                Object other$card = other.getCard();
                if (this$card == null) {
                    if (other$card != null) {
                        return false;
                    }
                } else if (!this$card.equals(other$card)) {
                    return false;
                }

                label105: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label105;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label105;
                    }

                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                label91: {
                    Object this$account = this.getAccount();
                    Object other$account = other.getAccount();
                    if (this$account == null) {
                        if (other$account == null) {
                            break label91;
                        }
                    } else if (this$account.equals(other$account)) {
                        break label91;
                    }

                    return false;
                }

                Object this$locked = this.getLocked();
                Object other$locked = other.getLocked();
                if (this$locked == null) {
                    if (other$locked != null) {
                        return false;
                    }
                } else if (!this$locked.equals(other$locked)) {
                    return false;
                }

                label77: {
                    Object this$lockedReason = this.getLockedReason();
                    Object other$lockedReason = other.getLockedReason();
                    if (this$lockedReason == null) {
                        if (other$lockedReason == null) {
                            break label77;
                        }
                    } else if (this$lockedReason.equals(other$lockedReason)) {
                        break label77;
                    }

                    return false;
                }

                label70: {
                    Object this$passwordExpire = this.getPasswordExpire();
                    Object other$passwordExpire = other.getPasswordExpire();
                    if (this$passwordExpire == null) {
                        if (other$passwordExpire == null) {
                            break label70;
                        }
                    } else if (this$passwordExpire.equals(other$passwordExpire)) {
                        break label70;
                    }

                    return false;
                }

                Object this$roles = this.getRoles();
                Object other$roles = other.getRoles();
                if (this$roles == null) {
                    if (other$roles != null) {
                        return false;
                    }
                } else if (!this$roles.equals(other$roles)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDO;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $account = this.getAccount();
        result = result * 59 + ($account == null ? 43 : $account.hashCode());
        Object $locked = this.getLocked();
        result = result * 59 + ($locked == null ? 43 : $locked.hashCode());
        Object $lockedReason = this.getLockedReason();
        result = result * 59 + ($lockedReason == null ? 43 : $lockedReason.hashCode());
        Object $passwordExpire = this.getPasswordExpire();
        result = result * 59 + ($passwordExpire == null ? 43 : $passwordExpire.hashCode());
        Object $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : $roles.hashCode());
        Object $card = this.getCard();
        result = result * 59 + ($card == null ? 43 : $card.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UserNewDO(id=" + this.getId() + ", name=" + this.getName() + ", password=" + this.getPassword() + ", type=" + this.getType() + ", account=" + this.getAccount() + ", locked=" + this.getLocked() + ", lockedReason=" + this.getLockedReason() + ", passwordExpire=" + this.getPasswordExpire() + ", roles=" + this.getRoles() + ", card=" + this.getCard() +")";
    }

    public static class UserNewDOBuilder {
        private String id;
        private String name;
        private String password;
        private String type;
        private String account;
        private Boolean locked;
        private String lockedReason;
        private Long passwordExpire;
        private Set<RoleDO> roles;
        private String card;

        UserNewDOBuilder() {
        }

        public UserNewDo.UserNewDOBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public UserNewDo.UserNewDOBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserNewDo.UserNewDOBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserNewDo.UserNewDOBuilder type(final String type) {
            this.type = type;
            return this;
        }

        public UserNewDo.UserNewDOBuilder account(final String account) {
            this.account = account;
            return this;
        }

        public UserNewDo.UserNewDOBuilder locked(final Boolean locked) {
            this.locked = locked;
            return this;
        }

        public UserNewDo.UserNewDOBuilder lockedReason(final String lockedReason) {
            this.lockedReason = lockedReason;
            return this;
        }

        public UserNewDo.UserNewDOBuilder passwordExpire(final Long passwordExpire) {
            this.passwordExpire = passwordExpire;
            return this;
        }

        public UserNewDo.UserNewDOBuilder roles(final Set<RoleDO> roles) {
            this.roles = roles;
            return this;
        }

        public UserNewDo.UserNewDOBuilder card(final String card) {
            this.card = card;
            return this;
        }

        public UserNewDo build() {
            return new UserNewDo(this.id, this.name, this.password, this.type, this.account, this.locked, this.lockedReason, this.passwordExpire, this.roles,this.card);
        }

        @Override
        public String toString() {
            return "UserNewDo.UserNewDOBuilder(id=" + this.id + ", name=" + this.name + ", password=" + this.password + ", type=" + this.type + ", account=" + this.account + ", locked=" + this.locked + ", lockedReason=" + this.lockedReason + ", passwordExpire=" + this.passwordExpire + ", roles=" + this.roles + ", card="+ this.card +")";
        }
    }
}
