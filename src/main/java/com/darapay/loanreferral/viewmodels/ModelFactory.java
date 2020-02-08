package com.darapay.loanreferral.viewmodels;


import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.entities.BaseEntity;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ModelFactory<TEntity extends BaseEntity<TKey>, TKey> extends TransactionEntityImpl<TKey> implements IModelFactory<TEntity, TKey> {

	@ModelBinding()
	TKey id;

	public TKey getId()
	{
		return id;
	}

	@Override
	public void Parse(TEntity tEntity) {
		id = tEntity.getId();
		AnonymousFunc.AssignClass(tEntity, this);
	}

	@Override
	public void Update(TEntity tEntity) {
		AnonymousFunc.AssignClass(this, tEntity);
	}


}
