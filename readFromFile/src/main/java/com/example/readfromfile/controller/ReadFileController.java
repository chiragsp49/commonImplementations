package com.example.readfromfile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.readfromfile.service.ReadFileService;

@RestController
@RequestMapping("/v1/readfile/")
public class ReadFileController {
	
	@Autowired
	ReadFileService readFileService;

	@RequestMapping("")
	public ResponseEntity<List<String>> getFilesWithNameAndVersion(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "version", required = true) String version){
		if(name=="") {
			// throw exception
		}
		
		if(version=="") {
			//throw exception
		}
		
		List<String> filesWithNameAndVersion = readFileService.getListOfNameAndVersion(name, version);
		return new ResponseEntity<List<String>>(filesWithNameAndVersion, HttpStatus.OK);
		
		
	}
}
