package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDisplayDdlModel {

    public RoleDisplayDdlModel() {}

    public RoleDisplayDdlModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;
}
