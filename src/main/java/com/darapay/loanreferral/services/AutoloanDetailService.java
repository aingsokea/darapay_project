package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.AutoloanDetail;
import com.darapay.loanreferral.viewmodels.AutoloanDetailModel;
import com.darapay.loanreferral.viewmodels.export.AutoloanDetailExportModel;

import java.util.List;

public interface AutoloanDetailService extends BaseService<AutoloanDetail, String, AutoloanDetailModel, AutoloanDetailExportModel> {
    List<AutoloanDetailModel> findAllByAutoloanId(String autoloanid, Boolean enable);
}
