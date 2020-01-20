package com.irshad.apps.filemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.irshad.apps.filemanagement.service.FileService;
import com.irshad.apps.filemanagement.vo.User;

@RestController
public class FileController {

	@Autowired
	FileService fileService;
	/*
	 * @RequestMapping(value = "/users", method = RequestMethod.POST,consumes =
	 * MediaType.MULTIPART_FORM_DATA_VALUE) public String addUser(@RequestPart
	 * MultipartFile file) { System.out.println("File........"+file.getResource());
	 * return "User Added!"; }
	 */

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addUser(@ModelAttribute User user) {

		if (user.getPhoto() != null) {
			System.out.println("Received File -> " + user.getPhoto().getOriginalFilename());
			if (user.getPhoto().getSize() > 65535) {
				System.out.println("Blob cannot support more than 65535 bytes.. Please upload a small sized file");
				return "File is Too big!";
			}
		}
		fileService.addUser(user);

		return "Done!";
	}
}
