Welcome to the Processing ShaderTool!
===================

## About

*ShaderTool*  is a free software tool developed for [Processing](http://www.processing.org/) with the idea of creating a structured database of 
shaders made by any user, from the web and any other source that allows to export and import shaders from the database to *Processing* and run them.

With the idea of helping the user finding a shader inside the database a search engine was implemented with the use of tags and other relevant
information trying to return an accurate search result about the shaders inside the database. All the information and data inside *ShaderTool*
is hold in another [Github repository](https://github.com/remixlab/shaderdb). The main idea is to send all the shaders information
from *Processing* to Github with *ShaderTool*.

## Working OS

* Windows: Tested
* Linux: Tested, [Ubuntu](http://www.ubuntu.com/) and [ArchLinux](https://www.archlinux.org/)
* iOS: Not tested

## Installation

1. Download ans install Processing from [here](http://www.processing.org/download) 
2. Download the latest version of ShaderTool from [here](https://github.com/remixlab/shader_tool/releases/download/v-1.0.0-alpha.1/shadertool-1.0.0-alpha1.zip)
3. Extract the zip file to the Processing default tool folder location
(the location of the sketchbook folder is shown in the Processing's Preferences dialog). If not already present, create a folder named "tools" inside your Sketchbook folder.
4. Restart Processing.
5. You will find a Shader Tool in the Processing tool's menu. 

## Interface

###Simple Search
Shader Tool has a very simple interface.  
<br/>
![Shader Tool Interface](https://raw.githubusercontent.com/anfgomezmo/ImageReadme/master/Images/Shader%20Tool/ShaderTool1.jpg)
<br/>
There is a menu where all the shaders inside the database are indexed, as default this menu will show all the shaders indexed in the database.
Once a search is done the relevant shaders will be shown.

<!--- redactar mejor esta idea:  
The middle shows a small record of the shaders such as the uploader, tags or a small description and the last part is an image of the shader. 
-->

### Upload
<br/>
![Shader Tool Interface2](https://raw.githubusercontent.com/anfgomezmo/ImageReadme/master/Images/Shader%20Tool/ShaderTool2.jpg)
<br/>

If you want to upload a shader a sub-menu will appear. It is recommended that you fill all the fields since they are used to improve the
semantic search queries.

<!--- no entiendo muy bien esto:  
You can keep the changes in your local machine and upload them later once 
you upload other shader (commit).
-->

### Notes

* Uploading shaders from multiple tabs isn't supported yet.
* The ShaderTool will ask you to update your local db every time it's loaded. Do it everytime you plan to update
your own shaders to the [ShaderDB repository](https://github.com/remixlab/shaderdb) to avoid possible conflicts with other users' updates.
it is recommended to update every time you start ShaderTool.
* If you use a shader name that already exits, the older shader will be edited.

## License

The ShaderTool is released under the terms of the [GPL-v3](http://www.gnu.org/licenses/gpl.html). All the shaders are released by:


[![](http://i.creativecommons.org/l/by-nc-sa/3.0/88x31.png)](http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_US)

## Contact

If you want to remove a shader from the data base, report a bug or helping us improving the tool please send an email to shadertool@gmail.com  

## Links

For more information about  how to use  shaders in Processing follow this [tutorial](http://processing.org/tutorials/pshader/)
