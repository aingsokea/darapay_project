package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Autoloan;
import com.darapay.loanreferral.services.AutoloanService;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.viewmodels.AutoloanChangeStatusModel;
import com.darapay.loanreferral.viewmodels.AutoloanModel;
import com.darapay.loanreferral.viewmodels.core.AutoloanPostModel;
import com.darapay.loanreferral.viewmodels.core.AutoloanPutChangeStatus;
import com.darapay.loanreferral.viewmodels.export.AutoloanExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autoloan")
public class AutoloanController extends EntityApiController<Autoloan, String, AutoloanModel, AutoloanExportModel> {

    @Autowired
    private AutoloanService autoloanService;

    @Override
    protected BaseService<Autoloan, String, AutoloanModel, AutoloanExportModel> getBaseService() {
        return autoloanService;
    }

    @Override
    protected Autoloan NewEntity() {
        return new Autoloan();
    }

    @Override
    protected AutoloanModel NewModel() {
        return new AutoloanModel();
    }

    @RequestMapping(value = "/onload", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTOLOAN_READ')")
    public ResponseEntity<?> getForms() {
        return new ResponseEntity<Object>(autoloanService.getOnLoad(), HttpStatus.OK);
    }

    @RequestMapping(value = "/autoloanid/{autoloanid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTOLOAN_PHOTO_READ')")
    public ResponseEntity<?> getAutoLoanWithPhotos(@PathVariable String autoloanid) {
        return new ResponseEntity<Object>(autoloanService.getAutoloanPostById(autoloanid), HttpStatus.OK);
    }

    @RequestMapping(value = "/changestatus/{autoloanid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTOLOAN_CHANGESTATUS_READ')")
    public ResponseEntity<?> getAutoLoanChangeStatus(@PathVariable String autoloanid) {
        return new ResponseEntity<Object>(autoloanService.getAutoloanChangeStatusById(autoloanid), HttpStatus.OK);
    }

    @RequestMapping(value = "/form-photo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTOLOAN_FORM_PHOTO')")
    public ResponseEntity<?> postAutoLoanWithPhotos(@RequestBody AutoloanPostModel autoloanPostModel) {
        return new ResponseEntity<Object>(autoloanService.postAutoLoanWithPhotos(autoloanPostModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/form-photo/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTOLOAN_FORM_PHOTO_UPDATE')")
    public ResponseEntity<?> putAutoLoanWithPhotos(@PathVariable String id, @RequestBody AutoloanPostModel autoloanPostModel) {
        return new ResponseEntity<Object>(autoloanService.putAutoLoanWithPhotos(id, autoloanPostModel), HttpStatus.OK);
    }
    @RequestMapping(value = "/form-status/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTOLOAN_FORM_STATUS_UPDATE')")
    public ResponseEntity<?> putAutoLoanWithPhotos(@PathVariable String id, @RequestBody AutoloanPutChangeStatus autoloanPutChangeStatus) {
        return new ResponseEntity<Object>(autoloanService.putChangeStatus(id, autoloanPutChangeStatus), HttpStatus.OK);
    }
}
