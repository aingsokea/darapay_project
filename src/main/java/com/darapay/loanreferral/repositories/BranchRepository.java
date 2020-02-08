package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Branch;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface BranchRepository extends ExtendedRepository<Branch, String> {
    List<Branch> getAllByMfiidAndEnableOrderByCodeAsc(String mfiid, Boolean enable);
}
