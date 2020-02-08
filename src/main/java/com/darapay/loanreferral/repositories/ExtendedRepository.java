package com.darapay.loanreferral.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ExtendedRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
//    public List<T> findByAttributeContainsText(String attributeName, String text);
//    public List<T> findAllByAttribute(String attributeName, Object value);
}