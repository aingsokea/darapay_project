package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.LogMasters;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.LogMastersService;
import com.darapay.loanreferral.viewmodels.LogMastersModel;
import com.darapay.loanreferral.viewmodels.export.LogMastersExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logmaster")
public class LogMasterController extends EntityApiController<LogMasters, String, LogMastersModel, LogMastersExportModel> {

    @Autowired
    private LogMastersService logMastersService;

    @Override
    protected BaseService<LogMasters, String, LogMastersModel, LogMastersExportModel> getBaseService() {
        return logMastersService;
    }

    @Override
    protected LogMasters NewEntity() {
        return new LogMasters();
    }

    @Override
    protected LogMastersModel NewModel() {
        return new LogMastersModel();
    }
}
