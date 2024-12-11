# Final Project for the Design Patterns Course at University

### Technical Objective of the Task
The project required a non-trivial application of two design patterns from each category: creational, structural, and behavioral.

### Project Background
In the hive, there are three types of bees: the Queen, Worker Bees, and Drone Bees. The Queen creates all the bees and assigns them specific attributes, such as stamina, experience, and work ability. Worker Bees also have speed and weight, while Drone Bees have strength and smartness.  

The Queen Bee randomly divides the bees into three equally sized swarms, each assigned a specific task:
  - Swarm 1 pollinates flowers.
  - Swarm 2 builds the hive.
  - Swarm 3 feeds the Queen when she is hungry.

As bees perform tasks, their experience increases, but their stamina decreases. Bees with low stamina must rest before resuming work. The amount of work completed depends on the bee's experience and abilities. Bees specifically designed for certain tasks perform them better (Worker Bees for flower pollination, Drone Bees for hive building). However, other bees can adapt to perform these tasks, though their efficiency will be lower.

Bees can also join forces and form teams, which are more effective than individual bees.

## Design Patterns Used in the Project
### · Singleton
In the QueenBee class, the Singleton pattern is used to ensure that there is only one instance of the queen in the system. QueenBee is responsible for managing the observer list and assigning tasks to the swarms. As the central decision-making point, it must be unique to avoid conflicts and inconsistencies in the program.

### · Prototype
The WorkerBee and DroneBee classes implement the clone method, which allows bees to be cloned from existing prototypes. This enables the creation of bees with similar attributes and avoids reinitializing objects, saving time and resources.

### · Adapter
The DroneBeeAdapter and WorkerBeeAdapter classes enable WorkerBees and DroneBees to perform tasks they were not originally designed for by adapting the logic in the work method.

### · Facade 
The BeeWorkFacade class acts as a facade, enabling the initiation of bee tasks and task management through a single start method. It simplifies starting and managing the system while hiding its complexity (creating swarms and bees, managing tasks and swarm work,
queen bee service).

### · Observer
The QueenBeeObserver interface declares the queenHungry method, which is called when the QueenBee is hungry. The QueenBee class maintains a list of its observers and notifies all interested objects by invoking their queenHungry method whenever the Queen is hungry. A Swarm can act as an observer of the Queen by implementing the QueenBeeObserver interface, allowing it to react to the Queen's hunger by taking appropriate actions.

### · Strategy
The ActionStrategy interface defines a common interface for different tasks (strategies), such as:
  - FlowersPollination,
  - HiveConstruction,
  - QueenFeeding.
Each strategy has unique logic for task execution and its completion conditions.
