package org.medplus.assetmanagementwebapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.medplus.assetmanagementcore.exceptions.AssetException;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.AssetMapping;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.service.AssetService;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.medplus.assetmanagementcore.service.impl.EmployeeServiceImpl;
import org.medplus.assetmanagementcore.utils.AssetType;
import org.medplus.assetmanagementcore.utils.Encryption;
import org.medplus.assetmanagementcore.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssetController {

	@Autowired
	AssetService assetService;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	JdbcTemplate template;

	List<Request> requestList;

	Employee employee;

	List<Request> getAllAssetRequests;

	List<Asset> getAssetsByStatus;

	// getting form
	@RequestMapping(value = "/addAsset", method = RequestMethod.GET)
	public ModelAndView getAssetForm() {
		Asset asset = new Asset();
		ModelAndView mav = new ModelAndView();
		mav.addObject(asset);
		mav.setViewName("Asset");
		return mav;
	}

	@InitBinder
	public final void initBinderUsuariosFormValidator(
			final WebDataBinder binder, final Locale locale) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
				locale);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/addAsset", method = RequestMethod.POST)
	public ModelAndView addAsset(@ModelAttribute("asset") Asset asse,
			BindingResult result) {
		ModelAndView mav = new ModelAndView();

		String rows = null;

		try {

			rows = assetService.addAsset(asse);

			if (rows.equals("Failed to insert")) {
				String msg = "add asset failed";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");
			} else {
				String msg = " Asset added Successfully";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");

			}

		} catch (AuthenticationException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		} catch (AssetException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		} catch (EmployeeException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewAssets", method = RequestMethod.GET)
	public ModelAndView viewAssetForm() {
		ModelAndView mav = new ModelAndView();
		List<Asset> assetlist;
		try {
			assetlist = assetService.getAllAssets();
			mav.addObject("assets", assetlist);
			mav.addObject("viewdetails", "All Assets");
			mav.setViewName("ViewAssets");
			return mav;
		} catch (AssetException e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/viewAssetsByStatus", method = RequestMethod.GET)
	public ModelAndView viewAssetForm(@RequestParam("status") String status) {
		ModelAndView mav = new ModelAndView();
		List<Asset> assetlist;
		try {
			assetlist = assetService.getAssetsByStatus(status);
			mav.addObject("assets", assetlist);
			if (status.equals("A")) {
				mav.addObject("viewdetails", "Available Assets");
			} else if (status.equals("N"))
				mav.addObject("viewdetails", "Allocated Assets");
			mav.setViewName("ViewAssets");

			return mav;
		} catch (AssetException e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/allocateAsset", method = RequestMethod.POST)
			public ModelAndView 
			 uploadFileHandler(@RequestParam("employeeID") String employeeID,
					@RequestParam("assetID") String assetID,
					@RequestParam("assignedBy") String assignedBy,
					@RequestParam("file")  MultipartFile file) {
			 String rows="";
			 String message="";
			 	ModelAndView mav=new ModelAndView();
				try {
					rows= assetService.allocateAsset(employeeID, Long.parseLong(assetID), assignedBy);
				} catch (NumberFormatException | AssetException
						| AuthenticationException | EmployeeException e1) {
					System.out.println(e1);;
				}
				if(rows.equals("success"))
				{
					mav.addObject("message", "allocated asset successfully");

				if (!file.isEmpty()) {
					try {
						final byte[] bytes = file.getBytes();
						

						String rootPath = System.getProperty("catalina.home");
						File dir = new File(rootPath + File.separator + "AssetManagementForms");
						if (!dir.exists())
							dir.mkdirs();						File serverFile = new File(dir.getAbsolutePath()
								+ File.separator + employeeID+"-"+assetID+new Date());
						BufferedOutputStream stream = new BufferedOutputStream(
								new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						
						
						message+= "You successfully uploaded file=" + employeeID+"-"+assetID;
						mav.addObject("message", message);
						mav.setViewName("EDPHome");
						return mav;
					} catch (Exception e) {
						message+= "You failed to upload " + employeeID+"-"+assetID + " => " + e.getMessage();
						mav.addObject("message", message);
						mav.setViewName("EDPHome");
						return mav;
					}
				} else 
					{
					mav.addObject("message", message);
					mav.setViewName("EDPHome");
					return mav;
					}
			}
				mav.addObject("message", message);
				mav.setViewName("EDPHome");
				return mav;
		 }


	@RequestMapping(value = "/allocateAsset", method = RequestMethod.GET)
	public ModelAndView getAllocationForm(
			@RequestParam("assetID") String assetID) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("assetID", assetID);
		mav.setViewName("Allocation");
		return mav;
	}
	 


	@RequestMapping(value = "/deallocateAsset", method = RequestMethod.GET)
	public ModelAndView getDeAllocationForm(
			@RequestParam("assetID") String assetID) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("assetID", assetID);
		mav.setViewName("DeAllocation");
		return mav;
	}

	@RequestMapping(value = "/deallocateAsset", method = RequestMethod.POST)
	public ModelAndView DeallocateAsset(
			@RequestParam("assetID") String assetID,
			@RequestParam("deassignedBy") String deassignedBy)
			throws NumberFormatException, AuthenticationException {
		ModelAndView mav = new ModelAndView();

		String rows = "";
		try {
			try {
				rows = assetService.deAllocateAsset(Long.parseLong(assetID),
						deassignedBy);
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rows.equals("success")) {
				mav.addObject("message", "Deallocated asset successfully");
				mav.setViewName("EDPHome");
				return mav;
			} else {
				mav.addObject("message", "allocation failed");
				mav.setViewName("EDPHome");
				return mav;
			}
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/ViewAssetRequests", method = RequestMethod.GET)
	public ModelAndView viewAssetRequestForm() {
		ModelAndView mav = new ModelAndView();

		try {
			getAllAssetRequests = assetService.getAllAssetRequests();
			mav.addObject("assetRequests", getAllAssetRequests);
			mav.setViewName("ViewAssetRequests");
			return mav;
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/empassets", method = RequestMethod.GET)
	public ModelAndView EmployeeAssets(
			@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List<Asset> assetlist;
		try {

			assetlist = assetService.getAssetsOfEmployee(username);
			mav.addObject("assets", assetlist);
			try {
				employee = employeeService.getEmployee(username);
			} catch (EmployeeException e) {
				e.printStackTrace();
			}
			mav.addObject("emp", employee);
			mav.setViewName("EmployeeAsset");
			return mav;
		} catch (AssetException e) {
			mav.addObject("message", e);
			return mav;

		}

	}

	@RequestMapping(value = "/emprequest", method = RequestMethod.GET)
	public ModelAndView EmployeeRequest(
			@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		try {

			requestList = assetService.getAssetRequests(username);
			mav.addObject("requestList", requestList);

			mav.setViewName("EmployeeRequests");
			return mav;
		} catch (AssetException e) {
			mav.addObject("message", e);
			return mav;

		}

	}

	@RequestMapping(value = "assetrequest", method = RequestMethod.GET)
	public ModelAndView postNewTypeAssetRequestForm(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("NewRequest");

		return mav;

	}

	@RequestMapping(value = "assetrequest", method = RequestMethod.POST)
	public ModelAndView postNewTypeAssetRequest(
			@RequestParam("userName") String userName,
			@RequestParam("assetType") String assetType,
			@RequestParam("assetName") String assetName,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		String msg = "";
		try {
			msg = assetService.saveNewAssetTypeRequest(userName, assetType,
					assetName);
			mav.addObject("message", msg);
		} catch (AssetException e) {
			mav.addObject("message", e);

		} catch (AuthenticationException e1) {
			mav.addObject("message", e1);

		}
		mav.setViewName("EmployeeHome");

		return mav;

	}

	@RequestMapping(value = "postAssetRequests", method = RequestMethod.GET)
	public ModelAndView postAssetRequestForm(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Request1");
		return mav;

	}

	@RequestMapping(value = "postAssetRequests", method = RequestMethod.POST)
	public ModelAndView postAssetRequest(
			@RequestParam("EmployeeId") String requestedBy,
			@RequestParam("type") String type, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		try {
			String msg;
			try {
				msg = assetService.saveAssetRequest(AssetType.valueOf(type),
						requestedBy);
				mav.addObject("message", msg);
			} catch (AssetException e) {
				mav.addObject("message", e.getMessage() + ":" + e);
				e.printStackTrace();
			}

			mav.setViewName("EmployeeHome");
			return mav;
		} catch (AuthenticationException e) {
			mav.addObject("message", e);
			// e.printStackTrace();
		}

		return mav;

	}

	@RequestMapping(value = "/UpdateAsset", method = RequestMethod.GET)
	public ModelAndView updateAssetForm(@RequestParam("code") long assetID,
			HttpServletRequest request, HttpServletResponse response) {
		Asset asset = new Asset();
		ModelAndView mav = new ModelAndView();
		try {
			asset = assetService.getAsset(assetID);
			mav.addObject(asset);
			System.out.println("Iam for Asset");
			mav.addObject("viewdetails", "Update Asset Form");
			mav.setViewName("UpdateAsset");
			return mav;
		} catch (AssetException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("UpdateAsset");
			return mav;
		}

	}

	@RequestMapping(value = "/UpdateAsset", method = RequestMethod.POST)
	public ModelAndView updateAsset(@ModelAttribute("asset") Asset asset,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		String rows = null;
		try {

			asset.setModifiedBy(request.getSession(false)
					.getAttribute("username").toString());
			rows = assetService.updateAsset(asset);

			if (rows.equals("Failed to Update")) {

				String msg = "record not updated";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");
			} else {
				String msg = "record  updated";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");
			}
		} catch (AuthenticationException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		} catch (AssetException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/asset-mapping-log", method = RequestMethod.GET)
	public ModelAndView viewAssetMappingLog() {
		ModelAndView mav = new ModelAndView();
		List<AssetMapping> assetMappingLog;
		try {
			assetMappingLog = assetService.getAssetMappingLog();
			mav.addObject("assetMappingLog", assetMappingLog);
			mav.setViewName("ViewLog");
			return mav;
		} catch (AssetException e) {
			mav.addObject("message", e);
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "assetInfo", method = RequestMethod.GET)
	public ModelAndView viewAssetInfo(@RequestParam("assetId") long assetId) {
		ModelAndView mav = new ModelAndView();
		Asset asset;
		try {
			asset = assetService.getAsset(assetId);

			mav.addObject("asset", asset);
		} catch (AssetException e) {
			mav.addObject("message", e);
		}
		mav.setViewName("AssetInfo");

		return mav;

	}
	
	
	@RequestMapping(value = "/removeAssetRequest", method = RequestMethod.GET)
 	public ModelAndView removeRoleForm() {
 		Request request=new Request();
 		ModelAndView mav = new ModelAndView();
 		mav.addObject(request);
 		mav.addObject("viewdetails", "remove Asset Form");
 		mav.setViewName("RemoveAssetRequest");
 		
 		return mav;
 	}

 	@RequestMapping(value = "/removeAssetRequest", method = RequestMethod.POST)
 	public ModelAndView RemoveRoleForm(
 			@ModelAttribute("request") Request request)
 			 {
 		ModelAndView mav = new ModelAndView();

 		String rows = null;
 		
 			try {
 			
				rows =assetService.removeAssetRequest(request);
				
				if (rows.equals("FAILURE")) {
     				String msg = "role not removed";
     				mav.addObject("message", msg);
     				mav.setViewName("EDPHome");

     			} else {
     				String msg = rows;
     				mav.addObject("message", msg);
     				mav.setViewName("EDPHome");

     			}
			} catch (AssetException e) {
			mav.addObject("message",e.getMessage());
			mav.setViewName("EDPHome");
			}
 			
 		

 		return mav;
 		}

}
