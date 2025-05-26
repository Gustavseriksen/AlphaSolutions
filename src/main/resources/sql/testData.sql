/* Admins */
INSERT INTO Admins (admin_id, username, password) VALUES (1, 'ADM_bertram', '123');
INSERT INTO Admins (admin_id, username, password) VALUES (2, 'ADM_johan', '123');
INSERT INTO Admins (admin_id, username, password) VALUES (3, 'ADM_gustav', '123');
INSERT INTO Admins (admin_id, username, password) VALUES (4, 'ADM_guffe', '123');

/* ProjectManagers */
INSERT INTO ProjectManagers (manager_id, username, password) VALUES (1, 'PM_bertram', '123');
INSERT INTO ProjectManagers (manager_id, username, password) VALUES (2, 'PM_johan', '123');
INSERT INTO ProjectManagers (manager_id, username, password) VALUES (3, 'PM_gustav', '123');
INSERT INTO ProjectManagers (manager_id, username, password) VALUES (4, 'PM_guffe', '123');

/* Employees */
INSERT INTO Employees (employee_id, username, password) VALUES (1, 'EMP_bertram', '123');
INSERT INTO Employees (employee_id, username, password) VALUES (2, 'EMP_johan', '123');
INSERT INTO Employees (employee_id, username, password) VALUES (3, 'EMP_gustav', '123');
INSERT INTO Employees (employee_id, username, password) VALUES (4, 'EMP_guffe', '123');
INSERT INTO Employees (employee_id, username, password) VALUES (5, 'EMP_sofie', '123');
INSERT INTO Employees (employee_id, username, password) VALUES (6, 'EMP_lars', '123');
INSERT INTO Employees (employee_id, username, password) VALUES (7, 'EMP_anne', '123');

/*
------------------------------------------------------------------------------------------------------------------------
PROJECT 1: Implementering af nyt CRM-system for kunde X
Project Dates: 2025-06-01 to 2025-09-30
Estimated Hours: 480
Actual Hours Used: 220
Status: IN_PROGRESS (Because Subproject 1.2, 1.4, 1.3 are not COMPLETED)
------------------------------------------------------------------------------------------------------------------------
*/
INSERT INTO Projects (project_id, project_name, project_description, start_date, end_date, estimated_hours, actual_hours_used, project_priority, project_status)
VALUES (1, 'CRM Implementering - Kunde X', 'Implementering af nyt Salesforce CRM-system for kunde X, inkl. tilpasning og træning.', '2025-06-01', '2025-09-30', 480, 220, 'HIGH', 'IN_PROGRESS');

/* Tilknyt medarbejdere til Projekt 1 */
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (1, 1); -- EMP_bertram
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (3, 1); -- EMP_gustav
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (5, 1); -- EMP_sofie


/*
    Subproject 1.1: Analyse og designfasen
    Subproject Dates: 2025-06-01 to 2025-06-20 (within Project 1 dates)
    Estimated Hours: 80 (30 + 50)
    Actual Hours Used: 80 (30 + 50)
    Status: COMPLETED (All tasks are COMPLETED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (101, 1, 'Analyse & Design', 'Indsamling af krav og design af CRM-løsning.', '2025-06-01', '2025-06-20', 'HIGH', 80, 80, 'COMPLETED');

/* Tasks for Subproject 1.1 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1001, 101, 'Kravindsamlingsmøder', 'Afholde workshops med forretningsbrugere for kravindsamling.', 'HIGH', 30, 30, 'COMPLETED'); -- Task Dates: 2025-06-01 to 2025-06-07
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1002, 101, 'Udarbejdelse af designspecifikation', 'Dokumentation af systemdesign og tilpasninger.', 'HIGH', 50, 50, 'COMPLETED'); -- Task Dates: 2025-06-08 to 2025-06-20


/*
    Subproject 1.2: Konfiguration og udvikling
    Subproject Dates: 2025-06-23 to 2025-08-15 (within Project 1 dates)
    Estimated Hours: 150 (60 + 50 + 40)
    Actual Hours Used: 90 (60 + 10 + 20)
    Status: IN_PROGRESS (Tasks 1004, 1005 are IN_PROGRESS)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (102, 1, 'Konfiguration & Udvikling', 'Konfiguration af Salesforce og udvikling af tilpassede komponenter.', '2025-06-23', '2025-08-15', 'HIGH', 150, 90, 'IN_PROGRESS');

/* Tasks for Subproject 1.2 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1003, 102, 'Salesforce basiskonfiguration', 'Opsætning af brugere, profiler og grundlæggende objekter.', 'HIGH', 60, 60, 'COMPLETED'); -- Task Dates: 2025-06-23 to 2025-07-05
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1004, 102, 'Datamigrering scripts', 'Udvikling af scripts til migrering af eksisterende kundedata.', 'MEDIUM', 50, 10, 'IN_PROGRESS'); -- Task Dates: 2025-07-06 to 2025-07-30
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1005, 102, 'Custom Apex-udvikling', 'Udvikling af specifikke Apex-klasser og triggers.', 'HIGH', 40, 20, 'IN_PROGRESS'); -- Task Dates: 2025-07-25 to 2025-08-15


/*
    Subproject 1.4: Integrationer
    Subproject Dates: 2025-07-01 to 2025-09-10 (within Project 1 dates)
    Estimated Hours: 120 (40 + 50 + 30)
    Actual Hours Used: 50 (40 + 10 + 0)
    Status: IN_PROGRESS (Tasks 1011, 1012 are not COMPLETED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (104, 1, 'Integrationer', 'Udvikling af integrationer med eksisterende ERP- og marketing-systemer.', '2025-07-01', '2025-09-10', 'HIGH', 120, 50, 'IN_PROGRESS');

/* Tasks for Subproject 1.4 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1010, 104, 'ERP Integration Design', 'Design af integrationsløsning med kundens ERP-system.', 'HIGH', 40, 40, 'COMPLETED'); -- Task Dates: 2025-07-01 to 2025-07-15
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1011, 104, 'Marketing Automation Integration', 'Opsætning af integration til marketing automation platform.', 'MEDIUM', 50, 10, 'IN_PROGRESS'); -- Task Dates: 2025-07-10 to 2025-08-20
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1012, 104, 'API Konfiguration', 'Konfiguration af nødvendige API-adgange og endpoints.', 'MEDIUM', 30, 0, 'NOT_STARTED'); -- Task Dates: 2025-08-25 to 2025-09-10


/*
    Subproject 1.3: Test og udrulning
    Subproject Dates: 2025-08-18 to 2025-09-30 (within Project 1 dates)
    Estimated Hours: 130 (40 + 50 + 20 + 20)
    Actual Hours Used: 0
    Status: NOT_STARTED (All tasks are NOT_STARTED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (103, 1, 'Test & Udrulning', 'Systemtest, brugeraccepttest og produktionsudrulning.', '2025-08-18', '2025-09-30', 'MEDIUM', 130, 0, 'NOT_STARTED');

/* Tasks for Subproject 1.3 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1006, 103, 'Udarbejdelse af testcases', 'Skrive testcases for alle definerede funktioner.', 'MEDIUM', 40, 0, 'NOT_STARTED'); -- Task Dates: 2025-08-18 to 2025-08-25
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1007, 103, 'Gennemførelse af systemtest', 'Udførelse af interne tests for at sikre funktionalitet.', 'HIGH', 50, 0, 'NOT_STARTED'); -- Task Dates: 2025-08-26 to 2025-09-10
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1008, 103, 'Brugeraccepttest (UAT)', 'Facilitere og understøtte kundens UAT.', 'HIGH', 20, 0, 'NOT_STARTED'); -- Task Dates: 2025-09-11 to 2025-09-20
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1009, 103, 'Produktionsudrulning', 'Klargøring og implementering af systemet i produktion.', 'HIGH', 20, 0, 'NOT_STARTED'); -- Task Dates: 2025-09-25 to 2025-09-30


/*
------------------------------------------------------------------------------------------------------------------------
PROJECT 2: Udvikling af ny B2B E-handelsplatform
Project Dates: 2025-07-01 to 2026-03-31
Estimated Hours: 780
Actual Hours Used: 0
Status: NOT_STARTED (All subprojects are NOT_STARTED)
------------------------------------------------------------------------------------------------------------------------
*/
INSERT INTO Projects (project_id, project_name, project_description, start_date, end_date, estimated_hours, actual_hours_used, project_priority, project_status)
VALUES (2, 'B2B E-handelsplatform', 'Udvikling af en skalerbar B2B e-handelsplatform for stor kunde inden for byggebranchen.', '2025-07-01', '2026-03-31', 780, 0, 'HIGH', 'NOT_STARTED');

/* Tilknyt medarbejdere til Projekt 2 */
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (2, 2); -- EMP_johan
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (4, 2); -- EMP_guffe
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (6, 2); -- EMP_lars
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (7, 2); -- EMP_anne

/*
    Subproject 2.1: Arkitektur og Teknologivalg
    Subproject Dates: 2025-07-01 to 2025-07-15 (within Project 2 dates)
    Estimated Hours: 60 (20 + 40)
    Actual Hours Used: 0
    Status: NOT_STARTED (All tasks are NOT_STARTED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (201, 2, 'Arkitektur & Teknologi', 'Valg af platform og teknologier til e-handelsløsningen.', '2025-07-01', '2025-07-15', 'HIGH', 60, 0, 'NOT_STARTED');

/* Tasks for Subproject 2.1 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2001, 201, 'Review af platforme', 'Undersøgelse af potentielle e-handelsplatforme.', 'HIGH', 20, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-01 to 2025-07-07
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2002, 201, 'Design af mikroservices-arkitektur', 'Udarbejdelse af den overordnede arkitektur.', 'HIGH', 40, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-08 to 2025-07-15

/*
    Subproject 2.2: Frontend-udvikling
    Subproject Dates: 2025-07-16 to 2025-10-31 (within Project 2 dates)
    Estimated Hours: 320 (120 + 100 + 100)
    Actual Hours Used: 0
    Status: NOT_STARTED (All tasks are NOT_STARTED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (202, 2, 'Frontend Udvikling', 'Udvikling af brugerflade og interaktion for B2B e-handelsplatformen.', '2025-07-16', '2025-10-31', 'HIGH', 320, 0, 'NOT_STARTED');

/* Tasks for Subproject 2.2 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2003, 202, 'UI/UX Design Implementering', 'Oversættelse af design til interaktive komponenter.', 'HIGH', 120, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-16 to 2025-08-31
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2004, 202, 'Produktvisning & Filter', 'Udvikling af sider til visning af produkter med avanceret filtrering.', 'MEDIUM', 100, 0, 'NOT_STARTED'); -- Task Dates: 2025-09-01 to 2025-10-15
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2005, 202, 'Indkøbskurv & Checkout', 'Implementering af funktionalitet for indkøbskurv og bestillingsflow.', 'HIGH', 100, 0, 'NOT_STARTED'); -- Task Dates: 2025-10-01 to 2025-10-31

/*
    Subproject 2.3: Backend-udvikling
    Subproject Dates: 2025-07-16 to 2025-11-30 (within Project 2 dates)
    Estimated Hours: 400 (80 + 120 + 100 + 100)
    Actual Hours Used: 0
    Status: NOT_STARTED (All tasks are NOT_STARTED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (203, 2, 'Backend Udvikling', 'Udvikling af server-side logik, databasemodeller og API-endpoints.', '2025-07-16', '2025-11-30', 'HIGH', 400, 0, 'NOT_STARTED');

/* Tasks for Subproject 2.3 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2006, 203, 'Bruger- og Rolleadministration', 'Implementering af brugerlogin, registrering og rollebaseret adgang.', 'HIGH', 80, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-16 to 2025-08-15
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2007, 203, 'Produktkatalog Service', 'Udvikling af service til håndtering af produktdata, priser og lager.', 'HIGH', 120, 0, 'NOT_STARTED'); -- Task Dates: 2025-08-16 to 2025-10-15
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2008, 203, 'Ordrebehandling Service', 'Implementering af logik for ordreoprettelse, statusopdatering og historik.', 'HIGH', 100, 0, 'NOT_STARTED'); -- Task Dates: 2025-09-01 to 2025-11-15
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (2009, 203, 'Betalingsgateway Integration', 'Integrering med valgt betalingsløsning.', 'MEDIUM', 100, 0, 'NOT_STARTED'); -- Task Dates: 2025-10-15 to 2025-11-30


/*
------------------------------------------------------------------------------------------------------------------------
PROJECT 3: Konsulentbistand til GDPR-compliance
Project Dates: 2025-05-27 to 2025-07-31
Estimated Hours: 200
Actual Hours Used: 120
Status: IN_PROGRESS (Because Subproject 3.1 and 3.3 are not COMPLETED)
------------------------------------------------------------------------------------------------------------------------
*/
INSERT INTO Projects (project_id, project_name, project_description, start_date, end_date, estimated_hours, actual_hours_used, project_priority, project_status)
VALUES (3, 'GDPR Compliance Audit', 'Gennemgang og audit af kundes GDPR-compliance og anbefalinger til forbedringer.', '2025-05-27', '2025-07-31', 200, 120, 'MEDIUM', 'IN_PROGRESS');

/* Tilknyt medarbejdere til Projekt 3 */
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (1, 3); -- EMP_bertram
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (4, 3); -- EMP_guffe

/*
    Subproject 3.1: Data mapping og risikovurdering
    Subproject Dates: 2025-05-27 to 2025-06-15 (within Project 3 dates)
    Estimated Hours: 80 (30 + 50)
    Actual Hours Used: 40 (20 + 20)
    Status: IN_PROGRESS (Tasks 3001, 3002 are IN_PROGRESS)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (301, 3, 'Data Mapping & Risiko', 'Kortlægning af datastrømme og vurdering af risici.', '2025-05-27', '2025-06-15', 'HIGH', 80, 40, 'IN_PROGRESS');

/* Tasks for Subproject 3.1 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3001, 301, 'Workshops med afdelinger', 'Facilitere workshops for at identificere datastrømme.', 'HIGH', 30, 20, 'IN_PROGRESS'); -- Task Dates: 2025-05-27 to 2025-06-05
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3002, 301, 'Udarbejdelse af datakort', 'Dokumentere alle identificerede datastrømme.', 'HIGH', 50, 20, 'IN_PROGRESS'); -- Task Dates: 2025-06-06 to 2025-06-15

/*
    Subproject 3.2: Anbefalinger og Handlingsplan
    Subproject Dates: 2025-06-16 to 2025-07-10 (within Project 3 dates)
    Estimated Hours: 80 (40 + 20 + 20)
    Actual Hours Used: 80 (40 + 20 + 20)
    Status: COMPLETED (All tasks are COMPLETED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (302, 3, 'Anbefalinger & Plan', 'Udarbejdelse af anbefalinger og handlingsplan for compliance.', '2025-06-16', '2025-07-10', 'MEDIUM', 80, 80, 'COMPLETED');

/* Tasks for Subproject 3.2 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3003, 302, 'Analyse af gaps', 'Identificere områder med manglende compliance.', 'HIGH', 40, 40, 'COMPLETED'); -- Task Dates: 2025-06-16 to 2025-06-25
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3004, 302, 'Præsentation af handlingsplan', 'Præsentation for kundens ledelse.', 'HIGH', 20, 20, 'COMPLETED'); -- Task Dates: 2025-06-26 to 2025-06-30
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3005, 302, 'Implementeringssupport', 'Løbende support under implementering af anbefalinger.', 'LOW', 20, 20, 'COMPLETED'); -- Task Dates: 2025-07-01 to 2025-07-10

/*
    Subproject 3.3: Træning og Opfølgning
    Subproject Dates: 2025-07-15 to 2025-07-31 (within Project 3 dates)
    Estimated Hours: 40 (15 + 20 + 5)
    Actual Hours Used: 0
    Status: NOT_STARTED (All tasks are NOT_STARTED)
*/
INSERT INTO Subprojects (subproject_id, project_id, subproject_name, subproject_description, start_date, end_date, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (303, 3, 'Træning & Opfølgning', 'Gennemførelse af træning for kundens medarbejdere og løbende opfølgning.', '2025-07-15', '2025-07-31', 'LOW', 40, 0, 'NOT_STARTED');

/* Tasks for Subproject 3.3 */
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3006, 303, 'Udvikling af træningsmateriale', 'Klargøring af præsentationer og øvelser.', 'MEDIUM', 15, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-15 to 2025-07-20
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3007, 303, 'Gennemførelse af træningssessioner', 'Afholde workshops for relevante afdelinger.', 'HIGH', 20, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-21 to 2025-07-28
INSERT INTO Tasks (task_id, subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (3008, 303, 'Opfølgende rådgivning', 'Besvare spørgsmål og yde support efter implementering.', 'LOW', 5, 0, 'NOT_STARTED'); -- Task Dates: 2025-07-29 to 2025-07-31