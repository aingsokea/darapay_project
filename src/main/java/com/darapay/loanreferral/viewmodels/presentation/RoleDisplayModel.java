package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoleDisplayModel {

    public RoleDisplayModel() {}

    public RoleDisplayModel(String key, String name, String level, String owner, String mfiid, String responsible, Date effectivedate, Date expirationdate, String description) {
        this.key = key;
        this.name = name;
        this.level = level;
        this.owner = owner;
        this.mfiid = mfiid;
        this.responsible = responsible;
        this.effectivedate = effectivedate;
        this.expirationdate = expirationdate;
        this.description = description;
    }

    private String key;
    private String name;
    private String level;
    private String owner;
    private String mfiid;
    private String responsible;
    private Date effectivedate;
    private Date expirationdate;
    private String description;
}
