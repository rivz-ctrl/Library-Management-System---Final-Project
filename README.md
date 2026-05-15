# Library-Management-System---Final-Project

Description:

Library Management System allowing the creation of said library and in it: users(teacher,student,admin) and items(books,dvd,magazine).
Also, allows the creation of reports by the admin, assigning stasuses to the current disposition of each items (available, borrowed,missing), sorting strategies for the data in the library.
Contains methods to answer input errors (throwing exceptions)

-Classes: 
  User: Parent class for users
  Admin: Type of user with proprietary features and restrictions
  Teacher: Type of user with proprietary features and restrictions
  Student: Type of user with proprietary features and restrictions

  Item:
  Book: Type of item with proprietary features
  DVD: Type of item with proprietary features
  Magazine: Type of item with proprietary features

  Library: System containing item and user data

  Reportable: Interface allowing for the creation of report by the admin(s)

  ItemSort: Amalgamation of sorting strategies for items
  UserSort: Amalgamation of sorting strategies for users
  
  CSVHandler: Handles interaction of data with CSV files

  BookTest: Collection of test cases for books
  DVDTest: Collection of test cases for dvds
  LibraryTest: Collection of test cases for the library's features
  MagazineTest: Collection of test cases for magazines
  UserTest: Collection of test cases for users
  

-Language:
Java

-Libraries:
JUnit Jupiter 4.13.2 : unit testing framework ensuring code validity
Junit 5 Pathway
