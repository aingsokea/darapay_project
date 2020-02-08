package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoloanStatusDisplayModel {

    public AutoloanStatusDisplayModel() {}

    public AutoloanStatusDisplayModel(String id, String name, String namekh) {
        this.id = id;
        this.name = name;
        this.namekh = namekh;
    }

    private String id;
    private String name;
    private String namekh;
}
