# SExpressionCalculator
This is a simple version of an S-expression calculator as part of an assessment I was given.

<br>

Requirements: 
* Java 17
* Maven

<br>

You can compile the project using Maven by running the following while in the project root folder:
```shell
mvn package
```

<br>


Once compiled, you can run the jar as follows.
```shell
# Where <expression> is an S-expression such as (add 12 12)
java -jar ./target/SExpressionCalculator-1.0-SNAPSHOT-shaded.jar "<expression>"

# For example
java -jar ./target/SExpressionCalculator-1.0-SNAPSHOT-shaded.jar "(add 12 12)"
```

<br>

Extra credit:
* Implemented a subtract and exponent function types since it was easy to extend my base code to cover these.