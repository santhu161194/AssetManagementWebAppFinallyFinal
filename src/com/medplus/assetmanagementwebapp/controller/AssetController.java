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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medplus.assetmanagementcore.exceptions.AssetException;
import com.medplus.assetmanagementcore.exceptions.AuthenticationException;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.service.AssetService;
import com.medplus.assetmanagementcore.utils.AssetStatus;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;
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
		@Autowired
		List<Asset> getAssetsByStatus;
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
			public ModelAndView addAsset(@ModelAttribute("asset") Asset asse, BindingResult result)
			{
				ModelAndView mav=new ModelAndView();
				
				String rows;
				try {
					
					rows = assetServiceImpl.addAsset(asse,"10", new Date());
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
		 
		 
		 @RequestMapping(value="/viewAssetsByStatus",method=RequestMethod.GET)
			public ModelAndView viewAssetForm(@RequestParam("status") String status)
			{
				ModelAndView mav=new ModelAndView();
				
			try {
				assetlist=assetServiceImpl.getAssetsByStatus(status);
				mav.addObject("assets", assetlist);
				mav.setViewName("ViewAssets");
		        return mav;
			} catch (AssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mav;
			}
		 
		 @RequestMapping(value="/allocateAsset",method=RequestMethod.GET)
			public ModelAndView getAllocationForm(){
				
				ModelAndView mav=new ModelAndView();
				
				mav.setViewName("Allocation");
				return mav;
		}
		 
		 
		 @RequestMapping(value="/allocateAsset",method=RequestMethod.POST)
			public ModelAndView allocateAsset(@RequestParam("employeeID") String employeeId,
					@RequestParam("assetID") String assetID,
					@RequestParam("assignedBy") String assignedBy) throws NumberFormatException, AuthenticationException
			{
				ModelAndView mav=new ModelAndView();
				System.out.println("I reached");
				boolean rows;
				try {
					rows = assetServiceImpl.allocateAsset(employeeId, Integer.parseInt(assetID), assignedBy, new Date());
					if(rows==true)
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
		 
		 @RequestMapping(value="/deallocateAsset",method=RequestMethod.GET)
			public ModelAndView getDeAllocationForm(){
				
				ModelAndView mav=new ModelAndView();
				
				mav.setViewName("DeAllocation");
				return mav;
		}
		 
		 
		 @RequestMapping(value="/deallocateAsset",method=RequestMethod.POST)
			public ModelAndView DeallocateAsset(
					@RequestParam("assetID") String assetID,
					@RequestParam("deassignedBy") String deassignedBy) throws NumberFormatException, AuthenticationException
			{
				ModelAndView mav=new ModelAndView();
				System.out.println("I reached");
				boolean rows;
				try {
					rows = assetServiceImpl.deAllocateAsset(Integer.parseInt(assetID), deassignedBy, new Date());
					if(rows==true)
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

		
		 
		 
		 @RequestMapping(value="postAssetRequests",method=RequestMethod.GET)
		 public ModelAndView postAssetRequestForm(@RequestParam("type") String type) 
			{
				ModelAndView mav=new ModelAndView();
				
			
			try {
				assetServiceImpl.postAssetRequest(AssetTypeEnum.valueOf(type),"10",new Date());
				
				mav.setViewName("PostAssetRequests1");
				return mav;
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return mav;
			
			}
		
	}


