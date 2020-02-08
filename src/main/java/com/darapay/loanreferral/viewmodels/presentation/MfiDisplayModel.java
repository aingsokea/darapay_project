package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MfiDisplayModel {

    public MfiDisplayModel() {}

    public MfiDisplayModel(String key, String name, String pic, String keyfrm) {
        this.key = key;
        this.name = name;
        this.pic = pic;
        this.keyfrm = keyfrm;
    }

    private String key;
    private String name;
    private String pic;
    private String keyfrm;
}
