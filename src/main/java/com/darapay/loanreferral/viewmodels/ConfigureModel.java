package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Configure;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ConfigureModel extends ModelFactory<Configure, String> implements IModelFactory<Configure, String> {

    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    private String item1;

    @ModelBinding()
    private String item2;
    @ModelBinding()
    @NotNull(message = "Type cannot be null.")
    private String type;
}
