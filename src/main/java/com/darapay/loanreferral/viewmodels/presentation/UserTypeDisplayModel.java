package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTypeDisplayModel {
    public UserTypeDisplayModel() {}

    public UserTypeDisplayModel(String userkey, String name, String namekh, String order) {
        this.userkey = userkey;
        this.name = name;
        this.namekh = namekh;
        this.order = order;
    }

    private String userkey;
    private String name;
    private String namekh;
    private String order;
}
