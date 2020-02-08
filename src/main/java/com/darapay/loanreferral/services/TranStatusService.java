package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.TranStatus;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import com.darapay.loanreferral.viewmodels.export.TranStatusExportModel;
import com.darapay.loanreferral.viewmodels.presentation.TranStatusDisplayModel;

import java.util.List;

public interface TranStatusService extends BaseService<TranStatus, String, TranStatusModel, TranStatusExportModel> {
    List<TranStatusDisplayModel> getAllByFrmLoanIdOrderByCreatedDate(String frmloanid, Boolean enable);
}
