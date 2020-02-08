package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accountroleauthorize")
@Getter
@Setter
public class AccountRoleAuthorize extends TransactionEntityImpl<String> {
    @ModelBinding()
    @Column(name = "accountid")
    private @NonNull
    String accountid;

    @ModelBinding()
    @Column(name = "username")
    private @NonNull
    String username;

    @ModelBinding()
    @Column(name = "roleid")
    private @NonNull
    String roleid;
}
