package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.MainAddress;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.MainAddressRepository;
import com.darapay.loanreferral.services.MainAddressService;
import com.darapay.loanreferral.viewmodels.MainAddressModel;
import com.darapay.loanreferral.viewmodels.export.MainAddressExportModel;
import com.darapay.loanreferral.viewmodels.presentation.MainAddressDisplayDdlModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class MainAddressServiceImpl extends BaseServiceImpl<MainAddress, String, MainAddressModel, MainAddressExportModel> implements MainAddressService {
    @Autowired
    private MainAddressRepository mainAddressRepository;

    @Override
    protected ExtendedRepository<MainAddress, String> getBaseRepository() {
        return mainAddressRepository;
    }

    @Override
    public List<MainAddress> findAll() {
        return mainAddressRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return MainAddressModel.class;
    }

    @Override
    Class aliasExportModel() {
        return MainAddressExportModel.class;
    }

    @Override
    public List<MainAddressDisplayDdlModel> getMainAddressByParentCode(String parentcode) {
        List<MainAddress> mainAddresses = mainAddressRepository.findAllByParentcodeAndEnable(parentcode, true);
        List<MainAddressDisplayDdlModel> mainAddressDisplayModelArrayList = new ArrayList<>();
        mainAddresses.forEach(p -> {
            MainAddressDisplayDdlModel m = new MainAddressDisplayDdlModel(p.getId(), p.getName(), p.getNamekh(), p.getAddresscode(), p.getParentcode());
            mainAddressDisplayModelArrayList.add(m);
        });
        return mainAddressDisplayModelArrayList;
    }

    @Override
    public MainAddressDisplayDdlModel getByAddressCode(String addrCode, Boolean enable) {
        MainAddress md = mainAddressRepository.findByAddresscodeAndEnable(addrCode, enable);
        MainAddressDisplayDdlModel madm = new MainAddressDisplayDdlModel(md.getId(), md.getName(), md.getNamekh(),
                md.getAddresscode(), md.getParentcode());
        return madm;
    }
}
