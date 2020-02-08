package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Setting;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SettingModel extends ModelFactory<Setting, String> implements IModelFactory<Setting, String> {

    @ModelBinding()
    @NotNull(message = "Role id cannot be null.")
    private String roleid;

    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    @NotNull(message = "Value cannot be null.")
    private String value;
}
