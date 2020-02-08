package com.darapay.loanreferral.models;


import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@ToString
public class Privilege extends TransactionEntityImpl<String> {

    public Privilege() {}

    public Privilege(@NotEmpty String name, @NotEmpty String description) {
        this.name = name;
        this.description = description;
    }

    @ModelBinding()
    @Column(name = "name", unique = true)
    private @NonNull @NotEmpty String name;

    @ModelBinding()
    @Column(name = "description")
    private @NonNull @NotEmpty String description;

}
