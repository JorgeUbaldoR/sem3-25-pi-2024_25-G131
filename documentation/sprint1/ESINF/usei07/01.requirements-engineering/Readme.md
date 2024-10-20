# USEI07 - Produce a listing representing the flow dependency between machines


## 1. Requirements Engineering

### 1.1. User Story Description

As a user of the system, I want to create a list representing the flow dependency between machines.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The listing should be sorted in descending order of processed items.

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** There should be machines.
* **AC2:** There should be items.
* **AC3** The listing should be sorted in descending order of processed items.

### 1.4. Found out Dependencies

* There is a dependency on "USEI02 - Implement a simulator that processes all the items according" as there must be a simulator.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
	
* Selected data:

**Output Data:**

* List of items that show in which machines it did use
* List of dependencies between machines in order
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.