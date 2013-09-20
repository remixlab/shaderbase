/**
 * you can put a one sentence description of your tool here.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

package Shader.tool;
 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.*;

import processing.app.*;
import processing.app.tools.*;
 
/**
 * GUI tool for shader program
 */

public class ShaderTool extends JFrame implements Tool{ 
 //public class ShaderTool implements Tool {
 Editor editor;
 
 //Variables 
 String code = "";
 JTextArea codevisual; 
 JButton save;
 JTextField name;
 String name1;
 JButton connect;
 JButton next;
 int conta =0; 
 byte[] image = null;
 Image rpta=null;
 Blob imagen=null;
 BufferedImage Picture1;
 JLabel picLabel;
 ImageIcon one;

  

 
public String getMenuTitle() {
	    return "Shader Tool";
}

public void init(Editor editor) {
this.editor = editor;

Container inicio = getContentPane();
inicio.setLayout(new BorderLayout());
final JPanel window = new JPanel();
window.setBorder(new EmptyBorder(5, 5, 5, 5));
inicio.add(window, BorderLayout.CENTER);
window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));

String labelText =
"Descripcion------------------------------------------------------------------------------------------";


JTextArea textarea = new JTextArea(labelText);
textarea.setBorder(new EmptyBorder(5, 5, 10, 5));
textarea.setBackground(null);
textarea.setEditable(false);
textarea.setHighlighter(null);
textarea.setFont(new Font("Dialog", Font.PLAIN, 12));
window.add(textarea);

final JPanel visual = new JPanel();
visual.add(new JLabel("Code:"));
final JTextArea codevisual;
visual.add(codevisual = new JTextArea(code));
//Scrollbar (no implementado del todo)
JScrollPane textScroll=new JScrollPane(codevisual,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
visual.add(textScroll); 

window.add(visual);

JPanel filename = new JPanel();
filename.add(new JLabel("Filename:"));
final JTextField name;
filename.add(name = new JTextField(20));
filename.add(new JLabel(".glsl"));
window.add(filename);

JPanel buttons = new JPanel();
connect = new JButton("Connect");
connect.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      com();
    System.out.println("Testing");  
    System.out.println(code);
    codevisual.setText(code);
    //codevisual.append(code);
    name.setText("shader"); //Adicionar Función nombre per QUERY
    save.setEnabled(true);  
    name1 = name.getText();
   
    picLabel = new JLabel();
    
    one = new ImageIcon(rpta);
    //picLabel = new JLabel(new ImageIcon(rpta));
    //one.getImage().flush();
    picLabel.setIcon(one);
   
    //picLabel.setIcon( new ImageIcon(rpta));
    
    visual.add(picLabel);
    visual.setVisible(true);    
    
    
    
    
    }
  });

next = new JButton("Next");
next.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     if (code==null){
    	 conta = 1;
     }
     else {
    	conta = conta +1;
     }
     visual.setVisible(false);
     nextquery();
    }
  });

save = new JButton("Save");
save.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      build();
    }
  });
save.setEnabled(false);

buttons.add(connect);
buttons.add(save);
buttons.add(next);
window.add(buttons);




 }

public void run() {
    setVisible(true);
  }//end run

public void build(){
	
	System.out.println("Saving");
	//System.out.println(name1);
	
	String filename = name1.trim();

	
	System.out.println(filename);
	    if (filename.length() == 0) {
	      JOptionPane.showMessageDialog(this, "Enter a file name for the font.",
	                                    "Lameness", JOptionPane.WARNING_MESSAGE);
	      return;
	    }
	    if (!filename.endsWith(".glsl")) {
	      filename += ".glsl";
	    }

	    File folder = editor.getSketch().prepareDataFolder();
	    String f = code;
	    try{
	    
	    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(folder, filename
                ), true));
	    bw.write(f);
        bw.newLine();
        bw.close();
	    }
	    catch (IOException e)
	    {
	    System.out.println("Exception ");
	    }
	    
	    //File file = new File (folder, filename);
	    //f.save((new File(folder, filename));

	
	}//end build

public void com(){
	//conta = 1;
	System.out.println("Connecting .....");
	try {
        //Cargamos el puente JDBC =&gt; Mysql
        System.out.println("Loading JDBC driver...");
        Class.forName("com.mysql.jdbc.Driver");

        //Intentamos conectarnos a la base de Datos en este caso una base llamada temp
        System.out.println("Connecting Shader DB...");
        Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost/TESISPRUEBA", "root", "@1RainbowSix"
        //"jdbc:mysql://mysql15.000webhost.com/a4278501_shader", "a4278501_anfgo", "@shader"            		
        //"jdbc:mysql://mysql.serversfree.com/u514037168_sha", "u514037168_anf", "@shader"
        //"jdbc:mysql://sql3.freesqldatabase.com:3306/sql316268", "sql316268", "uL9!pA6%"         
        		
        );
        System.out.println("Connected Shader DB");
        
        
        //IMAGEN
        
        rpta=null;
        Statement stmtimg = con.createStatement();
        ResultSet imagen1 = stmtimg.executeQuery("SELECT idCodigo, Imagen FROM codigo WHERE idCodigo ="+conta);
        System.out.println("Exc Query Image");
        
                
        
        while(imagen1.next()){
        imagen = imagen1.getBlob("Imagen");
        rpta= javax.imageio.ImageIO.read(imagen.getBinaryStream());
       
        }
        //while(imagen.next()) {
        //    image = imagen.getBytes("myimage");
       // }
        
        //CODIGO (FRAGMENTO)
        
        Statement stmt = con.createStatement();
        ResultSet codigo1 = stmt.executeQuery("SELECT idCodigo, Fragment FROM codigo WHERE idCodigo ="+conta);
        System.out.println("Exc Query");
        while (codigo1.next()) {
        		      
        	code = codigo1.getString("FRAGMENT");
        	System.out.println(codigo1.getString("FRAGMENT"));
        	
       	
        		      //System.out.println("Nombre: " + resultado.getString("nombre"));
        		      //System.out.println("Clave: " + resultado.getString("clave"));
        		  }
        
        //String codigo2 = codigo2.setText(codigo1.getString("Codigo"));
        //System.out.println(codigo1.getString("CODIGO"));          
        
        } catch(SQLException ex) {
        System.out.println("Error MYSQL");
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        } catch(Exception e) {
        System.out.println("Unexpected error: "+e.getMessage());
    }
	
		
	}//end com
public void nextquery(){
	System.out.println(conta);

	//
	code = null;
	picLabel.setIcon(null);
	rpta = null;
	
	//
	
	}// end nextquery




}

 
		