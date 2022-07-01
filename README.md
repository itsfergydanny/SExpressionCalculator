# SExpressionCalculator
I was tasked to create a simple integer calculator that can process a syntax that resembles [S-expressions](https://en.wikipedia.org/wiki/S-expression) 
which can take english like expressions and return their result and that is capable of processing nested expressions.

<br>  

## How to compile
Requirements: 
* Java 17
* Maven

<br>  

You can compile the project using Maven by running the following while in the project root folder:
```shell
mvn package
```
  
  
<br>  

## How to execute
Once compiled, you can run the jar as follows.
```shell
# Where <expression> is an S-expression such as (add 12 12)
java -jar ./target/SExpressionCalculator-1.0-SNAPSHOT-shaded.jar "<expression>"

# For example
java -jar ./target/SExpressionCalculator-1.0-SNAPSHOT-shaded.jar "(add 12 12)"
```

<br>  

An expression is formed the following way: (`function` `integer/nested expression` `integer/nested expression`)

<br>  
Available functions:  

| Name     | Syntax Example    | Expected Result |
|----------|-------------------|-----------------|
| Add      | (add 10 10)       | 20              |
| Multiply | (multiply 10 5)   | 50              |
| Subtract | (subtract 100 25) | 75              |
| Exponent | (exponent 2 5)    | 32              |


<br>  

## Extra credit:
* Implemented subtract and exponent function types since it was easy to extend my base code to cover these.
* Made it so you can specify as many arguments to a function as you want, for example: "add 10 20 30 40 50"