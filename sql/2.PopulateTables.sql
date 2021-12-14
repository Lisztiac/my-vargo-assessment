USE danlandb;

INSERT INTO employee
(first_name, last_name, title, company)
VALUES
("Michael", "McSweeny", "Director of Software Development", "Vargo Solutions"),
("Joel", "Raymond", "Manager of Software Development", "Vargo Solutions"),
("Kit", "Hodges", "Super Long Software Engineer Title", "Vargo Solutions"),
("Dan", "Lan", "Software Developer", "UWM"),
("John", "Garcia", "Software Developer", "UWM"),
("Carlos", "Ayala-Gonzalez", "Software Developer", "UWM");

INSERT INTO project
(code_name, start_date, end_date)
VALUES
("Top Secret Project", DATE("2011-11-11"), null),
("Avengers", DATE("2012-05-05"), DATE("2019-04-26")),
("Iron Man", DATE("2014-07-20"), DATE("2017-02-13")),
("Claymore", DATE("2015-03-04"), DATE("2020-09-10")),
("Area 51", DATE("2016-08-09"), null),
("Home Advantage", DATE("2017-06-01"), DATE("2017-12-25")),
("Finding Aliens", DATE("2018-01-01"), null),
("Electric Feel", DATE("2019-02-19"), DATE("2020-10-10")),
("Fish Are Friends, Not Food", DATE("2020-12-25"), DATE("2021-03-17")),
("World Domination", DATE("2021-05-04"), null);

INSERT INTO employee_project
(employee_id, project_id)
VALUES
(1, 1),
(1, 4),
(1, 9),
(1, 5),
(2, 1),
(2, 3),
(2, 6),
(3, 2),
(3, 5),
(3, 7),
(3, 10),
(4, 6),
(4, 8),
(4, 5),
(5, 10),
(5, 8),
(5, 2),
(6, 2),
(6, 4);