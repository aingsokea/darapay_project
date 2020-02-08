package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.Configure;
import com.darapay.loanreferral.viewmodels.ConfigureModel;
import com.darapay.loanreferral.viewmodels.export.ConfigureExportModel;
import com.darapay.loanreferral.viewmodels.presentation.UserTypeDisplayModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public interface ConfigureService extends BaseService<Configure, String, ConfigureModel, ConfigureExportModel> {
    List<UserTypeDisplayModel> getUserTypeByTypeAndEnable(String type, Boolean enable);
    List<UserTypeDisplayModel> getUserTypeByTypeAndEnableOrderByItem2(String type, Boolean enable);
    List<UserTypeDisplayModel> getUserTypeByTypeInIdAndEnable(String type, String[] id, Boolean enable);
    List<UserTypeDisplayModel> getUserTypeByTypeInNameAndEnable(String type, String[] name, Boolean enable);

}
