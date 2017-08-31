##Part 1
**What about your API/design is intended to be flexible?**
	My design is intended to support multiple turtles on the front end, and the storage of multiple future TurtleStates.

**How is your API/design encapsulating your implementation decisions?**
	My Turtles store their TurtleStates in an ArrayList, but this is hidden from the other classes- they cannot return the ArrayList of states. The getter methods for the Turtle’s current state returns values based off the first entry in the Turtle’s ArrayList, but this is also not known to other classes.
	It is flawed because the front end could potentially modify the TurtleStates that it is supposed to be reading using its addTurtleState function.

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**
Turtles will throw StateNotFound exceptions if they do not store enough states in their list to actually be able to perform commands. For example, if the turtle has 0 states in its list, there is no meaningful way to get the x value of the turtle’s position, so getX() throws an exception. Similarly, if the turtle only has 1 state in its list, the turtle cannot draw a new trail, so drawNextTrail throws a StateNotFoundException.

**Why do you think your API/design is good (also define what your measure of good is)?**
Both people should complete Part 1 before continuing on to Part 2.
	It supports multiple Turtles and states, so that commands can be given to turtles even with multiple future states.

##Part 2
**How do you think Design Patterns are currently represented in the design or could be used to help improve the design?	**
A reflection-based Factory will probably be used to instantiate our commands classes from user input in an efficient fashion. A Mediator will be used to connect the front end and the back end without “tightly” linking the two components, and Decorator patterns may be used on UI components such as the display canvas and the text boxes to allow the users to add additional functionalities to JavaFX tools.

**How do you think the "advanced" Java features will help you implement your design?**
	Our team is planning on using reflection to implement our commands. Each command will correspond to a single class within the back end. Observers for the Turtle’s could provide a non- collection based method of updating the display screen based on changes in the Turtle’s state.

**What feature/design problem are you most excited to work on?**
	I’m excited to set up the GUI class because it provides an opportunity for me to learn about the workings of user interfaces.

**What feature/design problem are you most worried about working on?**
	I’m the most worried about setting up the GUI class, as I don’t have much experience with user interfaces.

Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
**Use case 1:**
The back end calls the front end to instantiate a new turtle.

A Turtle is instantiated with its default constructor Turtle(), setting its position, heading, and draw
style to default parameters. It is then added to the collection of turtles stored in the Parser. Then, when fd 100 is called on the turtle, the back end uses the getFinalTurtleState and the getter methods within the TurtleState class to generate the parameters for a new TurtleState. It then calls the function addTurtleState on the Turtle to add a new kind of TurtleState to the Turtle. 

**Use case 2:**
The turtle display screen tries to draw trails corresponding to the movement of the turtle.

Whenever the display screen is told to update itself, it iterates through the collection of turtles that it is told to refer to. For each turtle, the display screen calls the Turtle’s drawTurtleTrail function to produce a new TurtleTrail object. drawTurtleTrail looks at the first two elements of the Turtle’s list, removes the first element, and uses the two elements to create the parameters for a line as represented by the TurtleTrail. It can then use the getter methods within the TurtleTrail to produce the line it needs.

**Use case 3:**
drawTurtleTrail is called on a turtle with only 1 state in it.

The Turtle throws a StateNotFoundException, because drawTurtleTrail requires the turtle to have at least two states stored in its internal list.

**Use case 4:**
The display screen places a turtle corresponding to the parameters in its current state.

The display screen calls the getter methods within the turtle that poll the front state in its list of states. Based on the getter methods within this TurtleState, it returns the position of the turtle, the orientation of the turtle, and whether it should be displaying right now.

**Use case 5:**
The turtle receives a “pu” command.

The addTurtleState() command is called, causing the turtle to add a new state to its queue. This state is identical to the last state in the list, except that the boolean variable penDown is set to false.