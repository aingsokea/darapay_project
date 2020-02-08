package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Mfi;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MfiModel extends ModelFactory<Mfi, String> implements IModelFactory<Mfi, String> {
    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    private String pic;

    @ModelBinding()
    private String key;

    @ModelBinding()
    private String taborder;
}
