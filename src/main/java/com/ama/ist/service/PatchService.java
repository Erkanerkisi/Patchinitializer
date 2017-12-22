package com.ama.ist.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.ama.ist.model.Patch;

@Service
public class PatchService {

	public String create(Patch patch) {

		String ConstantPath = patch.getDestination();
		
		
		File testFile = new File("");
	    String currentPath = testFile.getAbsolutePath();
	    System.out.println("current path is: " + currentPath);
		
		
		System.out.println("ConstantPath => " + ConstantPath);
//		if (!(isValidPath(ConstantPath))) {
//			return "invalid Path";
//		}

		// System.out.println("Valid mi " + isValidPath(ConstantPath));

		String foldername = patch.getWinNum() + " - " + patch.getWinName();
		System.out.println(ConstantPath + foldername);

		File files = new File(ConstantPath + foldername);
		if (files.exists()) {
			return "The Folder is already created in that path";
		}

		File files1 = new File(ConstantPath + foldername + "\\Patch");
		File files2 = new File(ConstantPath + foldername + "\\Backup");
		File files3 = new File(ConstantPath + foldername + "\\Backup\\UAT");
		File files4 = new File(ConstantPath + foldername + "\\Backup\\PROD");

		if (!files.exists()) {
			if (files.mkdirs()) {
				files1.mkdir();
				files2.mkdir();
				files3.mkdir();
				files4.mkdir();

				createReadme(ConstantPath + foldername, patch);

				if (patch.isChecked()) {

					System.out.println("patch.getDestination => " + patch.getDestination());
					System.out.println("patch.getDetail => " + patch.getDetail());
					System.out.println("patch.getSvnPath => " + patch.getSvnPath());
					System.out.println("patch.getWinName => " + patch.getWinName());
					System.out.println("patch.getWinNum => " + patch.getWinNum());
					
					System.out.println("patch.getUserName => " + patch.getUser().getUserName());
					System.out.println("patch.getPassword => " + patch.getUser().getPassword());
					ImportSvn(patch);

				}

				System.out.println("Multiple directories are created!");
				return "Success";
			} else {
				System.out.println("Failed to create multiple directories!");
				return "Unknwon error";
			}
		} else {
			return "File name is already exists";
		}

	}

	public static boolean isValidPath(String path) {
		System.out.println("path => " + path);
		File f = new File(path);

		if (f.isDirectory()) {
			System.out.println("true => ");
			return true;
		} else {
			System.out.println("false => ");
			return false;
		}

	}

	public void createReadme(String path, Patch patch) {

		try {
			
			InputStream is = getClass().getResourceAsStream("/Readme.txt");
			System.out.println("is => " + is);
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
		    //String line;
		    System.out.println("is => " + is);
		    
			//ClassLoader classLoader = getClass().getClassLoader();
			//File file = new File(classLoader.getResource("Readme.txt").getFile());
			//String path1 = new File(".").getCanonicalPath();
			//String url = this.getClass().getResource("").getPath();
			 
			 //System.out.println("url => " + url);
			// System.out.println("getCanonicalPath => " + path1);
			 //File file = new File("/Readme.txt");
			 //System.out.println("classLoader getAbsolutePath => " + file.getAbsolutePath());

			//FileReader reader = new FileReader(file);
			//BufferedReader bufferedReader = new BufferedReader(reader);

			String line;
			PrintWriter writer = new PrintWriter(path + "\\Readme.txt", "UTF-8");
			System.out.println("path=> " + path + "\\Readme.txt");
			while ((line = br.readLine()) != null) {
				System.out.println("line=> " + line);
				line = line.replace("#Winnumber", Integer.toString(patch.getWinNum()));
				line = line.replace("#NameSurname", " ");
				line = line.replace("#Type", "Package");
				line = line.replace("#detail", patch.getDetail());
				

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String date = sdf.format(new Date());
				line = line.replace("#Date", date);

				line = line.replace("#Desc", patch.getWinName());

				writer.println(line);

				System.out.println(line);
			}
			br.close();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ImportSvn(Patch patch) {

		String name = patch.getUser().getUserName();
		String password = patch.getUser().getPassword();
		String filename = patch.getWinNum() + " - " + patch.getWinName();

		String url = patch.getSvnPath() + "/" + filename;

		ISVNAuthenticationManager authManager = new BasicAuthenticationManager(name, password);

		SVNCommitClient commitClient = new SVNCommitClient(authManager, SVNWCUtil.createDefaultOptions(true));
		File f = new File(patch.getDestination() + filename);
		try {
			String logMessage = filename;
			commitClient.doImport(f, // File/Directory to be imported
					SVNURL.parseURIEncoded(url), // location within svn
					logMessage, // svn comment
					new SVNProperties(), // svn properties
					true, // use global ignores
					false, // ignore unknown node types
					SVNDepth.INFINITY);
			// SVNClientManager cm =
			// SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true),authManager);
			//
			// SVNUpdateClient uc = cm.getUpdateClient();
			// long[] l = uc.doUpdate(new File[]{dstPath},
			// SVNRevision.HEAD,SVNDepth.INFINITY, true,true);
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
