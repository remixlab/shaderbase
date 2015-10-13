package shaderbase;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Iterator;
import javax.swing.JOptionPane;
import processing.app.ui.Editor;
import org.apache.lucene.queryparser.classic.ParseException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;


class Pull {

  private static Editor editor = null;

	public Pull(Path pathos, Editor editor) throws TransportException, 
	GitAPIException, IOException, NoWorkTreeException, URISyntaxException{
	
	this.editor = editor;
	//Path path = pathos;
	final String repo = pathos.toString()+"/Data"; 
	final File dir = new File(repo);
	
	String url = "https://github.com/Shadertool/shaderdb.git";

    //Repo Local
    
    FileRepositoryBuilder builder1 = new FileRepositoryBuilder();
    Repository repositorylocal = builder1.setGitDir(new File(repo + "/.git"))
            .readEnvironment() // scan environment GIT_* variables
            .findGitDir() // scan up the file system tree
            .build();
    
    final String name = "Shadertool";
	final String password = "1ergosum";
 	CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name, password);
    
    Git gitlocal = new Git (repositorylocal);
       
    PullResult res = gitlocal.pull().call();
	String result = res.toString();
	System.out.println(result);
	
    String b = repo+"/ShaderData/";
    File local = new File(b);
    
    
    //PUSH N
    
    
    PushCommand pc = gitlocal.push();
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
	}//End Push 
	
    
    
    if (result.contains("Already-up-to-date")){
    	JOptionPane.showMessageDialog(null,
			     "Already up to date"
			     ,"Already up to date", 
			 	  JOptionPane.INFORMATION_MESSAGE); 
    }else{
    	
    	try {
			Index index = new Index(pathos, null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

	}
  }

}//end Pull