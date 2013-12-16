package Shader.tool;

import Shader.tool.ShaderTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.nio.file.StandardCopyOption.*;

import processing.app.*;
import processing.app.tools.*;
import processing.app.Base;
import processing.app.Editor;
import processing.app.Sketch;
import processing.app.SketchCode;
import processing.core.PApplet;

class Save{
	
	private static final String String = null;
	String pdecode;
	String vertex;
	String fragment;	
	String filenamef = null;
	String code = null;
	
	private static Editor editor = null;
	
		
	public Save(String shaderse, Editor editor)
	{
	
		
		this.editor = editor;
	
		//Extraer PDE, VERTEX Y FRAGMENT 
		
		try {
			//editor.getBase();
			//editor.getBase();
			
			Path p5 = Paths.get(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/", "Shaderdb");
		 	Class.forName("org.h2.Driver");
	        Connection con = DriverManager.getConnection(
	        "jdbc:h2:"+p5
		 	
	        );
	    
	        //CODIGO (FRAGMENTO)
	        
	        Statement stmt = con.createStatement();
	        ResultSet codigo1 = stmt.executeQuery("SELECT idCodigo, Fragment FROM codigo WHERE idCodigo ="+shaderse);
	        while (codigo1.next()) {
	        		      
	        	fragment = codigo1.getString("FRAGMENT");
	        	//System.out.println(codigo1.getString("FRAGMENT"));
	        			  }
	        
	        
	        // CODIGO PDE
	        
	     
	        Statement stmtpde = con.createStatement();
	        ResultSet codigopde = stmtpde.executeQuery("SELECT idCodigo, PDE FROM codigo WHERE idCodigo ="+shaderse);
	        while (codigopde.next()) {
	        		      
	        	pdecode = codigopde.getString("PDE");
	        	
	        	//System.out.println(codigopde.getString("PDE"));
	        	
	       			  }
	        
	        // CODIGO VERTEX
	        
	        Statement stmtvert = con.createStatement();
	        ResultSet codigovert = stmtvert.executeQuery("SELECT idCodigo, Vertex FROM codigo WHERE idCodigo ="+shaderse);
	        while (codigovert.next()) {
	        		      
	        	vertex = codigovert.getString("Vertex");
	        	//System.out.println(codigovert.getString("Vertex"));
	        	
	       			  }
	        
	        
	        } catch(SQLException ex) {
	        	
	        	System.out.println("Error SQL");
	        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        } catch(Exception e) {
	        System.out.println("Unexpected error: "+e.getMessage());
	    }
		
		
		
		try {
			editor.getBase();
			editor.getBase();
			//System.out.println(pdecode);
			editor.setText(pdecode);
			
			Sketch sketch = editor.getSketch();
			File sketchFolder = sketch.getFolder();
			File sketchbookFolder = Base.getSketchbookFolder();
			String filename = null;
			
			if (vertex==null){
			
				
				
			}
			else{
				filenamef = "vertex";
				code = vertex;
	
			    //Path delvert = Paths.get(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/", "vertex.glsl");
			    
			   // Files.delete(delvert);
				rsave();
			}
			

			if (fragment==null){
			
				
				
			}
			else{
				
				filenamef = "fragment";
				code = fragment;
				rsave();
			}
			
		}
		catch(Exception excp){
		}
		
		
			
		
	}
	
	
	// public Editor editor() {
	//		return editor;
	//	}

	private void rsave() throws IOException{
		String filename = filenamef.trim();
		
				
		if (!filename.endsWith(".glsl")) {
		      filename += ".glsl";
		    }

		    File folder = editor.getSketch().prepareDataFolder();
		    	    
		    String f = code;
		    try{
		    	 	
		    File fileTemp = new File(folder, filename);
		    if (fileTemp.exists()){
		    fileTemp.delete();
		    }  
		    
		    
		    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(folder, filename
	                ), true));
		    bw.write(f);
	        bw.newLine();
	        bw.close();
	        
	        //Path currentRelativePath = Paths.get("");
			//String s = currentRelativePath.toAbsolutePath().toString();
	        
			//String path = new File(".").getCanonicalPath();
			//System.out.println("Path "+s +path);
			
			//Enviar JPG predeterminado
			  
			  Path pathimg = Paths.get(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/", "img.jpg");
			  File imgpre = new File(folder, "img.jpg");
			  Files.copy( pathimg, imgpre.toPath() );
			  
			  
		    }
		    
		   
		    
		    catch (IOException e)
		    {
		    System.out.println("Exception ");
		    }
		    
		
	}
	
	
	
}
	
