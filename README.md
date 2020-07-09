# Evo

## Overview 
Evo was my first attempt at working with genetic algorithms and artificial neural networks. The goal of the project is to train a randomly generated population of entities living in a 2D word how to efficiently search for resources. 

## Key features 
*	The simulation starts with 100 entities which have randomly generated genetic traits (i.e. the weights and biases of the neural network) and short-term memory (i.e. are able to remember their last move and their last visual input). 
* The entities can see the cell in front of them, rotate 90 degrees to the right or move to the tile they are directly facing. Each decision is associated with a specific code. 
*	The project implements from scratch a feed forward neural network with one hidden layer.
* The input layers is made up of the bias, the hp value, the current and last visual inputs, and the code of the last decision made.
*	After each iteration, the top 10 entities  ranked in terms of fitness (longest survival time) are used to create a new generation.
*	The user is able to manually select each individual entity and follow their behavior or see their neural network.

## Screenshots

<div align="center">
  <img width="600" src="https://github.com/Valentin0709/Evo/blob/master/Images/capture2.PNG">
  <p>Control panel - the user can compute the fitness of each enitity, select the top entities and generate the next population. The Auto button can be used to execute all these functions together. At the top of the screen, the program displays the generation number and the average/maximum/minimum fitness scores</p>
</div>


<div align="center">
  <img width="600" src="https://github.com/Valentin0709/Evo/blob/master/Images/capture1.PNG">
  <p>In simulation mode, you can see the red entity searching for food which is represented by the green cells. In the bottom right corner, the app displays the health points and the values stored in the nodes of the neural network. Clicking one of the nodes shows all its corresponding weights.</p>
</div>
