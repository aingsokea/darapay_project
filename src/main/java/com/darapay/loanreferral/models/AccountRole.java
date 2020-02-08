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
@Table(name = "accountrole")
public class AccountRole extends BaseEntity<String> {
    public AccountRole() {
    }

    public AccountRole(AccountRole accountRole) {
        this.id = accountRole.id;
        this.accountid = accountRole.accountid;
        this.roleid = accountRole.roleid;
        this.reportto = accountRole.reportto;
    }

    public AccountRole(String accountid, String roleid, String reportto) {
        this.accountid = accountid;
        this.roleid = roleid;
        this.reportto = reportto;
    }

    @ModelBinding()
    @Column(name = "accountid")
    private @NonNull String accountid;

    @ModelBinding()
    @Column(name = "roleid")
    private @NonNull String roleid;

    @ModelBinding()
    @Column(name = "reportto")
    private String reportto;
}
