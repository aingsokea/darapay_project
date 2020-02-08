package com.darapay.loanreferral.services;


import com.darapay.loanreferral.models.MainAddress;
import com.darapay.loanreferral.viewmodels.MainAddressModel;
import com.darapay.loanreferral.viewmodels.export.MainAddressExportModel;
import com.darapay.loanreferral.viewmodels.presentation.MainAddressDisplayDdlModel;

import java.util.List;

public interface MainAddressService extends BaseService<MainAddress, String, MainAddressModel, MainAddressExportModel> {
    List<MainAddressDisplayDdlModel> getMainAddressByParentCode(String parentcode);
    MainAddressDisplayDdlModel getByAddressCode(String addrCode, Boolean enable);
}
