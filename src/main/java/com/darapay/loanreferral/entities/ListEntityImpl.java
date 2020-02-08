package com.darapay.loanreferral.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public abstract class ListEntityImpl<TKey> extends BaseEntity<TKey> implements ListEntity
{
	@Column(name = "enable", nullable = false)
	private int enable = 0;

	@Column(name = "createddate", nullable = false)
	private Date createddate = new Date();
	@Column(name = "modifieddate")
	private Date modifieddate;
}
