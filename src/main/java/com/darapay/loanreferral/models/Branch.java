package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "branch")
@Getter
@Setter
public class Branch extends TransactionEntityImpl<String> {

    @ModelBinding()
    private @NonNull
    String mfiid;

    @ModelBinding()
    private @NonNull
    String name;

    @ModelBinding()
    private String code;

    @ModelBinding()
    private @NonNull
    int index;
}
