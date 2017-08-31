# slogo

A development environment that helps users write SLogo programs.

#Names

Jacob Conroy, Daniel Li, Ali Huang, Kyle Finke, and Andres Lebbos Habchi

#Date started, date finished, approximate number of hours spent

Started: February 14th. Finished: March 10th. Approximately 390 hours amongst the 5 of us.

#Each person's role in developing the project

Jake: For the first sprint, I developed the turtle screen class and its associated buttons. I developed the language choosing ability including updating the buttons with the translations into the selected language. I also developed the ability to change the image used for the turtle and the ability to change both the background and turtle trail colors. For the second sprint, I added CSS styling to the GUI, the ability to run multiple simulations simultaneously as separate tabs, bound the image and color choosing to the model with the setpalette command, and worked with Andres to develop XML writing and reading for the front and back end (and their associated buttons).

Daniel: During the first sprint, I worked on designing the Turtle, TurtleStates and TurtleTrail classes. I also animated the turtlescreen so that turtles would transition between states and draw lines based on TurtleTrails between each step of the animation. I wrote the Main, Model and UserInterface classes. During the second sprint, I refactored the Model to split into subcomponents TurtleModel, VisualOptionsModel, LanguageModel and ProgrammingModel. I set up listeners between model components so that changing variables that applied to all turtles would change all turtles. I set up the listeners between the model and front end components in the control so that the front end could be changed by altering a variable in the back end. I worked with Kyle to create the Model functions he needed to run his commands. I refactored the Model to remove redundant data structures such as a list of variable names. I refactored the program so that the language settings were moved from the Control to the model, and refactored Control to remove unnecessary code. I pair programmed with Jake to create multiple tabs.

Ali: My contributions were on the front-end side of the program. For the first sprint, I wrote the TextInput and ConsoleOutput objects, which is where the user can enter her commands and then see the output of the execution. I also authored the DropdownClickableObject, which was used to make the three histories for all commands, user-made commands, and user-made variables. I also made the help page where the HTML code is displayed to show the commands that the user can use. I contributed to some methods in the Interface class. For the second sprint, I made the PopUpSettings object which is used to display all of the current parameters of the turtle that was just clicked. This pop-up can directly edit the turtle. I also created an object ToggleSwitch for all of the switches. I made green circles for active turtles and these circles can be hidden. I contributed to some methods in the TurtleScreen class to facilitate these changes.

Kyle: For the project, I designed, wrote the code for, and debugged all of the commands. I created a commands hierarchy with a basic command at the top followed by groups of commands such as TurtleCommands and MathCommmands and the lowest level of the hierarchy are the individual commands themselves. As an additional extension, the additional command Deactivate was added to allow users to deactivate a group of currently active Turtles without deactivating all of the currently active Turtles. For the commands, I also wrote the CommmandExceptions class and the commandExceptions.properties file to allow commands to properly handle invalid inputs from the Parser or the user. As a part of working on the commands I worked closely with Andres, who worked on the parser. With Andres, I helped design the connection between the commands and the parser. Ultimately, we decided to use reflection to instantiates commands in the Parser and in order to do this, I designed each command to have the same constructor and execute method parameters. Additionally, I worked with Daniel on the Model to ensure that the correct data was available to all of the commands, for instance the SetPenColor command requires the ability to set the pen color through the Model based on an index passed from the Parser that was entered by the user in the GUI. I also did significant testing and debugging of the project by testing the different features and commands available to the user to determine where the issues were in the code and work with the proper person on the team to fix the errors.

Andres: My main job is the Parser: From the parser I handle multiple turtles, groups, and parsing. The parsing handles groups inside and outside chains of parsing and nested groups and lists so if you have repeat ( sum 1 2 3 ) [ repeat sum 1 2 [ fd 10 ] ] it works. Also, the parser understand the creation and execution of user made commands and when these happen it makes sure  that it is actually doing what the user wants it to do by creating or executing commands and by overwriting commands if necessary. This not only applies to commands but also to variables. Additionally, the parser throws specific exceptions that are only needed to show with the user because they are in made exceptions that allow just to get the message that is user friendly in order to show the user that message. Also, all these exceptions extend ParserException so that in terms of design the architecture of exceptions is, in terms of design, effective. Finally, I worked with Jake to develop XML writer and reader for both a library in the back end or the visuals in the front end. We also added the respective buttons for these features.

#Books, papers, online resources, and people used for help

https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
http://www.oodesign.com/
http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
http://www.java2s.com/Code/Java/Collections-Data-Structure/ConvertaQueuetoaList.htm
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
https://docs.oracle.com/javase/8/docs/api/java/util/List.html
https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
http://stackoverflow.com/questions/18341777/convert-color-rgb-to-hex-int-result
https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html
http://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
https://translate.google.com/
http://stackoverflow.com/questions/3064423/how-to-convert-an-array-to-a-set-in-java
http://stackoverflow.com/questions/715650/how-to-clone-arraylist-and-also-clone-its-contents
http://stackoverflow.com/questions/12006170/what-does-mean-in-java
https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
http://www.javaworld.com/article/2076721/core-java/designing-with-exceptions.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Tab.html#setClosable-boolean-
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Tab.html#closableProperty
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
http://stackoverflow.com/questions/27203671/javafx-how-to-clear-the-canvas
https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableList.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ColorPicker.html
https://docs.oracle.com/javafx/2/api/javafx/scene/image/Image.html
http://stackoverflow.com/questions/18146712/combo-box-select-item-in-javafx-2
http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextField.html
https://docs.oracle.com/javafx/2/api/javafx/scene/paint/Color.html
https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableMap.html
https://docs.oracle.com/javase/8/javafx/api/javafx/collections/MapChangeListener.html
https://docs.oracle.com/javafx/2/api/javafx/beans/value/ObservableValue.html
https://docs.oracle.com/javafx/2/api/javafx/beans/value/ObservableStringValue.html

Jake asked for help and guidance from Yumin, my TA. As a group we definitely consulted with Professor Duvall. Kyle got guidance from his TA Jay. We also used the example_advanced and example_xml codes that Professor Duvall provided in class. 

#Files used to start the project

Just run "Main.java" in the main package.

#Files used to test the project

We tested the ability to do command and variable setting with the "flower.logo" file (changing the size of the parameter to 8). We also tested the ability of our program to do recursion using the "spiral.logo" file (with a parameter of 10). Most of the gui components we simply needed to test by running the program itself. For ensuring that the XML files were saved and loaded correctly, we made TEST.xml and TryFlower.xml in the data folder and the latter of this can be loaded and then the flower command can be run without having to execute a "to flower..." command. Test.xml has saved the necessary components from the GUI. 

#Any data or resources required by the project

All required resource files are in the "resources" package in the "src/" folder of the project. Everything "required" can be found here. Additional files that can be used in the project are found in the outermost portion of the "data/" folder within this project. The relevant files are the ones ending in ".xml" and each front end xml file contains "BackgroundColor", "ImageNumber", "NumberOfTurtles", and "TrailColor". Each back end xml file contains a list of commands and a list of variables.

#Any information about using the program

Try "spiral.logo" to see recursion work! Load the TryFlower.xml to the library and then type in "tell [ 1 2 4 6 ] flower id" to watch multiple turtles draw their own flower according to the size of their ids! Press the Hide/Show button on the gui to remove the text boxes and press it again to bring them back. Groups work in the back end with Turtle, Boolean, and Math commands. The buttons and text items in the gui all change language upon switching the language and setting the language. Listeners are implemented so that front end components are modified by editing data structures in the back end.

#Known bugs, crashes, or problems with functionality

You cannot run a misc command with commands in its parameters. DOTIMES is not working for commands within the first set of brackets. 

#Extra features

Grouping, scope, and recursion are all implemented in the back end.

#Impressions of the assignment

We found the project to be very fun and informative to write from a design perspective. We certainly learned a lot about writing code that is amenable to extension with little modification to existing classes. From an optimization perspective, we found this project to be difficult. We understand that this is not the goal of the course, but with so much happening in the project on all levels, entering in commands that make lots of turtles/trails drawn will make the program lag pretty significantly. It'd be nice if there were less features to implement and we could have spent a little bit of time ensuring that larger amounts of commands could be entered well. Also we felt the additions to the front end of the project in the second sprint to be quite a bit compared to the back end. Finally, we wanted to say that splitting up the front and back end into two teams was a welcome suggestion and made the project much less daunting to complete. We found it to be very useful to abstract away certain objects by splitting up the project in this way: front end people don't need to know much about the parser or commands and back end people don't need to know much about the rendering of the turtle display.