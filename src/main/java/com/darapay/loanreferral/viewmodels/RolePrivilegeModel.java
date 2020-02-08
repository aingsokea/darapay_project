package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.RolePrivilege;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RolePrivilegeModel extends ModelFactoryBase<RolePrivilege, String> implements IModelFactory<RolePrivilege, String> {
    @ModelBinding()
    private String roleid;

    @ModelBinding()
    @NotNull(message = "privilege id cannot be null.")
    private String privilegeid;

    private String privilege;
}
