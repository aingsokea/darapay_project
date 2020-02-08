package com.darapay.loanreferral.viewmodels;

public interface IModelFactory<TEntity, TKey> {
	TKey getId();

	void Parse(TEntity entity);

	void Update(TEntity entity);
}
