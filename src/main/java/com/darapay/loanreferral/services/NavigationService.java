package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.Navigation;
import com.darapay.loanreferral.viewmodels.NavigationModel;
import com.darapay.loanreferral.viewmodels.export.NavigationExportModel;
import com.darapay.loanreferral.viewmodels.presentation.NavigationDisplayModel;

import java.util.List;

public interface NavigationService extends BaseService<Navigation, String, NavigationModel, NavigationExportModel> {
    List<NavigationDisplayModel> findNavigationByRoleIdAndEnable(String roleid, Boolean enable);
}
