package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Privilege;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PrivilegeModel extends ModelFactory<Privilege, String> implements IModelFactory<Privilege, String> {
    @ModelBinding()
    @NotNull(message = "name cannot be null.")
    private String name;

    @ModelBinding()
    @NotNull(message = "description cannot be null.")
    private String description;
}
