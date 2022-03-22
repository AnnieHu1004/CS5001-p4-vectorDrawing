# CS5001-p4-vectorDrawing

practical 4 for CS5001

Features I have implemented
=================================================================

[Basic requirements]
---------------------
- Drawing straight lines, ellipses and diagonal crosses 
  by clicking corresponding button and pressing and releasing the mouse
- Undo the shapes by clicking the UNDO button
- Redo the shapes by clicking the REDO button
- Drawing with different colors by choosing a color in the JColorChooser dialog

[Advanced requirements]
---------------------

- Pressing the Control key to lock ratio to draw squares, circles, equal triangles, equal hexagons and equal diagonal crosses
- Drawing additional shapes, triangles and hexagons 
  by clicking corresponding button and pressing and releasing the mouse
- Loading and saving drawings in a format that permits them to be manipulated as vector drawings after loading.




Explain clearly how to run my program and how to use all its features
=================================================================

[how to run my program]
---------------------
- Use IDEA to run the program

or

- Use the terminal of the lab computers
	- open the terminal under the directory of ../CS5001-p4-vectorDrawing/src
	- javac model/*.java
	- javac controller/*.java
	- javac view/guiView/*.java
	- javac main/*.java
	- java main/RunDrawingPanel

- java -jar [directory]


[how to use all its features]
---------------------

- By clicking different shape buttons, you can draw corresponding shapes on the canvas
- By keeping pressing the control key, you can lock the aspect ratio to draw different equal shapes in different size
- Once you release the control key, you won't be able to lock the aspect ratio

- By clicking the button "Choose a Color", you can change the color of the shape that you are going to draw next in a JColorChooser dialogue

- Next to the button "Choose a Color", you can see what kind of color you are choosing right now

- By clicking the button "UNDO", you can undo the shapes you just drew
- By clicking the button "REDO", you can repaint  the shapes you just undoed
- However, when you draw a new shape after undoing old shapes, you won't be able to redo those new shapes.
