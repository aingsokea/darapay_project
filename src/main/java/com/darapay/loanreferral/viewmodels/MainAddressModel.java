package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.MainAddress;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MainAddressModel extends ModelFactory<MainAddress, String> implements IModelFactory<MainAddress, String> {
    @ModelBinding()
    @NotNull(message = "Name cannot be null.")
    private String name;

    @ModelBinding()
    @NotNull(message = "Name Khmer cannot be null.")
    private String namekh;

    @ModelBinding()
    @NotNull(message = "Type can not be null.")
    private String type;

    @ModelBinding()
    @NotNull(message = "Address Code cannot be null.")
    private String addresscode;

    @ModelBinding()
    private String parentcode;

    @ModelBinding()
    private String reference;
}
