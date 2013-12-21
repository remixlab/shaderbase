package Shader.tool;


import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.h2.fulltext.*;

import processing.app.Editor;

class Search {

	String searchin;
	int contaid;
	String[] searchid;
	String[] searchnames;
	private static Editor editor = null;
	Path p5 = null;
	
	public Search(String[] searchid2, String[] searchnames2, Editor edito, Path pathos) {
		// TODO Auto-generated constructor stub
		this.editor = editor;
		p5=pathos;
		
	}

	Search changeSearch(String newValue) {

		searchin = newValue;
        
		try {
			//Path p5 = Paths.get(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/", "Shaderdb");
		 	Class.forName("org.h2.Driver");
	        Connection con = DriverManager.getConnection(
	        "jdbc:h2:"+p5
		 	);
        
        
        //Search Query
       	      		  
        Statement stmtdescrip = con.createStatement();
        //System.out.println("SELECT * FROM FT_SEARCH('"+searchin+"', 0, 0)");
        //System.out.println("Exc Query Busqueda");
        ResultSet rs = stmtdescrip.executeQuery("SELECT * FROM FT_SEARCH('" + searchin +"', 0, 0)");
       
        //Contador (posiblemente hay otra forma mas simple)
        
        System.out.println(rs);
        int k =0;
        while (rs.next()){
        	k++;
        	//System.out.println(k);
        	contaid =k;
        }
        
        if (contaid == 0) {
  	      JOptionPane.showMessageDialog(editor, "Nothing found, try other word(s)",
  	                                    "No Luck", JOptionPane.WARNING_MESSAGE);
  	      return null;
  	    }
        
             
        //ID Shader (Lucene, H2)
        String arreglo [] = new String[contaid];
        for(int i=1; i<=contaid; i++){	
        
        Statement searchresult = con.createStatement();
        //System.out.println("SELECT * FROM FT_SEARCH('"+searchin+"', 0, 0)");
        //System.out.println("Exc Query Busqueda");
        ResultSet rsearch = searchresult.executeQuery("SELECT * FROM FT_SEARCH('" + searchin +"', 0, 0)");
        int k1=0;
        while (rsearch.next()){
        	String searchout = rsearch.getString(1);
        	//System.out.println(searchout);
        	int stringnum = searchout.length();
        	//System.out.println(stringnum);
        	String sSubCadena = searchout.substring(35,stringnum); //Valor ID
        	//System.out.println(sSubCadena);
        	arreglo[k1] = sSubCadena;
        	//System.out.println(arreglo[k1]);
          	k1++;
          	searchid = arreglo;
          	//System.out.println(searchid[k1]);
        	
        }

        }
       
        //Names Search (List Array)
        
        String names [] = new String [contaid];
       
        int k2=0;
        for(int i=1; i<=contaid; i++){	
        	Statement name = con.createStatement();
            ResultSet rname = name.executeQuery("SELECT idCodigo, Nombre FROM codigo WHERE idCodigo ="+searchid[k2]);
            while(rname.next()){
              	String namesql = rname.getString("NOMBRE");
            	//System.out.println(rname.getString("NOMBRE"));
                System.out.println(namesql);
            	names[k2] = namesql;
            	k2++;
            	//System.out.println(names[i]);
            	searchnames = names;
        }
        }	
		}catch(SQLException ex) {
    	
    	System.out.println("Error Search");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("Unexpected error: "+e.getMessage());
		}
		return new Search(searchid, searchnames, editor, p5);
	//return searchid;
	//return searchnames;
	}

	
}//end Search