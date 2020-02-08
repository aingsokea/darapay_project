package com.darapay.loanreferral.viewmodels;


import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.entities.BaseEntity;

public abstract class ModelFactoryBase<TEntity extends BaseEntity<TKey>, TKey> implements IModelFactory<TEntity, TKey> {
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
