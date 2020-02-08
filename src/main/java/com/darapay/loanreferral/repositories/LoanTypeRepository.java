package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.LoanType;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface LoanTypeRepository extends ExtendedRepository<LoanType, String> {
    List<LoanType> getAllByMfiidAndEnableOrderByTaborderAsc(String mfiid, Boolean enable);
}
