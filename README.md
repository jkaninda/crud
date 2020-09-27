# Android kotlin Mysql Library


[![License: Apache-2.0](https://img.shields.io/badge/License-Apache%202.0-yellow.svg)](http://www.apache.org/licenses/LICENSE-2.0)

## Download and Import
### PHP API

Download from [here](https://github.com/jkanTech/crud/raw/master/api/api.zip).


### Android Studio/Gradle



 - Maven:
 
 ```groovy
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
	
	

	<dependency>
	    <groupId>com.github.jkanTech</groupId>
	    <artifactId>Crud</artifactId>
	    <version>1.0.1</version>
	</dependency>


 ```
 
 - JitPack.io, add `jitpack.io` repositiory and dependency to your `build.gradle`:
 
 ```groovy
    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
	
    dependencies {
	        implementation 'com.github.jkantech:crud:1.0.2'
		}
 ```
 ## Usage
 
 1. Download the latest version of the API
 2. Unzip it in your server directory
 3. Go to the api/query/v1/database folder
 4. Open the config.php file
 5. Put the database username, password and database name
 
  ```php
  <?php
$DB_USER = "root";//Data base user
$DB_PASS = "root"; // Date base user pass
$DB_DATABASE = "crudexample"; //Data base name
   
  ```
   
### Android Studio

 ```groovy
    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
	
    dependencies {
	        implementation 'com.github.jkantech:crud:1.0.2'
		}
 ```
 ### Manifests
```xml
    <uses-permission android:name="android.permission.INTERNET"/>

<application

        android:usesCleartextTraffic="true"

```

### Sample Kotlin Usage 
#### Get All

```kotlin
 private fun getAll(){
  val crud=Crud(this,"http://192.168.8.101/api/query/v1/")

        
        crud.get("users",null,object :OnResponseListener{
            override fun onError(error: String?) {
                toastMessage(requireContext(),"Error")

            }

            //on response
            override fun onResponse(response: String?) {
                toastMessage(requireContext(),response.toString())
            }

        })
    }

```

```kotlin
private fun getUser(){
//get : table,condition

 val crud=Crud(this,"http://192.168.8.101/api/query/v1/")

        //Get Users with where condition
      
        val conds=JSONObject()
        try {
            conds.put("id",2)

        }catch (e:JSONException){
            e.printStackTrace()
        }
        crud.get("users", conds, object : OnResponseListener{
            override fun onError(error: String?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(response: String?) {
                TODO("Not yet implemented")
            }

        })
	}

```
#### Update

```kotlin
 private fun updateUser(id:Int,name:String,first_name:String,email:String){
  val crud=Crud(this,"http://192.168.8.101/api/query/v1/")

        val data=JSONObject()
        val conds=JSONObject()
        try {
            //Condition
            conds.put("id",id)

            //Rows and Values
            data.put("user_name",name)
            data.put("first_name",first_name)
            data.put("user_email",email)


        }catch (e:JSONException){
            e.printStackTrace()
        }
        crud.set("users",data,conds,object :OnResponseListener{
            override fun onError(error: String?) {
                toastMessage(requireContext(),"Error")

            }

            //on response
            override fun onResponse(response: String?) {
                toastMessage(requireContext(),response.toString())
            }

        })
    }

```




<h2 id="examples">Examples :eyes:</h2>

Download the [Crud Example App]() or look at the [source code](https://github.com/jkanTech/crud/tree/master/CrudExample).


<br/>
 
## Authors

* **Jonas Kaninda**  - [jkanTech](https://github.com/jkantech)


## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
