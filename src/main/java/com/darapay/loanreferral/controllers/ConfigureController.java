package com.darapay.loanreferral.controllers;


import com.darapay.loanreferral.models.Configure;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.ConfigureService;
import com.darapay.loanreferral.viewmodels.ConfigureModel;
import com.darapay.loanreferral.viewmodels.export.ConfigureExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configure")
public class ConfigureController extends EntityApiController<Configure, String, ConfigureModel, ConfigureExportModel> {
    @Autowired
    private ConfigureService configureService;

    @Override
    protected BaseService<Configure, String, ConfigureModel, ConfigureExportModel> getBaseService() {
        return configureService;
    }

    @Override
    protected Configure NewEntity() {
        return new Configure();
    }

    @Override
    protected ConfigureModel NewModel() {
        return new ConfigureModel();
    }
}
