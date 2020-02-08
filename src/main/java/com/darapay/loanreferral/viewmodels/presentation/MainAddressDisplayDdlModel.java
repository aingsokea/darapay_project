package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainAddressDisplayDdlModel {
    public MainAddressDisplayDdlModel() {}

    public MainAddressDisplayDdlModel(String key, String name, String namekh, String code, String parent) {
        this.key = key;
        this.name = name;
        this.namekh = namekh;
        this.code = code;
        this.parent = parent;
    }

    private String key;
    private String name;
    private String namekh;
    private String code;
    private String parent;
}
