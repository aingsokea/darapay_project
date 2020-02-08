package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configure")
@Getter
@Setter
public class Configure extends TransactionEntityImpl<String> {

    @ModelBinding()
    @Column(name = "name")
    private @NonNull
    String name;

    @ModelBinding()
    @Column(name = "item1")
    private String item1;

    @ModelBinding()
    @Column(name = "item2")
    private String item2;

    @ModelBinding()
    @Column(name = "type")
    private @NonNull String type;
}
