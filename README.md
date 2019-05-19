JBirdie
=======

Yet another dialect of Lisp. Done for a Computer Science assignment 
to build an interpreter with a concept of arrays, variables, and a 
rudimentary class-object model.

[Documentation for all the functions here.](./REFERENCE.md)

### Building

The project uses Maven. To build a jar file, simply do:

    mvn package
    
A jar file with named with the appropriate version should exist in `target/`.

### Examples

Examples are provided within `examples/`. To run one, run something like:

    java -jar target/jbirdie-0.0.1.jar examples/choose-your-bonus.bdl
    
The tests files are also good to look at.
    
### Quirks

* The interactive prompt requires a full statement within only one line.
* `read-number` is unstable. ¯\\\_(ツ)\_/¯
