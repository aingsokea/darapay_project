package com.darapay.loanreferral.services;


import com.darapay.loanreferral.models.FrmApp;
import com.darapay.loanreferral.viewmodels.FrmAppModel;
import com.darapay.loanreferral.viewmodels.export.FrmAppExportModel;

public interface FrmAppService extends BaseService<FrmApp, String, FrmAppModel, FrmAppExportModel> {
    FrmApp saveWithEmail(FrmApp entity);
}
