package com.darapay.loanreferral.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity<TKey> {
	@Id
	@Column(name = "id")
    protected TKey id;
}
