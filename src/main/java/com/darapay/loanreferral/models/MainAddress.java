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
@Table(name = "mainaddress")
@Getter
@Setter
public class MainAddress extends TransactionEntityImpl<String> {
    @ModelBinding()
    @Column(name = "name")
    private @NonNull
    String name;

    @ModelBinding()
    @Column(name = "namekh")
    private @NonNull
    String namekh;

    @ModelBinding()
    @Column(name = "type")
    private @NonNull
    String type;

    @ModelBinding()
    @Column(name = "addresscode")
    private @NonNull
    String addresscode;

    @ModelBinding()
    @Column(name = "parentcode")
    private String parentcode;

    @ModelBinding()
    @Column(name = "reference")
    private String reference;
}
