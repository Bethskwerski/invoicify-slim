package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.FlatFeeBillingRecordRepository;


@Controller
@RequestMapping("/billing-records/flat-fees")
public class FlatFeeBillingRecordController {
	private CompanyRepository clients;
	private FlatFeeBillingRecordRepository records;

	public FlatFeeBillingRecordController(FlatFeeBillingRecordRepository records, CompanyRepository clients) {
		this.records = records;
		this.clients = clients;
	}

	@PostMapping("")
	public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {
		// Get the user from the auth.getPrincipal() method
		User user = (User) auth.getPrincipal();
		// Find the client using the client id
		Company client = clients.findOne(clientId);
		// Set the client on the record
		record.setClient(client);
		// Set the user on the record for he created by property
		record.setCreatedBy(user);
		// Save the record
		records.save(record);
		return new ModelAndView("redirect:/billing-records");
	}

}
