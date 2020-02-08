package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.models.*;
import com.darapay.loanreferral.repositories.AutoloanRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.*;
import com.darapay.loanreferral.viewmodels.*;
import com.darapay.loanreferral.viewmodels.core.AutoloanPostModel;
import com.darapay.loanreferral.viewmodels.core.AutoloanPutChangeStatus;
import com.darapay.loanreferral.viewmodels.export.AutoloanExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Service
public class AutoloanServiceImpl extends BaseServiceImpl<Autoloan, String, AutoloanModel, AutoloanExportModel> implements AutoloanService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AutoloanRepository autoloanRepository;

    @Autowired
    private AutoloanDetailService autoloanDetailService;

    @Autowired
    private ConfigureService configureService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MainAddressService mainAddressService;

    @Autowired
    private TranStatusService tranStatusService;

    @Override
    protected ExtendedRepository<Autoloan, String> getBaseRepository() {
        return autoloanRepository;
    }

    @Override
    public List<Autoloan> findAll() {
        return autoloanRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return AutoloanModel.class;
    }

    @Override
    Class aliasExportModel() {
        return AutoloanExportModel.class;
    }

    @Override
    public AutoLoanOnloanDisplayModel getOnLoad() {
        List<UserTypeDisplayModel> userTypeDisplayModels = configureService.getUserTypeByTypeAndEnableOrderByItem2("CURRENCYTYPE", true);
        List<CurrencyDisplayModel> currencyDisplayModelList = new ArrayList<>();
        userTypeDisplayModels.forEach(u -> {
            CurrencyDisplayModel cdm = new CurrencyDisplayModel(u.getUserkey(), u.getName(), u.getNamekh());
            currencyDisplayModelList.add(cdm);
        });

        List<MainAddressDisplayDdlModel> provinces = mainAddressService.getMainAddressByParentCode("855");
        return new AutoLoanOnloanDisplayModel(currencyDisplayModelList, provinces);
    }

    @Transactional
    @Override
    public Boolean postAutoLoanWithPhotos(AutoloanPostModel autoloanPostModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        Account account = accountService.findAccountByUsernameAndEnable(auth, true);

        // Save Auto Loan
        AutoloanModel alm = new AutoloanModel(autoloanPostModel.getAutoloanModel());
        Autoloan al = new Autoloan(alm.getCustomername(), alm.getPhone1(), alm.getPhone2(), alm.getLoanamount(), alm.getLoanamountcurrency(),
                alm.getPrice(), alm.getPricecurrency(), alm.getYearmade(), alm.getAvg_income(), alm.getAvgincomecurrency(), alm.getProvince(),
                alm.getDistrict(), alm.getCommune(), alm.getVillage(), alm.getStatus());
        al.setCreatedby(auth);
        al.setCreateddate(createddate);
        Autoloan nal = save(al);

        // Save Auto Loan Detail (Photo)
        List<AutoloanDetailModel> autoloanDetailModelList = autoloanPostModel.getAutoloanDetailModelsList();
        autoloanDetailModelList.forEach(ad -> {
            AutoloanDetail ald = new AutoloanDetail(nal.getId(), ad.getImage_url(), ad.getImage_type());
            ald.setCreatedby(auth);
            ald.setCreateddate(createddate);
            autoloanDetailService.save(ald);
        });

        // Save Log Master

        // Sending Mail
        String[] to = {"huot.dina@canadiabank.com.kh", "vefloan@canadiabank.com.kh","vanthirin.yos@canadiabank.com.kh", "corporate@darapay.com.kh","operations@darapay.com.kh"};
        // String[] to    ={"sokea.aing@darapay.com.kh"};
        Configure ammountCurrency = configureService.find(al.getLoanamountcurrency());
        String fullName = account.getFirstname() + " " +  account.getLastname();
        String shopName = "";
        if(account.getPosition().toLowerCase() == "n/a" || account.getPosition() == ""){
            shopName = account.getPositionkh();
        } else {
            shopName = account.getPosition();
        }
        String htmltext = "<p>Dear Respected Team,</p>" +
                "<p>Customer has requested Auto Loan for Customer Name <b>" + al.getCustomername() + " </b> with Amount <b> " + al.getLoanamount() + " " + ammountCurrency.getName() + "</b></p>"+
                "<ul>"+
                "<li>Dealer Name: <b>"+fullName+"</b></li>"+
                "<li>Shop  name: <b>"+shopName+"</b></li>"+
                "<li>Contact person: <b>"+account.getContactperson()+"</b></li>"+
                "<li>Address: <b>"+account.getShopaddress()+"</b></li>"+
                "</ul>"+
                "<p>For more detail please kindly check this request in DaraPay Portal: https://services.darapay.com.kh/lnrfrfront and proceed accordingly.</p>" +
                "<p>Best Regards,</p>" +
                "<p>DaraPay Team</p>" +
                "</br><p style='color: red;'><i>Note: This is Automation from DaraPay Portal</i></p>";
        emailService.sendMessageWithHtmlTemplate("darapayportal@gmail.com",to, "Request Auto Loan Via DaraPay Portal", "it@darapay.com.kh" ,htmltext);
        return true;
    }

    @Override
    public AutoloanPostModel getAutoloanPostById(String id) {
        Autoloan al = autoloanRepository.getOne(id);
        AutoloanModel alm = new AutoloanModel(al.getId(), al.getCustomername(), al.getPhone1(), al.getPhone2(), al.getLoanamount(),
                al.getLoanamountcurrency(), al.getPrice(), al.getPricecurrency(), al.getYearmade(), al.getAvg_income(),
                al.getAvgincomecurrency(), al.getProvince(), al.getDistrict(), al.getCommune(), al.getVillage(), al.getStatus(),
                al.getCreatedby(), al.getCreateddate());

        List<AutoloanDetailModel> aldm = autoloanDetailService.findAllByAutoloanId(al.getId(), true);
        List<MainAddressDisplayDdlModel> provinces = mainAddressService.getMainAddressByParentCode("855");
        List<MainAddressDisplayDdlModel> districts = mainAddressService.getMainAddressByParentCode(al.getProvince());
        List<MainAddressDisplayDdlModel> communes = mainAddressService.getMainAddressByParentCode(al.getDistrict());
        List<MainAddressDisplayDdlModel> villages = mainAddressService.getMainAddressByParentCode(al.getCommune());
        boolean statusAllowUpdate;
        // Get List of Transaction History
        List<TranStatusDisplayModel> tsdm = tranStatusService.getAllByFrmLoanIdOrderByCreatedDate(id, true);
        if(tsdm.size()==0) {statusAllowUpdate=false;}else{statusAllowUpdate=true;}
        return new AutoloanPostModel(alm, aldm, provinces, districts, communes, villages,statusAllowUpdate);
    }

    @Override
    public AutoloanChangeStatusModel getAutoloanChangeStatusById(String id) {
        Autoloan al = autoloanRepository.getOne(id);
        AutoloanModel alm = new AutoloanModel(al.getId(), al.getCustomername(), al.getPhone1(), al.getPhone2(), al.getLoanamount(),
                al.getLoanamountcurrency(), al.getPrice(), al.getPricecurrency(), al.getYearmade(), al.getAvg_income(),
                al.getAvgincomecurrency(), al.getProvince(), al.getDistrict(), al.getCommune(), al.getVillage(), al.getStatus(),
                al.getCreatedby(), al.getCreateddate());

        List<AutoloanDetailModel> aldm = autoloanDetailService.findAllByAutoloanId(al.getId(), true);

        Account acc = accountService.findAccountByUsernameAndEnable(al.getCreatedby(), true);
        MainAddressDisplayDdlModel village;
        if (acc.getVillage() == null || acc.getVillage().equals("")) {
            village = new MainAddressDisplayDdlModel("", "N/A", "N/A", "", "");
        } else {
            village = mainAddressService.getByAddressCode(acc.getVillage(), true);
        }
        MainAddressDisplayDdlModel commune;
        if (acc.getCommune() == null || acc.getCommune().equals("")) {
            commune = new MainAddressDisplayDdlModel("", "N/A", "N/A", "", "");
        } else {
            commune = mainAddressService.getByAddressCode(acc.getCommune(), true);
        }
        MainAddressDisplayDdlModel district;
        if (acc.getDistrict() == null || acc.getDistrict().equals("")) {
            district = new MainAddressDisplayDdlModel("", "N/A", "N/A", "", "");
        } else {
            district = mainAddressService.getByAddressCode(acc.getDistrict(), true);
        }
        MainAddressDisplayDdlModel province;
        if (acc.getProvince() == null || acc.getProvince().equals("")) {
            province = new MainAddressDisplayDdlModel("", "N/A", "N/A", "", "");
        } else {
            province = mainAddressService.getByAddressCode(acc.getProvince(), true);
        }
        AccountAutoloanChangeStatusDisplayModel aacsdm = new AccountAutoloanChangeStatusDisplayModel(
                acc.getFirstname() + " " + acc.getLastname(), acc.getPhone1(), acc.getPhone2(), village.getNamekh(),
                commune.getNamekh(), district.getNamekh(), province.getNamekh());
        String[] statusids = {"Sta01","b74ac23e-2b4a-44db-b84f-00ec343b452c", "217a09c1-01df-4e63-9cc1-d5a7006fd692", "bc7031b2-2987-4ba0-9c19-ceec6c81d9c8", "2715d4df-5620-461a-b572-2b7c9f4d836b"};
        List<UserTypeDisplayModel> status = configureService.getUserTypeByTypeInIdAndEnable("STATUSTYPE", statusids, true);
        List<AutoloanStatusDisplayModel> autoloanStatusDisplayModelList = new ArrayList<>();
        status.forEach(s -> {
            AutoloanStatusDisplayModel autoloanStatusDisplayModel = new AutoloanStatusDisplayModel(s.getUserkey(), s.getName(), s.getNamekh());
            autoloanStatusDisplayModelList.add(autoloanStatusDisplayModel);
        });

        List<UserTypeDisplayModel> currency = configureService.getUserTypeByTypeAndEnableOrderByItem2("CURRENCYTYPE", true);
        List<CurrencyDisplayModel> currencyDisplayModelList = new ArrayList<>();
        currency.forEach(c -> {
            CurrencyDisplayModel currencyDisplayModel = new CurrencyDisplayModel(c.getUserkey(), c.getName(), c.getNamekh());
            currencyDisplayModelList.add(currencyDisplayModel);
        });

        List<MainAddressDisplayDdlModel> provinces = mainAddressService.getMainAddressByParentCode("855");
        List<MainAddressDisplayDdlModel> districts = mainAddressService.getMainAddressByParentCode(al.getProvince());
        List<MainAddressDisplayDdlModel> communes = mainAddressService.getMainAddressByParentCode(al.getDistrict());
        List<MainAddressDisplayDdlModel> villages = mainAddressService.getMainAddressByParentCode(al.getCommune());

        // Get List of Transaction History
        List<TranStatusDisplayModel> tsdm = tranStatusService.getAllByFrmLoanIdOrderByCreatedDate(id, true);
        return new AutoloanChangeStatusModel(alm, aldm, provinces, districts, communes, villages, aacsdm,autoloanStatusDisplayModelList , currencyDisplayModelList,tsdm);
    }

    @Transactional
    @Override
    public Boolean putAutoLoanWithPhotos(String id, AutoloanPostModel autoloanPostModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date modifieddate = new Date();

        // Save Auto Loan
        Autoloan almserver = find(id);
        AutoloanModel alm = new AutoloanModel(autoloanPostModel.getAutoloanModel());
        Autoloan al = new Autoloan(alm.getCustomername(), alm.getPhone1(), alm.getPhone2(), alm.getLoanamount(), alm.getLoanamountcurrency(),
                alm.getPrice(), alm.getPricecurrency(), alm.getYearmade(), alm.getAvg_income(), alm.getAvgincomecurrency(), alm.getProvince(),
                alm.getDistrict(), alm.getCommune(), alm.getVillage(), alm.getStatus());
        al.setId(id);
        al.setCreateddate(almserver.getCreateddate());
        al.setCreatedby(almserver.getCreatedby());
        al.setModifiedby(auth);
        al.setModifieddate(modifieddate);
        Autoloan nal = save(al);

        // Save Auto Loan Detail (Photo)
        List<AutoloanDetailModel> autoloanDetailModelList = autoloanPostModel.getAutoloanDetailModelsList();
        autoloanDetailModelList.forEach(ad -> {
            if (ad.getId().equals("")) {
                AutoloanDetail ald = new AutoloanDetail(nal.getId(), ad.getImage_url(), ad.getImage_type());
                ald.setCreatedby(auth);
                ald.setCreateddate(modifieddate);
                autoloanDetailService.save(ald);
            } else {
                AutoloanDetail ald = new AutoloanDetail(ad.getId(), nal.getId(), ad.getImage_url(), ad.getImage_type());
                ald.setEnable(ad.isEnable());
                ald.setCreatedby(ad.getCreatedby());
                ald.setCreateddate(ad.getCreateddate());
                ald.setModifiedby(auth);
                ald.setModifieddate(modifieddate);
                autoloanDetailService.save(ald);
            }
        });

        // Save LOG
        return true;
    }

    @Override
    public List<AutoloanModel> getAdvanceFilter(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        String sql = "SELECT ACC.username FROM accountrole AS ACCR " +
                " INNER JOIN account AS ACC ON ACC.id = ACCR.accountid " +
                " WHERE reportto LIKE '%' || (SELECT id FROM account WHERE username = '" + auth + "') || '%'";
        List<String> listauth = entityManager.createNativeQuery(sql).getResultList();
        final String[] listauthn = {"("};
        listauthn[0] += "'" + auth +  "'";
        // Loop to get Fields
        listauth.forEach(f -> {
            listauthn[0] += ",'" + f + "'";
        });
        listauthn[0] += ")";

        FilterModel filterByReportUser = new FilterModel("autoloan.createdby", "IN", "", "", listauthn[0], "G9");
        List<FilterModel> newfilterlist = advanceModel.getFilterModels();
        newfilterlist.add(filterByReportUser);
        advanceModel.setFilterModels(newfilterlist);
        return super.getAdvanceFilter(advanceModel);
    }

    @Override
    public Integer countAdvanceFilter(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        String sql = "SELECT ACC.username FROM accountrole AS ACCR " +
                " INNER JOIN account AS ACC ON ACC.id = ACCR.accountid " +
                " WHERE reportto LIKE '%' || (SELECT id FROM account WHERE username = '" + auth + "') || '%'";
        List<String> listauth = entityManager.createNativeQuery(sql).getResultList();
        final String[] listauthn = {"("};
        listauthn[0] += "'" + auth +  "'";
        // Loop to get Fields
        listauth.forEach(f -> {
            listauthn[0] += ",'" + f + "'";
        });
        listauthn[0] += ")";

        FilterModel filterByReportUser = new FilterModel("autoloan.createdby", "IN", "", "", listauthn[0], "G9");
        List<FilterModel> newfilterlist = advanceModel.getFilterModels();
        newfilterlist.add(filterByReportUser);
        advanceModel.setFilterModels(newfilterlist);
        return super.countAdvanceFilter(advanceModel);
    }

    @Override
    public List<AutoloanExportModel> getExport(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        String sql = "SELECT ACC.username FROM accountrole AS ACCR " +
                " INNER JOIN account AS ACC ON ACC.id = ACCR.accountid " +
                " WHERE reportto LIKE '%' || (SELECT id FROM account WHERE username = '" + auth + "') || '%'";
        List<String> listauth = entityManager.createNativeQuery(sql).getResultList();
        final String[] listauthn = {"("};
        listauthn[0] += "'" + auth +  "'";
        // Loop to get Fields
        listauth.forEach(f -> {
            listauthn[0] += ",'" + f + "'";
        });
        listauthn[0] += ")";
        FilterModel filterByReportUser = new FilterModel("autoloan.createdby", "IN", "", "", listauthn[0], "G9");
        List<FilterModel> newfilterlist = advanceModel.getFilterModels();
        newfilterlist.add(filterByReportUser);
        advanceModel.setFilterModels(newfilterlist);

        // Overide Fileds
        List<String> newfiles = new ArrayList<>();
        newfiles.add("CAST(ROW_NUMBER() OVER ( ORDER BY autoloan DESC ) AS TEXT) AS NO");
        newfiles.add("customername");
        newfiles.add("autoloan.phone1");
        newfiles.add("loanamount");
        newfiles.add("LAMC.name AS loanamountcurrencyname");
        newfiles.add("price");
        newfiles.add("PC.name AS carpricecurrencyname");
        newfiles.add("yearmade");
        newfiles.add("avg_income");
        newfiles.add("AVGC.name AS avgincomecurrencyname");
        newfiles.add("CONCAT(ACC.firstname, ' ', ACC.lastname) AS dealername");
        newfiles.add("autoloan.createddate");
        newfiles.add("C.NAME AS statusname");

        advanceModel.setFields(newfiles);

        // Overide Inner Join
        List<String> oldJoin = advanceModel.getJoinTables();
        oldJoin.add("INNER JOIN configure AS LAMC ON LAMC.id = loanamountcurrency");
        oldJoin.add("INNER JOIN configure AS PC ON PC.id = pricecurrency");
        oldJoin.add("INNER JOIN configure AS AVGC ON AVGC.id = avgincomecurrency");
        oldJoin.add("INNER JOIN account AS ACC ON ACC.username = autoloan.createdby");

        advanceModel.setJoinTables(oldJoin);
        return super.getExport(advanceModel);
    }

    @Transactional
    @Override
    public Boolean putChangeStatus(String id, AutoloanPutChangeStatus autoloanPutChangeStatus) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();
        Account account = accountService.findAccountByUsernameAndEnable(auth, true);

        // Change Status for Autoloan
        Autoloan al = autoloanRepository.getOne(id);
        Autoloan oldal = new Autoloan(al);
        al.setStatus(autoloanPutChangeStatus.getStatus());
        save(al);

        // Add to Transaction Status
        TranStatus ts = new TranStatus(al.getId(), oldal.getStatus(), al.getStatus(), autoloanPutChangeStatus.getComment());
        ts.setCreatedby(auth);
        ts.setCreateddate(createddate);
        tranStatusService.save(ts);

        // Send Mail
        String[] to = {"settlement@darapay.com.kh"};
        if (al.getStatus().equals("2715d4df-5620-461a-b572-2b7c9f4d836b")) {
            Configure ammountCurrency = configureService.find(al.getLoanamountcurrency());
            String fullName = account.getFirstname() + " " +  account.getLastname();
            String htmltext = "<p>Dear Respected Team,</p>" +
                    "<p>Please inform that CNB's Auto Loan has approved for Customer Name: <span style='font-size: 20px;'> " + al.getCustomername() + " </span> with Amount <b> " + al.getLoanamount() + " " + ammountCurrency.getName() + "</b> from Dealer Name: <b>" + fullName + "</b></p>" +
                    "<p>Please kindly workout with CNB team for more justification.</p>" +
                    "<p>Best Regards,</p>" +
                    "<p>DaraPay Team</p>" +
                    "</br><p style='color: red;'><i>Note: This is Automation from DaraPay Portal</i></p>";
            emailService.sendMessageWithHtmlTemplate("darapayportal@gmail.com",to, "Approved Auto Loan From CNB", "it@darapay.com.kh" ,htmltext);
        }
        return true;
    }
}
