package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.Setting;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.SettingRepository;
import com.darapay.loanreferral.services.SettingService;
import com.darapay.loanreferral.viewmodels.SettingModel;
import com.darapay.loanreferral.viewmodels.export.SettingExportModel;
import com.darapay.loanreferral.viewmodels.presentation.SettingDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class SettingServiceImpl extends BaseServiceImpl<Setting, String, SettingModel, SettingExportModel> implements SettingService {

    @Autowired
    private SettingRepository settingRepository;

    @Override
    protected ExtendedRepository<Setting, String> getBaseRepository() {
        return settingRepository;
    }

    @Override
    public List<Setting> findAll() {
        return settingRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return SettingModel.class;
    }

    @Override
    Class aliasExportModel() {
        return SettingExportModel.class;
    }

    @Override
    public List<SettingDisplayModel> getAllSettingsByRoleIdAndEnable(String roleid, Boolean enable) {
        List<Setting> settings = settingRepository.findAllByRoleidAndEnable(roleid, enable);
        List<SettingDisplayModel> sdm = new ArrayList<>();
        settings.forEach(s -> {
            SettingDisplayModel sd = new SettingDisplayModel(s.getId(), s.getName(), s.getValue());
            sdm.add(sd);
        });
        return sdm;
    }
}
