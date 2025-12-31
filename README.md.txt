================================================================================
RECIPE MANAGEMENT SYSTEM - SETUP INSTRUCTIONS
================================================================================

PREREQUISITES:
1. Java JDK 17 or higher
2. MySQL Server
3. Spring Tool Suite or IntelliJ IDEA
4. Visual Studio Code (optional)
5. Web Browser

================================================================================
SETUP INSTRUCTIONS
================================================================================

STEP 1: DATABASE SETUP
----------------------
1. Open MySQL Workbench or MySQL Command Line
2. Run this command:
   CREATE DATABASE recipe_system;
3. Verify database is created

STEP 2: BACKEND SETUP
---------------------
1. Open Spring Tool Suite (STS)
2. File → Import → Existing Maven Project
3. Select the 'backend' folder from this ZIP
4. Wait for dependencies to download

5. Open: src/main/resources/application.properties
6. Update these lines:
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   spoonacular.api.key=YOUR_SPOONACULAR_API_KEY

7. To get Spoonacular API key:
   - Visit: https://spoonacular.com/food-api
   - Click "Get Started"
   - Register for free account
   - Copy your API key from "My Console"

8. Right-click on RecipeManagementSystemApplication.java
9. Select: Run As → Spring Boot App
10. Wait for console to show: "Started RecipeManagementSystemApplication"
11. Backend is now running on: http://localhost:8080

STEP 3: FRONTEND SETUP
----------------------
1. Open the 'frontend' folder
2. Double-click on 'index.html' to open in browser
   OR
3. Open frontend folder in VS Code
4. Right-click index.html → Open with Live Server

STEP 4: TEST THE APPLICATION
----------------------------
1. You should see the landing page
2. Click "Get Started" to register
3. Fill in the form and submit
4. Login with your credentials
5. Browse recipes and enjoy!

================================================================================
TROUBLESHOOTING
================================================================================

Problem: Backend won't start
Solution: Check if MySQL is running and credentials are correct

Problem: No recipes showing
Solution: Verify your Spoonacular API key is valid

Problem: Port 8080 already in use
Solution: Change port in application.properties:
          server.port=8081

Problem: Database connection error
Solution: Ensure MySQL password in application.properties is correct

================================================================================
FOLDER STRUCTURE
================================================================================

Recipe-Management-System-Complete/
├── backend/                  (Spring Boot Project)
├── frontend/                 (HTML, CSS, JS files)
├── README.txt               (This file)
└── Documentation.pdf        (Complete project documentation)

================================================================================
SUPPORT
================================================================================

For any issues, please refer to the Documentation.pdf file or contact:
Email: biswalsubham990@gmail.com

Thank you for using Recipe Management System!
================================================================================