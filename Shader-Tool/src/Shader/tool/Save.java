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
import processing.app.Base;
import processing.app.Editor;
import processing.app.Sketch;

class Save{
	
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
	
		
	public Save(String shaderse, Editor editor, String shadersename)
	{
	
		//String path = shaderse;
		this.editor = editor;
		String name = shadersename;
		
	
		//Open PDE CODE
		
		//String path = shaderse +"/Code"+ "/"+ shadersename+ ".pde";
        
		String path = shaderse +"/" + shadersename+ ".pde";
		
        //File dir2 = new File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/Prueba.txt");        
        
        File dir = new File(path);        
        
        
        try {
            
        	BufferedReader br = new BufferedReader(new FileReader(dir));
        	StringBuilder sb = new StringBuilder();
 	        String line = br.readLine();

       while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            //System.out.println(everything);
            pdecode = everything;
        }
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
     		       
	        
	        // CODE PDE TO SKETCH
	          	
	        	
		
		try {
			editor.getBase();
			editor.getBase();
			//System.out.println(pdecode);
			editor.setText(pdecode);
			
			Sketch sketch = editor.getSketch();
			File sketchFolder = sketch.getFolder();
			File sketchbookFolder = Base.getSketchbookFolder();
			String filename = null;
			
		}
		catch(Exception excp){
		}
		
		File destFolder = editor.getSketch().prepareDataFolder();
		
		String pathos =  shaderse +"/Code";
		File srcFolder = new File(pathos);
		
		//copyFolder(srcFolder,destFolder);

		
		try{
        	copyFolder(srcFolder,destFolder);
           }catch(IOException e){
        	e.printStackTrace();
        	//error, just exit
                //System.exit(0);
           }
		
	}// PUBLIC SAVE	


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
	}
	

	
