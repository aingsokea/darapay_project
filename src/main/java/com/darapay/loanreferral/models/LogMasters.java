package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "logmasters")
public class LogMasters extends TransactionEntityImpl<String> {
    public LogMasters() { }

    public LogMasters(String relatedid, String fromtable, String fromdatas, String todatas, String type) {
        this.relatedid = relatedid;
        this.fromtable = fromtable;
        this.fromdatas = fromdatas;
        this.todatas = todatas;
        this.type = type;
    }

    @ModelBinding()
    @Column(name = "relatedid")
    private @NonNull
    String relatedid;

    @ModelBinding()
    @Column(name = "fromtable")
    private @NonNull String fromtable;

    @ModelBinding()
    @Column(name = "fromdatas")
    private @NonNull String fromdatas;

    @ModelBinding()
    @Column(name = "todatas")
    private @NonNull String todatas;

    @ModelBinding()
    @Column(name = "type")
    private @NonNull String type;
}
