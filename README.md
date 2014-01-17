# Conway's Game of Life
A simulation of [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) that runs on the console.

## Instructions
In order to build the corresponding artifacts, you must install [Java 1.7](http://www.java.com/en/download/index.jsp)
and [Maven 3.0](http://maven.apache.org/download.cgi).

In the root directory `life`, execute the following command:
```
$ mvn package
```

This produces two identical assemblies in `life/target` that contain everything needed to run the program.
```
life/
+ target/
  + life-1.0-bin.tar.gz
  + life-1.0-bin.zip
```

Unpacking either assembly produces the following output:
```
life-1.0/
+ bin/
  + life
+ lib/
  + ...
```

Run `bin/life --help` for instructions.
