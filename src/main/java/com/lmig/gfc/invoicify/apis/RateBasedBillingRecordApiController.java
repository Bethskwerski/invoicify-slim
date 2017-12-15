package com.lmig.gfc.invoicify.apis;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.RateBasedBillingRecordRepository;

@RestController
@RequestMapping("/api/ratefees")
public class RateBasedBillingRecordApiController {

	private CompanyRepository clients;
	private RateBasedBillingRecordRepository rateRecordRepo;

	public RateBasedBillingRecordApiController(RateBasedBillingRecordRepository rateRecordRepo, CompanyRepository clients) {
		
		this.clients = clients;
		this.rateRecordRepo = rateRecordRepo;
	}

	@PostMapping("")
	public RateBasedBillingRecord create(@RequestBody RateBasedBillingRecord rateRecord, Authentication auth) {
		User user = (User) auth.getPrincipal();
		// Find the client using the client id
		rateRecord.setClient(clients.findOne(rateRecord.getClient().getId()));

		// Set the user on the record for the created by property
		rateRecord.setCreatedBy(user);
		
		return rateRecordRepo.save(rateRecord);
	}

}
