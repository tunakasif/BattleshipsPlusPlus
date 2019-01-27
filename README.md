# BattleshipsPlusPlus
A desktop application written in Java, for the course CS102 in Bilkent University


Code Description: Battleships++ is a desktop application which is implemented with Java Swing using MVC. 
Basically it is an extended version of the traditional game of Battleships. This projects converts old 
school fun game of Battleships into a desktop application and extends its game play with the aid of special 
cards.

Status: Implementation of the game has minor problems such as a few missing parts. We implemented traditional 
Battleships game before and in the final code we extended it in order to have Battleships++. As current status 
user can play Battleships++ game as a desktop application. However it is not in its best form. For example, 
computer, as opponent, calls its shots randomly instead of understanding position of the ships after a few hits. 
With this kind of AI, we couldn't manage to make it use "SpecialCards". Thus, usage of SpecialCards are only available
for the player. Since the AI cannot use SpecialCards, implementation of "MineBall" card become meaningless because it 
depends on the opponent's usage of "Repair & Relocate Card".  In this context, we managed to implement Battleships++ 
although user plays the game against a crippled AI. AI needs to be improved to have more targeted guesses. Rest of the 
project works fine. We are really close to our planned design. Overall, our entire code works, only problem user plays 
against a dumb machine.

Organization: Basically we have 2 packages: game and gui. "game" package is composed of Model part of the project. 
It contains classes that take care of the logic of the game. "gui" package contains View and Control parts of the 
project. Essentially, game package creates and makes sure that game is played correctly where gui package provides 
view and controllers to the user. 

Implementation: We used Eclipse IDE to write Battleships++. We didn't use any external 
libraries or external classes, everything we wrote, we wrote on our own. We basically challenged ourselves to create a 
project with our learnings in CS102.

How To Use: There is main method under "HomePageFrame.class". Running just that class initiates the project. 
Transition of the game is provided by JFrames rest of the project will lead you into game. You don't have to 
do anything expect running main method in home page class. Also JAR file can be used to initiate the game.

