
SQL Instructions

1) Skip all this and check out the app at https://dans-vargo-assessment.herokuapp.com/

2) Create new local MySql instance with settings below:

host = localhost
MySql port = 4000
MySql username = root
MySql password = mysql1

3) Run "1.CreateDbAndTables.sql", then run "2.PopulateTables.sql"

Note the project is configured to read from the created "danlandb" database

Note: datasource is currently hardcoded, normally these would be read from env variables or profile