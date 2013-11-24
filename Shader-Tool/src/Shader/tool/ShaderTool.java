
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

import javax.swing.JFrame;
import processing.app.*;
import processing.app.tools.*;
import processing.app.Base;
import processing.app.Editor;
import processing.app.Sketch;
import processing.app.SketchCode;
import processing.core.PApplet;
import Shader.tool.Wizard;

 
/**
 * GUI tool for shader tool
 */

public class ShaderTool extends JFrame implements Tool{ 
 //public class ShaderTool implements Tool {
 Editor editor;
 
 //Variables 

  

public String getMenuTitle() {
	    return "Shader Tool";
}

public void init(Editor editor) {
this.editor = editor;



 
}








public void run() {

	 
	        //Create and set up the window.
	        JFrame frame = new JFrame("Shader Tool");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Create and set up the content pane.
	        Wizard ini = new Wizard();
	        ini.addComponentToPane(frame.getContentPane());
	 
	        //Display the window.
	        
	        frame.setSize(800,500);
	        frame.pack();
	        frame.setVisible(true);
	    
	 
	
	

}

	
	

}

 
	