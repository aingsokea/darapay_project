package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.LoanType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoanTypeModel extends ModelFactory<LoanType, String> implements IModelFactory<LoanType, String> {
    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    private String namekh;

    @ModelBinding()
    private String description;

    @ModelBinding()
    private String mfiid;

    @ModelBinding()
    private String taborder;
}
