package org.medplus.assetmanagementwebapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {
	JdbcTemplate template;
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("employeeID") String employeeID,
			@RequestParam("assetID") String assetID,
			@RequestParam("assignedBy") String assignedBy,
			@RequestParam("file")  MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				final byte[] bytes = file.getBytes();
				System.out.println("something");
				final InputStream inputStream=file.getInputStream();
				
				template.update("INSERT INTO tbl_forms values(?,?)",new PreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						
						ps.setBlob(1, inputStream);
						
						
					}
				});
				System.out.println("something");
				
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + employeeID);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				
				return "You successfully uploaded file=" + employeeID;
			} catch (Exception e) {
				return "You failed to upload " + employeeID + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + employeeID
					+ " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				
				message = message + "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}