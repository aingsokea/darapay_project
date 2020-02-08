package com.darapay.loanreferral.controllers;


import com.darapay.loanreferral.models.Account;
import com.darapay.loanreferral.models.FrmLoan;
import com.darapay.loanreferral.repositories.AccountRepository;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.ConfigureService;
import com.darapay.loanreferral.services.EmailService;
import com.darapay.loanreferral.services.FrmLoanService;
import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.FrmLoanAssignModel;
import com.darapay.loanreferral.viewmodels.FrmLoanModel;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import com.darapay.loanreferral.viewmodels.core.FrmLoanAgentModel;
import com.darapay.loanreferral.viewmodels.export.FrmLoanExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/frmloan")
public class FrmLoanController extends EntityApiController<FrmLoan, String, FrmLoanModel, FrmLoanExportModel> {
    private String className = this.getClass().getSimpleName().toLowerCase().split("controller")[0];

    @Autowired
    private FrmLoanService frmLoanService;

    @Override
    protected BaseService<FrmLoan, String, FrmLoanModel, FrmLoanExportModel> getBaseService() {
        return frmLoanService;
    }

    @Override
    protected FrmLoan NewEntity() {
        return new FrmLoan();
    }

    @Override
    protected FrmLoanModel NewModel() {
        return new FrmLoanModel();
    }

    // For Agent

    // Onload
    @RequestMapping(value = "/onload", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getForms() {
        return new ResponseEntity<Object>(frmLoanService.getOnLoad(), HttpStatus.OK);
    }

    // Get frmloan by id
    @RequestMapping(value = "/agent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_AGENT_BYID')")
    public ResponseEntity<?> getFormsById(@PathVariable String id) {
        FrmLoanAgentByIdDisplayModel flabdm = frmLoanService.getFrmLoanAgentById(id);
        return new ResponseEntity<Object>(flabdm, HttpStatus.OK);
    }

    // Get frmloan by each agent
    @RequestMapping(value = "/agent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_AGENT_FILTER')")
    public ResponseEntity<?> getFormsForAgent(@Valid @RequestBody AdvanceModel advanceModel) {
        advanceModel.setTable(className);
        FrmLoanDisplayModel fldm = frmLoanService.getFrmLoanForAgent(advanceModel);
        return new ResponseEntity<Object>(fldm, HttpStatus.OK);
    }
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AccountRepository accountRepository;
    // Create frmloan
    @Override
    public ResponseEntity<?> Post(@Valid @RequestBody FrmLoanModel frmLoanModel) {
        frmLoanModel.setLoanst("3ef22df0-32ac-4e86-9f5d-78d98f7ba463");
        frmLoanModel.setAssignedtobranch("");
        frmLoanModel.setAssignedtoperson("");
        frmLoanModel.setApprovedamt(0.0);
        frmLoanModel.setProcessedmfi("");
        String requestMFI;
        requestMFI=frmLoanModel.getReqmfi();
        if(requestMFI.equals("8bc9c63e-ccf8-4d61-8858-fb7c6f526d41")) {
            // Sending Mail
            String temp = "";
            String[] email;
            String[] to = new String[]{"sokea.aing@darapay.com.kh"};

           List<Account> accounts = accountRepository.findByIdentityidAndMfiid("",requestMFI);
            to = new String[accounts.size()];
            for (Account _account : accounts) {
                temp += _account.getEmail() + ",";
            }
            if (temp != "") {
                email = temp.split(",");
                to = new String[email.length];

                for (int i = 0; i < email.length; i++) {
                    to[i] = '"' + email[i] + '"';
                }
            } else {
                to = new String[]{"sokea.aing@darapay.com.kh"};
            }


            String [] currency={frmLoanModel.getReqcurrency()};
            List<UserTypeDisplayModel>  userModel=configureService.getUserTypeByTypeInIdAndEnable("CURRENCYTYPE",currency,true);
            String fullName = "DaraPay";
            String htmltext = "<p><b>Dear  Respective Manager,</b></p>" +
                    "<p>The below customers are referred to your business. so please assign your team to contact the customers accordingly</p>" +
                    "<p>Then, please update loan status on Darapay App in order to make our loan referral process go smoothly</p>" +
                    "<ul>" +
                    "<li>Customer Name: <b>" + frmLoanModel.getCustname() + "</b></li>" +
                    "<li>Customer Phone: <b>" + frmLoanModel.getCustphone() + "</b></li>" +
                    "<li>Request Amount: <b>" + frmLoanModel.getReqloanamount() + "</b></li>" +
                    "<li>Currency: <b>" + userModel.get(0).getName() + "</b></li>" +
                    "<li>Loan Term: <b>" + frmLoanModel.getLoanperiod() + " Year</b></li>" +
                    "</ul>" +
                    "<p><b>Thanks for your cooperation !!</b></p>" +
                    "</br><p style='color: red;'><i>Note: This is Automation from DaraPay Portal</i></p>";
            emailService.sendMessageWithHtmlTemplate("darapayportal@gmail.com", to, "Request loan via DaraPay Portal", "", htmltext);
        }
        return super.Post(frmLoanModel);
    }

    // Update frmloan
    @RequestMapping(value = "/agent/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_AGENT_UPDATE')")
    public ResponseEntity<?> getFormsForAgent(@PathVariable String id, @RequestBody FrmLoanAgentModel frmLoanAgentModel) {
        FrmLoanAgentModel flam = frmLoanService.updateFrmLoanByAgent(id, frmLoanAgentModel);
        return new ResponseEntity<Object>(flam, HttpStatus.OK);
    }

    // Update frmloan
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_AGENT_DELETE')")
    public ResponseEntity<?> getFormsToDeleteAgent(@PathVariable String id) {
        frmLoanService.deleteFrmLoan(id);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    // End For Agent

    // For DP Operation to Assign to MFI


    @Override
    public ResponseEntity<?> PostAdvanceFilter(@Valid @RequestBody AdvanceModel advanceModel) {
        return new ResponseEntity<Object>(frmLoanService.getFrmloanForDp(advanceModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/assign/onload/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_ONLOAD_DP_ASSIGN')")
    public ResponseEntity<?> getFormsAssign(@PathVariable String id) {
        return new ResponseEntity<Object>(frmLoanService.getAssignOnload(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/assignco/onload/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_ONLOAD_DP_ASSIGN')")
    public ResponseEntity<?> getFormsAssignCO(@PathVariable String id) {
        return new ResponseEntity<Object>(frmLoanService.getAssignOnloadFinalUser(id), HttpStatus.OK);
    }
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
   // @PreAuthorize("hasAuthority('FRMLOAN_DP_ASSIGN')")
    public ResponseEntity<?> assignToMfi(@Valid @PathVariable String id, @RequestBody FrmLoanAssignModel fam) {
        //FrmLoanAssignModel flam = frmLoanService.assigntoMfi(id, fam);
        return new ResponseEntity<Object>(frmLoanService.assigntoMfi(id, fam), HttpStatus.OK);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_DETAIL')")
    public ResponseEntity<?> getFromLoanDetail(@PathVariable String id) {
        FrmLoanDetailDisplayModel flddm = frmLoanService.getFrmLoanDetail(id);
        return new ResponseEntity<Object>(flddm, HttpStatus.OK);
    }
    // END DP

    // For MFI
    @RequestMapping(value = "/mfi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_MFI_FILTER')")
    public ResponseEntity<?> getFormsForMfi(@Valid @RequestBody AdvanceModel advanceModel) {
        advanceModel.setTable(className);
        FrmLoanDisplayModel fldm = frmLoanService.getFrmLoanForMfi(advanceModel);
        return new ResponseEntity<Object>(fldm, HttpStatus.OK);
    }

    @RequestMapping(value = "/mfiassign/onload/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_ONLOAD_MFI_ASSIGN')")
    public ResponseEntity<?> getFormsOnloadMfiAssign(@PathVariable String id) {
        return new ResponseEntity<Object>(frmLoanService.getOnloadMfiAssign(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/mfiassign/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_MFI_ASSIGN')")
    public ResponseEntity<?> assignMfiTobranch(@Valid @PathVariable String id, @RequestBody FrmLoanInprogressModel frmLoanInprogressModel) {
      //  FrmLoanInprogressModel flim = frmLoanService.assigntoBranch(id, frmLoanInprogressModel);
        return new ResponseEntity<Object>(frmLoanService.assigntoBranch(id, frmLoanInprogressModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/branchassign/onload/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_ONLOAD_BRANCH_ASSIGN')")
    public ResponseEntity<?> getFormsOnloadBranchAssign(@PathVariable String id) {
        return new ResponseEntity<Object>(frmLoanService.getOnloadBranchAssign(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/branchassign/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_BRANCH_ASSIGN')")
    public ResponseEntity<?> assignByBranch(@Valid @PathVariable String id, @RequestBody FrmLoanBranchModel frmLoanBranchModel) {
        FrmLoanBranchModel flbm = frmLoanService.assignFromBranch(id, frmLoanBranchModel);
        return new ResponseEntity<Object>(flbm, HttpStatus.OK);
    }
    // END MFI

    @RequestMapping(value = "/reject/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('FRMLOAN_REJECT')")
    public ResponseEntity<?> rejectFrmLoan(@Valid @PathVariable String id, @RequestBody TranStatusModel statusModel) {
        return new ResponseEntity<Object>(frmLoanService.rejectFrmLoan(id, statusModel), HttpStatus.OK);
    }


}
