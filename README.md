# Rule-Engine-with-AST
The application’s objective is to determine user eligibility based on dynamic rules that involve attributes 
like age, department, income, spend. Using AST, the system can represent, combine, and modify rules efficiently.

Setup Instructions

Backend Setup:
Install Java and Spring Boot.
Clone the repository and navigate to the backend directory.
Update application properties with MySQL connection details.
Run the application with mvn spring-boot:run.

Database Setup:
Set up a MySQL database and create tables as per the schema.

Frontend Setup:
Install Angular CLI.
Navigate to the frontend directory and run npm install.
Start the Angular application with ng serve.

Testing:
Use Postman or an API client to test endpoints.
Verify UI displays the eligibility results accurately.

APP COMPONENTS

CREATE RULE : will create a rule and show id in UI. for eg: you create two rules it will create two rules with id 1 and 2
Combine RULE: for eg : you add rule number id with comma separated format, it will create a mega rule with separate id on the tkinteR UI
EVALUATE RULE : Add the mega rule Id and data params that you need to provide in json format

Summary This updated README provides clear instructions on setting up, running, and testing the rule engine application. 






