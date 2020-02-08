package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MfiDisplayOnloadModel extends MfiDisplayModel {
    public MfiDisplayOnloadModel() {}

    public MfiDisplayOnloadModel(List<LoanTypeDisplayModel> loanTypeDisplayModels) {
        this.loanTypeDisplayModels = loanTypeDisplayModels;
    }

    public MfiDisplayOnloadModel(String key, String name, String pic, String keyfrm, List<LoanTypeDisplayModel> loanTypeDisplayModels,List<BranchDisPlayDdkModel> branchDisplayModels) {
        super(key, name, pic, keyfrm);
        this.loanTypeDisplayModels = loanTypeDisplayModels;
        this.branchDisplayModels=branchDisplayModels;
    }

    List<LoanTypeDisplayModel> loanTypeDisplayModels;
    List<BranchDisPlayDdkModel> branchDisplayModels;
}
