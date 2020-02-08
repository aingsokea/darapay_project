package com.darapay.loanreferral.services;


import com.darapay.loanreferral.models.Role;
import com.darapay.loanreferral.viewmodels.RoleModel;
import com.darapay.loanreferral.viewmodels.export.RoleExportModel;
import com.darapay.loanreferral.viewmodels.presentation.RoleDisplayDdlModel;
import com.darapay.loanreferral.viewmodels.presentation.RoleDisplayModel;

import java.util.List;

public interface RoleService extends BaseService<Role, String, RoleModel, RoleExportModel> {
    RoleDisplayModel save(RoleModel roleModel);
    RoleDisplayDdlModel getRoleById(String id);
    List<RoleDisplayDdlModel> getAllRoleEnable();
    List<RoleDisplayDdlModel> getAllByRoleId(String id, boolean enable);
}
