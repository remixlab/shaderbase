package Shader.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import processing.app.Base;
import processing.app.Editor;
import processing.app.Sketch;

class Delete{
	
	private static final String String = null;
	String pdecode;
	String vertex;
	String fragment;	
	String filenamef = null;
	String code = null;
	Path p5 =null;
	Path img=null;
	static String OS = System.getProperty("os.name").toLowerCase();
	
	private static Editor editor = null;
	
		
	public Delete(String shaderse, Editor editor, Path pathos)
	{
	
		//String path = shaderse;
		this.editor = editor;
		
		
	
		//Open FOLDER
		
		//String path = shaderse +"/Code"+ "/"+ shadersename+ ".pde";
        
		String path = shaderse; 
		
        //File dir2 = new File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/Prueba.txt");        
        
        File dir = new File(path);        
        
      

    	
        
        try {
        	System.gc();
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileUtils.deleteDirectory(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	}
	

	
