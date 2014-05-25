package Shader.tool;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;



import java.util.Iterator;

import javax.swing.JOptionPane;

import processing.app.Editor;

import org.apache.lucene.queryparser.classic.ParseException;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.MergeResult.MergeStatus;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import processing.app.Base;
import processing.app.Editor;
import processing.app.Sketch;

class Pull {

	private static Editor editor = null;
	
	public Pull(Path pathos, Editor editor) throws TransportException, GitAPIException, IOException, NoWorkTreeException, URISyntaxException{
		
		this.editor = editor;
		//Path path = pathos;
		final String repo = pathos.toString()+"/Data"; 
		final File dir = new File(repo);
		final String name = "Shadertool";
		final String password = "1ergosum";
		String url = "https://github.com/Shadertool/shaderdb.git";

		//CLONE REMOTE
		
        File localPath = File.createTempFile("TestGitRepository", "");
        localPath.delete();

        // then clone
        System.out.println("Cloning from " + url + " to " + localPath);
        Git.cloneRepository()
                .setURI(url)
                .setDirectory(localPath)
                .call();

        // now open the created repository
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(localPath+"/.git"))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        
        //GIT REMOTE & LOCAL
        
        FileRepositoryBuilder builder1 = new FileRepositoryBuilder();
        Repository repositorylocal = builder.setGitDir(new File(repo + "/.git"))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        
        
        Git gitremote = new Git(repository);
        Git gitlocal = new Git (repositorylocal);
        
        
       /* 
    	StoredConfig targetConfig = repositorylocal.getConfig();
    	targetConfig.setString("branch", "master", "remote", "origin");
    	targetConfig
    	.setString("branch", "master", "merge", "refs/heads/master");
    	RemoteConfig config = new RemoteConfig(targetConfig, "origin");

    	config
    	.addURI(new URIish(gitremote.getRepository().getWorkTree()
    	.getAbsolutePath()));
    	config.addFetchRefSpec(new RefSpec(
    	"+refs/heads/*:refs/remotes/origin/*"));
    	config.update(targetConfig);
    	targetConfig.save();
    	
    	
    	*/
    	
    	
    	PullResult res = gitlocal.pull().call();
    	String result = res.toString();
    	System.out.println(result);
        
    	//FetchResult fetchResult = res.getFetchResult();
    	//MergeResult mergeResult = res.getMergeResult();
    	//mergeResult.getMergeStatus(); 

        //repository.close();
        //repositorylocal.close();
		
        String a = localPath.toString()+"/ShaderData/";
        File sourcepath = new File(a);
        String b = repo+"/ShaderData/";
        File local = new File(b);
        if (result.contains("Already-up-to-date")){
        	JOptionPane.showMessageDialog(null,
				     "Already up to date"
				 , //Mensaje
				 	    "Already up to date", //Título
				 	    JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }else{
        	copyFolder(sourcepath, local);
        	try {
				Index index = new Index(pathos, null);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        	
        
        localPath.deleteOnExit();
        		       

	}
	
	
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
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }
	
	
}//end Pull