# Computer Programming Project
A game where an object has to avoid obstacles. The player must press <kbd>SPACE</kbd> to cause the ball to jump, which will allow it to avoid the obstacles. The goal of the game is to get to the highest level possible by avoiding the most obstacles that the player can. Made with Java Swing. - Aditya (Bobby), Pranav (Billy), Siddhant (Benny)

---------------------------------------------------------------------------------------------------

## Coming Soon: New Release (V2)!

### (c) Three Goats. All rights reserved. (This is a joke, don't take seriously please).

---------------------------------------------------------------------------------------------------

### Compilation (Described from command line using `java` tool):
It's Java, so compilation is same on most systems.
**NOTE: All paths described are only for demonstration of tree structure. Please change directory path during compilation and running accordingly.**
#### V1:
```java
// If using release v1.0 Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal-1.0\"
// If cloning Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V1\"
> javac Main.java
> java Main
```
#### V2:
From V2, Ball Runner uses a package, `ball_runner`, so compilation and running changes accordingly.
```java
// Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V2\"
> javac ball_runner\Main.java
> java ball_runner\Main.java
```

### JAR Creation (Described from command line using `java` and `jar` tool):
From V2, Ball Runner uses a package, `ball_runner`, so compilation and running changes accordingly.
```java
// Directory -> "C:\Users\Jon Doe\Downloads\CompProgFinal\V2\"
> jar cfm jarexample.jar MANIFEST.mf ball_runner\"*".class ball_runner\img\"*"."*"
> java -jar jarexample.jar
```
