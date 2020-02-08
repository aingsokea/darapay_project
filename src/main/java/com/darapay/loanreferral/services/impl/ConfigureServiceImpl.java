package com.darapay.loanreferral.services.impl;


import com.darapay.loanreferral.models.Configure;
import com.darapay.loanreferral.repositories.ConfigureRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.ConfigureService;
import com.darapay.loanreferral.viewmodels.ConfigureModel;
import com.darapay.loanreferral.viewmodels.export.ConfigureExportModel;
import com.darapay.loanreferral.viewmodels.presentation.UserTypeDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class ConfigureServiceImpl extends BaseServiceImpl<Configure, String, ConfigureModel, ConfigureExportModel> implements ConfigureService {
    @Autowired
    private ConfigureRepository configureRepository;

    @Override
    protected ExtendedRepository<Configure, String> getBaseRepository() {
        return configureRepository;
    }

    @Override
    public List<Configure> findAll() {
        return configureRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return ConfigureModel.class;
    }

    @Override
    Class aliasExportModel() {
        return ConfigureExportModel.class;
    }

    @Override
    public List<UserTypeDisplayModel> getUserTypeByTypeAndEnable(String type, Boolean enable) {
        List<Configure> c = configureRepository.findAllByTypeAndEnable(type, enable);
        return getListConfigure(c);
    }

    @Override
    public List<UserTypeDisplayModel> getUserTypeByTypeInIdAndEnable(String type, String[] id, Boolean enable) {
        List<Configure> c = configureRepository.findAllByTypeAndIdInAndEnableOrderByItem2Asc(type, id, enable);
        return getListConfigure(c);
    }

    @Override
    public List<UserTypeDisplayModel> getUserTypeByTypeInNameAndEnable(String type, String[] name, Boolean enable) {
        List<Configure> c = configureRepository.findAllByTypeAndNameInAndEnableOrderByItem2Asc(type, name, enable);
        return getListConfigure(c);
    }

    private List<UserTypeDisplayModel> getListConfigure(List<Configure> c) {
        List<UserTypeDisplayModel> utdm = new ArrayList<>();
        c.forEach(p -> {
            UserTypeDisplayModel u = new UserTypeDisplayModel(p.getId(), p.getName(), p.getItem1(), p.getItem2());
            utdm.add(u);
        });
        return utdm;
    }

    @Override
    public List<UserTypeDisplayModel> getUserTypeByTypeAndEnableOrderByItem2(String type, Boolean enable) {
        List<Configure> c = configureRepository.findAllByTypeAndEnableOrderByItem2Asc(type, enable);
        return getListConfigure(c);
    }


}
