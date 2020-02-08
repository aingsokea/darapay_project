package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDisPlayDdkModel {

    public BranchDisPlayDdkModel() {}

    public BranchDisPlayDdkModel(String key, String name, String code,int index) {
        this.key = key;
        this.name = name;
        this.code = code;
        this.index=index;
    }

    private String key;
    private String name;
    private String code;
    private int index;
}
