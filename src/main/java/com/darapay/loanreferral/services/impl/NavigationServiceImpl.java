package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.Navigation;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.NavigationRepository;
import com.darapay.loanreferral.services.NavigationService;
import com.darapay.loanreferral.viewmodels.NavigationModel;
import com.darapay.loanreferral.viewmodels.export.NavigationExportModel;
import com.darapay.loanreferral.viewmodels.presentation.NavigationDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class NavigationServiceImpl extends BaseServiceImpl<Navigation, String, NavigationModel, NavigationExportModel> implements NavigationService {

    @Autowired
    private NavigationRepository navigationRepository;

    @Override
    protected ExtendedRepository<Navigation, String> getBaseRepository() {
        return navigationRepository;
    }

    @Override
    public List<Navigation> findAll() {
        return navigationRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return NavigationModel.class;
    }

    @Override
    Class aliasExportModel() {
        return NavigationExportModel.class;
    }

    @Override
    public List<NavigationDisplayModel> findNavigationByRoleIdAndEnable(String roleid, Boolean enable) {
        List<Navigation> navs = navigationRepository.getAllByRoleidAndEnableOrderByTaborderAsc(roleid, enable);
        List<NavigationDisplayModel> ndm = new ArrayList<>();
        navs.forEach(n -> {
            NavigationDisplayModel nd = new NavigationDisplayModel(n.getKey(), n.getTitle(), n.getTranslate(),
                    n.getType(), n.getIcon(), n.getUrl());
            ndm.add(nd);
        });
        return ndm;
    }
}
