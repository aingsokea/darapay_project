package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.AccountRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AccountRoleModel extends ModelFactoryBase<AccountRole, String> implements IModelFactory<AccountRole, String> {
    public AccountRoleModel() {
    }
    public AccountRoleModel(String accountid, String roleid) {
        this.accountid = accountid;
        this.roleid = roleid;
    }

    @ModelBinding()
    @NotNull(message = "account id cannot be null.")
    private String accountid;

    @ModelBinding()
    @NotNull(message = "role id cannot be null.")
    private String roleid;

    @ModelBinding()
    private String reportto;
}
