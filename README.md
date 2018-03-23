A training project, a web application for working with online shop.
Was implemented about a year ago.
##### Was Used
- HTML/CSS/Bootstrap;
- JQuery;
- Javascript(ES5);
- Local storage and Session storage API;
- JAVA EE(Servlets);
- MySQL database;
- GlassFish application server;
- SOAP web-service to work with the prices that are located on another server;
##### Implemented
- Registration and authorization of users(admin or buyer);
- Administration panel for CRUD operations;
- Filtering, sorting, searching;
- Shopping cart(for unregistered user's too);
- User profile and purchase history;
- Rating for products(jquery.star-rating-svg.js);
- Administrator setup Latest or Most popular products will be displayed on the main page;
- CLI for SOAP service to manage prices;
##### Run
- Create Database from ```mysql_shop_db_dump.sql```;
- ```java -jar run/SOAP_WebService.jar``` ;
- Glassfish ```asadmin deploy shop.war```;
- go to ```localhost:port/shop/```;