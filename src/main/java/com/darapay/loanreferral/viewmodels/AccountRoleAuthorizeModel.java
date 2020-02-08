package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.AccountRoleAuthorize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountRoleAuthorizeModel extends ModelFactory<AccountRoleAuthorize, String> implements IModelFactory<AccountRoleAuthorize, String>{

    public AccountRoleAuthorizeModel() {}

    public AccountRoleAuthorizeModel(@NotNull(message = "Account Id can not be null.") String accountid, @NotNull(message = "Username can not be null.") String username, @NotNull(message = "Role Id can not be null.") String roleid) {
        this.accountid = accountid;
        this.username = username;
        this.roleid = roleid;
    }

    @ModelBinding()
    @NotNull(message = "Account Id can not be null.")
    private String accountid;

    @ModelBinding()
    @NotNull(message = "Username can not be null.")
    private String username;

    @ModelBinding()
    @NotNull(message = "Role Id can not be null.")
    private String roleid;
}
