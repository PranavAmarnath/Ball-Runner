# Computer Programming Project
A game where an object has to avoid obstacles. The player must press <kbd>SPACE</kbd> to cause the ball to jump, which will allow it to avoid the obstacles. The goal of the game is to get to the highest level possible by avoiding the most obstacles that the player can. Made with Java Swing. - Aditya (Bobby), Pranav (Billy), Siddhant (Benny)

---------------------------------------------------------------------------------------------------

### (c) aditya2604, PranavAmarnath, sidhullur12.
This product is licensed under the MIT License. See LICENSE for more details.
#### Unless otherwise stated, all commands have been tested with Java SE 15 though they should work with later versions.

---------------------------------------------------------------------------------------------------

### Compilation (Described from command line using `java` tool):
It's Java, so compilation is same on most systems.
<br />
Minimum Supported Java Version: JDK11.
<br />
**NOTE: The following commands may vary on different platforms. Test different commands, but they will be very similar to those described.**
<br />
**NOTE: All paths described are only for demonstration of tree structure. Please change directory path during compilation and running accordingly.**
<br />
### V1:
```java
// If using release v1.0 Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal-1.0\"
// If cloning Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V1\"
> javac Main.java
> java Main
```
### V2:
Double-click the JAR file present in the folder. If this doesn't work, try the below steps in command line.
```java
// Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V2\"
> java -jar jarexample.jar
// OR
> java -jar jarexample_JDK11.jar
```
If the JAR file does not work, create your own by trying the below steps.
#### JAR Creation (Described from command line using `java` and `jar` tools):
From V2, Ball Runner uses a package, `ball_runner`, so compilation and running changes accordingly.
```java
// Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V2\"
> jar cfm jarexample.jar MANIFEST.MF ball_runner\"*".class ball_runner\img\"*"."*"
> java -jar jarexample.jar
```
JDK11:
```java
// Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V2\"
> jar cfm jarexample_JDK11.jar MANIFEST.MF ball_runner
> java -jar jarexample_JDK11.jar
```

---------------------------------------------------------------------------------------------------

### Gameplay
https://user-images.githubusercontent.com/59426357/128301046-507f1286-d982-48b1-9f69-f9c2978fdddc.mov

### Game Preferences
https://user-images.githubusercontent.com/59426357/128302597-6e9c1212-1da8-4c59-9eda-b3dc954a497e.mov
