package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.MainAddress;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface MainAddressRepository extends ExtendedRepository<MainAddress, String> {
    List<MainAddress> findAllByParentcodeAndEnable(String parentcode, Boolean enable);
    MainAddress findByAddresscodeAndEnable(String addresscode, Boolean enable);
}
