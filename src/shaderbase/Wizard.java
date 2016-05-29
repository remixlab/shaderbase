package shaderbase;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.NoWorkTreeException;



import processing.app.ui.Editor;
import processing.app.Base;
import processing.app.Sketch;
import processing.app.SketchCode;


public class Wizard {

	String searchin = "shader";
	JTextArea output;
	JList list;
	JTable table;
	String newline = "\n";
	String descrip = "Welcome to ShaderBase";
	ListSelectionModel listSelectionModel;
	// String[] listadata = {"hola1", "hola2", "hola3","hola4"};
	String[] listadata = null;
	// String[] prueba = {"0", "1", "2", "3"};
	String[] prueba = null;
	int cuentat = 0;
	String shaderse;
	String shadersename;
	JButton save;
	JButton search;
	JButton git;
	JButton upload;
	JButton update;
	JButton delete;
	Image rpta = null;
	boolean updatecheck;

	final JLabel picLabel = new JLabel();
	final static String BUTTONPANEL = "Download";
	final static String TEXTPANEL = "Upload";
	final static String TEXTPANEL1 = "Update";
	final static int extraWindowWidth = 100;
	final JPanel card1 = new JPanel();
	final JPanel card2 = new JPanel();
	final JPanel card3 = new JPanel();
	String labelText;
	final JTextArea textarea = new JTextArea();
	JTextField searchtext;
	private Editor editor = null;
	static String OS = System.getProperty("os.name").toLowerCase();
	Path pathos;
	String[] searchid2 = null;
	String[] searchnames2 = null;
	String[] searchfolder2 = null;

	public Wizard(Editor editor) {
		this.editor = editor;
		// TODO Auto-generated constructor stub
	}

	public void addComponentToPane(Container pane) throws TransportException,
			GitAPIException, IOException, ParseException {
		JTabbedPane tabbedPane = new JTabbedPane();
		// OS Check

		// Prints OS
		// System.out.println(OS);

		File sketchbookFolder = Base.getSketchbookFolder();
		System.out.println("Test");
		System.out.println(sketchbookFolder);
		System.out.println("End Test");
		
		
		if (isWindows()) {
			System.out.println("Windows");
			//pathos = Paths.get(System.getProperty("user.home"),
			//		"Documents/Processing/tools/ShaderBase/tool/Shaderepo");
			String pathostring = sketchbookFolder.toString() + "/tools/ShaderBase/tool/repo";
			pathos = Paths.get(pathostring);
		} else if (isMac()) {
			System.out.println("Mac iOS");
			//pathos = Paths.get(System.getProperty("user.home"),
			//		"Documents/Processing/tools/ShaderBase/tool/Shaderepo");
			String pathostring = sketchbookFolder.toString() + "/tools/ShaderBase/tool/repo";
			pathos = Paths.get(pathostring);
		} else if (isUnix()) {
			System.out.println("Linux");
			//pathos = Paths.get(System.getProperty("user.home"),
			//		"sketchbook/tools/ShaderBase/tool/Shaderepo");
			String pathostring = sketchbookFolder.toString() + "/tools/ShaderBase/tool/repo";
			pathos = Paths.get(pathostring);
		} else if (isSolaris()) {
			System.out.println("Solaris");
		} else {
			System.out.println("Your OS is not supported!!");
		}

		// Shaderepo data (Yes = Pull) (No = Clone)

		if (Files.exists(pathos)) {
			System.out.println("Folder");

			int selectedOption = JOptionPane.showConfirmDialog(null,
					"Want to check for updates??", "Choose",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				try {
					updatecheck = true;

					Pull pull = new Pull(pathos, editor);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (GitAPIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoWorkTreeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String searchin1 = "shader";
				Search search2 = new Search(searchid2, searchnames2, editor,
						pathos, searchfolder2, searchin1);
				listadata = search2.searchnames;
				// prueba = search2.searchid;
				prueba = search2.searchfolder;

			} else {
				// Pull pull = new Pull(null, pathos); (Falla Encabezado revisar
				// como aconsejo grupo jgit Eclipse)

				// NO UPDATE
				JOptionPane.showMessageDialog(null,
						"ShaderBase will upload new shaders after next update",
						"Inane warning", JOptionPane.WARNING_MESSAGE);

				updatecheck = false;
				String searchin1 = "shader";
				Search search2 = new Search(searchid2, searchnames2, editor,
						pathos, searchfolder2, searchin1);
				listadata = search2.searchnames;
				// prueba = search2.searchid;
				prueba = search2.searchfolder;
			}

		} else {

			updatecheck = true;

			// System.out.println("No Folder");

			// default title and icon
			JOptionPane
					.showMessageDialog(null,
							"ShaderBase will download the shaders info, could take some minutes");

			// int selectedOption = JOptionPane.showConfirmDialog(null,
			// "Shader Tool needs to download all the repo data in order to continue",
			// "Choose",
			// JOptionPane.YES_NO_OPTION);
			// if (selectedOption == JOptionPane.YES_OPTION) {

			// JProgressBar aJProgressBar = new
			// JProgressBar(JProgressBar.HORIZONTAL);
			// final JFrame frame = new JFrame("Stepping Progress");
			// final JDialog dlg = new JDialog(frame, "Progress Dialog", true);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// aJProgressBar.setIndeterminate(true);
			// dlg.add(BorderLayout.CENTER, aJProgressBar);
			// aJProgressBar.setStringPainted(true);

			// frame.add(aJProgressBar);

			// frame.setSize(400, 20);
			// frame.setLocationRelativeTo(null);
			// frame.setUndecorated(true);
			// frame.setVisible(true);

			// Thread t = new Thread(new Runnable() {
			// public void run() {
			// frame.setVisible(true);
			// }
			// });

			// Toolkit toolkit = Toolkit.getDefaultToolkit();
			// toolkit.setCursor
			// (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			// Ini??

			// t.start();

			Clone clone = new Clone(null, pathos); // Clone Git
			Index index = new Index(pathos, null); // Index Lucene
			Search search1 = new Search(searchid2, searchnames2, editor,
					pathos, searchfolder2, searchin);

			// System.out.println(search.searchnames[2]);
			// System.out.println(search.searchfolder[3]);
			// System.out.println(search.searchid[4]);

			listadata = search1.searchnames;
			prueba = search1.searchfolder;
			// frame.setVisible(false);

			// }else {

			// }
		}

		// Initial values
		Start();

		String[] listData = listadata;
		list = new JList(listData);

		listSelectionModel = list.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(list);

		// Build output area.
		output = new JTextArea(1, 10);
		output.setEditable(false);
		JScrollPane outputPane = new JScrollPane(output,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Do the layout.
		// JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel splitPane = new JPanel();
		// add(splitPane, BorderLayout.CENTER);

		JPanel topHalf = new JPanel();
		topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
		JPanel listContainer = new JPanel(new GridLayout(1, 1));
		// listContainer.setBorder(BorderFactory.createTitledBorder("Shader List"));
		// TitledBorder title;
		// title = BorderFactory.createTitledBorder(null, "Shader List");
		// title.setTitlePosition(TitledBorder.TOP);
		// listContainer.setBorder(title);

		listContainer.add(listPane);

		// topHalf.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
		topHalf.add(listContainer);
		// topHalf.add(tableContainer);

		// topHalf.setMinimumSize(new Dimension(100, 50));
		topHalf.setPreferredSize(new Dimension(450, 200));
		// splitPane.add(topHalf); **

		JPanel bottomHalf = new JPanel(new BorderLayout());
		// JPanel buttons = new JPanel();

		// Cambio
		bottomHalf.add(outputPane, BorderLayout.CENTER);
		// XXX: next line needed if bottomHalf is a scroll pane:
		// bottomHalf.setMinimumSize(new Dimension(400, 50));
		bottomHalf.setPreferredSize(new Dimension(400, 135));
		// bottomHalf.add(save);
		// bottomHalf.add(search);
		// splitPane.add(bottomHalf); **

		// textarea.setBorder(new EmptyBorder(5, 5, 10, 5));
		textarea.setBackground(null);
		textarea.setEditable(false);
		// textarea.setHighlighter(null);
		textarea.setFont(new Font("Dialog", Font.PLAIN, 12));

		// Card layout.
		final JPanel card1 = new JPanel() {

			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += extraWindowWidth;
				return size;
			}
		};

		// textarea.append(descrip);
		// card1.add(textarea); **
		// card1.add(new JButton("Button 1"));
		save = new JButton("Download");
		save.setEnabled(false);
		delete = new JButton("Delete");
		upload = new JButton("Upload");
		delete.setEnabled(false);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sketchcode = null;
				boolean check;
				editor.getBase();
				editor.getBase();
				// System.out.println(pdecode);
				// sketchcode = editor.getText();

				Editor editor2 = editor.getBase().getActiveEditor();

				Sketch sketch = editor2.getSketch();
				File sketchFolder = sketch.getFolder();
				String folderpath = sketchFolder.toString();
				File Folder = new File(folderpath + "/data");
				File Folder1 = new File(folderpath);
				String maintab = sketch.getName();
				// System.out.println(maintab);
				int numtab = sketch.getCodeCount();
				SketchCode[] tabs = sketch.getCode();

				if (numtab > 1) {

					editor2.getSketch().handleNextCode();
					sketchcode = editor2.getSketch().getMainProgram();

				} else {

					sketchcode = editor2.getText();

				}

				if (sketchcode.isEmpty()) {

					Save save = new Save(shaderse, editor2, shadersename);
					System.out.println("Loading .....");

				} else {

					/*
					 * Code to load the shader in a new sketch if the sketch has
					 * code
					 */

					//

					editor2.getBase().handleNew();

					java.util.List<Editor> editorlist = editor.getBase()
							.getEditors();
					Editor editornew = editorlist.get(editorlist.size() - 1);
					// String testnew =
					// editornew.getSketch().getFolder().toString();
					// System.out.println(testnew);

					Save save = new Save(shaderse, editornew, shadersename);
					System.out.println("Loading .....");
					//

					/*
					 * Code to load the shader in the same skecth always
					 * 
					 * 
					 * int selectedOption1 = JOptionPane.showConfirmDialog(null,
					 * "The sketch has a code already,\n" +
					 * "the code will be deleted\n" +
					 * "Do you want to continue???", "Choose",
					 * JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
					 * 
					 * if (selectedOption1 == JOptionPane.YES_OPTION) {
					 * 
					 * // editor.handleSave(true); //editor.
					 * 
					 * if(numtab>1){
					 * 
					 * 
					 * 
					 * File dir = sketchFolder; String[] dirinfo = dir.list();
					 * for (int x=0;x<dirinfo.length;x++){
					 * if(dirinfo[x].contains(maintab)){
					 * 
					 * }else{ if(dirinfo[x].contains(".pde")){ Path path =
					 * Paths.get(folderpath+"/"+dirinfo[x]);
					 * //System.out.println(path); try {
					 * Files.deleteIfExists(path); } catch (IOException e1) { //
					 * TODO Auto-generated catch block e1.printStackTrace(); }
					 * 
					 * }
					 * 
					 * } }
					 * 
					 * 
					 * //for(int x=1;x<numtab;x++){
					 * 
					 * //} }
					 * 
					 * try { FileUtils.cleanDirectory(Folder);
					 * //FileUtils.cleanDirectory(Folder1); } catch (IOException
					 * e1) { // TODO Auto-generated catch block
					 * e1.printStackTrace(); } sketch.reload(); Save save = new
					 * Save(shaderse,editor,shadersename);
					 * System.out.println("Loading .....");
					 * 
					 * }
					 */

				}

			}

		});

		searchtext = new JTextField("Search ie: blur, texture", 50);
		// card1.add(new JTextField("Search for a Shader", 50));

		search = new JButton("Search");
		update = new JButton("Update");

		// card1.add(upload); **
		// card1.add(update); **

		// group grid
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(
				layout.createParallelGroup(LEADING)
						.addGroup(
								layout.createSequentialGroup()
										.addComponent(searchtext)
										.addComponent(search))
						.addGroup(
								layout.createParallelGroup(LEADING).addGroup(
										layout.createSequentialGroup()

										.addComponent(topHalf)
												.addComponent(picLabel)))

						.addGroup(layout.createParallelGroup(LEADING)

						.addGroup(layout.createSequentialGroup()
						// .addGroup(layout.createParallelGroup(LEADING)
								.addComponent(bottomHalf)))

						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.TRAILING)
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(save)
														// .addComponent(update)
														.addComponent(delete)))

		));

		layout.setVerticalGroup(layout
				.createSequentialGroup()

				.addGroup(
						layout.createParallelGroup(BASELINE)
								.addComponent(searchtext).addComponent(search))
				.addGroup(
						layout.createParallelGroup(BASELINE)
								.addComponent(topHalf).addComponent(picLabel))

				.addGroup(
						layout.createParallelGroup(BASELINE).addComponent(
								bottomHalf)
				// .addComponent(save)
				)

				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.TRAILING)
						// .addGroup(layout.createSequentialGroup()
								.addComponent(save)
								// .addComponent(update)
								.addComponent(delete))

		);

		// card1.removeAll();
		card1.add(panel);

		// Show

		// card1.add(searchtext);
		// card1.add(search);
		// card1.add(splitPane);
		// card1.add(topHalf);
		// card1.add(picLabel);
		// card1.add(bottomHalf);

		// Upload

		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Upload Upload = new Upload(editor, pathos, card2,
							listadata, updatecheck);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (GitAPIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		// DELETE LOCAL

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Start();

				Delete delete = new Delete(shaderse, editor, pathos);

				shaderse = "1";

				/*
				 * String searchin = "shader"; Search search; try { search = new
				 * Search(searchid2, searchnames2, editor, pathos,
				 * searchfolder2, searchin); search.changeSearch(searchin);
				 * 
				 * //String[] listadata1 = new String[search.searchnames];
				 * 
				 * prueba = search.searchfolder; listadata = search.searchnames;
				 * list.setListData(listadata);
				 * 
				 * } catch (ParseException e1) { // TODO Auto-generated catch
				 * block e1.printStackTrace(); }
				 */

			}

		});
		// UPDATE PULL

		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Pull pull = new Pull(pathos, editor);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (GitAPIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoWorkTreeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		// card1.add(search);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchin = searchtext.getText();
				Search search;
				try {
					search = new Search(searchid2, searchnames2, editor,
							pathos, searchfolder2, searchin);
					search.changeSearch(searchin);

					// String[] listadata1 = new String[search.searchnames];

					prueba = search.searchfolder;
					listadata = search.searchnames;
					list.setListData(listadata);

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// String[] inid = search.searchid;
				// String[] inname = search.searchnames;

				// System.out.println(inname[0]);
				// System.out.println(inid[0]);
				// list.setListData(inname);
				// prueba = inid;
			}

		});

		// card1.add(new JButton("Save"));

		// Menu 2

		// card2.add(new JTextField("Search for a Shader", 50));

		git = new JButton("GitPrueba");
		// card2.add(git);
		// card2.add(new JButton(""));

		git.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Prueba Git .....");

			}

		});

		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if (sourceTabbedPane.getTitleAt(index) == "Upload")
					;
				{

					// Editor editor2 = editor.getBase().getActiveEditor();

					// if (updatecheck == true){

					try {
						Upload Upload = new Upload(editor, pathos, card2,
								listadata, updatecheck);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (TransportException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (GitAPIException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// }

					/*
					 * if (updatecheck == false){
					 * 
					 * 
					 * try { UploadNU UploadNU = new UploadNU(editor, pathos,
					 * card2, listadata, updatecheck); } catch (IOException e1)
					 * { // TODO Auto-generated catch block
					 * e1.printStackTrace(); } catch (TransportException e1) {
					 * // TODO Auto-generated catch block e1.printStackTrace();
					 * } catch (GitAPIException e1) { // TODO Auto-generated
					 * catch block e1.printStackTrace(); }
					 * 
					 * 
					 * }
					 */

				}

				System.out.println("Tab changed to: "
						+ sourceTabbedPane.getTitleAt(index));
			}
		};
		tabbedPane.addChangeListener(changeListener);

		tabbedPane.addTab(BUTTONPANEL, card1);
		tabbedPane.addTab(TEXTPANEL, card2);
		// /tabbedPane.addTab(TEXTPANEL1, card3);

		pane.add(tabbedPane, BorderLayout.CENTER);

	}// end component

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();

			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();
			// boolean isAdjusting = e.getValueIsAdjusting();
			// output.append("Event for indexes "
			// + firstIndex + " - " + lastIndex
			// + "; isAdjusting is " + isAdjusting
			// + "; selected indexes:");

			// output.append("selected indexes:");
			output.append(descrip);

			if (lsm.isSelectionEmpty()) {
				output.append("");
			} else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						output.append(" " + i);

						shaderse = prueba[i];
						shadersename = listadata[i];

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

			// DELETE
			if (shadersename.contains("(LOCAL)")) {
				delete.setEnabled(true);

			} else {
				delete.setEnabled(false);
			}
			// DELETE

			one = new ImageIcon(rpta);
			picLabel.setIcon(one);

			picLabel.setIcon(new ImageIcon(rpta));
			labelText = descrip;
			// labelText = "Hola";

			// card1.add(picLabel);

		}
	}// SharedList Listener

	public void inforeq() {

		// Values IMG, Description, etc (TAGS)
		try {

			// IMG JPG
			rpta = null;

			try {
				// BufferedImage image = ImageIO.read(new
				// File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/img.jpg"));

				// BufferedImage image = ImageIO.read(new File(shaderse
				// +"/"+"shadersename"+".img"));
				String a = shaderse + "/" + shadersename + ".jpg";
				BufferedImage image = ImageIO.read(new File(a));
				// System.out.println(a);

				BufferedImage resizedImage = resize(image, 250, 200);
				rpta = resizedImage;
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Description

			descrip = null;

			// String dir = new
			// File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/img.jpg").toString();
			String a = shaderse + "/" + shadersename + ".txt";

			// File dir2 = new
			// File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/Prueba.txt");

			File dir2 = new File(a);

			try {

				BufferedReader br = new BufferedReader(new FileReader(dir2));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				String everything = sb.toString();
				// System.out.println(everything);
				descrip = everything;
			}

			catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}

	}// end inforeq

	// Ini

	public void Start() {

		try {

			// IMG JPG
			rpta = null;

			try {
				String imgini = pathos.toString();
				String imgpathini = null;
				if (imgini.contains("Shaderepo")) {
					String div = imgini;
					String parts[] = div.split("Shaderepo");
					imgpathini = parts[0] + "ShaderTool.jpg";

					System.out.println(imgpathini);
				}

				// BufferedImage image = ImageIO.read(new
				// File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/img.jpg"));

				// BufferedImage image = ImageIO.read(new File(shaderse
				// +"/"+"shadersename"+".img"));

				BufferedImage image = ImageIO.read(new File(imgpathini));
				// System.out.println(a);

				BufferedImage resizedImage = resize(image, 250, 200);
				rpta = resizedImage;

				ImageIcon one;

				one = new ImageIcon(rpta);
				picLabel.setIcon(one);

			} catch (IOException e) {
				e.printStackTrace();
			}

			// Description

			descrip = null;

			// String dir = new
			// File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/img.jpg").toString();
			String txtini = pathos.toString();
			String txtpathini = null;
			if (txtini.contains("Shaderepo")) {
				String div = txtini;
				String parts[] = div.split("Shaderepo");
				txtpathini = parts[0] + "Welcome.txt";
			}

			// File dir2 = new
			// File(System.getProperty("user.home"),"Documents/Processing/tools/ShaderTool/tool/Prueba.txt");

			System.out.println(txtpathini);

			File dir2 = new File(txtpathini);

			try {

				BufferedReader br = new BufferedReader(new FileReader(dir2));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				String everything = sb.toString();
				// System.out.println(everything);
				descrip = everything;
				labelText = descrip;
			}

			catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}

	}// end Ini

	public Editor editor() {
		return editor;
	}

	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		int original_width = image.getWidth();
		int original_height = image.getHeight();
		int bound_width = width;
		int bound_height = height;
		int new_width = original_width;
		int new_height = original_height;

		// first check if we need to scale width
		if (original_width > bound_width) {
			// scale width to fit
			new_width = bound_width;
			// scale height to maintain aspect ratio
			new_height = (new_width * original_height) / original_width;
		}

		// then check if we need to scale even with the new height
		if (new_height > bound_height) {
			// scale height to fit instead
			new_height = bound_height;
			// scale width to maintain aspect ratio
			new_width = (new_height * original_width) / original_height;
		}

		BufferedImage bi = new BufferedImage(new_width, new_height,
				BufferedImage.TRANSLUCENT);
		BufferedImage bi2 = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi2.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		int x = (width - bi.getWidth(null)) / 2;
		int y = (height - bi.getHeight(null)) / 2;
		g2d.drawImage(image, x, y, new_width, new_height, null);
		g2d.dispose();

		return bi2;
	}// Resize image

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}// end win

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}// end Mac

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
				.indexOf("aix") > 0);

	}// end Linux

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}// end Solaris

}// end wizard