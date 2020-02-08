package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusNavDisplayModel {

    public StatusNavDisplayModel() {}

    public StatusNavDisplayModel(String title, Integer count, String icon, Boolean active) {
        this.title = title;
        this.count = count;
        this.icon = icon;
        this.active = active;
    }

    private String title;
    private Integer count;
    private String icon;
    private Boolean active;
}
