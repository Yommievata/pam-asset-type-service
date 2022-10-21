# pam-asset-type-service

To be able to run the application properly you need to have all this installed

- An IDE (Intellij recommended)
- Java 11
- Docker

Download the project to your computer and follow the steps below on
how to set everything up.

## How to run & debug application in local environment
### Add project to Intellij (Recommended)

1. git clone git@github.com:icomdev/pam-assert-type-service.git
2. IntelliJ -> New -> Project from Existing Sources...
3. Select the root pom
4. After the project is loaded, edit 'StartSpringbootApi' configuration, add *Environment Variable*.
5. Run the application


Navigate to the root of pam-asset-type-service **mvn clean install**, this will create everything that pam-assert-type-service
need itself.

To debug connect a *Remote JVM Debug* configuration on port **5006**

### Environment variables

Important environment variables and how to set them up to get everything running.
<br> *Note: The variables are set in the StartSpringbootApi configuration in Intellij as it holds configuration settings for the environment you will be using*

Budget-service uses the following environment variables:
* spring.profiles.active=local

## Ports

| Service            | Port  |
|--------------------|-------|
| pam-budget-service | 23151 |
| Remote JVM Debug   | 5006  |


## Database

### Local

pam-asset-type-service uses the H2 engine to run an embedded db in local environment and its pipelines.

To view the database:

1. Start pam-asset-type-service application
2. Go to localhost:23151/h2-console
3. User name: sa
4. JDBC URL: jdbc:h2:mem:asset-type-service

#### H2 Database Credentials

Note: Database credentials in application-local.properties must be correct
* spring.datasource.username=sa
* spring.datasource.password=

#### Azure Database

pam-asset-type-service uses Postgres as it's RDBMS

To view the database it's recommended to use pgAdmin:

1. Install and start up the program
2. Username and password exists as secrets in Azure

## Swagger

Swagger includes automated documentation of the restful APIs expressed using json. It displays all the endpoints in a project and generates test-cases
for those. To be able to test the endpoints, an authorization is needed through OAuth2. After that all endpoints can be tested out.
<br>
The site displays which environment that the project is currently running on under the title "Environment:".
<br>
When the asset-type-service application is running:
* To access the site for the Local environment, swagger can be found with this url `http://localhost:23151/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`
* To access the site for the Dev environment, swagger can be found with this url `https://app-pam-asset-type-service-dev.azurewebsites.net/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`
* To access the site for the Test environment, swagger can be found with this url `https://app-pam-asset-type-service-test.azurewebsites.net/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`
* To access the site for the stage environment, swagger can be found with this url `https://app-pam-asset-type-service-stage.azurewebsites.net/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`
* To access the site for the Prod environment, swagger can be found with this url `https://app-pam-asset-type-service-prod.azurewebsites.net/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`

The configuration file for swagger is in **apiconfiguration/OpenapiConfiguration.java**

Note: All environments except local are protected using client ID. To get access to the environments, fetch client ID from Azure Key Vault [https://portal.azure.com/#@OneIIG.onmicrosoft.com/resource/subscriptions/73352462-5185-4eab-afca-eed6b857bbe0/resourceGroups/BackendRewrite/providers/Microsoft.Web/sites/app-pam-asset-type-service-dev/configuration].

## Client URL for Environment

* Local: http://localhost:3000
* Stage: https://stage.forena.inter.ikea.net/
* Prod: https://forena.inter.ikea.net/

## Testing (Local Tips)

* Run the application before running the component test. Make sure that your docker is running
* Keep in mind though that you, the user, is responsible for the application lifecycle.

## How to deploy

1. Create a branch from main
2. Make changes in code and commit
3. Create a pull request to master and wait for approval
4. Merge to main (Deploys automatically to dev)
5. Test in dev environment
6. If test is successful, click on deploy in test environment. This will deploy to test
7. Test in test environment
8. If test is successful, click on deploy in stage environment. This will deploy to stage
9. Test in stage environment
10. If test is successful, click on deploy in prod environment. This will deploy to prod
11. Test in prod environment if needed

## How to use Asset-type-service-client

Changes in Asset-type-service-client means the version of the client has to be changed in the Asset-type-service-client pom.xml. This means that every change in the Client has to be merged first before a new version of the client is published (Version can be found in Azure Artifacts).
<br>

