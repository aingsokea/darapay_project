package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.Autoloan;
import com.darapay.loanreferral.viewmodels.AutoloanChangeStatusModel;
import com.darapay.loanreferral.viewmodels.AutoloanModel;
import com.darapay.loanreferral.viewmodels.core.AutoloanPostModel;
import com.darapay.loanreferral.viewmodels.core.AutoloanPutChangeStatus;
import com.darapay.loanreferral.viewmodels.export.AutoloanExportModel;
import com.darapay.loanreferral.viewmodels.presentation.AutoLoanOnloanDisplayModel;

import java.util.List;

public interface AutoloanService extends BaseService<Autoloan, String, AutoloanModel, AutoloanExportModel>{
    AutoLoanOnloanDisplayModel getOnLoad();
    AutoloanPostModel getAutoloanPostById(String id);
    AutoloanChangeStatusModel getAutoloanChangeStatusById(String id);
    Boolean postAutoLoanWithPhotos(AutoloanPostModel autoloanPostModel);
    Boolean putAutoLoanWithPhotos(String id, AutoloanPostModel autoloanPostModel);
    Boolean putChangeStatus(String id, AutoloanPutChangeStatus autoloanPutChangeStatus);
}
