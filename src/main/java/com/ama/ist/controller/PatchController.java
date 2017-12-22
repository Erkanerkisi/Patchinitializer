package com.ama.ist.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ama.ist.model.CustomErrorType;
import com.ama.ist.model.Patch;
import com.ama.ist.service.PatchService;



@RestController
public class PatchController {
	
	@Autowired
	private PatchService patchService;
	

	@RequestMapping(value = "/mk", method = RequestMethod.POST)
	public ResponseEntity<?> createFolder(@RequestBody Patch patch) {
		

		System.out.println("patch ddest: => " + patch.getDestination());
		String iscreatedstatus = patchService.create(patch);
		System.out.println("iscreatedstatus" + iscreatedstatus);
		if (!(iscreatedstatus.equals("Success"))) {
			System.out.println("if success" );
			return new ResponseEntity<Object>(new CustomErrorType("ER",iscreatedstatus), HttpStatus.NOT_FOUND);
		}
		System.out.println("if disinda success" );
			return new ResponseEntity<Object>(new CustomErrorType("OK",iscreatedstatus), HttpStatus.CREATED);
	}
	
//	
	@RequestMapping("/resource")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
	  }

}