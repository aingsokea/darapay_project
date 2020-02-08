package com.darapay.loanreferral.repositories.impl;

import com.darapay.loanreferral.repositories.ExtendedRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {

	 private EntityManager entityManager;

	  // There are two constructors to choose from, either can be used.
	  public ExtendedRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
	    super(domainClass, entityManager);

	    // This is the recommended method for accessing inherited class dependencies.
	    this.entityManager = entityManager;
	  }


    @Transactional
    public List<T> findByAttributeContainsText(String attributeName, String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.like(root.<String> get(attributeName), "%" + text + "%"));
        TypedQuery<T> q = entityManager.createQuery(query);
        return q.getResultList();
    }

    @Transactional
	public List<T> findAllByAttribute(String attributeName, Object value) {
		  CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
	        Root<T> root = query.from(getDomainClass());
	        query.select(root).where(builder.equal(root.<String> get(attributeName), value));
	        TypedQuery<T> q = entityManager.createQuery(query);
	        return q.getResultList();
	}

}