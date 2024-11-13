create database library_ms;
create table users(id int primary key  not null auto_increment, name varchar(50),password varchar(50),email varchar(50), contact varchar(50));
drop table users;
select * from users;
create table book_details(book_id int primary key auto_increment not null, book_name varchar(250), author varchar(100), quantity int );
create table students_details(student_id int primary key auto_increment not null, name varchar(100), course varchar(100), branch varchar(100) );
create table issue_book_details(id int primary key not null auto_increment , book_id int, book_name varchar(150) , student_id int , student_name varchar(50), issue_date date, due_date date, status varchar (20));
