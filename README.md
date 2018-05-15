IBM MobileFirst Android JSONStore Sync

[![](https://img.shields.io/badge/bluemix-powered-blue.svg)](https://bluemix.net)
[![Platform](https://img.shields.io/badge/platform-android-lightgrey.svg?style=flat)](https://developer.android.com/index.html)

### Table of Contents
* [Summary](#summary)
* [Requirements](#requirements)
* [Conversation Configuration](#conversation-configuration)
* [Mobile Foundation Configuration](#mobile-foundation-configuration)
* [Run](#run)
* [License](#license)

### Summary
This IBM Cloud Mobile Offline Data Synch Starter showcases the Mobile Foundation and Offline Data Synch capabilities and provides the integration points for each of the IBM Cloud Mobile services.

### Requirements
* [Android Studio](https://developer.android.com/studio/index.html)
* An [IBM Cloud Account](https://www.bluemix.net/)

Creating an instance of the Offline Data Synch starter will create an instance of Cloudant NoSQL DB and Mobile Foundation service.

### Cloudant Configuration
* Create the [Cloudant Service](https://console.bluemix.net/catalog/services/cloudant-nosql-db) in an [IBM Cloud Account](https://www.bluemix.net/)
* To configure cloudant NoSQL DB for the Offline Data Synch app navigate to the cloudant instance created and from the service credentials tab copy the user name and password and update this information in **populate_cloudant_db.sh** which can be found in the android project folder. Ensure that you have *execute* and *write* permissions to run these scripts.
* Run **populate_cloudant_db.sh**. This should populate the cloudant database with demo data.


### Mobile Foundation Configuration

##### Steps:
Follow the steps below to configure the Mobile Foundation service

* Open the project in Android Studio and perform a Gradle Sync.

* Register the application in MFP Operations Console and Update the server details in **mfpclient.properties** of the application.

* Download the JSONStoreSync adapter from the 'Deploying the sync adapter' section from [this link](https://mobilefirstplatform.ibmcloud.com/blog/2018/02/23/jsonstoresync-couchdb-databases/).

* After registration , navigate to mfp operations console , go to the dashboard , Click on New button beside the Adapters tag, Click on Deploy Adapter button and upload the downloaded .adapter file. 

* Configure the adapter with the Cloudant NoSQL DB credentials with database name as 'foodmenu'.

### Run
You can now run the application on a simulator or a physical device

### License
This package contains code licensed under the Apache License, Version 2.0 (the "License"). You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 and may also view the License in the LICENSE file within this package.
