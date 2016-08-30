Read me file for Mingo - An air ticket reservation system
-----------------------------------------------------------------
This is a placeholder file describing all activities that the outside world ?requires to know about the current status of the project under construction. This document will have relevant information about the 
"how-tos" of consuming different artifacts required for the project(design documents, build/installation process and source code/test cases, it will). In addition it will also carry the current status of the project against the proposed "plan of action"


Section 1 - Contents available in the archive
-----------------------------------------------
1. High level requirements analysis - "Requirementsanalysis-ver-1.pdf"
2. Design documentation - "Design-document-ver-2.pdf"
2. A web application war file containing the implementation - "resrv.war"
3. Instructions to deploy it in tomcat7.0.70 - Section 3
4. MySQL Database scripts for creating and population necessary data - 
5. Instructions to test the system with a Vanilla usecase - "sql-scripts"
6. Issue list - Section 4
7. Access to bitbucket repository - Section 5
8. Codebase as an eclipse project(needs gwt plugin) - "resrv"
	-Version of GWT used for development - 2.7 (http://www.gwtproject.org/versions.html)
9. Other necessary screen shots for reference and server log (mingo.log), build log, resrv-my-version under "my-test-output"


NOTE- 
1.The first time build will take longer as the GWTC takes multiple passes to compile java into javascript.
2. Testcases will not run if the database is not set properly

Section 2 - Setup requirements for the application
-------------------------------------------------------
1. JDK 1.8 - Its important to use 1.8 and not lesser version, as the codebase uses closure support offered in 1.8
2. Eclipse Luna - 4.4
3. GWT preferably as an eclipse plugin (2.7.0)
	-Follow instructions under https://developers.google.com/eclipse/docs/download for setting up eclipse plugin
	-If you want a non eclipse route, follow below instructions
		-Download GWT version 2.7 from http://www.gwtproject.org/versions.html
		-Install it in your favorite folder
		-export GWT_HOME pointing it your install folder as an environment variable(MANDATORY)
4. 	MySQL 5.7 with Innodb and MyISAM engines installed
5. Firefox 24.3.0 for dev mode testing
	https://ftp.mozilla.org/pub/firefox/releases/24.3.0esr/win32/en-US/
	

Section 3 - Deployment and smoke test instructions
------------------------------------------------------	
1. Setup tomcat7.0.70, download it from below url
	- http://tomcat.apache.org/download-70.cgi
2. unpack either the build war("resrv.war") or if it doesnt work for some reason, then unpack "resrv-my-version.war"(present under "my-test-output") under webapps folder(this is the one that I used for my UI test)
3. open WEB-INF/applicationContext.xml and change the access attributes(url, username and password) for connection pool configuration
4. execute sql scripts present under sql-scripts folder in following order
	- txn-datamodel-ddl.sql
	- sor-datmodel-ddl.sql
	- fligh-plan-schedule-dml.sql
5. run <INSTALL_DIR>/apache-tomcat-7.0.70/bin/startup.bat or startup.sh
6. after successful startup, access the below given url
	http://localhost:8080/resrv/Resrv.html
7. In the search section of the Book tickets tab,
	-Use 09/20/2016 for the date field
	-Use 'New york'	for origin
	-Use 'Denver' or 'San francisco' for destination
	-Select the flight made available in the first list grid
	-Select the first ticket made available in the adjacent list grid
	-Update the contact details in the third grid
	-Update the dummy credit card details in the form section and confirm the purchase


Section 4 - Issue list and TODO
--------------------------------------------------------
Issues (1–12 of 12) - As filed in bitbucket account for this project

#15:Test coverage for the middleware code
#14:Ticketing transactions need to happen in a different database from Reservation System of record for peformance consideration
#13: Application needs to be compliant to I18N/L10N requirements, including timezone support to be taken seriously
#12: Need to universalize/globalize/constantize all the data columns in booking data source
#11: Separate reservation data from ticketpool data
#10: No user should be offered ability to choose more than 4 seats
#9: Any past date in seat search should not be encouraged
#8: Airline staff pages yet to be done
#7: Email and pdf generation for tickets need to be done
#6: OAuth2 based implementation wither with twitter or linked in is yet to be done
#5: Design documentation is yet to be completely updated
#4: Web check in is yet to be implemented
#3: Cancel booking along with Mybookings page is yet to be implemented
#2: Origin and Destination needs to offer intellisense
#1: Expiry date in credit card details section needs only month and year

Section 5 - Bit bucket access
--------------------------------------------------------------
I have created a bit bucket account for you to access codebase from bit bucket if you want
user-name - mingo-atrs
email id - mingo.atrs@gmail.com
password - Mingo123


Appendix - PLAN OF ACTION
-------------------------------------------------------------
1. Design documentation - 16 hours
	-Data model preparation
		- Transactional
		- Master
		- User
		- Audit (if time permits)
		- Configuration
	-UI pages
		-Connect the details to the bigpicture
	-Inter module relationship and architectural aspects
		- Transactional system
		- Batch process sytem
	-Business logic and use case aspects - (normal and boundary cases)
	-Configuration aspects	
	-Interface contracts for service layer
	-Testcases
		- Unit
		- Functional
		- Boundary
	-Technology stack description
		-A small description of what and why
	-Deployment choices
		-Access to my cloud space deployment
	-One final review
	-Database scripts

2. Project codebase setup in git/bitbucket
3. Cloud environment setup
	- JDK
4. Implementation - Data access layer and Service layer implementation(check into vcs mandaroty)
	- Coding the outgoing data transfer object contracts	
	- Coding the business logic behind the different CRUD usecases
	- Codiing the service layer
5. Implementation - Reservation UI implementation and integration with service layer
	-One round of testing for multi form factor support
6. Implementation - Other views based on design output
7. Final build testing with integrated unit tests
8. Final build testing with integrated functional tests
9. Document known issues also in the readme.txt
	-Log them in your bitbucket account and create an access for crossover folks to view