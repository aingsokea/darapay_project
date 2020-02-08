package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleModel extends ModelFactory<Role, String> implements IModelFactory<Role, String>{
    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    private String level;

    @ModelBinding()
    private String owner;

    @ModelBinding()
    private String mfiid;

    @ModelBinding()
    private String branchid;

    @ModelBinding()
    private String responsible;

    @ModelBinding()
    private Date effectivedate;

    @ModelBinding()
    private Date expirationdate;

    @ModelBinding()
    private String description;

    @ModelBinding()
    private String protype;

    private List<RolePrivilegeModel> rolePrivilegeModels;

    private List<SettingModel> settingModels;

    private List<NavigationModel> navigationModels;
}
