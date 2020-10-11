# Azure function-simple file processor

This is a simple Azure function, which gets triggered when a file is uploaded to a blob named "uploadedfiles".

It sorts the content of the file, and adds 4 character serial to each ID i.e. first 4 character of each line

## Environment Requiremnt for Development andDebugging

### Basic

- Java Development Kit
- Node x64 bit
- Python 3 x64 bit
- Visual Studio Code (VS Code)
- Git
- Azure Functions Core Tools
- Azure CLI
- VS Code Extentions
  - Azure Extentions Pack
  - Java Extentions Pack
- Postman

### For Azure storage related development

Microsoft SQL Server + Azure Storage Emulator

OR

Azurite

Microsoft Azure Storage Explorer

## Initialization Steps

- Open VS Code
- Click on Azure plugin on left
- Under function click create new project
- Select the target folder
- Select language as java
- Select version of java
- Give group id
- Give artifact id
- Provide a version for project
- Provide a package name
- Provide an app name
- Select add to workspace
- A new project will be created

### Given Values

- Language: java
- Version: 8
- Group ID: GROUP_ID
- Artifact ID: BlobProcessorJava
- ProjectVersion: 1.0-Snapshot
- App Name: BlobProcessorJava-RANDOM_NUMBER

### Structure at initialization

#### host.json

    {
    "version": "2.0",
    "extensionBundle": {
    "id": "Microsoft.Azure.Functions.ExtensionBundle",
    "version": "[1.\*, 2.0.0)"
    }
    }

#### local.settings.json

    {
      "IsEncrypted": false,
      "Values": {
        "AzureWebJobsStorage": "",
        "FUNCTIONS_WORKER_RUNTIME": "java"
      }
    }

## Preparing for blob trigger an blob output

### Update pom.xml files

Add dependencies for Azure storage, these dependencies are under comment "Azure storage dependencies" in "pom.xml"

### Update local.settings.json

#### For debug using local storage emulator

    {
      "IsEncrypted": false,
      "Values": {
        "AzureWebJobsStorage": "UseDevelopmentStorage=true;",
        "FUNCTIONS_WORKER_RUNTIME": "java"
      }
    }

#### For debug using Azure cloud storage

    {
      "IsEncrypted": false,
      "Values": {
        "AzureWebJobsStorage": "DefaultEndpointsProtocol=https;AccountName=<STORAGE_ACCOUNT_NAME>;AccountKey=<KEY>;EndpointSuffix=core.windows.net",
        "FUNCTIONS_WORKER_RUNTIME": "java"
      }
    }

### Desable Tests

under src\test in "FunctionTest.java" add annotation "@Disabled" over class and comment the last two lines in the function

## Creating local Azure storage emulator

### Using Azurite

Azurite is a open source cross plateform Azure storage emulator

#### Install Azurite (On Windows)

    choco azurite

#### Run Azurite

Create a directory ".azurite"

    azurite --location ./.azurite --debug ./.azurite/debug.log --silent

#### Connect Microsoft Azure Storage Explorer

- Open Microsoft Azure Storage Explorer
- Under accounts click "Add an account.."
- Select "Attach to local emmulator"
- Give "Display Name" and Click "Next"
- Click "Connect"

## To push/deploy the function to Azure

In Azure plugin on left

- Under function click login
- Complete login process
- Select deploy to Functions App
- Enter the asked values

### Given Values

- Globally Unique Name: UNIQUE_NAME
- Runtime Stack: Java 8
- Location : East Us
- OS: Linux
- Hosting Plan: Consuption
- Resource Group: RESOURCE_GROUP
- Storage Account: STORAGE_ACCOUNT
- Insight account: skip
