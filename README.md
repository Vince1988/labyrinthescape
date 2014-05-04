# Labyrinth Escape

Projectwork for "concepts and methods in programming" class

## Task: Escaping a labyrinth

Write a graphical application (Swing or JavaFX) that displays a labyrinth (see the example below) and that shows the way out starting from an entered position.

The labyrinth should be initialized from a text or xml file with an appropriate format, e.g. like the given example. The numbers in the first line indicate the dimensions of the labyrinth. The walls of the labyrinth are indicated by asterisks (*). Use a class Tile to represent the different parts of the labyrinth (wall or empty), and a class LabyrinthModel to define the structure of the whole labyrinth.

In your application, provide 2 input fields to enter the row and column of the start position in the labyrinth. When the button “Way out” is pressed, compute the escape way from the labyrinth and display it.

Implement a recursive method in the class LabyrinthModel to find the way out. Use the following recursive approach to check whether you can escape from the labyrinth: If you are at an exit (an exit is an empty tile at the border of the labyrinth), return true. Recursively check whether you can escape from one of the empty neighboring tiles without visiting the current location (mark the tiles that you have already checked).