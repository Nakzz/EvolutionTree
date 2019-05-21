



﻿# EvolutionTree
 This application which will keep track of any organization/ hierarchical information and help connect members with each other based on specific interest. 
For example: Posse Scholarship recipients. Each year there are 10 students inducted from 4 different cities. Each student has a mentor assigned from the earlier year as their mentor. This creates a lineage. This application can create a map of the linear and maybe export an visual representation. We can include informations as city, major, clubs affiliated with, current position at the workforce and etc. 
The front-end of the application can query information based on the organization. Let’s take Posse Foundation for example- we can query for the lineage, every member interested or majored in Computer Science, or people who have graduated. 



Team Members for A-Team 63:
Ajmain Naqib: naqib@wisc.edu
Ben Procknow: bprocknow@wisc.edu
Callan Patel: ccpatel2@wisc.edu
Erica Heying: eheying@wisc.edu

Course: CS400, Spring 2019    Lecturer's Name: Debra Depler

Time spent: Aprox. ** 34 ** hours spent in total

## Project Introduction
Our application allows users to search for other users dependent on the search criteria. A base set of users is added via a JSON file when the program first runs. There is an option for a new user to sign up either as a faculty or as a student. Once logged in (or signed up) the user can search for other users based on the criteria entered on the screen. Then the user will be shown all the users with the search criteria provided. The user has the option to edit their profile and to save their profile to the database (JSON file). If the user does not save their profile before logging out, any changes are lost. 

Class Diagram:
<img src='Class Diagram.png' title='Class Diagram' width='' alt='Class Diagram' />

The following **required** functionality is completed:





Button
ComboBox

produce some type of computed results for the user
display results to user and provide way to continue working with program

* [x] read data in from json data file
* [x] write data out to a json data file
* [x] contain at least these UI controls: TextField, Button, ComboBox
* [x] IMPLEMENT THE TESTS

Possible Improvement: 
* [ ] User Authentication with MD5 Hashing
* [ ] Automatic update of JSON Datasabase or possible implementation of MongoDB

## Video Walkthrough

Here's a walkthrough of implemented functionality:

<img src='walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Notes
Some of issues found:

Firstly error in jar file, not created properly, had to create on own.
Issues while editing profile, keeps on placing 0 in fields and shifts the value down. 
For ex: if first field is 'a' with value 'value1', and the next field is 'b'. Then after editing, the field 'b' has value 'value1' on display. Though possible stored correct in background.

Could not search for faculty with courses taught properly. 
Able to add same user again.

## License

    Copyright [2018] [Ajmain Naqib]


