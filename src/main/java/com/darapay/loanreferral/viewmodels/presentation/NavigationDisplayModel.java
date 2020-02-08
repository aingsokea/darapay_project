package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavigationDisplayModel {

    public NavigationDisplayModel() {}

    public NavigationDisplayModel(String id, String title, String translate, String type, String icon, String url) {
        this.id = id;
        this.title = title;
        this.translate = translate;
        this.type = type;
        this.icon = icon;
        this.url = url;
    }

    private String id;
    private String title;
    private String translate;
    private String type;
    private String icon;
    private String url;
}
