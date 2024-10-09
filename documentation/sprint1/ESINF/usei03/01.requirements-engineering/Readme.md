# US006 - Create a Task 


## 1. Requirements Engineering

### 1.1. User Story Description

As an organization employee, I want to create a new task in order to be further published.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost, as well as a task category. 

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.

> **Question:** Monetary data is expressed in any particular currency?
>
> **Answer:** Monetary data (e.g. estimated cost of a task) is indicated in POT (virtual currency internal to the platform).

### 1.3. Acceptance Criteria

* **AC1:** The system successfully retrieves operation times and calculates total production times for all items in a production order.
* **AC2:** The output is accurate and aligns with the input data.
* **AC3:** The interface displays results clearly, allowing for easy review by the Production Manager.
* **AC3:** Appropriate error messages are generated for incomplete data.

### 1.4. Found out Dependencies

* There is a dependency on "US001 - "  " as must define the adequate data structures to store the information imported from the files.
* There is a dependency on "US002 - "  " as must implement a simulator that processes all the items according to the following criteria.
* There is a dependency on "US004 - "  " as must calculate execution times by each operation. 
* There is a dependency on "US005 - "  " as must present a list of machines with total time of operation, and percentages relative to the operation time and total execution time.
* There is a dependency on "US006 - "  " as must present average execution times per operation and the corresponding waiting times.
* There is a dependency on "US007 - "  " as must produce a listing representing the flow dependency between machines.
* There is a dependency on "US008 - "  " as must improvement to the simulator developed in USEI02 that takes into account a processing order based on priority.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
    * List of Operations
    * Time Estimates 
    * Operational Sequence
	
**Output Data:**
* Item identifier
* Breakdown of time for each operation
* Total production time for the item
* Overall total production time for all items in the order
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.