package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.RateBasedBillingRecordRepository;

@Controller
@RequestMapping("/billing-records/rate-baseds")
public class RateBasedBillingRecordController {

	private CompanyRepository clients;
	private RateBasedBillingRecordRepository records;

	public RateBasedBillingRecordController(CompanyRepository clients, RateBasedBillingRecordRepository records) {

		this.clients = clients;
		this.records = records;

	}

	@PostMapping("")
	public ModelAndView create(RateBasedBillingRecord record, long clientId, Authentication auth) {
		// Get the user from the auth.getPrincipal() method
		User user = (User) auth.getPrincipal();
		// Find the client using the client id
		Company client = clients.findOne(clientId);
		// Set the client on the record
		record.setClient(client);
		// Set the user on the record for the created by property
		record.setCreatedBy(user);
		// Save the record
		records.save(record);
		return new ModelAndView("redirect:/billing-records");
	}

}
