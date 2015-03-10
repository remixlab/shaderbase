package Shader.tool;


import java.util.Arrays;
import java.util.Iterator;
import processing.app.Editor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import static javax.swing.GroupLayout.Alignment.*;


import processing.app.Base;
import processing.app.Editor;
import processing.app.Sketch;
import processing.app.SketchCode;

class Upload{
	
	private static final String String = null;
	String pdecode;
	String imgpath = null;
	String imgpathfinal;
	
	
	private static Editor editor = null;
	
		
	//public Upload(String shaderse, Editor editor, String shadersename)
	

	public Upload(final Editor editor, final Path pathos, JPanel card2, String[] listadata, boolean updatecheck) throws IOException, TransportException, GitAPIException
	{
	
		//String path = shaderse;
		final String[] listadata2 = listadata;
		this.editor = editor;
		//Path path = pathos;
		final String repo = pathos.toString()+"/Data"; 
		final File dir = new File(repo);
		final String name = "Shadertool";
		final String password = "1ergosum";
		String url = "https://github.com/Shadertool/shaderdb.git";
		
		
		
		
		

		
		//GUI
		
		//Frame frame = new JFrame();
		JPanel panel = new JPanel();
		//frame.setTitle("Shader Upload");	
        //frame.setLocation(500, 200);
        JLabel namel = new JLabel("Shader name:");
        JLabel tagl = new JLabel("Tags:");
        JLabel desl = new JLabel("Description:");
        JLabel authorl = new JLabel("Author:");
        JLabel emaill = new JLabel("Email:");
        JLabel imagel = new JLabel ("Adding an Image:");
        final JTextField namet = new JTextField();
        namet.setText("Name your shader");
        final JTextField tagt = new JTextField();
        tagt.setText("Write some tags about your shader");
        final JTextArea dest = new JTextArea();
        dest.setText("Write a small description");
        dest.setColumns(20);
        dest.setLineWrap(true);
        dest.setRows(5);
        dest.setWrapStyleWord(true);
        dest.setEditable(true);
        
        
        //dest.setSize(250 , 100);
        final JTextField authort = new JTextField();
        authort.setText("add your name or nickname");
        final JTextField emailt = new JTextField();
        emailt.setText("add your email (optional)");
        final JButton commitb = new JButton("Save");
        JButton pushb = new JButton("Remote rep");
        final JButton imgb = new JButton("Load Image");
        final JCheckBox noimg = new JCheckBox("Default Image");
        final JCheckBox noremote = new JCheckBox("Save in local machine");
        
        //Checkbox borders
        
        noimg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        //Groups
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        			//.addComponent(namel)
        			//.addComponent(namet)
        		      .addGroup(layout.createParallelGroup(LEADING)
        		      
        		     .addGroup(layout.createSequentialGroup()	
        		    		 .addComponent(namel)
        	        		 .addComponent(namet))
        			 .addGroup(layout.createSequentialGroup()	
        			//.addGroup(layout.createParallelGroup(LEADING)
        				
            			.addComponent(tagl)
            			.addComponent(tagt))
            		 .addGroup(layout.createSequentialGroup()
            				 
            		//.addGroup(layout.createParallelGroup(LEADING)	
            			
            			.addComponent(desl)
            			.addComponent(dest))
            		 .addGroup(layout.createSequentialGroup()
            		//.addGroup(layout.createParallelGroup(LEADING)	
            			.addComponent(authorl)
            			.addComponent(authort))
            		 .addGroup(layout.createSequentialGroup()	
            		//.addGroup(layout.createParallelGroup(LEADING)	
            			.addComponent(emaill)
            			.addComponent(emailt))
            			
            		 .addGroup(layout.createSequentialGroup()	
            		//.addGroup(layout.createParallelGroup(LEADING)	
            			.addComponent(imagel)
            			.addComponent(imgb)
        				.addComponent(noimg)
        				//.addComponent(noremote)
        				)		
        				
        				 .addGroup(layout.createSequentialGroup()	
        		    //.addGroup(layout.createParallelGroup(LEADING)	
        		    	.addComponent(commitb))
        		    	//.addComponent(pushb))			
        				
        ));
        
        
        layout.setVerticalGroup(
        		layout.createSequentialGroup()	
        		
        				
        				
        				
        				
        				
        		.addGroup(layout.createParallelGroup(BASELINE)
        				.addComponent(namel)
        				.addComponent(namet))
                .addGroup(layout.createParallelGroup(BASELINE)
                		.addComponent(tagl)
                		.addComponent(tagt))
                
                .addGroup(layout.createParallelGroup(BASELINE)
                		.addComponent(desl)
                		.addComponent(dest))
                
                .addGroup(layout.createParallelGroup(BASELINE)
                		.addComponent(authorl)
                		.addComponent(authort))
                		
                .addGroup(layout.createParallelGroup(BASELINE)
                		.addComponent(emaill)
                		.addComponent(emailt))
                		
                .addGroup(layout.createParallelGroup(BASELINE)
                		.addComponent(imagel)
                		.addComponent(noimg)
                		.addComponent(imgb)
                		//.addComponent(noremote)
                		)
                		
                .addGroup(layout.createParallelGroup(BASELINE)
                		.addComponent(commitb))
        		    	//.addComponent(pushb))
                		
        );
        panel.setPreferredSize(new Dimension(800, 480));
        
        card2.removeAll();
        card2.add(panel);
        
        //frame.pack();
        //frame.setSize(500,500);
        //frame.setVisible(true);
        
      
        card2.setVisible(true);
        
        
        
        noimg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	
        if (noimg.isSelected()==true) {
            imgb.setEnabled(false);
            
            JOptionPane.showMessageDialog(null,
				     "Using default image"
				 , //Mensaje
				 	    "Default Image", //Título
				 	    JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
            
            imgpath = pathos.toString();
            
            if (imgpath.contains("Shaderepo"))
	        {
	          String div = imgpath;
	          String parts[] = div.split("Shaderepo");
	      	  imgpathfinal = parts[0]+"/img.jpg";
	      	  
	      	  
	      	   
	      	}
            
        }else{
        	if (noimg.isSelected()==false) {
                imgb.setEnabled(true);
        	}
                
        }
       
            }
        });
        
        imgb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	
            	JFileChooser c = new JFileChooser();
    			//Setting Up The Filter
    			FileFilter imageFilter = new FileNameExtensionFilter(
    			    "JPG Files", "jpg");

    			//Attaching Filter to JFileChooser object
    			c.setFileFilter(imageFilter);

    			//Displaying Filechooser
    			int rVal = c.showOpenDialog(new JPanel());
    			
    			File img = c.getSelectedFile();
    			imgpathfinal = img.getPath(); 
           
            	
            } 
           
        });
       
        
    
        commitb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
           	
            	 String a1 = namet.getText();
                 String b1 = tagt.getText();
                 String c1 = dest.getText();
                 String d1 = authort.getText();
                 String folderpath = null;
                 String[] allnames1 = null;/////
                 boolean checkname = false;
                 ///////
                 
                 //Search all the names
                 try {
					Search search = new Search(null, null, editor, pathos, null, "shader");
					
					allnames1 = search.searchnames; 
					
                 } catch (ParseException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}//Search all the names
                 
                 final String[] allnames = allnames1;        ///////
                 
            	 if(a1.contains("Name your shader")||b1.contains("Write some tags about your shader")||c1.contains("Write a small description")
                 		||d1.contains("add your name or nickname")	
                 		){
            		 JOptionPane.showMessageDialog(null,
            				     "Please fill the form, \n" +
            				     "it will take few minutes"
            				 , //Mensaje
            				 	    "Fill the form", //Title
            				 	    JOptionPane.ERROR_MESSAGE); //Message
                 }else{
                
                	 
                	 int selectedOption1 = JOptionPane.showConfirmDialog(null, 
                             "Do you want to share the shader with other users??", 
                             "Choose", 
                             JOptionPane.YES_NO_OPTION); 
             		if (selectedOption1 == JOptionPane.YES_OPTION) {
                	 
                	 /////
                	 //Name Check
                	 


                if (Arrays.asList(allnames).contains(a1)){
                
                		 
                 int selectedOptionB = JOptionPane.showConfirmDialog(null,  
                 "The shader already exists. Do you want to edit it??", 
                 "Name Conflict", 
                 JOptionPane.YES_NO_OPTION); 
         		if (selectedOptionB == JOptionPane.YES_OPTION) {
         		
         			
         		//Clean old values Repo 
         			
         		FileRepositoryBuilder builder = new FileRepositoryBuilder();
         	    Repository repository;
         		try {
         				repository = builder.setGitDir(new File(repo+"/.git"))
         				        .readEnvironment() // scan environment GIT_* variables
         				        .findGitDir() // scan up the file system tree
         				        .build();
         			
         	    
         		File dirdata = new File(repo+"/ShaderData/"+a1+"/Code");
         		String[] dirdatainfo = dirdata.list();
         		
         	    Git git = new Git(repository);
         	    
         	   /*
         	    try {
					git.rm().addFilepattern("ShaderData/"+a1+"/"+ a1+".pde")
					.call();
				} catch (NoFilepatternException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (GitAPIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         	    
         	    */
         	   for (int x=0;x<dirdatainfo.length;x++){
				 	
         		
					
         		   
         		   
         		   try {
						git.rm()
						.addFilepattern("ShaderData/"+a1+"/Code/"+ dirdatainfo[x])
						.call();
					} catch (NoFilepatternException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (GitAPIException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
         	   }
         	   
         	CommitCommand commit = git.commit();
      		commit.setCommitter(authort.getText(), emailt.getText())
      		.setMessage(dest.getText());
      		try {
				commit.call();
			} catch (NoHeadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoMessageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnmergedPathsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConcurrentRefUpdateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WrongRepositoryStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		
         	   
         	   
         	   
         	   
         	   
         	   	repository.close();
         		
         		} catch (IOException e2) {
        			// TODO Auto-generated catch block
        			e2.printStackTrace();
        		}
         		
         		checkname = true; 
         			
         		}else{
         			 
         			 checkname =  false;
         			 JOptionPane.showMessageDialog(null,
        				     "Change the name of the shader and try again"
        				 , //Mensaje
        				 	    "Change Name", //Title
        				 	    JOptionPane.ERROR_MESSAGE); //Message
         			
         		}
                		 
                		 
                	 }else{
                		 checkname = true;
                	 }


                	 //////
        if (checkname == true){
                	 
                	 //PDE CODE
                	 
             		try {
            			editor.getBase();
            			editor.getBase();
            			//System.out.println(pdecode);
            			pdecode = editor.getText();
            			
            			Sketch sketch = editor.getSketch();
            			File sketchFolder = sketch.getFolder();
            			
            			File sketchbookFolder = Base.getSketchbookFolder();
            			folderpath = sketchFolder.toString();
            			
            			
            			System.out.println(folderpath);
            			
            			/*
            			//
            			int sketch11 = sketch.getCodeCount(); //Numero Tabs
            			sketch.handleNextCode();
            			String sketch111 = editor.getText();
            			
            			System.out.println("Cuenta algo" + sketch11 + sketch111);
            			//
                 	
                 		*/
            			
             		}catch(Exception excp){
            		}
            	
       //PDE CHECK
               		
        if(pdecode.contains("PShader")){
		
		// credentials
		CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name, password);
		
		
			
				
		//FOLDERS
		
		File theDir = new File(repo+"/ShaderData/"+a1);
		 System.out.println(repo+"/ShaderData/"+a1);
		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
		    //System.out.println("creating directory: " + directoryName);
		    boolean result = theDir.mkdir();  

		     if(result) {    
		       System.out.println("DIR created");  
		     }
		  }
		  
		  File theDir2 = new File(repo+"/ShaderData/"+a1+"/Code");
			 System.out.println(repo+"/ShaderData/"+a1+"/Code");
			  // if the directory does not exist, create it
			  if (!theDir2.exists()) {
			    //System.out.println("creating directory: " + directoryName);
			    boolean result = theDir2.mkdir();  

			     if(result) {    
			       System.out.println("DIR 2 created");  
			     }
			  }
		
		
        //Check Name
		if(Arrays.asList(listadata2).contains(namet.getText())){
			System.out.println("Nombre Existe");  
		}	  
		//TXT FILE
		
			  
			  
		PrintWriter writer;
		try {
			writer = new PrintWriter(repo+"/ShaderData/"+a1+"/"+a1+".txt", "UTF-8");
			
			writer.println("Name:");
			writer.println(namet.getText());
			writer.println("Tags:");
			writer.println("shader, "+ tagt.getText());
			writer.println("Description:");
			writer.println(dest.getText());
			writer.println("Autor:");
			writer.println(authort.getText());
			writer.println("Email:");
			writer.println(emailt.getText());
			writer.close();
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		
	        // PDE FILE
	          	
	        	
		

		String filename = a1;
			
	
			
			if (!filename.endsWith(".pde")) {
			      filename += ".pde";
			    }

			   // File dirc = new File(repo+"/"+);
			    	    
			    String f = pdecode;
			    try{
			    
			    	System.gc();
		        try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	 	
			    File fileTemp = new File(theDir, filename);
			    if (fileTemp.exists()){
			    fileTemp.delete();
			    }  
			    
			    
			    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(theDir, filename
		                ), true));
			    bw.write(f);
		        bw.newLine();
		        bw.close();
		        
			
		}
		catch(Exception excp){
		}
		
	    //DATA
	    
	    File dirdata = new File(folderpath + "/data");
	    String[] dirdatainfo = dirdata.list();
	    if (dirdatainfo == null){
	    	JOptionPane.showMessageDialog(null,
				     "The data folder is empty"
				 , //Mensaje
				 	    "No files in data folder", //Título
				 	    JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
	        try {
				throw new Exception("Data Folder empty");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	}
	        else { 
	        		try{
	        			copyFolder(dirdata,theDir2);
	        		}catch(IOException e){
	        			e.printStackTrace();
	        	
	        		}
	        	        	
	        for (int x=0;x<dirdatainfo.length;x++){
	        System.out.println(dirdatainfo[x]);
	        	}
	        
	        }
	    
	    //IMG COPY
	    
	    File imgini = new File(imgpathfinal);
	    File imgfinal = new File(repo+"/ShaderData/"+a1+"/"+ a1+ ".jpg");
	    try {
			FileUtils.copyFile(imgini, imgfinal);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
	    
	    
	    //
	    
	    
		//REPO
		
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository;
		try {
			repository = builder.setGitDir(new File(repo+"/.git"))
			        .readEnvironment() // scan environment GIT_* variables
			        .findGitDir() // scan up the file system tree
			        .build();
		
                
        Git git = new Git(repository);
        
        //File dir2 = new File(repo+ "/Prueba");
		//File myfile = new File(dir+"/ShaderData/folder_prueba", "shaderfile.glsl");
		//try {
		//	myfile.createNewFile();
		//} catch (IOException e2) {
			// TODO Auto-generated catch block
		//	e2.printStackTrace();
		//}

		// run the add-call
		try {
			//git.add()
			//        .addFilepattern("ShaderData/folder_prueba/shaderfile.glsl")
			//        .call();
			//TXT
			
			git.add()
	        .addFilepattern("ShaderData/"+a1+"/"+ a1+".txt")
	        .call();
			
			//PDE
			git.add()
	        .addFilepattern("ShaderData/"+a1+"/"+ a1+".pde")
	        .call();			
			//DATA FOLDER
			
			 for (int x=0;x<dirdatainfo.length;x++){
				 	git.add()
			        .addFilepattern("ShaderData/"+a1+"/Code/"+ dirdatainfo[x])
			        .call();
				  //  System.out.println(dirdatainfo[x]);
			        	}
			
			//IMG
			 git.add()
		        .addFilepattern("ShaderData/"+a1+"/"+ a1+".jpg")
		        .call();			
			 
			
			
		} catch (NoFilepatternException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GitAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	//	System.out.println("Added file " + myfile + " to repository at " + dir);



		// commit
		CommitCommand commit = git.commit();
		commit.setCommitter(authort.getText(), emailt.getText())
		.setMessage(dest.getText());
		try {
			commit.call();
		} catch (UnmergedPathsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		//RE-INDEX
		
		try {
			Index index = new Index(pathos, null);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//End REINDEX
		
		int selectedOption = JOptionPane.showConfirmDialog(null, 
                "Do you want to upload the shader now??", 
                "Choose", 
                JOptionPane.YES_NO_OPTION); 
		if (selectedOption == JOptionPane.YES_OPTION) {
			

			PushCommand pc = git.push();
			pc.setCredentialsProvider(cp)
			.setForce(true)
			.setPushAll();
			Iterator<PushResult> it;
			try {
				it = pc.call().iterator();
				if(it.hasNext()){
					System.out.println(it.next().toString());
					}
			} catch (TransportException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        
		
			
		}
		
		
		// PUSH
	/*	
		PushCommand pc = git.push();
		pc.setCredentialsProvider(cp)
		.setForce(true)
		.setPushAll();
		Iterator<PushResult> it;
		try {
			it = pc.call().iterator();
			if(it.hasNext()){
				System.out.println(it.next().toString());
				}
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        */
		repository.close();
		
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        
		}else {//END IF PDE
        	JOptionPane.showMessageDialog(null,
				     "The sketch doesn't contains a PShader class, there isn't any shader in code," +
				     "please check your sketch code"
				 , //Mensaje
				 	    "PShader Missing", //Título
				 	    JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
        
        }//ELSE PDE
		
		
        }
            
        }//else 
        else {//ADD LOCAL
		
		String a2 = a1+" (LOCAL)";
		a1=a2;
		boolean checklocal;
		
		if (Arrays.asList(allnames).contains(a1)){
			
		int selectedOptionB = JOptionPane.showConfirmDialog(null,  
	    "The shader already exists. Do you want to edit it??", 
	    "Name Conflict", 
	    JOptionPane.YES_NO_OPTION); 
	    if (selectedOptionB == JOptionPane.YES_OPTION) {
	     checklocal = true;
	    }else{
	    	checklocal = false;
	    	JOptionPane.showMessageDialog(null,
				     "Change the name of the shader and try again"
				 , //Mensaje
				 	    "Change Name", //Title
				 	    JOptionPane.WARNING_MESSAGE); //Message
	    }
	    	
	    }else {
	    checklocal = true;
	    }
	    
	    if (checklocal==true){
	    
	    	
	    	try {
    			editor.getBase();
    			editor.getBase();
    			//System.out.println(pdecode);
    			pdecode = editor.getText();
    			Sketch sketch = editor.getSketch();
    			File sketchFolder = sketch.getFolder();
    			File sketchbookFolder = Base.getSketchbookFolder();
    			folderpath = sketchFolder.toString();
    			
    			System.out.println(folderpath);
         	
     		}catch(Exception excp){
    		}
		//FOLDERS
		
		File theDir = new File(repo+"/ShaderData/"+a1);
		 System.out.println(repo+"/ShaderData/"+a1);
		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
		    //System.out.println("creating directory: " + directoryName);
		    boolean result = theDir.mkdir();  

		     if(result) {    
		       System.out.println("DIR created");  
		     }
		  }
		  
		  File theDir2 = new File(repo+"/ShaderData/"+a1+"/Code");
			 System.out.println(repo+"/ShaderData/"+a1+"/Code");
			  // if the directory does not exist, create it
			  if (!theDir2.exists()) {
			    //System.out.println("creating directory: " + directoryName);
			    boolean result = theDir2.mkdir();  

			     if(result) {    
			       System.out.println("DIR 2 created");  
			     }
			  }
		
		
        	  
		//TXT FILE
		
			  
			  
		PrintWriter writer;
		try {
			writer = new PrintWriter(repo+"/ShaderData/"+a1+"/"+a1+".txt", "UTF-8");
			
			writer.println("Name:");
			writer.println(namet.getText());
			writer.println("Tags:");
			writer.println("shader, "+ tagt.getText());
			writer.println("Description:");
			writer.println(dest.getText());
			writer.println("Autor:");
			writer.println(authort.getText());
			writer.println("Email:");
			writer.println(emailt.getText());
			writer.close();
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		
	        // PDE FILE
	          	
	        	
		

		String filename = a1;
			
	
			
			if (!filename.endsWith(".pde")) {
			      filename += ".pde";
			    }

			   // File dirc = new File(repo+"/"+);
			    	    
			    String f = pdecode;
			    try{
				    
			    	System.gc();
		        try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	 	
			    File fileTemp = new File(theDir, filename);
			    if (fileTemp.exists()){
			    fileTemp.delete();
			    }  
			    
			    
			    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(theDir, filename
		                ), true));
			    bw.write(f);
		        bw.newLine();
		        bw.close();
		        
			
		}
		catch(Exception excp){
		}
		
	    //DATA
	    
	    File dirdata = new File(folderpath + "/data");
	    String[] dirdatainfo = dirdata.list();
	    if (dirdatainfo == null){
	    	JOptionPane.showMessageDialog(null,
				     "The data folder is empty"
				 , //Mensaje
				 	    "No files in data folder", //Título
				 	    JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
	        try {
				throw new Exception("Data Folder empty");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	}
	        else { 
	        		try{
	        			copyFolder(dirdata,theDir2);
	        		}catch(IOException e){
	        			e.printStackTrace();
	        	
	        		}
	        	        	
	        for (int x=0;x<dirdatainfo.length;x++){
	        System.out.println(dirdatainfo[x]);
	        	}
	        
	        }
	    
	    //IMG COPY
	    
	    File imgini = new File(imgpathfinal);
	    File imgfinal = new File(repo+"/ShaderData/"+a1+"/"+ a1+ ".jpg");
	    try {
			FileUtils.copyFile(imgini, imgfinal);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
	    
		
	  //RE-INDEX
		
		try {
			try {
				Index index = new Index(pathos, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//End REINDEX
	    
		}
	}//ADD LOCAL
            
        
        
        
        
                 }//Else Name/////
        
            }//action performed
        
        
        } //action listener
        
    );//action listener

}//public Upload
	
	
	
	private void copyFolder(File src, File dest) throws IOException {
		// TODO Auto-generated method stub
		if(src.isDirectory()){
			 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
 
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	       // System.out.println("File copied from " + src + " to " + dest);
    	}
    }
	
}//class Upload	

	
