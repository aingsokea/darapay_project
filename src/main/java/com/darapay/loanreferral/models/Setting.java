package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "setting")
@Getter
@Setter
public class Setting extends TransactionEntityImpl<String> {

    public Setting() {}

    public Setting(String roleid, String name, String value) {
        this.roleid = roleid;
        this.name = name;
        this.value = value;
    }

    @ModelBinding()
    private @NonNull
    String roleid;

    @ModelBinding()
    private @NonNull
    String name;

    @ModelBinding()
    private @NonNull
    String value;
}
