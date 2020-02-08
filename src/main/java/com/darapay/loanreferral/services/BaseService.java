package com.darapay.loanreferral.services;

import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.IModelFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<TEntity, TKey extends Serializable, TModel extends IModelFactory<TEntity, TKey>, TExport> {
	TEntity save(TEntity entity);
	TEntity find(TKey id);
	List<TEntity> findAll();
	Optional<TEntity> fineById(TKey id);
	List<TModel> getAdvanceFilter(AdvanceModel advanceModel);
	List<TModel> getSearch(AdvanceModel advanceModel);
	Integer countAdvanceFilter(AdvanceModel advanceModel);
	Integer countFilter(AdvanceModel advanceModel);
	List<TExport> getExport(AdvanceModel advanceModel);
	List<TExport> getExportExcel(AdvanceModel advanceModel);
	List<TExport> ExportFile(AdvanceModel advanceModel);
	List<TExport> getExportFunan(AdvanceModel advanceModel);
}
