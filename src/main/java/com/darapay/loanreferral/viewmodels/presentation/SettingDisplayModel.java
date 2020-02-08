package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingDisplayModel {
    public SettingDisplayModel () {}

    public SettingDisplayModel(String key, String name, String value) {
        this.key = key;
        this.name = name;
        this.value = value;
    }

    private String key;
    private String name;
    private String value;
}
