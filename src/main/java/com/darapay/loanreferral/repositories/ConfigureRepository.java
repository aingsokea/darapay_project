package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Configure;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface ConfigureRepository extends ExtendedRepository<Configure, String> {
    List<Configure> findAllByTypeAndEnable(String type, Boolean enable);
    List<Configure> findAllByTypeAndEnableOrderByItem2Asc(String type, Boolean enable);
    List<Configure> findAllByTypeAndIdInAndEnableOrderByItem2Asc(String type, String[] id, Boolean enable);
    List<Configure> findAllByTypeAndNameInAndEnableOrderByItem2Asc(String type, String[] name, Boolean enable);
    List<Configure> findAllByEnable(Boolean enable);

}
