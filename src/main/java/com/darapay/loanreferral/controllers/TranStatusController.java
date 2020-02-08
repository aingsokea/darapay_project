package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.TranStatus;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.TranStatusService;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import com.darapay.loanreferral.viewmodels.export.TranStatusExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transtatus")
public class TranStatusController extends EntityApiController<TranStatus, String, TranStatusModel, TranStatusExportModel> {

    @Autowired
    private TranStatusService tranStatusService;

    @Override
    protected BaseService<TranStatus, String, TranStatusModel, TranStatusExportModel> getBaseService() {
        return tranStatusService;
    }

    @Override
    protected TranStatus NewEntity() {
        return new TranStatus();
    }

    @Override
    protected TranStatusModel NewModel() {
        return new TranStatusModel();
    }
}
