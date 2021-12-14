CREATE DATABASE danlandb;
USE danlandb;

CREATE TABLE employee
(
	id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    title VARCHAR(40) NOT NULL,
    company VARCHAR(50) NOT NULL,
    CONSTRAINT PK_employee_id
    PRIMARY KEY (id)
);

CREATE TABLE project
(
	id INT NOT NULL AUTO_INCREMENT,
    code_name VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    CONSTRAINT PK_project_id
    PRIMARY KEY (id)
);

CREATE TABLE employee_project
(
	id INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    project_id INT NOT NULL,
    CONSTRAINT PK_employee_project_id
    PRIMARY KEY (id)
);

ALTER TABLE employee_project
	ADD CONSTRAINT FK_employee_id
	FOREIGN KEY (employee_id)
	REFERENCES employee(id),

	ADD CONSTRAINT FK_project_id
	FOREIGN KEY (project_id)
	REFERENCES project(id);


