package Shader.tool;

import Shader.tool.Save;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import processing.app.Editor;




public class Wizard  {
	
    JTextArea output;
	JList list;
	JTable table;
	String newline = "\n";
	String descrip; 
	ListSelectionModel listSelectionModel;
	String[] listadata;
	String[] prueba = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
	int cuentat = 0;
	String shaderse;
	JButton save;
	JButton search;
	Image rpta=null;
	Blob imagen=null;
	final JLabel picLabel = new JLabel();
    final static String BUTTONPANEL = "Shader List";
    final static String TEXTPANEL = "Options";
    final static int extraWindowWidth = 100;
    final JPanel card1 = new JPanel();
    final JPanel card2 = new JPanel();
    String labelText;
    final JTextArea textarea = new JTextArea();
    JTextField searchtext;
    private Editor editor = null;
    
    
    public Wizard(Editor editor) {
    	this.editor = editor;
    	
    	    	
    	// TODO Auto-generated constructor stub
	}


	public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();
        com();
        
        String[] listData = listadata;
        list = new JList(listData);
 
        listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionHandler());
        JScrollPane listPane = new JScrollPane(list);
 
        
 
        //Build output area.
        output = new JTextArea(1, 10);
        output.setEditable(false);
        JScrollPane outputPane = new JScrollPane(output,
                         ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
        //Do the layout.
        //JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel splitPane = new JPanel ();
       // add(splitPane, BorderLayout.CENTER);
 
        JPanel topHalf = new JPanel();
        topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
        JPanel listContainer = new JPanel(new GridLayout(1,1));
        listContainer.setBorder(BorderFactory.createTitledBorder(
                                                "Shader List"));
        listContainer.add(listPane);
         
        topHalf.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
        topHalf.add(listContainer);
        //topHalf.add(tableContainer);
 
        //topHalf.setMinimumSize(new Dimension(100, 50));
        topHalf.setPreferredSize(new Dimension(450, 150));
        splitPane.add(topHalf);
 
        JPanel bottomHalf = new JPanel(new BorderLayout());
        //JPanel buttons = new JPanel();
        
               
        //Cambio
        bottomHalf.add(outputPane, BorderLayout.CENTER);
        	//XXX: next line needed if bottomHalf is a scroll pane:
        	//bottomHalf.setMinimumSize(new Dimension(400, 50));
        bottomHalf.setPreferredSize(new Dimension(400, 135));
        //bottomHalf.add(save);
        //bottomHalf.add(search);
        splitPane.add(bottomHalf);
        
        
        //labelText="Descripcion--------------------------------------------------------";
        //final JTextArea textarea = new JTextArea(labelText);
      
//        textarea.setBorder(new EmptyBorder(5, 5, 10, 5));
        textarea.setBackground(null);
        textarea.setEditable(false);
        //textarea.setHighlighter(null);
        textarea.setFont(new Font("Dialog", Font.PLAIN, 12));

        
        
        //Create the "cards".
       final JPanel card1 = new JPanel() {
            //Make the panel wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        
        
        card1.add(splitPane);
        card1.add(picLabel);
        //textarea.append(descrip);
        card1.add(textarea);
        //card1.add(new JButton("Button 1"));
        save = new JButton("Load");
        card1.add(save); 
        save.setEnabled(false);
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	
            	Save salvar = new Save(shaderse,editor);
            	System.out.println("Loading .....");
            	
            } 
           
        });
        
        
        searchtext = new JTextField("Search ie: blur, texture", 50);
        //card1.add(new JTextField("Search for a Shader", 50));
        card1.add(searchtext); 
        search = new JButton("Search");
        //card1.add(search);    
        
        searchtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	String searchin = searchtext.getText(); 
           	Search search = new Search(null, null); 
            search.changeSearch(searchin);
            String[] inid = search.searchid;
            String[] inname = search.searchnames;
            System.out.println(inname[0]); 
            System.out.println(inid[0]); 
            list.setListData(inname);
            prueba = inid;
            } 
           
        });
        
        
        //card1.add(new JButton("Save"));
        
 
        //Menu 2 
        
        //card2.add(new JTextField("Search for a Shader", 50));
        //search = new JButton("Search");
        //card2.add(search);    
        //card2.add(new JButton("Search"));
 
        tabbedPane.addTab(BUTTONPANEL, card1);
        tabbedPane.addTab(TEXTPANEL, card2);
 
        pane.add(tabbedPane, BorderLayout.CENTER);
    }//end component

	
    
    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
 
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            //boolean isAdjusting = e.getValueIsAdjusting();
            //output.append("Event for indexes "
            //              + firstIndex + " - " + lastIndex
            //             + "; isAdjusting is " + isAdjusting
            //              + "; selected indexes:");
            
            //output.append("selected indexes:");
            output.append(descrip);
            
            
            if (lsm.isSelectionEmpty()) {
                output.append(" <none>");
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        output.append(" " + i);
                        //shaderse = i+1;
                        //Ojo prueba String hay que revisar como cambiar a int          
                        shaderse = prueba[i];
                        System.out.println(shaderse);
                        
                    }
                }
            }
            output.append(newline);
            output.setCaretPosition(output.getDocument().getLength());
            
            rpta = null;
            picLabel.setIcon(null);
            inforeq();        
            save.setEnabled(true);  
            ImageIcon one;      
           
            
            one = new ImageIcon(rpta);
            picLabel.setIcon(one);
            
            
           
          picLabel.setIcon( new ImageIcon(rpta));
          labelText = descrip;
            
          
           //card1.add(picLabel);
            
        }
    }//SharedList Listener

    
    
	 public void com() {
		//Lista
		 
		 try {
		        
			 	Path p5 = Paths.get(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/", "Shaderdb");
			 	System.out.println("Loading JDBC driver...");
		        Class.forName("org.h2.Driver");
		        System.out.println("Connecting Shader DB...");
		        Connection con = DriverManager.getConnection(
		        //"jdbc:h2:ShaderTool/Shaderdb"
		        		"jdbc:h2:"+p5
		        );
		        System.out.println("Connected Shader DB");
		        Statement conta = con.createStatement();
		        ResultSet conta1 = conta.executeQuery("SELECT COUNT(*) AS nombre FROM codigo");
		        
		        while (conta1.next()){
		        	int cuenta = conta1.getInt("NOMBRE");
		        	System.out.println(conta1.getInt("NOMBRE"));
		        	cuentat = cuenta;
		        	
		        }
		        
		        String arreglo [] = new String[cuentat];
		        for(int i=1; i<=cuentat; i++){		      
		        Statement stmtlist = con.createStatement();
		        ResultSet lista = stmtlist.executeQuery("SELECT nombre FROM codigo");
		        int k = 0;
		        while (lista.next()) {
		        	
		        	
		        	String listadata2 = lista.getString("NOMBRE");
		        	arreglo[k] = listadata2;
		        	//System.out.println(lista.getString("NOMBRE"));
		        	//System.out.println(listadata2);
		        	
		        	
		        	//System.out.println(k);
		        	k ++; 
		        	 }	
		        	
		       	        		  
		        	
		        }
		        listadata = arreglo;
		 }catch(SQLException ex) {
	        	
	        	System.out.println("Error MYSQL");
	        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        } catch(Exception e) {
	        System.out.println("Unexpected error: "+e.getMessage());
	    }
		
	}//end com

    
	 public void inforeq() {
		 
		 //Adquiere los valores de IMG, Descripción, etc (TAGS)
		 try {
		        
			 	Path p5 = Paths.get(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/", "Shaderdb");
			 	Class.forName("org.h2.Driver");
		        Connection con = DriverManager.getConnection(
		        "jdbc:h2:"+p5
			 	);
		        //IMG
		        rpta=null;
		        Statement stmtimg = con.createStatement();
		        ResultSet imagen1 = stmtimg.executeQuery("SELECT idCodigo, Imagen FROM codigo WHERE idCodigo ="+shaderse);
		        //System.out.println("Exc Query Image");
		              
		        while(imagen1.next()){
		        imagen = imagen1.getBlob("Imagen");
		        //rpta= javax.imageio.ImageIO.read(imagen.getBinaryStream());
		        BufferedImage image= javax.imageio.ImageIO.read(imagen.getBinaryStream());
		   	    BufferedImage resizedImage=resize(image,250,200);
		   	    rpta= resizedImage; 
		   	    
		        }
		        
		        //Descripción
		       	      		  
		        descrip=null;
		        Statement stmtdescrip = con.createStatement();
		        ResultSet descripsqls = stmtdescrip.executeQuery("SELECT idDescripcion, Descripcion FROM descripcion WHERE idDescripcion ="+shaderse);
		        System.out.println("Exc Query Descripcion");
		        
		                
		        
		        while(descripsqls.next()){
		          	descrip = descripsqls.getString("DESCRIPCION");
		        	System.out.println(descripsqls.getString("DESCRIPCION"));
		       
		        }	
		     
		  
		 }catch(SQLException ex) {
	        	
	        	System.out.println("Error MYSQL");
	        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        } catch(Exception e) {
	        System.out.println("Unexpected error: "+e.getMessage());
	    }
		 
		 
	 }//end inforeq
	 
	 public Editor editor() {
			return editor;
		}
	
	 
	 
	 
	 
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		    Graphics2D g2d = (Graphics2D) bi.createGraphics();
		    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		    g2d.drawImage(image, 0, 0, width, height, null);
		    g2d.dispose();
		    return bi;
	}//Resize image
	 
    
	}//end wizard