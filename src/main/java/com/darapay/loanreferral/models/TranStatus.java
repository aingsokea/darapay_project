package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transtatus")
@Getter
@Setter
public class TranStatus extends TransactionEntityImpl<String> {

    public TranStatus() {}

    public TranStatus(String frmloanid, String fromst, String tost, String comment) {
        this.frmloanid = frmloanid;
        this.fromst = fromst;
        this.tost = tost;
        this.comment = comment;
    }

    @ModelBinding()
    private @NonNull
    String frmloanid;

    @ModelBinding()
    private @NonNull
    String fromst;

    @ModelBinding()
    private @NonNull
    String tost;

    @ModelBinding()
    private String comment;
}
