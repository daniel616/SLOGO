#Internal Back-end API

Kyle Finke and Matthew Barbano

###Part 1

1. What about your API/design is intended to be flexible?

The internal back-end API is intended to be flexible in adding new commands or groups of commands. Each new command is added as a subclass to a type of command class. For example, the forward command is a subclass of the TurtleCommands class. Further, types of commands, such as TurtleCommands can be added as subclasses to the top level Commands class to make it easier to add different groups of commands under one group. This increases flexibility because existing classes do not have to be altered to add new commands or groups of commands which allows the code to obey the open-close principle.

2. How is your API/design encapsulating your implementation decisions?

The API encapsulates our implementation decisions because it provides a degree of separation between the parser and the commands so that the parser simply interprets input from the front-end and then passes along the necessary information and allows the commands to interact with the information to return the correct values and, if necessary, alter the state of the Turtle.

3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Errors in the commands will be thrown and required to be handled by the class that passed the faulty information to the command class. Possible exceptions in commands include invalid data being passed to the command class, such as a negative number being passed to the log command class. Also, turtle commands should check if the turtle object passed is valid (i.e. not null) and throw an invalid turtle error otherwise.

4. Why do you think your API/design is good (also define what your measure of good is)?

Our API/design is good because it provides a clear separation of requirements between the different sections of the project. For example, the parser only handles interpreting instructions and the commands only handle returning the proper output based on the information passed to them. My measure of good is that the code is sufficiently modular and extendable to make it easier for an outside coder to understand the connection between classes and relatively easily add their own features to the program after understanding how similar current features work.


###Part 2

1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

Our current design represents an Interpreter in the form of the parser class because the parser interprets strings entered from the user and converts them to commands to be executed, some of which occur on the Turtle. 

2. How do you think the "advanced" Java features will help you implement your design?

The reflection feature will help in the parser by allowing us the create the proper class based on the name of the command entered from the user. The bindings feature may also be useful to update the user interface when a command changes the state of a Turtle object so the user interface can properly update the display to show the Turtle with its updated state.

3. What feature/design problem are you most excited to work on?

I am most excited to work on altering the state of the Turtle through the commands class so that it shows up properly on the user interface.

4. What feature/design problem are you most worried about working on?

I am most worried to work on Variables, Control Structures, and User-Defined Commands because it will be difficult to handle the order of interpreting commands in the parser and implementing them in the commands classes.

5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

* The user wants to randomly pick a number from 0 to 100.:  Random class is called with the variable 100 representing the max number a random number can be: the Random class, a subclass of MathCommands, returns Math.random()*100 to the CommandParser class.

* The user wants to move towards (10,6): Towards class is called with the variables (10,6) representing the direction the turtle should point: the Towards class, a subclass of TurtleCommands, which is called using the execute(Turtle t) method, will get the last TurtleState object from the queue inside the Turtle it is passed (t.getFinalTurtleState()) and based on the current location of the Turtle will calculate the angle of the heading the Turtle must have to point towards (10,6). Then the Towards class will create a new TurtleState object (assumed it is called info) and pass this to the Turtle to add to the queue of TurtleState objects (t.addTurtleState(info)).

* The user wants to repeat the command "fd 40" 3 times: Repeat class is passed as a parameter the number of times it must repeat the command, an instance of Forward with the proper distance already stored in the command. Then Repeat calls execute on that instance of Forward 3 times while summing the return values of all three execute methods. Finally, Repeat returns 120, the total distance moved by forward.

* The user wants to make a variable named "variable" with the value of 0: Make class is called and passed the variable name and the value to be stored. The variable name is checked against a HashMap of variable to see if it already exists. The variable name then becomes the key and 0 becomes the value. Finally, zero is returned.

* The user enters the command "LOG -1": The Log class is passed the value -1. Since log cannot return imaginary values, the Log class will throw an error that an invalid value was entered.