package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.entities.BaseEntity;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import com.darapay.loanreferral.models.Account;
import com.darapay.loanreferral.models.LogMasters;
import com.darapay.loanreferral.repositories.AccountRepository;
import com.darapay.loanreferral.security.Exceptioins.RecoredNotFoundException;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.EmailService;
import com.darapay.loanreferral.services.LogMastersService;
import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.IModelFactory;
import com.darapay.loanreferral.viewmodels.presentation.UserTypeDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;

@RestController
public abstract class EntityApiController<TEntity extends BaseEntity<TKey>, TKey  extends Serializable, TModel extends IModelFactory<TEntity, TKey>, TExport> {

    protected abstract BaseService<TEntity, TKey, TModel, TExport> getBaseService();

    @Autowired
    private LogMastersService logMastersService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(" hasPermission(this, 'READ')")
    @ResponseBody
    public ResponseEntity<?> get() {
        Object resource =  getResources();
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(this, 'READ')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable TKey id) {
        Object resource =  getResource(id);
        return new ResponseEntity<>(resource, HttpStatus.OK);

    }

    @RequestMapping(value = "/advancefilter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasPermission(this, 'ADVANCE_FILTER')")
    public ResponseEntity<?> PostAdvanceFilter(@Valid @RequestBody AdvanceModel advanceModel) {
        String table = this.getClass().getSimpleName().toLowerCase().split("controller")[0];
        advanceModel.setTable(table);
        List<TModel> models = getBaseService().getAdvanceFilter(advanceModel);
        Integer count = getBaseService().countAdvanceFilter(advanceModel);
        Map<String, Object> map = new HashMap<>();
        map.put("models", models);
        map.put("count", count);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    protected TModel getResource(TKey id)
    {
        TModel model = NewModel();
        Optional<TEntity> entity = getBaseService().fineById(id);
        if (!entity.isPresent()) {
            throw new RecoredNotFoundException("This record id = " + id + " was not found");
        }
        model.Parse(entity.get());
        return model;
    }

    protected Object getResources()
    {
        return getBaseService().findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasPermission(this, 'CREATE')")
    public ResponseEntity<?> Post(@Valid @RequestBody TModel model) {

        if (!IsValid(model))
        {
            return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
        }

        TEntity entity = New(model);
        String arr = entity.getClass().getSimpleName();
        String tableName = "TABLE_"+arr.substring(arr.lastIndexOf('.') + 1).toUpperCase();
        entity =  getBaseService().save(entity);
        String relatedId = entity.getId().toString();
        String todata = AnonymousFunc.converFromModelToToJson(entity);
        afterSave(relatedId, tableName,"", todata,"CREATE_RECORD");
        TModel resource =  getResource(entity.getId());


        return new ResponseEntity<Object>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/exportadvancefilter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
   // @PreAuthorize("hasPermission(this, 'ADVANCE_EXPORT')")
    public ResponseEntity<?> PostExportModel(@Valid @RequestBody AdvanceModel advanceModel) {
        String table = this.getClass().getSimpleName().toLowerCase().split("controller")[0];
        advanceModel.setTable(table);
        List<TExport> models = getBaseService().getExport(advanceModel);
        Map<String, Object> map = new HashMap<>();
        map.put("models", models);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @RequestMapping(value = "/exportadvanceExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    // @PreAuthorize("hasPermission(this, 'ADVANCE_EXPORT')")
    public ResponseEntity<?> PostExportModels(@Valid @RequestBody AdvanceModel advanceModel) {
        String table = this.getClass().getSimpleName().toLowerCase().split("controller")[0];
        advanceModel.setTable(table);
        List<TExport> models = getBaseService().getExportExcel(advanceModel);
        Map<String, Object> map = new HashMap<>();
        map.put("models", models);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    //export Funan and CP
    @RequestMapping(value = "/exportfunan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasPermission(this, 'ADVANCE_EXPORT')")
    public ResponseEntity<?> PostExportModelFunan(@Valid @RequestBody AdvanceModel advanceModel) {
        String table = this.getClass().getSimpleName().toLowerCase().split("controller")[0];
        advanceModel.setTable(table);
        List<TExport> models = getBaseService().getExportFunan(advanceModel);
        Map<String, Object> map = new HashMap<>();
        map.put("models", models);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasPermission(this, 'UPDATE')")
    public ResponseEntity<?> Put(@PathVariable TKey id, @Valid @RequestBody TModel model) {

        if (!IsValid(model))
        {
            return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
        }

        TEntity entity = getBaseService().find(id);
        if (entity == null)
        {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        if (!IsEditable(entity))
        {
            return new ResponseEntity<>("Cannot Edit", HttpStatus.FORBIDDEN);
        }

        BeforeUpdate(entity, model);
        model.Update(entity);
        Update(entity, model);

        getBaseService().save(entity);

        TModel resource =  getResource(entity.getId());
        return new ResponseEntity<Object>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasPermission(this, 'DELETE')")
    public ResponseEntity<?> Delete(@PathVariable TKey id) {
        TEntity entity = getBaseService().find(id);
        if (entity == null)
        {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        if (!IsDeletable(entity))
        {
            return new ResponseEntity<>("Cannot Edit", HttpStatus.FORBIDDEN);
        }
        Remove(entity);

        getBaseService().save(entity);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    protected TEntity New(TModel model)
    {
        TEntity entity = NewEntity();
        if (TransactionEntityImpl.class.isAssignableFrom(entity.getClass()))
        {
            ((TransactionEntityImpl)entity).setCreateddate(new Date());
            ((TransactionEntityImpl)entity).setCreatedby(GetCurrentPrincipalName());
        }

        model.Update(entity);
        return entity;
    }

    protected abstract TEntity NewEntity();
    protected abstract TModel NewModel();

    protected void BeforeUpdate(TEntity entity, TModel model)
    {
        String arr = entity.getClass().getSimpleName();
        String tableName = "TABLE_"+arr.substring(arr.lastIndexOf('.') + 1).toUpperCase();
        String relatedId = entity.getId().toString();
        String fromdate = AnonymousFunc.converFromModelToToJson(entity);
        String todata = AnonymousFunc.converFromModelToToJson(model);
        afterSave(relatedId, tableName,fromdate ,todata,"MODIFIED_RECORD");
    }

    protected void Update(TEntity entity, TModel model)
    {
        if (TransactionEntityImpl.class.isAssignableFrom(entity.getClass()))
        {
            ((TransactionEntityImpl)entity).setModifieddate(new Date());
            ((TransactionEntityImpl)entity).setModifiedby(GetCurrentPrincipalName());
        }
    }

    protected Boolean IsValid(TModel model)
    {
        return true;
    }

    protected Boolean IsEditable(TEntity entity)
    {
        return true;
    }

    protected Boolean IsDeletable(TEntity entity)
    {
        if (!IsEditable(entity))
            return false;

        return true;
    }

    protected void Remove(TEntity entity){
    }

    protected String GetCurrentPrincipalName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public void afterSave(String relatedId, String tableName, String fromdata, String todata, String type) {
        LogMasters lm = new LogMasters(relatedId,tableName, fromdata, todata,type);
        lm.setCreateddate(new Date());
        lm.setCreatedby(AnonymousFunc.GetCurrentPrincipalName().getName());
        logMastersService.save(lm);
    }
}
