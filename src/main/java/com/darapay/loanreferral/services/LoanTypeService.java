package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.LoanType;
import com.darapay.loanreferral.viewmodels.LoanTypeModel;
import com.darapay.loanreferral.viewmodels.export.LoanTypeExportModel;
import com.darapay.loanreferral.viewmodels.presentation.LoanTypeDisplayModel;

import java.util.List;

public interface LoanTypeService extends BaseService<LoanType, String, LoanTypeModel, LoanTypeExportModel> {
    List<LoanTypeDisplayModel> getLoanTypeByMfiId(String mfiid, Boolean enable);
}
