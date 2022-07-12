# AP-University-Portal-System
Student systems are designed to mechanize student activities and usually with a specific purpose. In this project, you will implement a simple example of these systems that provide basic features similar to the portal system and the student affairs management system. The people who are allowed to enter the system include only one administrator and two categories of staff and students. All people have a username and password and use it to enter the system; The username must not be repeated and the password must contain at least eight characters. Profile characteristics and features that can be used for each person are as follows:

## Admin:
The admin profile contains only his username and password.
- Admin can change his username and password.
- The administrator should be able to prepare the current week's meal plan for the students.
- The admin should be able to see a list of the profiles of students, professors and classes offered.
- Admin must be able to add professors and students.

## Professor:
Professor's profile includes username, password, units offered and list of students in each unit.
- The teacher can change his username and password.
- The professor can prepare a class for students. Each class includes name, number of units, formation time and capacity. Regardless of the number of units, classes are held from Saturday to Wednesday from 8 to 10, 10 to 12, and 14 to 16.
- He should be able to view a list of his students' information and give them a grade. This score
It directly affects the student's grade point average.
- He can also close a class whenever he wants.

## Student:
The student's profile includes his username, password, account balance, list of classes and his GPA.
- Students can change their username and password.
- Students can increase their account balance. This is done by entering the card number, its password and the desired amount.
- He can see the weekly meal schedule and according to his account balance, reserve food for any desired day, in which case, the amount of the food will be deducted from his account balance.
- The student can choose his favorite class. After the capacity of the class is completed or the number of units exceeds 20 when his GPA is less than 17 and 24 when his GPA is above 17, he will not be able to choose that class. Note that a student's grade point average is considered below seventeen if no professor has given him a grade. When a class is closed, the number of units selected by the students of that class will decrease, their time in that class will be free and they can choose an alternative class.
