package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Mfi;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface MfiRepository extends ExtendedRepository<Mfi, String> {
    List<Mfi> getAllByEnableOrderByTaborderAsc(Boolean enable);
    List<Mfi> getAllByEnableAndNameIsNotInOrderByTaborderAsc(Boolean enable, String[] name);
}
