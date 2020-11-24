# Wentworth Institute of Technology
## COMP1050
## Project: Mastermind Game

Last updated: 24-July-2020

### Description
Welcome to the world of international espionage and secret codes. *Mastermind* is a classic board game where a
*CodeMaker* builds a secret code with colored pegs and a *CodeBreaker* has a number of guesses to break the
code, using feedback provide by the *CodeMaker*.

In this project, you will build a one-round game of *Mastermind*, with the option of going beyond
for extra credit. 

Note: The course syllabus suggests an order and timing for your development. That said, you are welcome to develop the game in any order that makes sense to you, and at your own pace, so long as the project is completed by the due date. 

### Requirements
* The computer will be the CodeMaker
* A person will be the CodeBreaker
* You must implement this game with a JavaFX GUI
* You must use the Apache Commons configuration library to manage configuration

### Individual vs. 2-Person Teams
You can choose to do this assignment solo or with a partner. If you choose to do it with a partner, the optional parts of the assignment become mandatory. The most significant functionality that would become mandatory for a 2-person team is the requirement that:

* The game is timed, as specified in the configuration file (in minutes)
* The team implements a human-to-human option over the Internet

For solo implementers, you can earn extra credit if:
* You implement a configuration dialog box the enables the user to change the configuration for the next game
* You implement a game clock to support a timed games
* You implement a drag-and-drop

### History & How to Play
Instead of reinventing the wheel and explaining how to play, here are some good references that explain the game and its rules:

* [Mastermind - from Wikipedia](https://en.wikipedia.org/wiki/Mastermind_(board_game))
* [YouTube: How to Play Mastermind](https://www.google.com/search?q=mastermind+board+game+rules&oq=mastermind+board+game+rules&aqs=chrome..69i57.8596j0j1&sourceid=chrome&ie=UTF-8#kpvalbx=_nFx7XtnVG5SEytMPipGkgAY53)
* [How to Play Mastermind at WikiHow](https://www.wikihow.com/Play-Mastermind)

### Game Options
As part of this assignment, you are to provide options for game play. The options are:

* The size of the code - default to *4*. You do not need to change the code size for this project unless you want to.
* The number of guess rows (code peg rows) available to the CodeBreaker (8, 10, 12) - default to *10*
* The CodeMaker can use duplicate colors (true/false) - default to *true*
* The CodeMaker can use blank spaces (true/false) - default to *false*
* If the game is timed, the number of minutes the total game can take (maxTime) - default to *0*  minutes (untimed)
    * This is optional for solo implementers
    * A maxTime of 0 in the configuration file means that the game isn't timed/has no time limit

These options are to be specified in a configuration file, *mmind.properties.* You are to
use the Apache Commons Configuration 2.7 library (or higher) to support your configuration needs.
Note the use of the _.properties_ file. This is the de facto standard for Java applications.

Here is an example of the file's contents:

```
# Mastermind Properties File (mmind.properties)
codeSize = 4
codePegRows = 10
dupsAllowedInCode = true
blanksAllowedInCode = false
playSounds = true
maxTime = 0
```

In the above configurtion file, you will see a _playSounds_ entry. You do not need to implement this. I implemented
sounds in my solution to demonstate how to use the JavaFX _MediaPlayer_. If you choose to implement sound capabilties
(optional), you should add an option in the configuration file, as I've done, to control whether or not sounds are played. 

### Creative License
As long as you fulfill the requirements of the project, you are good to go. That said, if you find 
that you're completing the project quickly and want to challenge yourself to learn more, by all means 
experiment and add your own features. Some ideas to get you started:

* Implement a menu that enables the user to start a new game or exit the application
* Add a dialog box that enables the user to change the application's configuration settings
* Add a dialog or application argument where the application can accept the user's first name to make messaging more personal
* Add an About box that shows application info - author, etc. launched from a menu or button
* For timed games, implement an analog clock face vs. a digital display
* Enable saving and loading games
* Enable changing board and background colors
* Enable changing the code size
* Add computer-assisted guessing using an algorithm or machine-learning


### Advice
I am here to help with questions including your design, implemenation details, etc. This can be a challenging and
somewhat time-consuming project, so don't wait until the last minute to get started! Make incremental progress every
day.

Also, I suggest you get the core components of the application working, e.g., being able to create a code, score the
code against the solution, etc., before you start on the GUI. Trying to do both at the same time can make things
more confusing. As a general rule: _get the data structures and algorithms working before adding any GUI._ This helps
to ensure that when you add a GUI, you don't need to worry about the application's foundation. 

---
