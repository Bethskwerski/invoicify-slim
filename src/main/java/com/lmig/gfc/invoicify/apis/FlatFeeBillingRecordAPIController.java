package com.lmig.gfc.invoicify.apis;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;


@RestController
@RequestMapping("/api/flatfees")
public class FlatFeeBillingRecordAPIController {

	private BillingRecordRepository recordRepo;
	private CompanyRepository clients;

	public FlatFeeBillingRecordAPIController(BillingRecordRepository recordRepo, CompanyRepository clients) {
		this.clients = clients;
		this.recordRepo = recordRepo;
		
	}

	@PostMapping("")
	public FlatFeeBillingRecord create(@RequestBody FlatFeeBillingRecord flatFeeRecord, Authentication auth) {
		User user = (User) auth.getPrincipal();
		flatFeeRecord.setClient(clients.findOne(flatFeeRecord.getClient().getId()));
		flatFeeRecord.setCreatedBy(user);
		return recordRepo.save(flatFeeRecord);
	}

}
