package com.ama.ist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ama.ist.model.Folder;
import com.ama.ist.model.User;
import com.ama.ist.service.SvnService;



@RestController
public class SvnController {
	
	
	@Autowired
	private SvnService svnservice;
	
	@RequestMapping(value = "/svnfolders", method = RequestMethod.GET)
	public ResponseEntity<List<Folder>> getFolders(User user) {
		
	

		List<Folder> folders = new ArrayList<Folder>();
		//List<Folder> folder = new ArrayList<Folder>();
		try {
			folders = svnservice.getFolders(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int i=0;i<folders.size();i++)
//		{
//			Folder f = new Folder();
//			f.setFolderName(folders.get(i));
//			folder.add(f);
//		}
		
		if (folders.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//System.out.println("if disinda success" );
			return new ResponseEntity<List<Folder>>(folders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/localfolders", method = RequestMethod.GET)
	public ResponseEntity<List<Folder>> getlocalFolders(User user) {
		
		System.out.println("localPath => " + user.getLocalPath());

		List<Folder> fold = new ArrayList<Folder>();
		try {
			fold = svnservice.getlocalFolders(user.getLocalPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (fold.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//System.out.println("if disinda success" );
			return new ResponseEntity<List<Folder>>(fold, HttpStatus.OK);
	}
	

}
