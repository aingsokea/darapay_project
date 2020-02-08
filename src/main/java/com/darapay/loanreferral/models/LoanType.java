package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loantype")
@Getter
@Setter
public class LoanType extends TransactionEntityImpl<String> {
    @ModelBinding()
    private @NonNull
    String name;

    @ModelBinding()
    private String namekh;

    @ModelBinding()
    private String description;

    @ModelBinding()
    private String mfiid;

    @ModelBinding()
    private String taborder;
}
