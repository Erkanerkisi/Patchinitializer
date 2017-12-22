package com.ama.ist.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc2.ISvnObjectReceiver;
import org.tmatesoft.svn.core.wc2.SvnList;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import com.ama.ist.model.Folder;
import com.ama.ist.model.Patch;
import com.ama.ist.model.User;

@Service
public class SvnService {

	public List<Folder> apiVersions;

	public List<Folder> getFolders(User user) throws SVNException {



		String url = user.getSvnPath();
		String name = user.getUserName();
		String password = user.getPassword();

		System.out.println("user => " + url);
		System.out.println("user => " + name);

		System.out.println("user => " + password);
		//
		final SVNURL url1 = SVNURL.parseURIDecoded(url);
		Folder folder = null;
		apiVersions = new ArrayList<Folder>();
		SVNRevision revision = SVNRevision.HEAD;
		SvnOperationFactory operationFactory = new SvnOperationFactory();
		operationFactory.setAuthenticationManager(new BasicAuthenticationManager(name, password));

		final SvnList list = operationFactory.createList();
		list.setDepth(SVNDepth.IMMEDIATES);
		list.setRevision(revision);
		list.addTarget(SvnTarget.fromURL(url1, revision));
		System.out.println("Burada1");
		list.setReceiver(new ISvnObjectReceiver<SVNDirEntry>() {
			public void receive(SvnTarget target, SVNDirEntry object) throws SVNException {
				final String name = object.getRelativePath();

				try {
					if (name != null && !(name.equals("") &&!(name.equals(" ")))) {
					System.out.println("name => " + name);
					Folder f = new Folder();
					f.setFolderName(name);
					apiVersions.add(f);
					}
				} catch (Exception e1) {
					System.out.println("name ex => " + name);
				}

			}
		});

		list.run();

		return apiVersions;

	}
	
	
	public List<Folder> getlocalFolders(String localPath)  {
		System.out.println("burda!!");
		List<Folder> fol = new ArrayList<Folder>();
		File folder = new File(localPath);
		File[] listOfFiles = folder.listFiles();
		
		    for (int i = 0; i < listOfFiles.length; i++) {
		   
		    	Folder f = new Folder ();
		    	f.setFolderName(listOfFiles[i].getName());
		    	fol.add(f);
		        System.out.println(listOfFiles[i].getName());
		      
		    }
		
		return fol;
	}
	

	
	
	
	

}
