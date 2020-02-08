package com.darapay.loanreferral.entities;

import com.darapay.loanreferral.annotations.ModelBinding;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public class TransactionEntityImpl<TKey> extends BaseEntity<TKey> implements TransactionEntity {
	
	@ModelBinding()
	@Column(name = "createddate")
	private Date createddate;

	@ModelBinding()
	@Column(name = "createdby")
	private String createdby;

	@ModelBinding()
	@Column(name = "modifieddate")
	private Date modifieddate;

	@ModelBinding()
	@Column(name = "modifiedby")
	private String modifiedby;

	@ModelBinding()
	@Column(name = "enable")
	private @NonNull
	boolean enable = true;
}
