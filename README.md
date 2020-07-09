# Evo

## Overview 
Evo was my first attempt at working with genetic algorithms and artificial neural networks. The goal of the project is to train a randomly generated population of entities living in a 2D word how to efficiently search for resources. 

## Key features 
*	The simulation starts with 100 entities which have randomly generated genetic traits (i.e. the weights and biases of the neural network) and short-term memory (i.e. are able to remember their last move and their last visual input). 
* The entities can see the cell in front of them, rotate 90 degrees to the right or move to the tile they are directly facing. 
*	The project implements from scratch a feed forward neural network with one hidden layer.
*	After each iteration, the top 10 entities  ranked in terms of fitness (longest survival time) are used to create a new generation.
*	The user is able to manually select each individual entity and follow their behavior or see their neural network.
