package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.Mfi;
import com.darapay.loanreferral.viewmodels.MfiModel;
import com.darapay.loanreferral.viewmodels.export.MfiExportModel;
import com.darapay.loanreferral.viewmodels.presentation.MfiDisplayModel;
import com.darapay.loanreferral.viewmodels.presentation.MfiDisplayOnloadModel;

import java.util.List;

public interface MfiService extends BaseService<Mfi, String, MfiModel, MfiExportModel> {
    List<MfiDisplayOnloadModel> getAllMfiByEnableOrderByTabOrder(Boolean enable);
    List<MfiDisplayModel> getAllMfiToAssignByEnableOrderByTabOrder(Boolean enable);
    List<MfiDisplayModel> getAllMfiToAssignByEnableAndNameIsNotInOrderByTaborder(Boolean enable, String[] name);
}
