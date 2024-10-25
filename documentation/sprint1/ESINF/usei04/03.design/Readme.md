# USEI04 - Calculate execution times by each operation

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...           | Answer              | Justification (with patterns)                                                                      |
|:-------------  |:------------------------------------------------------|:--------------------|:---------------------------------------------------------------------------------------------------|
| Step 1  		 | 	... interacting with the actor?                      | SimulatorUI         | Pure Fabrication: Responsible for interacting with the user, displaying data, and receiving inputs |
| 			  		 | 	... coordinating the simulation?                     | SimulatorController | Controller: Responsible for coordinating the simulation logic and interacting with repositories.   |
| 			  		 | ... retrieving the operation map with their time?     | Simulator           | IE: Simulator has a map of operations with their time.                                             |
| Step 2  		 | 	...starting the simulation?		                        |   SimulatorController                  |  Controller: The controller starts the simulation and passes the necessary configurations to the simulator.                                                                                                  |
| Step 3  		 | 	...showing the operations and their respective time? | Simulator                | IE: Simulator has a map of operations with their time.                                                     |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* OperationQueue
* Operation
* Simulator
* ID

Other software classes (i.e. Pure Fabrication) identified: 

* SimulatorUI


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)