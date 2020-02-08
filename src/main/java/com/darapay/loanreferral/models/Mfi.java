package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mfi")
@Getter
@Setter
public class Mfi extends TransactionEntityImpl<String> {
    @ModelBinding()
    private @NonNull
    String name;

    @ModelBinding()
    private String pic;

    @ModelBinding()
    private @NonNull
    String key;

    @ModelBinding()
    private @NonNull
    String taborder;
}
