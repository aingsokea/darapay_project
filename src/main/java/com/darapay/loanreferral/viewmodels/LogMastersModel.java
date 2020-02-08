package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.LogMasters;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LogMastersModel extends ModelFactory<LogMasters, String> implements IModelFactory<LogMasters, String> {
    @ModelBinding()
    @NotNull(message = "Related Id cannot be null.")
    private String relatedid;

    @ModelBinding()
    @NotNull(message = "From Table cannot be null.")
    private String fromtable;

    @ModelBinding()
    @NotNull(message = "From Datas cannot be null.")
    private String fromdatas;

    @ModelBinding()
    @NotNull(message = "To Datas cannot be null.")
    private String todatas;

    @ModelBinding()
    @NotNull(message = "Type cannot be null.")
    private String type;
}
