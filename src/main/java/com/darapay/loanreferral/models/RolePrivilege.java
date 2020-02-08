package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.BaseEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "roleprivilege")
public class RolePrivilege extends BaseEntity<String> {

    public RolePrivilege () {}

    public RolePrivilege(String roleid, String privilegeid) {
        this.roleid = roleid;
        this.privilegeid = privilegeid;
    }

    @ModelBinding()
    @Column(name = "roleid")
    private @NonNull String roleid;

    @ModelBinding()
    @Column(name = "privilegeid")
    private @NonNull String privilegeid;
}
