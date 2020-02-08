package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "navigation")
@Getter
@Setter
public class Navigation extends TransactionEntityImpl<String> {
    public Navigation() {}

    public Navigation(String roleid, String key, String title, String translate, String type, String icon, String url, String taborder) {
        this.roleid = roleid;
        this.key = key;
        this.title = title;
        this.translate = translate;
        this.type = type;
        this.icon = icon;
        this.url = url;
        this.taborder = taborder;
    }

    @ModelBinding()
    private @NonNull
    String roleid;

    @ModelBinding()
    private String key;

    @ModelBinding()
    private String title;

    @ModelBinding()
    private String translate;

    @ModelBinding()
    private String type;

    @ModelBinding()
    private String icon;

    @ModelBinding()
    private String url;

    @ModelBinding()
    private String taborder;
}
