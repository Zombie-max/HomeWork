AUTOMATION TEST
HOMEWORK

Preconditions:
- Java 1.8 (or newer)
- Selenium 3.141.59
- JUnit5 or TestNG
- Rest API library:
        • Okhttp3
        • Apache Http

Case 1 - Automate User Registration Process
1. Open this url: http://automationpractice.com/index.php
2. Click on sign in link.
3. Enter your email address in ‘Create and account’ section.
4. Click on Create an Account button.
5. Enter your Personal Information, Address and Contact info.
6. Click on Register button.
7. Validate that user is created.

Case 2 - Verify error messages for mandatory fields
1. Open this url http://automationpractice.com/index.php
2. Click on sign in link.
3. Enter email address and click Register button.
4. Leave the mandatory fields (marked with *) blank and click Register button.
5. Verify that error has been displayed for the mandatory fields.

Case 3 - iFrame and tab handling
1. Open this url http://demo.guru99.com/test/guru99home/
2. Locate the image inside the iframe near bottom of the page
just above “Email Submission” and click on it
3. Verify new page is loaded in new tab with title “Selenium Live Project: FREE Real Time
Project for Practice”
4. Close this tab and switch back to main window
5. From top menu click on Selenium link that can be seen while hovering on Testing menu
item
6. Verify the wide red “Join Now” button is displayed near bottom of the page

Case 4 - Scroll down with JavaScript
1. Open this url: http://automationpractice.com/index.php
2. With JavaScript keep on scrolling down the page by 100 pixels until the footer of the page
becomes visible

Case 5 – REST API testing
1. Add Rest Api library for the test project
2. Create and send request:
Request type: GET
Host: https://jsonplaceholder.typicode.com
Endpoint: /users
GET https://jsonplaceholder.typicode.com/users
3. Parse response to Json format
4. Log only the names and emails from the response data
Example:
Leanne Graham | Sincere@april.biz
Ervin Howell | Shanna@melissa.tv
5. Verify the first email address contains ‘@’

