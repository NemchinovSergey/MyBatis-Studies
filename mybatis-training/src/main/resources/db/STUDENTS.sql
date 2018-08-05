CREATE TABLE STUDENTS
(
  stud_id INT(11)     NOT NULL AUTO_INCREMENT,
  name    VARCHAR(50) NOT NULL,
  email   VARCHAR(50) NOT NULL,
  dob     DATE                 DEFAULT NULL,
  PRIMARY KEY (stud_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;
/*Sample Data for the students table */
INSERT INTO students (stud_id, name, email, dob)
VALUES (1, 'Student1', 'student1@gmail.com', '1983-06-25');
INSERT INTO students (stud_id, name, email, dob)
VALUES (2, 'Student2', 'student2@gmail.com', '1983-06-25');
