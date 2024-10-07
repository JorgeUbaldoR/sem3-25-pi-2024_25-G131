# MusgoSublime Application Manual

# Introduction

## Purpose and Scope

This manual serves as a comprehensive guide for green space managers and administrators utilizing MusgoSublime, a digital tool created to streamline the management and maintenance of green spaces.

MusgoSublime is a digital software product developed to simplify the upkeep of green areas. It provides a diverse variety of features and functionalities to assist users in organizing and maintaining green spaces.

Within this manual, readers will discover detailed instructions on how to effectively utilize the various features and tools offered by the MusgoSublime application. From fundamental navigation to advanced functionalities, each aspect of the application is extensively explained to ensure users can understand its full potential.

Intended for a wide audience involved in the management of green spaces, this manual caters to Human Resources Managers, Vehicle and Fleet Managers, Green Space Managers, and any other personnel responsible for overseeing and maintaining green areas.

# System Overview

## Product Description

MusgoSublime is a digital solution designed to simplify and enhance the management and maintenance of green spaces. Whether overseeing vast public parks, urban gardens, or recreational areas, MusgoSublime provides a comprehensive suite of tools to streamline various aspects of green space management.

With MusgoSublime, users can efficiently plan, organize, and execute maintenance tasks within green spaces, ensuring their optimal condition and usability. The application uses both modern technology and intuitive design principles to offer a great user experience for administrators and end-users alike.

## Objectives

The primary objective of MusgoSublime is to empower users with the tools they need to efficiently manage green spaces while enhancing sustainability and environmental practices. Specifically, the application aims to achieve the following objectives:

- **Facilitate Green Space Management**: MusgoSublime streamlines the planning, organization, and execution of maintenance tasks within green spaces, making it easier for administrators to ensure the upkeep of these areas.

- **Enhance Communication and Collaboration**: The application promotes communication and collaboration among team members responsible for green space management, encouraging a collaborative environment where tasks can be coordinated effectively.

- **Provide Real-time Insights**: MusgoSublime offers real-time insights and data analytics to inform decision-making processes related to green space management. Users can access valuable information to optimize resource allocation and improve operational efficiency.

- **Improve User Experience**: By offering a user-friendly interface and intuitive features, MusgoSublime enhances the overall user experience for both administrators and end-users of green spaces. It provides a seamless platform for interaction and engagement, promoting transparency and community involvement.

## Structure

MusgoSublime is structured around a modular architecture, with each module catering to specific aspects of green space management:

1. **Administrative Module**: This module is designed for administrators and managers responsible for overseeing the maintenance and operations of green spaces. It includes features for task assignment, team management, resource allocation, and reporting.

2. **User Portal**: The User Portal serves as an interface for end-users, such as park visitors or community members, to access information about green spaces, report issues, and provide feedback. It promotes transparency and engagement between stakeholders and green space managers.

3. **Resource Management**: The Resource Management module provides tools for tracking and managing resources essential for green space maintenance, including vehicles, equipment, machines, and personnel. It allows administrators to optimize resource allocation and scheduling to ensure efficient operations.

4. **Reporting and Analytics**: This module offers comprehensive reporting capabilities and data analytics functionalities. Users can generate custom reports, analyze trends, and gain valuable insights into the performance and condition of green spaces over time.

By combining these modules, MusgoSublime offers a solution for effective green space management, empowering users to maintain and enhance the quality of green areas for the benefit of communities and the environment.

# Features

This section provides an overview of the key features of MusgoSublime, a digital tool designed for the management and maintenance of green spaces. Each feature corresponds to a user story and is targeted at specific user roles within the application.

## 1. Register Skills for Collaborators | Human Resources Manager

- **Description**: This feature allows the Human Resources Manager to register the skills that a collaborator may have.
- **Instructions**:
  - Access the application interface.
  - View or add skills in the Skills Registration section.
  - Confirm the entered data.
  - Upon confirmation, the system displays a success message.

## 2. Register Job for Collaborators | Human Resources Manager

- **Description**: This feature enables the Human Resources Manager to register a job that a collaborator needs to have.
- **Instructions**:
  - Activate the HRM role.
  - Select the option to add a new job within the application interface.
  - Provide the name.
  - Confirm the entered data.
  - Upon submission, the system confirms the successful addition of the job.

## 3. Register Collaborator with Job and Characteristics | Human Resources Manager

- **Description**: This feature allows the Human Resources Manager to register a collaborator with essential details and assign them a specific job.
- **Instructions**:
  - Request to register a new collaborator within the application.
  - Provide the required information, including name, birthdate, admission date, residence, contact details, taxpayer number, and ID document type and number.
  - Select the appropriate job for the collaborator from the provided list.
  - Confirm the entered data and job selection.
  - Upon confirmation, the system displays a success message indicating the completion of the operation.

## 4. Assign Skills to Collaborators | Human Resources Manager

- **Description**: This feature enables the Human Resources Manager to assign one or more skills to a collaborator.
- **Instructions**:
  - Access the Skills Assignment page within the application.
  - Select the collaborator for whom you want to assign skills.
  - Choose the relevant skills from the provided list.
  - Confirm the selection to save the assignment.

## 5. Generate Team Proposal Automatically | Human Resources Manager

- **Description**: This feature allows the Human Resources Manager to automatically generate a proposal for a team based on specified criteria.
- **Instructions**:
  - Navigate to the Team Proposal Generator tool within the application.
  - Specify the minimum and maximum team size and the required set of skills.
  - Click on the "Generate Proposal" button to create a team proposal.

## 6. Register Vehicle Details | Fleet Manager

- **Description**: This feature enables the Fleet Manager to register a new vehicle along with its details such as brand, model, type, weight, and maintenance information.
- **Instructions**:
  - Navigate to the Vehicle Registration section within the application.
  - Enter the required vehicle details, including brand, model, type, weight, maintenance frequency, and last check-up.
  - Save the vehicle information.

## 7. Register Vehicle Check-up | Fleet Manager

- **Description**: This feature allows the Fleet Manager to register a check-up for a vehicle.
- **Instructions**:
  - Go to the Vehicle Check-up Registration page within the application.
  - Select the vehicle for which you want to register a check-up.
  - Enter the details of the check-up, such as the date and current mileage.
  - Save the check-up record.

## 8. View List of Vehicles Needing Check-up | Fleet Manager

- **Description**: This feature enables the Fleet Manager to view a list of vehicles that require a check-up.
- **Instructions**:
  - Navigate to the Check-up Status page within the application.
  - View the list of vehicles due for a check-up, along with their details.

## 9. Analyze Water Consumption Costs | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to analyze water consumption costs for specific green spaces.
- **Instructions**:
  - Load the "water consumption.csv" file containing daily water consumption records.
  - Specify the year, time period (StartMonth, EndMonth), and park identification.
  - Generate a bar plot representing monthly water consumption.
  - Specify the number of parks to be analyzed and park identification to calculate the average monthly water consumption costs.
  - Compare statistical indicators (mean, median, standard deviation, coefficient of skewness) between the park with the highest and lowest water consumption.
  - Create frequency tables, check for outliers, and generate histograms with specified class numbers.

## 10. Analyze Equipment Usage | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to understand user preferences regarding park equipment usage.
- **Instructions**:
  - Load the "EquipmentUsed.csv" file containing equipment usage records from 1000 users.
  - Create a pie chart representing the percentage usage of each piece of equipment.

## 11. Analyze Park Use by Age Group | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to analyze park usage by different age groups.
- **Instructions**:
  - Load the "Inquiry.csv" file containing survey responses.
  - Identify the type of each variable (age range, recommendation, monthly visits).
  - Calculate the proportion of users in each age group who would recommend the park.
  - Generate box plots for each age group regarding monthly park visits and draw conclusions from the results.

## 12. Import Water Supply Data | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to import a CSV file containing water supply points and distances.
- **Instructions**:
  - Load the CSV file with lines containing Water Point X, Water Point Y, and Distance.
  - Import the data into a unified data structure representing possible routes for laying pipes.

## 13. Optimize Irrigation Routes | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to optimize the routes for irrigation systems to minimize cost.
- **Instructions**:
  - Apply an algorithm to determine the minimum cost routes for laying pipes.
  - Ensure all water points are adequately supplied.
  - Output the subgraph with routes and costs to a CSV file.
  - Visualize the input and output graphs using a suitable package (e.g., Graphviz).

## 14. Test Algorithm Efficiency | Quality Assurance Manager (QAM)

- **Description**: This feature allows the QAM to test the algorithm's efficiency with varying input sizes.
- **Instructions**:
  - Run tests on 30 input files of variable sizes.
  - Record the execution time for each input size in a CSV file.
  - Generate a graph showing the execution time as a function of input size.
  - Present the results in a CSV file and an image file with the execution time graph.

## 15. Predict Monthly Water Cost for New Park | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to predict the average monthly cost of water consumption for a new park based on its size.
- **Instructions**:
  - Load the "water consumption updated.csv" file and the "Area.csv" file containing park areas.
  - Apply a linear regression model using park area as the independent variable and average monthly water cost as the response variable.
  - Assess the feasibility of the linear model.
  - Predict the average monthly water cost for a new 55-hectare park.

## 16. Polynomial Regression for Execution Time | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to apply polynomial regression to determine the best fit line for execution time data.
- **Instructions**:
  - Load the data from US14 or the "solution us14.csv" file.
  - Apply polynomial regression to find the best fit line.
  - Analyze the results and interpret the polynomial regression model.

## 17. Plan Emergency Evacuation Routes | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to plan emergency evacuation routes to an Assembly Point.
- **Instructions**:
  - Import a CSV file containing a weighted matrix of costs between points.
  - Use an algorithm to determine the shortest route from any sign to the Assembly Point.
  - Output the routes and path costs to a CSV file.
  - Visualize the input graph and output paths.

## 18. Plan Multiple Emergency Evacuation Routes | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to plan emergency evacuation routes to the closest Assembly Point.
- **Instructions**:
  - Import a CSV file containing a weighted matrix of costs between points.
  - Use an algorithm to determine the shortest route to the closest Assembly Point.
  - Output the routes and path costs to a CSV file.
  - Visualize the input graph and output paths.

## 19. Analyze Worst-Case Time Complexity | Quality Assurance Manager (QAM)

- **Description**: This feature allows the QAM to analyze the worst-case time complexity of developed procedures.
- **Instructions**:
  - Analyze the worst-case time complexity for the algorithms developed in US13, US17, and US18.
  - Present the algorithms in pseudo-code.
  - Document the theoretical framework and complexity analysis in a PDF.

## 20. Register Green Space | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to register a new green space, specifying its type and area.
- **Instructions**:
  - Access the Green Space Registration section within the application.
  - Provide details such as the name, type (garden, medium-sized park, large-sized park), and area.
  - Save the new green space.

## 21. Add New Entry to To-Do List | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to add a new entry to the To-Do list.
- **Instructions**:
  - Go to the To-Do List section within the application.
  - Select the green space for the new entry from a list of managed spaces.
  - Provide the details for the new entry.
  - Save the entry to the To-Do list.

## 22. Add New Entry to Agenda | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to add a new entry to the Agenda.
- **Instructions**:
  - Navigate to the Agenda section within the application.
  - Select the green space associated with the new entry.
  - Ensure the new entry exists in the To-Do list.
  - Provide details for the new entry and save it to the Agenda.

## 23. Assign Team to Agenda Entry | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to assign a team to an entry in the Agenda.
- **Instructions**:
  - Access the Agenda section within the application.
  - Select the entry to which a team needs to be assigned.
  - Choose team members from a list and save the assignment.
  - Send a notification message to all team members using a configured email service.

## 24. Postpone Agenda Entry | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to postpone an entry in the Agenda to a specific future date.
- **Instructions**:
  - Access the Agenda section within the application.
  - Select the entry that needs to be postponed.
  - Choose a new date for the entry and save the changes.

## 25. Cancel Agenda Entry | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to cancel an entry in the Agenda.
- **Instructions**:
  - Access the Agenda section within the application.
  - Select the entry that needs to be canceled.
  - Change the state of the entry to "Canceled" without deleting it.

## 26. Assign Vehicles to Agenda Entry | Green Space Manager (GSM)

- **Description**: This feature allows the GSM to assign one or more vehicles to an entry in the Agenda.
- **Instructions**:
  - Go to the Agenda section within the application.
  - Select the entry to which vehicles need to be assigned.
  - Choose vehicles from a list and save the assignment.

## 27. List Managed Green Spaces | Green Space Manager (GSM)

- **Description**: This feature enables the GSM to list all green spaces managed by them.
- **Instructions**:
  - Access the Managed Green Spaces section within the application.
  - View the list of green spaces, sorted by size in descending order.
  - The sorting algorithm used should be configurable, with at least two options available.

## 28. Consult Assigned Tasks | Collaborator

- **Description**: This feature allows a collaborator to consult tasks assigned to them between two dates.
- **Instructions**:
  - Access the Assigned Tasks section within the application.
  - Specify the date range for the task consultation.
  - View the list of tasks, sorted by date.
  - Filter the tasks by status if necessary.

## 29. Record Task Completion | Collaborator

- **Description**: This feature allows a collaborator to record the completion of a task.
- **Instructions**:
  - Navigate to the Assigned Tasks section within the application.
  - Select the completed task.
  - Update the task status to "Completed" and save the changes.


# Troubleshooting

- **Problem identification**: Create a collaborator without jobs.
- **Solution**: Create a job before creating a collaborator, so that the job can be given to the collaborator during the creation.


- **Problem identification**: Assign skills without collaborators.
- **Solution**: Create a collaborator before assigning a skill to it, since it is impossible to assign a skill to anything else.


- **Problem identification**: Generate teams without collaborators.
- **Solution**: Create a collaborator before generating a team, since it is impossible to generate a team without at least one collaborator.


- **Problem identification**: Creating an agenda without having a team.
- **Solution**: Generate a team before creating an agenda, so that the agenda has a team registered with it.


# FAQ - MusgoSublime

### How do I register skills for collaborators in MusgoSublime?

To register skills for collaborators:
1. Access the application interface.
2. Navigate to the Skills Registration section.
3. View or add skills as needed.
4. Confirm the entered data to save the changes.

### How do I generate a team proposal automatically?

To generate a team proposal automatically:
1. Navigate to the Team Proposal Generator tool.
2. Specify the criteria, including minimum and maximum team size and required skills.
3. Click on the "Generate Proposal" button to create the team proposal.

### How do I view a list of vehicles needing check-up in MusgoSublime?

To view a list of vehicles needing check-up:
1. Navigate to the Check-up Status page within the application.
2. View the list of vehicles due for a check-up, along with their details.

### How do I postpone an entry in the Agenda to a specific future date?

To postpone an entry in the Agenda:
1. Select the entry you wish to postpone.
2. Choose the option to postpone or reschedule.
3. Specify the new date for the entry.
4. Confirm the changes to update the entry in the Agenda.

### How do I record the completion of a task as a collaborator in MusgoSublime?

To record the completion of a task as a collaborator:
1. Access the application interface.
2. Navigate to the list of tasks assigned to you.
3. Select the task you have completed.


### How do I register a new vehicle in MusgoSublime?

To register a new vehicle:
1. Go to the Vehicle Registration section.
2. Enter the vehicle details, including brand, model, type, weight, and maintenance information.
3. Save the vehicle information to complete the registration process.


### How can I resolve login issues with MusgoSublime?

If you experience login issues:
1. Double-check your credentials for accuracy.
2. Rewrite your password if necessary.
3. Read all error messages.

### What should I do if I encounter errors while using a specific feature in MusgoSublime?

If you encounter errors with a specific feature:
1. Make sure you are following the correct steps outlined in the user manual.

### How can I get help or support for MusgoSublime?

If you need assistance with MusgoSublime:
1. Check the user manual or documentation provided within the application.




