# Student Feedback System
## How to deploy:
1. pull front end from [this repository](https://github.com/FunnyPhantom/Student_Feedback_Automation_System_Frontend) to `/Front` repository.
2. open up `cmd` to current directory
3. run `deploy.bat`
4. wait for all processes to finish.
5. open up [`http://localhost`](http://localhost)
6. voila!

### Reqirement:
#### Build:
* npm
* maven
* jdk 12.0.1 or higher
* git
* internet connection

#### Run:
* npm
* jre 12.0.0 or higher
* port `8082`, `3000` and `80` (respectively for backend, fronend and nginx)


##### Common Problems
* ports are not used by other processes:
    search for commands respectively to your operating system which allows you to see which process uses the ports stated above and terminate them.
* port `80` is used by system and wont let go:
    disable that damn IIS service.
* `[npm | mvn | java] is not recognized as an internal or external command`: download the tools stated above.
* `java.lang.UnsupportedClassVersionError: blah blah blah has been compiled by a more recent version of the Java Runtime **(class file version 56.0)**, this version of the Java Runtime only recognizes class file versions up to X.Y`:
    the JRE known to you computer (A.K.A: env varable PATH), is lower that 12, if you have indeed installed JRE 12 or higher, add that to PATH, or if it's there, delete other java pathes that are lower than JRE 12.
    
Hope you enjoy this shit.