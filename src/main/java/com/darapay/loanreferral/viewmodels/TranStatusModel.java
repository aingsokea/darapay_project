package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.TranStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranStatusModel extends ModelFactory<TranStatus, String> implements IModelFactory<TranStatus, String> {

    @ModelBinding()
    private String frmloanid;

    @ModelBinding()
    private String fromst;

    @ModelBinding()
    private String tost;

    @ModelBinding()
    private String comment;

}
