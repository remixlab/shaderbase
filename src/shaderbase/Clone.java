package shaderbase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

class Clone {
  public Clone(String args[], Path pathos) throws TransportException,
  GitAPIException, IOException {

    String repo = pathos.toString() + "/Data";

    // credentials
    CredentialsProvider cp = new UsernamePasswordCredentialsProvider(
        ShaderBase.username, ShaderBase.password);
    // clone
    File dir = new File(repo);
    CloneCommand cc = new CloneCommand().setCredentialsProvider(cp)
        .setDirectory(dir).setURI(ShaderBase.repoURL);
    Git git = cc.call();
    // add
    AddCommand ac = git.add();
    ac.addFilepattern(".");
    try {
      ac.call();
    } catch (NoFilepatternException e) {
      e.printStackTrace();
    }

    // cleanup
    dir.deleteOnExit();
  }

}// end Clone