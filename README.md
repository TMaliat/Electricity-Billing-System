# Electricity-Billing-System
An offline desktop software which will provide Electricity Billing Scope under a pre registered admin

Electricity Billing System is an offline desktop application which is developed by Asfin Jannat Shamsi,Tasfia Tabassum & Tanzeem Maliat in Ubuntu Operating System.This project is developed under the requirements for CSE2112 course in order to understand & implement the concepts of Object Oriented Programming Language. We have decided to use Java, a powerful tool, as our Object Oriented Programming Language.
In this project,a pre-registered admin is given the access to the system to their individual credentials(Username & Password) & the admin is also given access to a database table through our system so that each entry for any operation is properly updated on the database table. The admin can see their system development kit in case of maintenance in future, can create a new connection,can see the customers registered under their credentials & file complaints if needed.

For this project,the development kit includes:

-> Java

-> JavaFX

-> Intellij idea ultimate IDE

-> Scene builder

-> MySQL

-> CSS

In order to set up an environment to run this system,the following installments must be done:

Intellij Idea Ultimate:

$ sudo snap install intellij-idea-community --classic

OR

$ sudo snap install intellij-idea-ultimate --classic

OR

$ sudo snap install intellij-idea-educational --classic

The user of the system can run the IntelliJ Idea with these commands:

$ intellij-idea-community

OR

$ intellij-idea-ultimate

OR

$ intellij-idea-educational


JavaFX : 

Using the following link,one can install the JavaFX on their machine:

Downloading the proper javafx installment package from https://openjfx.io/ 

Then unzipping those files & loading the .jar files into the IDE. As IntelliJ Idea Ultimate , which has been used by us, did not need the manual plugins of these .jar files,this IDE is rececommended by us.

Database :

Database read/write is an important part of our project.We have run these commands to properly set up the database.

sudo apt update

sudo apt install mysql-server

sudo systemctl start mysql.service

sudo mysql_secure_installation

sudo mysql

mysql -u root -p

CREATE USER 'username'@'host' IDENTIFIED WITH authentication_plugin BY 'password';

CREATE USER 'oop'@'localhost' IDENTIFIED BY 'oopsql';

GRANT PRIVILEGE ON database.table TO 'username'@'host';


