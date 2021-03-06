package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.TranStatus;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface TranStatusRepository extends ExtendedRepository<TranStatus, String> {
    List<TranStatus> findAllByFrmloanidAndEnableOrderByCreateddateDesc(String frmloanid, Boolean enable);
}
