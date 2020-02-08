package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.models.*;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.RoleRepository;
import com.darapay.loanreferral.services.*;
import com.darapay.loanreferral.viewmodels.NavigationModel;
import com.darapay.loanreferral.viewmodels.RoleModel;
import com.darapay.loanreferral.viewmodels.RolePrivilegeModel;
import com.darapay.loanreferral.viewmodels.SettingModel;
import com.darapay.loanreferral.viewmodels.export.RoleExportModel;
import com.darapay.loanreferral.viewmodels.presentation.RoleDisplayDdlModel;
import com.darapay.loanreferral.viewmodels.presentation.RoleDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Repository
public class RoleServiceImpl extends BaseServiceImpl<Role, String, RoleModel, RoleExportModel> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @Autowired
    private LogMastersService logMastersService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private NavigationService navigationService;

    @Override
    protected ExtendedRepository<Role, String> getBaseRepository() {
        return roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return RoleModel.class;
    }

    @Override
    Class aliasExportModel() {
        return RoleExportModel.class;
    }

    @Override
    @Transactional
    public RoleDisplayModel save(RoleModel rm) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Save Role
        Role role = new Role(rm.getName(), rm.getLevel(), rm.getOwner(),rm.getMfiid(), rm.getBranchid(),rm.getResponsible(),
                rm.getEffectivedate(), rm.getExpirationdate(), rm.getDescription(), rm.getProtype());
        role.setCreatedby(auth);
        role.setCreateddate(createddate);
        Role afterRole = save(role);

        // Save Role Log
        String todata = AnonymousFunc.converFromModelToToJson(afterRole);
        LogMasters lm = new LogMasters(afterRole.getId(), "TABLE_ROLE", "", todata, "CREATE_RECORD");
        lm.setCreatedby(AnonymousFunc.GetCurrentPrincipalName().getName());
        lm.setCreateddate(new Date());
        logMastersService.save(lm);

        // Save Role Privilege
        List<RolePrivilegeModel> rpm = rm.getRolePrivilegeModels();
        rpm.forEach(r -> {
            RolePrivilege rp = new RolePrivilege(afterRole.getId(), r.getPrivilegeid());
            rolePrivilegeService.save(rp);
        });

        // Save Role With Setting
        List<SettingModel> sm = rm.getSettingModels();
        sm.forEach(s -> {
            Setting setting = new Setting(afterRole.getId(), s.getName(), s.getValue());
            setting.setCreatedby(auth);
            setting.setCreateddate(createddate);
            settingService.save(setting);
        });

        // Save List Navigation
        List<NavigationModel> nm = rm.getNavigationModels();
        nm.forEach(n -> {
            Navigation nav = new Navigation(afterRole.getId(), n.getKey(), n.getTitle(), n.getTranslate(), n.getType(), n.getIcon(),
                    n.getUrl(), n.getTaborder());
            nav.setCreatedby(auth);
            nav.setCreateddate(createddate);
            navigationService.save(nav);
        });

        RoleDisplayModel rdm = new RoleDisplayModel(afterRole.getId(), afterRole.getName(), afterRole.getLevel(),
                afterRole.getOwner(), afterRole.getMfiid(), afterRole.getResponsible(), afterRole.getEffectivedate(),
                afterRole.getExpirationdate(), afterRole.getDescription());
        return rdm;
    }

    @Override
    public List<RoleDisplayDdlModel> getAllRoleEnable() {
        List<Role> roleList = roleRepository.getAllByEnable(true);
        List<RoleDisplayDdlModel> roleDisplayDdlModelList = new ArrayList<>();
        roleList.forEach(r -> {
            RoleDisplayDdlModel rd = new RoleDisplayDdlModel(r.getId(), r.getName());
            roleDisplayDdlModelList.add(rd);
        });
        return roleDisplayDdlModelList;
    }

    @Override
    public RoleDisplayDdlModel getRoleById(String id) {
        Role r = find(id);
        return new RoleDisplayDdlModel(r.getId(), r.getName());
    }

    @Override
    public List<RoleDisplayDdlModel> getAllByRoleId(String id, boolean enable) {
        List<Role> roleList = roleRepository.findAllByIdAndEnable(id, enable);
        List<RoleDisplayDdlModel> roleDisplayDdlModelList = new ArrayList<>();
        roleList.forEach(r -> {
            RoleDisplayDdlModel rd = new RoleDisplayDdlModel(r.getId(), r.getName());
            roleDisplayDdlModelList.add(rd);
        });
        return roleDisplayDdlModelList;
    }
}
