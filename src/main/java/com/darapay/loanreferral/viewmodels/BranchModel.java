package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Branch;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BranchModel extends ModelFactory<Branch, String> implements IModelFactory<Branch, String> {

    @ModelBinding()
    @NotNull(message = "Mfi id cannot be null.")
    private String mfiid;

    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    private String code;
}

