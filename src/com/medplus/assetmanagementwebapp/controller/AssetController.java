package com.medplus.assetmanagementwebapp.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.medplus.assetmanagementcore.exceptions.AssetException;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.model.NewTypeRequest;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.service.AssetService;
import com.medplus.assetmanagementcore.utils.AssetValidation;
@Controller
public class AssetController {

	
		@Autowired	
		AssetService assetServiceImpl;
		@Autowired
		Asset asset;
		@Autowired
		AssetValidation validation;
		@Autowired
		List<Asset> assetlist;
		
		@Autowired
		Employee employee;
		@Autowired
		List<Request> getAllAssetRequests;
		
		//view employees
		/*@RequestMapping("/viewAssets")
		public ModelAndView viewEmployees(@ModelAttribute("asset") Asset ass,BindingResult result){
			ModelAndView mav=new ModelAndView();
			List<Asset> asset=assetServiceImpl.getAllAssets();
			mav.addObject("as", asset);
			mav.setViewName("ViewAssets");
			return mav;
		}*/
		
		//getting form
		@RequestMapping(value="/addAsset",method=RequestMethod.GET)
			public ModelAndView getAssetForm(){
				System.out.println("i got called");
				ModelAndView mav=new ModelAndView();
				mav.addObject(asset);
				mav.setViewName("Asset");
				return mav;
		}

		 @InitBinder
		 public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
		     final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
		     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
		 }

		
/*
		 @InitBinder
		 public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
		     final SimpleDateFormat public class EmployeeController {dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
		     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
		 }

		*/
		 @RequestMapping(value="/addAsset",method=RequestMethod.POST)
			public ModelAndView addAsset(@ModelAttribute("asset") Asset asse,BindingResult result)
			{
				ModelAndView mav=new ModelAndView();
				
				String rows;
				try {
					rows = assetServiceImpl.addAsset(asse,"13", new Date());
					if(rows.equals(0))
					{
						
						return mav;
					
					}
					else
					{
					return new ModelAndView("redirect:home");
				}
				} catch (AssetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return mav;
			}
		 @RequestMapping(value="/viewAssets",method=RequestMethod.GET)
			public ModelAndView viewAssetForm()
			{
				ModelAndView mav=new ModelAndView();
				
			try {
				assetlist=assetServiceImpl.getAllAssets();
				mav.addObject("assets", assetlist);
				mav.setViewName("ViewAssets");
		        return mav;
			} catch (AssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mav;
			}
		 @RequestMapping(value="/ViewAssetRequests",method=RequestMethod.GET)
			public ModelAndView viewAssetRequestForm()
			{
				ModelAndView mav=new ModelAndView();
				
			try {
				getAllAssetRequests=assetServiceImpl.getAllAssetRequests();
				mav.addObject("assetRequests",getAllAssetRequests);
				mav.setViewName("ViewAssetRequests");
		        return mav;
			} catch (AssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mav;
			}
		/* @RequestMapping(value="/postAssetRequest",method=RequestMethod.GET)
			public ModelAndView postAssetRequestForm(@ModelAttribute("asset") Asset asset)
			{
				ModelAndView mav=new ModelAndView();
				
			try {
				postAssetRequest=assetServiceImpl.postAssetRequest(asset.getAssetType(),"u",new Date());
				mav.addObject("assetRequests",getAllAssetRequests);
				mav.setViewName("ViewAssetRequests");
		        return mav;
			} catch (AssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mav;
			}*/
	}

