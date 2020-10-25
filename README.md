# Android Kotlin RecyclerView App consuming RESTFul Node API :iphone:

![alt text](https://github.com/Nxssie/KotlinNode/blob/master/AppFlow.png)

### Prerequisites :notebook:

To install this project you need:
* A working node environment.
* A working Android environment.
* A Mysql Server.

You'll need to understand the HTTP METHODS:
* [Postman Documentation](https://documenter.getpostman.com/view/13039694/TVYF8Jio)

Each task needs:
* ID (Auto-incremented in the database so you can keep empty)
* Title (Varchar(30))
* Description (Varchar(255))
* Done (Boolean / Tinyint(1))

### Installing :gear:

Open a command line console and clone this project.

```
git clone https://github.com/Nxssie/KotlinNode
```

Go to the new created directory. After that go to the backend directory and install all dependencies:

```
cd KotlinNode
cd backend
npm install
```

After that start your MySQL Server and import the database [db_tasks.sql](https://github.com/Nxssie/KotlinNode/blob/master/backend/db_tasks.sql) included in the /backend directory of this project.

Start the backend project

```
node server.js
```

And finally start the frontend Android App with Android Studio (ver. 4.1).

## Built With :wrench:

* [Android Studio](https://developer.android.com/studio?hl=es) - Android Studio IDE.
* [Git](https://git-scm.com) - You can install it from https://git-scm.com/downloads.
* [MySQL](https://www.mysql.com) - You can install it from https://www.mysql.com/downloads/.
* [Node.js](https://nodejs.org) - Install node.js from https://nodejs.org/es/download/. It's advisable to install the LTS version.

## Acknowledgments :paperclip:

* https://developer.android.com/training/volley/requestqueue?hl=es - How to configure a Singleton.
* https://developer.android.com/training/volley?hl=es - How to send data with Volley from Kotlin App.
* https://www.varvet.com/blog/kotlin-with-volley/ - Kotlin with Volley