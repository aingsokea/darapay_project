package com.darapay.loanreferral.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterModel {

    public FilterModel() {}

    public FilterModel(String name, String operation, String from, String to, String value, String group) {
        this.name = name;
        this.operation = operation;
        this.from = from;
        this.to = to;
        this.value = value;
        this.group = group;
    }

    private String name;
    private String operation;
    private String from;
    private String to;
    private String value;
    private String group;
}

