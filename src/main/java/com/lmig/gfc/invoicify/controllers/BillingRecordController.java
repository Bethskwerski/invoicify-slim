package com.lmig.gfc.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	private CompanyRepository companies;
	private BillingRecordRepository records;

	public BillingRecordController(CompanyRepository companies, BillingRecordRepository records) {
		this.companies = companies;
		this.records = records;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");

		// Get all the billing records and add them to the model and view with the key
		// "records"
		mv.addObject("records", records.findAll());

		// Get all the companies and add them to the model and view with the key
		// "companies"
		mv.addObject("companies", companies.findAll());
		return mv;
	}

}
