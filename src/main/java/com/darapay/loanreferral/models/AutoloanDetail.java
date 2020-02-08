package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "autoloandetail")
public class AutoloanDetail extends TransactionEntityImpl<String> {

    public AutoloanDetail() {}

    public AutoloanDetail(String autoloanid, String image_url, String image_type) {
        this.autoloanid = autoloanid;
        this.image_url = image_url;
        this.image_type = image_type;
    }

    public AutoloanDetail(String id, String autoloanid, String image_url, String image_type) {
        this.id = id;
        this.autoloanid = autoloanid;
        this.image_url = image_url;
        this.image_type = image_type;
    }


    @ModelBinding()
    @Column(name = "autoloanid")
    private @NonNull
    String autoloanid;

    @ModelBinding()
    @Column(name = "image_url")
    private  @NonNull
    String image_url;

    @ModelBinding()
    @Column(name = "image_type")
    private @NonNull
    String image_type;
}
