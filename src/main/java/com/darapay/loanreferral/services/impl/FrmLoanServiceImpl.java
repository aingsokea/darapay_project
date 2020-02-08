package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.models.*;
import com.darapay.loanreferral.repositories.AccountRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.FrmLoanRepository;
import com.darapay.loanreferral.security.Exceptioins.RecoredNotFoundException;
import com.darapay.loanreferral.services.*;
import com.darapay.loanreferral.viewmodels.*;
import com.darapay.loanreferral.viewmodels.core.FrmLoanAgentModel;
import com.darapay.loanreferral.viewmodels.export.FrmLoanExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;
import org.hibernate.engine.spi.ActionQueue;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@Repository
@Service
public class FrmLoanServiceImpl extends BaseServiceImpl<FrmLoan, String, FrmLoanModel, FrmLoanExportModel> implements FrmLoanService {

    @Autowired
    private FrmLoanRepository frmLoanRepository;

    @Autowired
    private ConfigureService configureService;

    @Autowired
    private MainAddressService mainAddressService;

    @Autowired
    private MfiService mfiService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LogMastersService logMastersService;

    @Autowired
    private TranStatusService tranStatusService;

    @Autowired
    private LoanTypeService loanTypeService;

    @Autowired
    private BranchService branchService;

    @Override
    protected ExtendedRepository<FrmLoan, String> getBaseRepository() {
        return frmLoanRepository;
    }

    @Override
    public List<FrmLoan> findAll() {
        return frmLoanRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return FrmLoanModel.class;
    }


    Class branchModel() {
        return BranchDisPlayDdkModel.class;
    }

    @Override
    Class aliasExportModel() {
        return FrmLoanExportModel.class;
    }

    @Override
    public FrmLoanOnloadModel getOnLoad() {
        // Get Currencies
        List<UserTypeDisplayModel> utdp = configureService.getUserTypeByTypeAndEnable("CURRENCYTYPE", true);

        // Get Provinces
        List<MainAddressDisplayDdlModel> madm = mainAddressService.getMainAddressByParentCode("855");

        // Get Mfis
        List<MfiDisplayOnloadModel> mdms = mfiService.getAllMfiByEnableOrderByTabOrder(true);
        return new FrmLoanOnloadModel(utdp , madm, mdms);
    }

    @Override
    public FrmLoanDisplayModel getFrmLoanForAgent(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Role r = getRole();
        List<FilterModel> fm = advanceModel.getFilterModels();
        if (r.getName().equals("AGENT")) {
            FilterModel authfm = new FilterModel("frmloan.createdby", "=", null, null, "'" + auth + "'", "Z1");
            fm.add(authfm);
        }
        advanceModel.setFilterModels(fm);
        List<FrmLoanModel> models = getAdvanceFilter(advanceModel);
        Integer count = countAdvanceFilter(advanceModel);
        List<StatusNavDisplayModel> sndms = new ArrayList<>();
        return new FrmLoanDisplayModel(sndms,models, count);
    }

    @Override
    public FrmLoanDisplayModel getFrmLoanForMfi(AdvanceModel advanceModel) {
        Role r = getRole();
        List<FilterModel> fm = advanceModel.getFilterModels();
        String assignedtoperson;
        assignedtoperson="LOWER('%%')";
        // Add Filter to get each mfi
        fm.add(new FilterModel("frmloan.processedmfi", "=", null, null, "'" + r.getMfiid() + "'", "Z1"));
        advanceModel.setFilterModels(fm);
        // Add Reponsible to get each loan type base on mfi
        if (!r.getResponsible().equals("")) {
            fm.add(new FilterModel("frmloan.loantype", "IN", null, null, "'" + r.getResponsible() + "'", "Z2"));
        }
        // Add More Filter when they are the branch user
        if (r.getBranchid() != null && !r.getBranchid().equals("")) {
            fm.add(new FilterModel("frmloan.assignedtobranch", "=", null, null, "'" + r.getBranchid() + "'", "Z3"));

        }
        List<FrmLoanModel> models = getAdvanceFilter(advanceModel);
        Integer count = countAdvanceFilter(advanceModel);
        //add loan status
        List<FilterModel> filterModels=advanceModel.getFilterModels();
        FilterModel firstmodel= filterModels.get(5);
        String mfiid=firstmodel.getValue();



        List<StatusNavDisplayModel> sndms = new ArrayList<>();
        List<UserTypeDisplayModel> allActiveStatus = configureService.getUserTypeByTypeAndEnableOrderByItem2("STATUSTYPE", true);
        allActiveStatus.forEach(a -> {
            //  Integer count = countAllByLoanstAndEnable(a.getUserkey(), true);
            Integer count1  =  countAllByMFIFunan(assignedtoperson,mfiid,a.getUserkey(), true);
            StatusNavDisplayModel sndm = new StatusNavDisplayModel(a.getName(), count1, a.getNamekh(), false);
            sndms.add(sndm);
        });
      // Add All
        //Integer countAll = countAllByEnable(true);
        Integer countAll = countAllByMFIPersonFunan(assignedtoperson,mfiid,true);
        StatusNavDisplayModel sndm = new StatusNavDisplayModel("All", countAll, "ទាំងអស់", false);
        sndms.add(sndm);
        return new FrmLoanDisplayModel(sndms,models, count);
    }
    public Integer countAllByMFIFunan(String assignedperson,String mfiid,String loanst, Boolean enable) {
        String sql = "SELECT COUNT(*) FROM frmloan where loanst='"+loanst+"' and enable='"+enable+"' and processedmfi LIKE "+mfiid+"  and assignedtoperson LIKE "+assignedperson+"";
        Query query= entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
    public Integer countAllByMFIPersonFunan(String assignedperson,String mfiid, Boolean enable) {

        String sql = "SELECT COUNT(*) FROM frmloan where  enable='"+enable+"' and processedmfi LIKE "+mfiid+" and assignedtoperson LIKE "+assignedperson+"";
        Query query= entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
    @Override
    public FrmLoanAssignOnloadModel getAssignOnload(String id) {

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }


        // Get Status
        String[] sts = {"INITIAL"};
        List<UserTypeDisplayModel> status = configureService.getUserTypeByTypeInNameAndEnable("STATUSTYPE", sts ,true);

        // Get Mfi
        //String[] mfis = {"មួយណាក៏បាន"};
       // List<MfiDisplayModel> mdom = mfiService.getAllMfiToAssignByEnableAndNameIsNotInOrderByTaborder(true, mfis);
        List<MfiDisplayOnloadModel> mfimodels = mfiService.getAllMfiByEnableOrderByTabOrder(true);
        return new FrmLoanAssignOnloadModel(id, status, mfimodels);
    }
    @Override
    public FrmLoanAssignOnloadModel getAssignOnloadFinalUser(String id) {

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        // Get Status
        String[] sts = {"DECLINED","IN-PROGRESS", "REJECTED", "APPROVED","DISBURSEMENT"};
        List<UserTypeDisplayModel> status = configureService.getUserTypeByTypeInNameAndEnable("STATUSTYPE", sts ,true);

        // Get Mfi
      //  String[] mfis = {"មួយណាក៏បាន"};
       // List<MfiDisplayModel> mdom = mfiService.getAllMfiToAssignByEnableAndNameIsNotInOrderByTaborder(true, mfis);
        // Get Mfis
        List<MfiDisplayOnloadModel> mfimodels = mfiService.getAllMfiByEnableOrderByTabOrder(true);

        return new FrmLoanAssignOnloadModel(id, status, mfimodels);
    }
    @Override
    public FrmLoanForDpModel getFrmloanForDp(AdvanceModel advanceModel) {
        advanceModel.setTable("frmloan");
        List<FrmLoanTableDpDisplayModel> frmloanfordp = new ArrayList<>();
        List<FrmLoanModel> models = getSearch(advanceModel);
        models.forEach(f -> {
            FrmLoanTableDpDisplayModel fltd = new FrmLoanTableDpDisplayModel(f.getKey(), f.getCustname(), f.getCustphone(),
                    f.getReqloanamount(), f.getCurrency(), f.getLoanperiod(), f.getStatusname(), f.getMfipic(), f.getCreateddate(),f.getVillage(),f.getCommune(),f.getDistrict(),f.getProvince(),
                    f.getFirstname(),f.getLastname(),f.getUsername(),f.getPhone(),f.getPicmfireq(),f.getPicmfipro());
            frmloanfordp.add(fltd);
        });
        Integer counts = countFilter(advanceModel);
        List<FilterModel> filterModels=advanceModel.getFilterModels();
        FilterModel firstmodel= filterModels.get(3);
        String mfiid=firstmodel.getValue();
        FilterModel models2= filterModels.get(6);
        String assignedtoperson;
        assignedtoperson=models2.getValue();

        List<StatusNavDisplayModel> sndms = new ArrayList<>();
        List<UserTypeDisplayModel> allActiveStatus = configureService.getUserTypeByTypeAndEnableOrderByItem2("STATUSTYPE", true);
        allActiveStatus.forEach(a -> {
          //  Integer count = countAllByLoanstAndEnable(a.getUserkey(), true);
            Integer count  =  countAllByMFI(assignedtoperson,mfiid,a.getUserkey(), true);
            StatusNavDisplayModel sndm = new StatusNavDisplayModel(a.getName(), count, a.getNamekh(), false);
            sndms.add(sndm);
        });
        // Add All
        //Integer countAll = countAllByEnable(true);
        Integer countAll = countAllByMFIPerson(assignedtoperson,mfiid,true);
        StatusNavDisplayModel sndm = new StatusNavDisplayModel("All", countAll, "ទាំងអស់", false);
        sndms.add(sndm);
        return new FrmLoanForDpModel(sndms, frmloanfordp, counts);
    }


    public Integer countAllByMFI(String assignedperson,String mfiid,String loanst, Boolean enable) {
        String sql = "SELECT COUNT(*) FROM frmloan where loanst='"+loanst+"' and enable='"+enable+"' and (processedmfi LIKE "+mfiid+" or (reqmfi LIKE "+mfiid+" and processedmfi='')) and assignedtoperson LIKE "+assignedperson+"";
        Query query= entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Integer countAllByEnable(Boolean enable) {
        return frmLoanRepository.countAllByEnable(enable);
    }
    public Integer countAllByMFIPerson(String assignedperson,String mfiid, Boolean enable) {

        String sql = "SELECT COUNT(*) FROM frmloan where  enable='"+enable+"' and (processedmfi LIKE "+mfiid+" or (reqmfi LIKE "+mfiid+" and processedmfi='' )) and assignedtoperson LIKE "+assignedperson+"";
        Query query= entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
    @Autowired
    private  EmailService emailService;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    @Transactional
    //public FrmLoanAssignModel assigntoMfi(String id, FrmLoanAssignModel fam) {
    public boolean assigntoMfi(String id, FrmLoanAssignModel fam) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);
        FrmLoan oldFrmLoan = new FrmLoan(fl);
        oldFrmLoan.setCreatedby(fl.getCreatedby());
        oldFrmLoan.setCreateddate(fl.getCreateddate());

        // Update Frm Loan
        fl.setLoanst(fam.getLoanst());
        if(fam.getApprovedamt()>0){
            fl.setApprovedamt(fam.getApprovedamt());
        }
        if(fam.getAcc()!=""){
            fl.setAcc(fam.getAcc());
        }
        if(fam.getProcessedmfi()!="") {
            fl.setProcessedmfi(fam.getProcessedmfi());
        }
        if(fam.getAssigntoperson()!=""){
            fl.setAssignedtoperson(fam.getAssigntoperson());
        }
        fl.setModifiedby(auth);
        fl.setModifieddate(createddate);
        FrmLoan newFrmLoan =  save(fl);

        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newFrmLoan);
        LogMasters lm = new LogMasters(newFrmLoan.getId(), "TABLE_FRMLOAN", fromdata, todata, "UPDATE_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);

        // Create TranStatus
        TranStatusModel tsm = fam.getTranStatusModels();
        TranStatus tranStatus = new TranStatus(newFrmLoan.getId(), oldFrmLoan.getLoanst(), newFrmLoan.getLoanst(), tsm.getComment());
        tranStatus.setCreatedby(auth);
        tranStatus.setCreateddate(createddate);
        TranStatus ts = tranStatusService.save(tranStatus);

        // Create Log For TranStatus
        String todatatran = AnonymousFunc.converFromModelToToJson(ts);
        LogMasters lmts = new LogMasters(newFrmLoan.getId(), "TABLE_TRANSTATUS", "", todatatran, "CREATE_RECORD");
        lmts.setCreatedby(auth);
        lmts.setCreateddate(createddate);
        logMastersService.save(lmts);


       // Sending Mail
       String requestMFI;
        requestMFI=fl.getProcessedmfi();
        List<Account> accounts;
        String temp="";
        String[] email;
        String[] to= new String[]{"sokea.aing@darapay.com.kh"};
       if(fam.getAssigntoperson()!=null && fam.getAssigntoperson()!="") {
            accounts=accountRepository.findByIdentityid(fam.getAssigntoperson());
        }else{
            accounts = accountRepository.findByIdentityidAndMfiid("",requestMFI);
        }
        if(!accounts.isEmpty()){
            to = new String[accounts.size()];
            for (Account _account : accounts) {
                temp += _account.getEmail() + ",";
            }
            if (temp != ""){
                 email = temp.split(",");
                  to = new String[email.length];

                    for (int i = 0; i < email.length; i++) {
                        to[i] = '"' + email[i] + '"';
                    }
            }else{
                to = new String[]{"pkayprek.nhep@funan.com.kh","naisim.sin@funan.com.kh","sokea.aing@darapay.com.kh"};
            }
        }else{
            to = new String[]{"pkayprek.nhep@funan.com.kh","naisim.sin@funan.com.kh","sokea.aing@darapay.com.kh"};
        }

        String [] currency={fl.getReqcurrency()};
        List<UserTypeDisplayModel>  userModel=configureService.getUserTypeByTypeInIdAndEnable("CURRENCYTYPE",currency,true);

        //Configure ammountCurrency = configureService.find(al.getLoanamountcurrency());
        String fullName = "Aing Sokea";
        String htmltext = "<p><b>Dear  Respective Manager,</b></p>" +
                "<p>The below customers are referred to your business. so please assign your team to contact the customers accordingly</p>" +
                "<p>Then, please update loan status on Darapay App in order to make our loan referral process go smoothly</p>" +
                "<ul>" +
                "<li>Customer Name: <b>"+fl.getCustname()+"</b></li>" +
                "<li>Customer Phone: <b>"+fl.getCustphone()+"</b></li>" +
                "<li>Request Amount: <b>"+fl.getReqloanamount()+"</b></li>" +
                "<li>Currency: <b>"+userModel.get(0).getName()+"</b></li>" +
                "<li>Loan Term: <b>"+fl.getLoanperiod()+" Year</b></li>" +
                "</ul>"+
                "<p><b>Thanks for your cooperation !!</b></p>" +
                "</br><p style='color: red;'><i>Note: This is Automation from DaraPay Portal</i></p>";
        emailService.sendMessageWithHtmlTemplate("darapayportal@gmail.com",to, "Request loan via DaraPay Portal", "" ,htmltext);

       // return new FrmLoanAssignModel(newFrmLoan.getLoanst(), newFrmLoan.getProcessedmfi(),newFrmLoan.getAssignedtobranch(), tsm);
        return true;
    }


    @Override
    public FrmLoanOnloadMfiAssignModel getOnloadMfiAssign(String id) {
        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        FrmLoan fl = find(id);

        // Get Branch
       // List<BranchDisPlayDdkModel> branchDdl = branchService.getAllByMfiidOrderByindex(fl.getProcessedmfi(), true);
        List<BranchDisPlayDdkModel> branchDdl=getAllBranchByMFI(fl.getProcessedmfi(), true);
        // Get Account by Branch
        Role r = getRole();

        List<AccountDisplayDdlModel> accDdl = accountService.findAccountByMfiProc(fl.getProcessedmfi());

        // Get Status
        String[] sts = {"ASSIGNED","DECLINED","CLEAR-INVOICE"};
        List<UserTypeDisplayModel> status = configureService.getUserTypeByTypeInNameAndEnable("STATUSTYPE", sts ,true);
        FrmLoanInprogressModel frmModel=getAssignLoan(id).get(0);
        return new FrmLoanOnloadMfiAssignModel(fl.getId(), status, branchDdl,accDdl,frmModel);
    }
    public List<BranchDisPlayDdkModel> getAllBranchByMFI(String mfiid, Boolean enable) {

        String sql = "SELECT id as key,name,code,index FROM branch where  enable='"+enable+"' and mfiid='"+mfiid+"' order by index";
        return   entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(branchModel())).list();

    }
    public List<FrmLoanInprogressModel> getAssignLoan(String key) {

        String sql = "SELECT loanst,assignedtobranch as branchid,assignedtoperson as accountid FROM frmloan where  enable=true and id='"+key+"'";
        return   entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(FrmLoanInprogressModel.class)).list();

    }
    @Override
    @Transactional
  //  public FrmLoanInprogressModel assigntoBranch(String id, FrmLoanInprogressModel fim) {
    public boolean assigntoBranch(String id, FrmLoanInprogressModel fim) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);
        FrmLoan oldFrmLoan = new FrmLoan(fl);
        oldFrmLoan.setCreatedby(fl.getCreatedby());
        oldFrmLoan.setCreateddate(fl.getCreateddate());

        // Update Frm Loan
        fl.setLoanst(fim.getLoanst());
        fl.setAssignedtobranch(fim.getBranchid());
        fl.setAssignedtoperson(fim.getAccountid());
        fl.setModifiedby(auth);
        fl.setModifieddate(createddate);
        FrmLoan newFrmLoan =  save(fl);

        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newFrmLoan);
        LogMasters lm = new LogMasters(newFrmLoan.getId(), "TABLE_FRMLOAN", fromdata, todata, "UPDATE_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);

        // Create TranStatus
        TranStatusModel tsm = fim.getTranStatusModels();
        TranStatus tranStatus = new TranStatus(newFrmLoan.getId(), oldFrmLoan.getLoanst(), newFrmLoan.getLoanst(), tsm.getComment());
        tranStatus.setCreatedby(auth);
        tranStatus.setCreateddate(createddate);
        TranStatus ts = tranStatusService.save(tranStatus);

        // Create Log For TranStatus
        String todatatran = AnonymousFunc.converFromModelToToJson(ts);
        LogMasters lmts = new LogMasters(newFrmLoan.getId(), "TABLE_TRANSTATUS", "", todatatran, "CREATE_RECORD");
        lmts.setCreatedby(auth);
        lmts.setCreateddate(createddate);
        logMastersService.save(lmts);

        // Sending Mail
        String requestMFI;
        requestMFI=fl.getProcessedmfi();
        List<Account> accounts;
        String temp="";
        String[] email;
        String[] to= new String[]{"sokea.aing@darapay.com.kh"};
        String assignedtoperson= fl.getAssignedtoperson();
        accounts = accountRepository.findByIdAndEnable(assignedtoperson,true);
        if(accounts.size()!=0 && !accounts.isEmpty()){
            to = new String[accounts.size()];
            for (Account _account : accounts) {
                temp += _account.getEmail() + ",";
            }
            if (temp != ""){
                email = temp.split(",");
                to = new String[email.length];

                for (int i = 0; i < email.length; i++) {
                    to[i] = '"' + email[i] + '"';
                }
            }else{
                to = new String[]{"pkayprek.nhep@funan.com.kh","naisim.sin@funan.com.kh","sokea.aing@darapay.com.kh"};
            }
        }else{
            to = new String[]{"pkayprek.nhep@funan.com.kh","naisim.sin@funan.com.kh","sokea.aing@darapay.com.kh"};
        }

        String [] currency={fl.getReqcurrency()};
        List<UserTypeDisplayModel>  userModel=configureService.getUserTypeByTypeInIdAndEnable("CURRENCYTYPE",currency,true);

        //Configure ammountCurrency = configureService.find(al.getLoanamountcurrency());
        String fullName = "Aing Sokea";
        String htmltext = "<p><b>Dear  Respective Manager,</b></p>" +
                "<p>The below customers are referred to your business. so please assign your team to contact the customers accordingly</p>" +
                "<p>Then, please update loan status on Darapay App in order to make our loan referral process go smoothly</p>" +
                "<ul>" +
                "<li>Customer Name: <b>"+fl.getCustname()+"</b></li>" +
                "<li>Customer Phone: <b>"+fl.getCustphone()+"</b></li>" +
                "<li>Request Amount: <b>"+fl.getReqloanamount()+"</b></li>" +
                "<li>Currency: <b>"+userModel.get(0).getName()+"</b></li>" +
                "<li>Loan Term: <b>"+fl.getLoanperiod()+" Year</b></li>" +
                "</ul>"+
                "<p><b>Thanks for your cooperation !!</b></p>" +
                "</br><p style='color: red;'><i>Note: This is Automation from DaraPay Portal(Assigned to branch)</i></p>";
        emailService.sendMessageWithHtmlTemplate("darapayportal@gmail.com",to, "Request loan via DaraPay Portal", "naisim.sin@funan.com.kh" ,htmltext);

        return true;
       // return new FrmLoanInprogressModel(newFrmLoan.getLoanst(), newFrmLoan.getAssignedtobranch(), newFrmLoan.getAssignedtoperson(), tsm);
    }

    @Override
    public FrmLoanOnloadBranchAssignModel getOnloadBranchAssign(String id) {

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        // Get Status
        String[] sts = {"IN-PROGRESS","APPROVED","DISBURSEMENT", "REJECTED","DECLINED"};
        List<UserTypeDisplayModel> status = configureService.getUserTypeByTypeInNameAndEnable("STATUSTYPE", sts ,true);

        return new FrmLoanOnloadBranchAssignModel(id, status);
    }

    @Override
    @Transactional
    public FrmLoanBranchModel assignFromBranch(String id, FrmLoanBranchModel flbm) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);
        FrmLoan oldFrmLoan = new FrmLoan(fl);
        oldFrmLoan.setCreatedby(fl.getCreatedby());
        oldFrmLoan.setCreateddate(fl.getCreateddate());

        // Update Frm Loan
        fl.setLoanst(flbm.getLoanst());
        fl.setAppram(flbm.getAppram());
        fl.setModifiedby(auth);
        fl.setModifieddate(createddate);
        FrmLoan newFrmLoan =  save(fl);

        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newFrmLoan);
        LogMasters lm = new LogMasters(newFrmLoan.getId(), "TABLE_FRMLOAN", fromdata, todata, "UPDATE_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);

        // Create TranStatus
        TranStatusModel tsm = flbm.getTranStatusModels();
        TranStatus tranStatus = new TranStatus(newFrmLoan.getId(), oldFrmLoan.getLoanst(), newFrmLoan.getLoanst(), tsm.getComment());
        tranStatus.setCreatedby(auth);
        tranStatus.setCreateddate(createddate);
        TranStatus ts = tranStatusService.save(tranStatus);

        // Create Log For TranStatus
        String todatatran = AnonymousFunc.converFromModelToToJson(ts);
        LogMasters lmts = new LogMasters(newFrmLoan.getId(), "TABLE_TRANSTATUS", "", todatatran, "CREATE_RECORD");
        lmts.setCreatedby(auth);
        lmts.setCreateddate(createddate);
        logMastersService.save(lmts);
        return new FrmLoanBranchModel(newFrmLoan.getLoanst(), newFrmLoan.getAppram(), tsm);
    }

    @Override
    public FrmLoanAgentByIdDisplayModel getFrmLoanAgentById(String id) {
        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);

        // Get Currencies
        List<UserTypeDisplayModel> utdp = configureService.getUserTypeByTypeAndEnable("CURRENCYTYPE", true);

        // Get Provinces
        List<MainAddressDisplayDdlModel> provinces = mainAddressService.getMainAddressByParentCode("855");
        List<MainAddressDisplayDdlModel> districts = mainAddressService.getMainAddressByParentCode(fl.getProvince());
        List<MainAddressDisplayDdlModel> communes = mainAddressService.getMainAddressByParentCode(fl.getDistrict());
        List<MainAddressDisplayDdlModel> villages = mainAddressService.getMainAddressByParentCode(fl.getCommune());

        // Get Mfis
        List<MfiDisplayOnloadModel> mdms = mfiService.getAllMfiByEnableOrderByTabOrder(true);

        FrmLoanAgentByIdDisplayModel flabdm = new FrmLoanAgentByIdDisplayModel(fl.getId(), fl.getCustname(), fl.getCustphone(),
                fl.getReqloanamount(), fl.getReqcurrency(), fl.getLoanperiod(), fl.getProvince(), fl.getDistrict(),fl.getCommune(),
                fl.getVillage(), fl.getReqmfi(), fl.getLoantype(), fl.getOtherloantype(), utdp, provinces, districts, communes, villages, mdms);
        return flabdm;
    }

    @Override
    @Transactional
    public FrmLoanAgentModel updateFrmLoanByAgent(String id, FrmLoanAgentModel flam) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);

        // Check Frmloan Update allow only status INITIAL
        if (!fl.getLoanst().equals("3ef22df0-32ac-4e86-9f5d-78d98f7ba463")) {
            throw new RecoredNotFoundException("Something woring please try again");
        }

        FrmLoan oldFrmLoan = new FrmLoan(fl);
        oldFrmLoan.setCreatedby(fl.getCreatedby());
        oldFrmLoan.setCreateddate(fl.getCreateddate());

        // Update Frm Loan
        fl.setCustname(flam.getCustname());
        fl.setCustphone(flam.getCustphone());
        fl.setReqloanamount(flam.getReqloanamount());
        fl.setReqcurrency(flam.getReqcurrency());
        fl.setLoanperiod(flam.getLoanperiod());
        fl.setReqmfi(flam.getReqmfi());
        fl.setLoantype(flam.getLoantype());
        fl.setOtherloantype(flam.getOtherloantype());
        fl.setProvince(flam.getProvince());
        fl.setDistrict(flam.getDistrict());
        fl.setCommune(flam.getCommune());
        fl.setVillage(flam.getVillage());
        fl.setModifiedby(auth);
        fl.setModifieddate(createddate);
        FrmLoan newFrmLoan =  save(fl);

        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newFrmLoan);
        LogMasters lm = new LogMasters(newFrmLoan.getId(), "TABLE_FRMLOAN", fromdata, todata, "UPDATE_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);
        return flam;
    }

    @Override
    public Boolean deleteFrmLoan(String id) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);

        FrmLoan oldFrmLoan = new FrmLoan(fl);
        oldFrmLoan.setCreatedby(fl.getCreatedby());
        oldFrmLoan.setCreateddate(fl.getCreateddate());

        fl.setEnable(false);
        FrmLoan newFrmLoan = save(fl);

        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newFrmLoan);
        LogMasters lm = new LogMasters(newFrmLoan.getId(), "TABLE_FRMLOAN", fromdata, todata, "DELETE_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);
        return true;
    }

    @Override
    public FrmLoanDetailDisplayModel getFrmLoanDetail(String id) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);

        // Get Address
        MainAddressDisplayDdlModel custvillage = new MainAddressDisplayDdlModel();
        if (fl.getVillage() != null && !fl.getVillage().equals("")) {
             custvillage = mainAddressService.getByAddressCode(fl.getVillage(), true);
        }
        MainAddressDisplayDdlModel custcommune = mainAddressService.getByAddressCode(fl.getCommune(), true);
        MainAddressDisplayDdlModel custdistrict = mainAddressService.getByAddressCode(fl.getDistrict(), true);
        MainAddressDisplayDdlModel custprovince = mainAddressService.getByAddressCode(fl.getProvince(), true);

        // Get Currency
        Configure currency = configureService.find(fl.getReqcurrency());

        // Get Status
        Configure status = configureService.find(fl.getLoanst());

        // Get Mfi
        Mfi mfi = mfiService.find(fl.getReqmfi());

        // Get Agent
        Account agent = accountService.findAccountByUsernameAndEnable(fl.getCreatedby(), true);

        // Get List of Transaction History
        List<TranStatusDisplayModel> tsdm = tranStatusService.getAllByFrmLoanIdOrderByCreatedDate(fl.getId(), true);

        FrmLoanDetailDisplayModel fddm = new FrmLoanDetailDisplayModel(fl.getId(), fl.getCustname(),
                fl.getCustphone(), custvillage.getNamekh(), custcommune.getNamekh(), custdistrict.getNamekh(),
                custprovince.getNamekh(), fl.getReqloanamount(), currency.getName(), fl.getLoanperiod(), fl.getAppram(),
                status.getName(), mfi.getName(), mfi.getPic(), "N/A", "N/A", "N/A" ,"N/A" ,"N/A","N/A",agent.getFirstname() + " " + agent.getLastname(),
                agent.getPhone1(), agent.getPhone2(), "N/A", "N/A", "N/A","N/A", tsdm);

        // Loan Type
        if (fl.getLoantype() != null && !fl.getLoantype().equals("")) {
            LoanType lt = loanTypeService.find(fl.getLoantype());
            fddm.setLoantype(lt.getName() + " (" + lt.getNamekh() + ")");
        }
        // Get Other Loan Type
        if (fl.getOtherloantype() != null && !fl.getOtherloantype().equals("")) {
            fddm.setOtherloantype(fl.getOtherloantype());
        }
        // Get Processed Mfi
        if (fl.getProcessedmfi() != null && !fl.getProcessedmfi().equals("")) {
            Mfi m = mfiService.find(fl.getProcessedmfi());
            fddm.setProcessedmfi(m.getName());
            fddm.setProcessedmfipic(m.getPic());
        }

        // Branch
        if (fl.getAssignedtobranch() != null && !fl.getAssignedtobranch().equals("")) {
              Branch brcn = branchService.find(fl.getAssignedtobranch());
              fddm.setBranch(brcn.getName());
        }
        // Branch User
        if (fl.getAssignedtoperson() != null && !fl.getAssignedtoperson().equals("")) {
            //Account accbrn = accountService.find(fl.getAssignedtoperson());
          /*  List<Account> accbrn = accountRepository.findByIdentityid(fl.getAssignedtoperson());
            if(accbrn!=null && accbrn.size()>0) {
                Account _acc = accbrn.get(0);
                fddm.setBranchuser(_acc.getFirstname() + " " + _acc.getLastname());
            } */
            Optional<Account> accbrn = accountRepository.findById(fl.getAssignedtoperson());
           accbrn.ifPresent(_acc -> {
               fddm.setBranchuser(_acc.getFirstname() + " " + _acc.getLastname());
           });

        }

        // Get Address Agent
        if (agent.getVillage() != null && !agent.getVillage().equals("")) {
            MainAddressDisplayDdlModel agentvillage = mainAddressService.getByAddressCode(agent.getVillage(), true);
            fddm.setAgvillage(agentvillage.getNamekh());
        }

        if (agent.getCommune() != null && !agent.getCommune().equals("")) {
            MainAddressDisplayDdlModel agentcommune = mainAddressService.getByAddressCode(agent.getCommune(), true);
            fddm.setAgcommnue(agentcommune.getNamekh());
        }

        if (agent.getDistrict() != null && !agent.getDistrict().equals("")) {
            MainAddressDisplayDdlModel agentdistrict = mainAddressService.getByAddressCode(agent.getDistrict(), true);
            fddm.setAgdistrict(agentdistrict.getNamekh());
        }

        if (agent.getProvince() != null && !agent.getProvince().equals("")) {
            MainAddressDisplayDdlModel agentprovince = mainAddressService.getByAddressCode(agent.getProvince(), true);
            fddm.setAgprovince(agentprovince.getNamekh());
        }

        return fddm;
    }

    @Override
    @Transactional
    public Boolean rejectFrmLoan(String id, TranStatusModel tsm) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!frmLoanRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        FrmLoan fl = find(id);

        FrmLoan oldFrmLoan = new FrmLoan(fl);
        oldFrmLoan.setCreatedby(fl.getCreatedby());
        oldFrmLoan.setCreateddate(fl.getCreateddate());

        fl.setLoanst("b74ac23e-2b4a-44db-b84f-00ec343b452c");
        fl.setModifiedby(auth);
        fl.setModifieddate(createddate);
        FrmLoan newFrmLoan = save(fl);

        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newFrmLoan);
        LogMasters lm = new LogMasters(newFrmLoan.getId(), "TABLE_FRMLOAN", fromdata, todata, "REJECT_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);

        // Create TranStatus
        TranStatus tranStatus = new TranStatus(newFrmLoan.getId(), oldFrmLoan.getLoanst(), newFrmLoan.getLoanst(), tsm.getComment());
        tranStatus.setCreatedby(auth);
        tranStatus.setCreateddate(createddate);
        TranStatus ts = tranStatusService.save(tranStatus);

        // Create Log For TranStatus
        String todatatran = AnonymousFunc.converFromModelToToJson(ts);
        LogMasters lmts = new LogMasters(newFrmLoan.getId(), "TABLE_TRANSTATUS", "", todatatran, "CREATE_RECORD");
        lmts.setCreatedby(auth);
        lmts.setCreateddate(createddate);
        logMastersService.save(lmts);

        return true;
    }

    @Override
    public Integer countAllByLoanstAndEnable( String loanst, Boolean enable) {
        return frmLoanRepository.countAllByLoanstAndEnable(loanst, enable);
    }


    private Role getRole() {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Account acc = accountService.findAccountByUsernameAndEnable(auth, true);
        AccountRole ar = accountRoleService.findByAccountId(acc.getId());
        return roleService.find(ar.getRoleid());
    }


    @Override
    public List<FrmLoanExportModel> getExport(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Account acc = accountService.findAccountByUsernameAndEnable(auth, true);
        AccountRole accrole = accountRoleService.findByAccountId(acc.getId());
        Role role = roleService.find(accrole.getRoleid());

        List<FilterModel> filtermodelsList = advanceModel.getFilterModels();
        if (role.getMfiid() != null && !role.getMfiid().equals("")) {
            filtermodelsList.add(new FilterModel("frmloan.processedmfi", "=", "", "", "'" + role.getMfiid() + "'", "G11"));
        }

        if (role.getBranchid() != null && !role.getBranchid().equals("")) {
            filtermodelsList.add(new FilterModel("frmloan.assignedtobranch", "=", "", "","'" + role.getBranchid() + "'", "G12"));
        }

        return super.ExportFile(advanceModel);
    }
    @Override
    public List<FrmLoanExportModel> getExportExcel(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Account acc = accountService.findAccountByUsernameAndEnable(auth, true);
        AccountRole accrole = accountRoleService.findByAccountId(acc.getId());
        Role role = roleService.find(accrole.getRoleid());

        List<FilterModel> filtermodelsList = advanceModel.getFilterModels();
        if (role.getMfiid() != null && !role.getMfiid().equals("")) {
            filtermodelsList.add(new FilterModel("frmloan.processedmfi", "=", "", "", "'" + role.getMfiid() + "'", "G11"));
        }

        if (role.getBranchid() != null && !role.getBranchid().equals("")) {
            filtermodelsList.add(new FilterModel("frmloan.assignedtobranch", "=", "", "","'" + role.getBranchid() + "'", "G12"));
        }
        return super.getExport(advanceModel);
    }
    @Override
    public List<FrmLoanExportModel> getExportFunan(AdvanceModel advanceModel) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Account acc = accountService.findAccountByUsernameAndEnable(auth, true);
        AccountRole accrole = accountRoleService.findByAccountId(acc.getId());
        Role role = roleService.find(accrole.getRoleid());

        List<FilterModel> filtermodelsList = advanceModel.getFilterModels();
        if (role.getMfiid() != null && !role.getMfiid().equals("")) {
            filtermodelsList.add(new FilterModel("frmloan.processedmfi", "=", "", "", "'" + role.getMfiid() + "'", "G11"));
        }

        if (role.getBranchid() != null && !role.getBranchid().equals("")) {
            filtermodelsList.add(new FilterModel("frmloan.assignedtobranch", "=", "", "","'" + role.getBranchid() + "'", "G12"));
        }

        return super.getExport(advanceModel);
    }
}
