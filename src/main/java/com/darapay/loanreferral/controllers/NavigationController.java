package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Navigation;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.NavigationService;
import com.darapay.loanreferral.viewmodels.NavigationModel;
import com.darapay.loanreferral.viewmodels.export.NavigationExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navigation")
public class NavigationController extends EntityApiController<Navigation, String, NavigationModel, NavigationExportModel> {

    @Autowired
    private NavigationService navigationService;

    @Override
    protected BaseService<Navigation, String, NavigationModel, NavigationExportModel> getBaseService() {
        return navigationService;
    }

    @Override
    protected Navigation NewEntity() {
        return new Navigation();
    }

    @Override
    protected NavigationModel NewModel() {
        return new NavigationModel();
    }
}
