package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTypeDisplayModel {

    public LoanTypeDisplayModel () {}

    public LoanTypeDisplayModel(String key, String name, String namekh) {
        this.key = key;
        this.name = name;
        this.namekh = namekh;
    }

    private String key;
    private String name;
    private String namekh;
}