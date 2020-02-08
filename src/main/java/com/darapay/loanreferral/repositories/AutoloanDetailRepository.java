package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.AutoloanDetail;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface AutoloanDetailRepository extends ExtendedRepository<AutoloanDetail, String>{
    List<AutoloanDetail> findAllByAutoloanidAndEnable(String autoloanid, Boolean enable);
}
