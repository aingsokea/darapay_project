package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.entities.BaseEntity;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.FilterModel;
import com.darapay.loanreferral.viewmodels.IModelFactory;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseServiceImpl<TEntity extends BaseEntity, TKey extends Serializable, TModel extends IModelFactory<TEntity, TKey>, TExport> implements BaseService<TEntity, TKey, TModel,TExport> {
	protected abstract ExtendedRepository<TEntity, TKey> getBaseRepository();

	@PersistenceContext
	private EntityManager entityManager;

	public TEntity save(TEntity entity) {
		if (entity.getId() == null) {
			entity.setId(UUID.randomUUID().toString());
		}
		return getBaseRepository().save(entity);
	}
	public TEntity find(TKey id) {
		// TODO Auto-generated method stub
		return (getBaseRepository().findById(id)).get();
	}
	public abstract List<TEntity> findAll();

	@Override
	public Optional<TEntity> fineById(TKey id) {
		return getBaseRepository().findById(id);
	}

	@Override
	public List<TModel> getAdvanceFilter(AdvanceModel advanceModel) {
		final String[] from = {""};
		final int[] i = {0};
		// Loop to get Fields
		advanceModel.getFields().forEach(f -> {
			if (i[0] == 0) {
				from[0] += f;
				i[0] += 1;
			} else {
				from[0] += "," + f;
			}
		});

		// Pagination Order Limit Offset
		String paging = " ORDER BY " + advanceModel.getPaginationModel().getOrderBy() + " " + advanceModel.getPaginationModel().getOrderDir() +
				" LIMIT " + advanceModel.getPaginationModel().getLimit() + " OFFSET " + advanceModel.getPaginationModel().getOffset() + ";";

		String sql = "SELECT distinct "+ from[0] +" FROM " + advanceModel.getTable() + " " + getAdvanceFilterByCondition(advanceModel) + " " + paging;
		return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(aliasModel())).list();
	}

	public List<TModel> getSearch(AdvanceModel advanceModel) {
		String custname,custphone,status,mfi,processedmfi,assignedtoperson,requestmfi;
		List<FilterModel> filterModels=advanceModel.getFilterModels();
		FilterModel FModel= filterModels.get(0);
		   custname=FModel.getValue();
		FilterModel LModel= filterModels.get(1);
		   custphone=LModel.getValue();
		FilterModel statusModel= filterModels.get(2);
		   status=statusModel.getValue();
		FilterModel MFIModel= filterModels.get(3);
			mfi=MFIModel.getValue();
		FilterModel processmfiModel= filterModels.get(4);
		   processedmfi=processmfiModel.getValue();
		FilterModel reqmfiModel= filterModels.get(5);
		   requestmfi=reqmfiModel.getValue();
		FilterModel assigntopersonModel= filterModels.get(6);
		   assignedtoperson=assigntopersonModel.getValue();
		// Pagination Order Limit Offset
		String paging = " ORDER BY " + advanceModel.getPaginationModel().getOrderBy() + " " + advanceModel.getPaginationModel().getOrderDir() +
				" LIMIT " + advanceModel.getPaginationModel().getLimit() + " OFFSET " + advanceModel.getPaginationModel().getOffset() + ";";

		String sql = "select frmloan.id AS key, custname, custphone, reqloanamount, CC.name AS currency, reqcurrency, loanperiod," +
				"      frmloan.loanst, C.name AS statusname,acc,(select name from loantype as p where p.id=assignedtoperson) as assigntobusiness," +
				"  (select  name from branch as p where p.id=assignedtoperson) as branchname, processedmfi," +
				"    M.name AS mfiname," +
				"    M.pic as mfipic, frmloan.createddate AS createddate," +
				"    l.name as loantype,approvedamt,frmloan.modifieddate,firstname,lastname,UserName,phone1 as phone," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.village  and type='village') as Village," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.commune  and type='commune') as Commune," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.district  and type='district') as District," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.province  and type='province') as Province," +
				"(select f.pic from mfi as f where f.id=frmloan.reqmfi) as picmfireq,"+
	        	"(select f.pic from mfi as f where f.id=frmloan.processedmfi) as picmfipro"+
				"    FROM frmloan INNER JOIN configure AS C ON C.id = frmloan.loanst" +
				"    INNER JOIN configure AS CC ON CC.id = frmloan.reqcurrency" +
				"    INNER JOIN account AS a On frmloan.createdby=a.username" +
				"    LEFT  JOIN mfi AS M ON (M.id = frmloan.processedmfi Or  (M.id = frmloan.reqmfi and frmloan.processedmfi=''))" +
				"    LEFT JOIN loantype AS l ON l.id = frmloan.loantype " +
				"   WHERE (LOWER(frmloan.custname) LIKE "+custname+" OR LOWER(frmloan.custphone) LIKE "+custphone+") AND (frmloan.enable = true) " +
				"        AND (LOWER(C.name) LIKE "+status+") AND (LOWER(M.id) LIKE "+mfi+") " +
				"        AND (LOWER(frmloan.processedmfi) LIKE "+processedmfi+" OR LOWER(frmloan.reqmfi) LIKE "+requestmfi+" ) " +
				"        AND (LOWER(frmloan.assignedtoperson) LIKE "+assignedtoperson+")" + paging;
		return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(aliasModel())).list();
	}
	@Override
	public Integer countAdvanceFilter(AdvanceModel advanceModel) {
		String sql1 = "SELECT COUNT(*) FROM " + advanceModel.getTable() + " " + getAdvanceFilterByCondition(advanceModel);
		Query query = entityManager.createNativeQuery(sql1);
		return ((BigInteger) query.getSingleResult()).intValue();
	}
	@Override
	public Integer countFilter(AdvanceModel advanceModel) {
		String custname,custphone,status,mfi,processedmfi,assignedtoperson,requestmfi;
		List<FilterModel> filterModels=advanceModel.getFilterModels();
		FilterModel FModel= filterModels.get(0);
		custname=FModel.getValue();
		FilterModel LModel= filterModels.get(1);
		custphone=LModel.getValue();
		FilterModel statusModel= filterModels.get(2);
		status=statusModel.getValue();
		FilterModel MFIModel= filterModels.get(3);
		mfi=MFIModel.getValue();
		FilterModel processmfiModel= filterModels.get(4);
		processedmfi=processmfiModel.getValue();
		FilterModel reqmfiModel= filterModels.get(5);
		requestmfi=reqmfiModel.getValue();
		FilterModel assigntopersonModel= filterModels.get(6);
		assignedtoperson=assigntopersonModel.getValue();
		// Pagination Order Limit Offset
		String paging = " ORDER BY " + advanceModel.getPaginationModel().getOrderBy() + " " + advanceModel.getPaginationModel().getOrderDir() +
				" LIMIT " + advanceModel.getPaginationModel().getLimit() + " OFFSET " + advanceModel.getPaginationModel().getOffset() + ";";

		String sql = "select count(*)" +
				"    FROM frmloan INNER JOIN configure AS C ON C.id = frmloan.loanst" +
				"    INNER JOIN configure AS CC ON CC.id = frmloan.reqcurrency" +
				"    INNER JOIN account AS a On frmloan.createdby=a.username" +
				"    LEFT  JOIN mfi AS M ON (M.id = frmloan.processedmfi Or  (M.id = frmloan.reqmfi and frmloan.processedmfi=''))" +
				"    LEFT JOIN loantype AS l ON l.id = frmloan.loantype " +
				"   WHERE (LOWER(frmloan.custname) LIKE "+custname+" OR LOWER(frmloan.custphone) LIKE "+custphone+") AND (frmloan.enable = true) " +
				"        AND (LOWER(C.name) LIKE "+status+") AND (LOWER(M.id) LIKE "+mfi+") " +
				"        AND (LOWER(frmloan.processedmfi) LIKE "+processedmfi+" OR LOWER(frmloan.reqmfi) LIKE "+requestmfi+") " +
				"        AND (LOWER(frmloan.assignedtoperson) LIKE "+assignedtoperson+")" ;

		Query query = entityManager.createNativeQuery(sql);
		return ((BigInteger) query.getSingleResult()).intValue();
	}
	@Override
	public List<TExport> ExportFile(AdvanceModel advanceModel) {
		String custname,custphone,status,mfi,processedmfi,assignedtoperson,requestmfi;
		List<FilterModel> filterModels=advanceModel.getFilterModels();
		FilterModel FModel= filterModels.get(0);
		custname=FModel.getValue();
		FilterModel LModel= filterModels.get(1);
		custphone=LModel.getValue();
		FilterModel statusModel= filterModels.get(2);
		status=statusModel.getValue();
		FilterModel MFIModel= filterModels.get(3);
		mfi=MFIModel.getValue();
		FilterModel processmfiModel= filterModels.get(4);
		processedmfi=processmfiModel.getValue();
		FilterModel reqmfiModel= filterModels.get(5);
		requestmfi=reqmfiModel.getValue();
		FilterModel assigntopersonModel= filterModels.get(6);
		assignedtoperson=assigntopersonModel.getValue();
		// Pagination Order Limit Offset


		String sql = "select frmloan.id AS key, custname, custphone, reqloanamount, CC.name AS currency, reqcurrency, loanperiod," +
				"      frmloan.loanst, C.name AS statusname,acc,(select name from loantype as p where p.id=assignedtoperson) as assigntobusiness," +
				"  (select  name from branch as p where p.id=assignedtoperson) as branchname, processedmfi," +
				"    M.name AS mfiname," +
				"    M.pic as mfipic, frmloan.createddate AS createddate," +
				"    l.name as loantype,frmloan.appram,frmloan.modifieddate,firstname,lastname,UserName,phone1 as phone," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.village  and type='village') as Village," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.commune  and type='commune') as Commune," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.district  and type='district') as District," +
				"    (select name from mainaddress as d where d.addresscode=frmloan.province  and type='province') as Province" +
				"    FROM frmloan INNER JOIN configure AS C ON C.id = frmloan.loanst" +
				"    INNER JOIN configure AS CC ON CC.id = frmloan.reqcurrency" +
				"    INNER JOIN account AS a On frmloan.createdby=a.username" +
				"    LEFT  JOIN mfi AS M ON (M.id = frmloan.processedmfi Or  (M.id = frmloan.reqmfi and frmloan.processedmfi=''))" +
				"    LEFT JOIN loantype AS l ON l.id = frmloan.loantype " +
				"   WHERE (LOWER(frmloan.custname) LIKE "+custname+" OR LOWER(frmloan.custphone) LIKE "+custphone+") AND (frmloan.enable = true) " +
				"        AND (LOWER(C.name) LIKE "+status+") AND (LOWER(M.id) LIKE "+mfi+") " +
				"        AND (LOWER(frmloan.processedmfi) LIKE "+processedmfi+" OR LOWER(frmloan.reqmfi) LIKE "+requestmfi+") " +
				"        AND (LOWER(frmloan.assignedtoperson) LIKE "+assignedtoperson+")" ;
		return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(aliasExportModel())).list();
	}
	@Override
	public List<TExport> getExport(AdvanceModel advanceModel) {
		final String[] from = {""};
		final int[] i = {0};
		// Loop to get Fields
		advanceModel.getFields().forEach(f -> {
			if (i[0] == 0) {
				from[0] += f;
				i[0] += 1;
			} else {
				from[0] += "," + f;
			}
		});

		String sql = "SELECT "+ from[0] +" FROM " + advanceModel.getTable() + " " + getAdvanceFilterByCondition(advanceModel) + ";";
		return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(aliasExportModel())).list();
	}
	@Override
	public List<TExport> getExportExcel(AdvanceModel advanceModel) {
		final String[] from = {""};
		final int[] i = {0};
		// Loop to get Fields
		advanceModel.getFields().forEach(f -> {
			if (i[0] == 0) {
				from[0] += f;
				i[0] += 1;
			} else {
				from[0] += "," + f;
			}
		});

		String sql = "SELECT "+ from[0] +" FROM " + advanceModel.getTable() + " " + getAdvanceFilterByCondition(advanceModel) + ";";
		return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(aliasExportModel())).list();
	}
	@Override
	public List<TExport> getExportFunan(AdvanceModel advanceModel) {
		final String[] from = {""};
		final int[] i = {0};
		// Loop to get Fields
		advanceModel.getFields().forEach(f -> {
			if (i[0] == 0) {
				from[0] += f;
				i[0] += 1;
			} else {
				from[0] += "," + f;
			}
		});

		String sql = "SELECT "+ from[0] +" FROM " + advanceModel.getTable() + " " + getAdvanceFilterByCondition(advanceModel) + ";";
		return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
				.setResultTransformer(Transformers.aliasToBean(aliasExportModel())).list();
	}
	protected String getAdvanceFilterByCondition(AdvanceModel advanceModel) {

		String condition = getFilterByCondition(advanceModel.getFilterModels());
		condition += ")";

		// Join Table
		final String[] join = {""};
		advanceModel.getJoinTables().forEach( k -> join[0] += " " + k + " ");


		return join[0] + " " + condition;
	}

	protected String getFilterByCondition(List<FilterModel> filterModels) {
		// Loop to get condition
		final String[] condition = {""};
		final int[] j = {0};
		// Sort List Filter
		filterModels.sort(Comparator.comparing(FilterModel::getGroup));

		final String[] currentState = {""};
		filterModels.forEach( m -> {
			if (currentState[0].equals("")) {
				currentState[0] = m.getGroup();
			}
			// First Check the Group if it different group it mean it will change to next
			// Then we will use Operation 'OR' or 'AND'
			if (currentState[0].equals(m.getGroup())) {
				currentState[0] = m.getGroup();
				if (m.getOperation().split(",").length > 1) {
					// Use this for Operation AND, BETWEEn
					if (j[0] == 0) {
						condition[0] += "WHERE (" + m.getName() + " " + m.getOperation().split(",")[0] + " " + m.getFrom() + " " + m.getOperation().split(",")[1] + " " + m.getTo();
						j[0] += 1;
					} else  {
						condition[0] += "OR " + m.getName() + " " + m.getOperation().split(",")[0] + " " + m.getFrom() + " " + m.getOperation().split(",")[1] + " " + m.getTo();
					}
				} else {
					// Use here for Operation '=' and 'IN'
					if (j[0] == 0) {
						// Check it Operation =
						if (m.getOperation().equals("=") || m.getOperation().equals("LIKE")) {
							condition[0] += "WHERE (" + m.getName() + " " + m.getOperation() + " " + m.getValue();
						}
						// Check if Operation AND
						if (m.getOperation().equals("IN")) {
							String tmpval = m.getValue();
							condition[0] += "WHERE (" + m.getName() + " " + m.getOperation() + " (" + tmpval;
						}
						j[0] += 1;
					} else  {
						// Check if Iperation =
						if (m.getOperation().equals("=") || m.getOperation().equals("LIKE")) {
							condition[0] += " OR " + m.getName() + " " + m.getOperation() + " " + m.getValue();
						}
						// Check if Operation AND
						if (m.getOperation().equals("IN")) {
							String tmpval = m.getValue();
//							String[] tmparr = tmpval.split(",");
//							String finalval = "(";
//							for (String num : tmparr) {
//								finalval += num;
//							}
//							finalval += ")";
							condition[0] += " OR " + m.getName() + " " + m.getOperation() + " " + tmpval;
						}
					}
				}
			} else {
				currentState[0] = m.getGroup();
				if (m.getOperation().split(",").length > 1) {
					if (j[0] == 0) {
						condition[0] += "WHERE (" + m.getName() + " " + m.getOperation().split(",")[0] + " " + m.getFrom() + " " + m.getOperation().split(",")[1] + " " + m.getTo();
						j[0] += 1;
					} else  {
						condition[0] += ") AND (" + m.getName() + " " + m.getOperation().split(",")[0] + " " + m.getFrom() + " " + m.getOperation().split(",")[1] + " " + m.getTo();
					}
				} else {
					if (j[0] == 0) {
						// Check if Operation =
						if (m.getOperation().equals("=") || m.getOperation().equals("LIKE")) {
							condition[0] += "WHERE (" + m.getName() + " " + m.getOperation() + " " + m.getValue();
						}
						// Check if Operation AND
						if (m.getOperation().equals("IN")) {
							String tmpval = m.getValue();
//							String[] tmparr = tmpval.split(",");
//							String finalval = "(";
//							for (String num : tmparr) {
//								finalval += num;
//							}
//							finalval += ")";
							condition[0] += "WHERE (" + m.getName() + " " + m.getOperation() + " " + tmpval;
						}

						j[0] += 1;
					} else  {
						// Check if Operation =
						if (m.getOperation().equals("=") || m.getOperation().equals("LIKE")) {
							condition[0] += ") AND (" + m.getName() + " " + m.getOperation() + " " + m.getValue();
						}
						// Check if Operation AND
						if (m.getOperation().equals("IN")) {
							String tmpval = m.getValue();
//							String[] tmparr = tmpval.split(",");
//							String finalval = "(";
//							for (String num : tmparr) {
//								finalval += num;
//							}
//							finalval += ")";
							condition[0] += ") AND (" + m.getName() + " " + m.getOperation() + " " + tmpval;
						}
					}
				}
			}
		});
		return condition[0];
	}

	abstract Class aliasModel();
	abstract Class aliasExportModel();
}
