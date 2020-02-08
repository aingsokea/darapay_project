package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@ToString
@Table(name = "role")
public class Role extends TransactionEntityImpl<String> {

    public Role() {}

    public Role(String name, String level, String owner, String mfiid, String branchid, String responsible, Date effectivedate, Date expirationdate, String description, String protype) {
        this.name = name;
        this.level = level;
        this.owner = owner;
        this.mfiid = mfiid;
        this.branchid = branchid;
        this.responsible = responsible;
        this.effectivedate = effectivedate;
        this.expirationdate = expirationdate;
        this.description = description;
        this.protype = protype;
    }

    @ModelBinding()
    @Column(name = "name", unique = true)
    private @NonNull String name;

    @ModelBinding()
    @Column(name = "level")
    private String level;

    @ModelBinding()
    @Column(name = "owner")
    private String owner;

    @ModelBinding()
    @Column(name = "mfiid")
    private String mfiid;

    @ModelBinding()
    @Column(name = "branchid")
    private String branchid;

    @ModelBinding()
    @Column(name = "responsible")
    private String responsible;

    @ModelBinding()
    @Column(name = "effectivedate")
    private Date effectivedate;

    @ModelBinding()
    @Column(name = "expirationdate")
    private Date expirationdate;

    @ModelBinding()
    @Column(name = "description")
    private String description;

    @ModelBinding()
    @Column(name = "protype")
    private String protype;

    @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }  )
    @JoinTable(
            name = "roleprivilege",
            catalog = "public",
            joinColumns = {@JoinColumn(name = "roleid")},
            inverseJoinColumns = {@JoinColumn(name = "privilegeid")}
    )
    private Set<Privilege> privileges = new HashSet<>(0);
}
