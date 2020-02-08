package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.Setting;
import com.darapay.loanreferral.viewmodels.SettingModel;
import com.darapay.loanreferral.viewmodels.export.SettingExportModel;
import com.darapay.loanreferral.viewmodels.presentation.SettingDisplayModel;

import java.util.List;

public interface SettingService extends BaseService<Setting, String, SettingModel, SettingExportModel> {
    List<SettingDisplayModel> getAllSettingsByRoleIdAndEnable(String roleid, Boolean enable);
}
