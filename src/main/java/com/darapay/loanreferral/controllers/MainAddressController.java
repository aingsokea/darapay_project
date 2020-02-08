package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.MainAddress;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.MainAddressService;
import com.darapay.loanreferral.viewmodels.MainAddressModel;
import com.darapay.loanreferral.viewmodels.export.MainAddressExportModel;
import com.darapay.loanreferral.viewmodels.presentation.MainAddressDisplayDdlModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mainaddress")
public class MainAddressController extends EntityApiController<MainAddress, String, MainAddressModel, MainAddressExportModel> {
    @Autowired
    private MainAddressService mainAddressService;

    @Override
    protected BaseService<MainAddress, String, MainAddressModel, MainAddressExportModel> getBaseService() {
        return mainAddressService;
    }

    @Override
    protected MainAddress NewEntity() {
        return new MainAddress();
    }

    @Override
    protected MainAddressModel NewModel() {
        return new MainAddressModel();
    }

    @CrossOrigin
    @RequestMapping(value = "/parentcode/{parentcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(this, 'READ')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> GetMainAddressByParentCode(@PathVariable String parentcode) {
        List<MainAddressDisplayDdlModel> mainAddressDisplayModels = mainAddressService.getMainAddressByParentCode(parentcode);
        return new ResponseEntity<>(mainAddressDisplayModels, HttpStatus.OK);
    }
}
