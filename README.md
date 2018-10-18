JBirdie
=======

Yet Another useless dialect of Lisp.

### Building

The project uses Maven. To build a jar file, simply do:

    mvn package
    
A jar file with named with the appropriate version should exist in `target/`.

### Examples

Examples are provided within `examples/`. To run one, run something like:

    java -jar target/jbirdie-0.0.1.jar examples/choose-your-bonus.bdl
    
### Quirks

* The interactive prompt requires a full statement within only one line.
* `read-number` throws an exception if the user inputs a non-number value.
* The interpreter can only currently parse cons lists, e.g. `(1 . 2)` cannot 
  be parsed.