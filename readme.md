# Minesweeper
The following kata is about the logic of Minesweeper. 
Minesweeper is a game where you have to find all hidden mines of a reactangle field.

This application will get a file with a representation of a minefield like the following one:

```
*.....
.**...
..*...
..*...
....*.
......
```

The dots at this field represent empty places. The stars represent mines. 
Your job is to calculate for each empty field how many direct neighbors (horizontally, vertically and diagonally) they have.
At las the calculation has to be written in another file. Each dot of the origin file should be replaced by the result of the calculation:

```
*32100
2**200
14*300
02*311
0112*1
000111
```

## Rules
* The minefield is a rectangular matrix.
* The field can be initialized with any width and length bigger or equal to 1
* The field width can be different from the fields length
* All lines must have the same length
* All columns must have the same length
* The the input data have any errors (like wron data) the application should stop with helpful error message
* Mines should still be represented as stars ``*`` within the output file
* Empty fields should contain the number of mines within the direct neighborhood (a number from 0 to 8)
  
 