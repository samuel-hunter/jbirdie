# BirdieLisp Function Reference

A comprehensive list of most functions builtin to the interpreter and
 in the standard libraries.

### Builtin Fucntions

#### while

```
(while COND
    BODY....)
```

While `COND` is truthy, evaluate `BODY`.

**Example:**

```lisp
(defvar counter 10)
(while (> counter 0)
    (println counter) ; Print to console.
    (dec counter)     ; Decrement counter.
)
```

#### foreach

```
(foreach (VARNAME LIST)
    BODY...)
```

Bind `VARNAME` to each element in `LIST` and evaluate `BODY`.

**Example:**

```lisp
(defvar numbers (range 50)) ; (1 2 3 4 ... 50)
(foreach (number numbers)
    (println number) ; Print number to console.
)
```

#### doc

```
(doc SYMBOL)
```

Return the docstring of `SYMBOL`

**Example:**

```lisp
(defvar students 100 "The number of students.")
(doc 'students) ; --> "The number of students."

(defun foo (X)
    "Return the first argument."
    X)
(doc 'foo) ; --> "Return the first argument."
```

#### env

```
(env)
```

Return an associative list of the program's environment.

#### read-line

```
(read-line)
```

Read a line from console input and return it as a string.

#### read-number

```
(read-number)
```

Read a number from console input and return it as a number.

**Example:**

```lisp
;; Read two numbers, add them together and print the output.
(println (+ (read-number) (read-number)))
```

#### print

```
(print ARGS...)
```

Concatenate `ARGS` and print the result to standard output. Return the 
concatenated result.

**Example:**

```lisp
(print "Hello, World!")
(print "Hello" "World")
(print "A" (+ 10 20) "C")
``` 

*Output:*

```
Hello, World!HelloWorldA30C
```

#### println

```
(println ARGS...)
```

Concatenate `ARGS` and print the result to standard output, followed by a 
newline. Return the concatenated result.

**Example:**

```lisp
(println "Hello")
(println "World!")
```

*Output:*

```
Hello
World!
```

#### quote

```
(quote ARG)
```

Return `ARG`, unevaluated.

**Example:**

```lisp
(quote (+ 20 30)) ;; (+ 20 30)
(quote foo) ;; foo
(quote 'foo) ;; (quote foo)
```

#### atom

```
(atom ARG)
```

Return truthy if `ARG` is atomic -- that is, it can't be broken down into two
 parts.
 
 **Example:**
 
 ```lisp
 (atom "hello")    ; --> t
 (atom t)          ; --> t
 (atom nil)        ; --> t
 (atom (cons 1 2)) ; --> nil
 ```
 
 
#### eq

```
(eq ARG1 ARG2)
```

Return truthy if `ARG1` is effectively equal to `ARG2` -- that is, numbers 
are the same, strings share the same value, and cons objects hold identical 
objects.

**Example:**

```lisp
(eq 1 1)             ; --> t
(eq 1 2)             ; --> nil
(eq "Hello" "hello") ; --> nil
(eq "Hello" "Hello") ; --> t
(eq '(1 2) '(1 2))   ; --> t
(eq '(1 2 3) '(1 2)) ; --> nil
```

#### cons

```
(cons CAR CDR)
```

Create a cons pair that contains two elements.

**Example:**

```lisp
(cons 1 2)                     ; --> (1 . 2)
(cons 3 4)                     ; --> (3 . 4)
(cons 1 (cons 2 3))            ; --> (1 2 . 3)
(cons 1 (cons 2 (cons 3 nil))) ; --> (1 2 3)
```

#### cond

```
(cond
    (COND1 RESULT1)
    (COND2 RESULT2)
    ...)
```

Return the first `RESULT` where its respective `COND` is truthy, or return 
nil if all CONDs are falsy.

**Example:**

```lisp
(cond
    (nil "foo")
    (t "bar")
    (nil "qux"))           ; --> "bar"
    
(cond
    ((= 1 1) "foo")
    ((= 2 2) "bar"))       ; --> "foo"
    
(cond
    (t "foo")
    (nil (println "bar"))) ; --> foo

;; NOTE: because `t` was truthy, the expression returned early and "bar" was 
;;  never printed.
```

#### car

```
(car CONS)
```

Return the CAR of a cons pair.

**Example:**

```lisp
(car (cons "foo" "bar")) ; --> "foo"
```

#### cdr

```
(cdr CONS)
```

Return the CDR of a cons pair.

**Example:**

```lisp
(car (cons "foo" "bar")) ; --> "bar"
```

#### lambda

```
(lambda (ARGS...)
    BODY...)
```

Return an anonymous function that, when supplied with arguments, returns the 
evaluated expression in `BODY`.

**Example:**

```lisp
(defvar double
        (lambda (X)
            (* X 2)))
            
(double 10) ; --> 20 
```

#### macro

```
(macro (ARGS...)
    BODY...)
```

Return an anonymous macro. *See `defmacro` for more details.*

**Example:**

```lisp
(defvar mymacro
        (macro (X)
            (reverse X)))

(mymacro (1 2 -)) ; --> 1
;; NOTE: this is identical to replacing the macro call with (- 2 1)
```

#### apply

```
(apply FUNCTION ARGS)
```

Apply `ARGS` to `FUNCTION`.

**Example:**

```lisp
(defvar mynumbers (range 10)) ; --> (1 2 3 ... 10)

(apply + mynumbers) ; --> 55
```

#### let

```
(let ((VARNAME1 VALUE1)
      (VARNAME2 VALUE2)
      ...)
     BODY...)
```

Evalute `BODY` with all `VARNAME`s bound to its respective `VALUE`.

**Example:**

```lisp
(let ((foo "a")
      (bar "b")
      (qux (+ 1 2)))

    (concat foo bar qux) ; --> "ab3.0"
)
```

#### progn

```
(progn
    BODY...)
```

Evaluate `BODY` and return its last expression.

**Example:**

```lisp
(progn
    1
    2
    3) ; --> 3
    
(progn
    (println "foo")
    (println "bar")
    (println "qux")) ; --> "qux"
```

*Output:*

```
foo
bar
qux
```

#### list

```
(list ARGS...)
```

Return a cons list containing all arguments.

**Example:**

```lisp
(list 1 2 3) ; -> (1 2 3)
(list 1 2 (+ 10 10)) ; -> (1 2 20)
```

#### append

```
(append LISTS...)
```

Combine all lists and return the result.

**Example:**

```lisp
(append (list 1 2) (list 3 4) (list 5 6)) ; --> (1 2 3 4 5 6)
```

#### +, -, *, /, <, >, <=, >=, %

Do the mathematical functions and comparisons they're supposed to do.

#### defconst

```
(defconst VARNAME VALUE [DOC])
```

Define a constant variable `VARNAME` with `VALUE` assigned to it, with an 
optional `DOC` docstring (see `doc`). If the variable already exists in an 
outer scope, `defconst` shadows that variable until the scope reaches out.

**Example:**

```lisp
(defconst foo 10)
foo ; --> 10

(setq foo 5) ; ERROR! You can't reset the value of constants!
```

#### defvar

```
(defvar VARNAME VALUE [DOC])
```

Define a variable `VARNAME` (unevaluated) with `VALUE` assigned to it, with an 
optional  `DOC` docstring (see `doc`). Return `VARNAME`. If the variable 
already  exists in an outer scope, `defvar` shadows that variable until the 
scope reaches out.

**Example:**

```lisp
(defvar foo 10) ; foo
foo ; --> 10

(setq foo 5)
foo ; --> 5
```

#### setq

```
(setq VARNAME VALUE)
```

Set `VARNAME` (unevaluated) to new value `VALUE`. Return `VARNAME`. If 
`VARNAME` does not yet exist, define it.

**Example:**

```lisp
(setq foo 10) ; --> foo
foo ; --> 10
(setq foo 5) ; --> foo
foo ; --> 5
```

#### defun


```
(defun NAME (ARGS...)
    [DOC]
    BODY...)
```

Define function `NAME` with args `ARGS` and set an optional `DOC` docstring 
to it (see `doc`).

**Example:**

```lisp
(defun doublemynumber (X)
    (* X 2))
    
(doublemynumber 10) ; --> 20
```

#### defmacro

```
(defmacro NAME (ARGS...)
    [DOC]
    BODY...)
```

Define macro `NAME` with args `ARGS` and set an optional `DOC` docstring to 
it (see `doc`). When a macro is called, its arguments remain unevaluated (i.e
. they're passed into the macro as-is), and the macro call gets substituted 
by the macro's result. This is commonly used for metaprogramming, e.g. 
stdclass's `defclass`.

**Example:**

```lisp
(defmacro mymacro (X)
    (reverse X))
    
;; NOTE: this is identical to (println "Hello, World!")
(mymacro ("Hello, World!" println))
```

*Output:*

```
Hello, World!
```

#### concat

```
(concat ARGS...)
```

Convert `ARGS` to string form, and return the concatenated result.

**Example:**

```lisp
(concat "Hello" "World") ; --> "HelloWorld"
(concat (+ 1 2) (+ 3 4)) ; --> "3.07.0"
```

#### to-string

```
(to-string ARG)
```

Convert `ARG` to a string value.

```lisp
(to-string 1)        ; --> "1.0"
(to-string "a")      ; --> "a" (this does nothing.)
(to-string '(1 2 3)) ; -> "(1 2 3)"
```

#### debug

```
(debug [ON?])
```

With no value, `debug` returns whether interpreter debugging is on. With a 
value, `debug` sets interpreter debugging to whether `ON?` is truthy.

Currently, all it does is print the Java Stack trace if the prompt hits an 
interpreter-induced excpetion at runtime.

#### exit

```
(exit [STATUS])
```

Exit the program, with an optional `STATUS` code (0 if none provided).

#### error

```
(error ARGS...)
```

Raise an exception, with `ARGS` concatenated as the error message.

### stdlib - standard base libary.

#### caar, cadr, cdar, cddr, etc.

Shortcuts for performing compound `car`/`cdr` operations, e.g. `(cdar CONS)` 
is congruent to `(cdr (car CONS))`.

#### first, rest, =

Aliases for `car`, `cdr`, and `eq` respectively.

#### null

```
(null ARG)
```

Return truthy if `ARG` is nil.

#### not

```
(not ARG)
```

Return `nil` if `ARG` is truthy; otherwise, return `t`.

#### and

```
(and ARG1 ARG2)
```

Return `ARG2` if `ARG1` is truthy; otherwise, return `nil`.

#### or

```
(or ARG1 ARG2)
```

Return `ARG1` if truthy; otherwise, return `ARG2` if truthy; otherwise, 
return nil.

#### if

```
(if COND
    WHENCASE
    [OTHERWISECASE])
```

Evaluate `WHENCASE` if `COND` is truthy; otherwise, evaluate `OTHERWISECASE` 
if it exists; otherwise, return `nil`.

**Example:**

```lisp
(if t "foo" "bar") ; --> "foo"
(if nil "foo" "bar") ; --> "bar" 
```

#### when

```
(when COND
    BODY...)
```

Evaluate `BODY` if `COND` is truthy; otherwise return `nil`.

**Example:**

```lisp
(defun checkgrades (Grades)
    "Report if the student has no grades."
    (when (null Grades)
          (println "The student has no grades!")))
```

#### unless

```
(unless COND
    BODY...)
```

Unless `COND` is truthy, evaluate BODY.

**Example:**

```lisp
(defun checkgrades (Grades)
    "Report if the student has grades."
    (unless (null Grades)
        (println "The student has their grades.")))
```

#### until

```
(until COND
    BODY...)
```

Until `COND` is truthy, evaluate `BODY`.

**Example:**

```lisp
;; Double the user's numbers until they enter "0".
(defvar Number nil)

(until (= Number 0)
    (setq Number (read-number))
    (println (* Number 2)))
```

#### assert

```
(assert COND)
```

Raise an error if `COND` is not truthy. Handy in tests.

**Example:**

```lisp
(assert (= (+ 1 2) 3)) ; OK
(assert (= 1 2))       ; ERROR!
```

### stdlist - standard list library.

#### fold

```
(fold FUNC START LIST)
```

Take a two-argument function `FUNC` and collapse `LIST` through this function.

**Example:**

```lisp
;; 0 + 1 + 2 + 3 + ... + 10
(fold + 0 (range 10)) ; --> 55

;; 1 * 1 * 2 * 3 * 4 * 5
(fold * 1 (range 5))  ; --> 120
```

#### mapcar

```
(mapcar FUNC LIST)
```

Return a new list where each element in `LIST` is processed by a 
single-argument function `FUNC`

**Example:**

```lisp
(mapcar +1 (range 5))       ; --> (2 3 4 5 6)

(mapcar to-string '(1 2 3)) ; --> ("1.0" "2.0" "3.0")


(defun *2 (X)
    (* X 2))
    
(mapcar *2 (range 5))       ; --> (2 4 6 8 10)
```

#### nth

```
(nth NTH LIST)
```

Return the `NTH` element of `LIST`, zero-indexed.

**Example:**

```lisp
(nth 1 '("foo" "bar" "qux")) ; --> "bar"
```

#### size

```
(size LIST)
```

Return the size of `LIST`

**Example:**

```lisp
(size '("foo" "bar" "qux")) ; --> 3
(size (range 5))            ; --> 5
(size (range 100))          ; --> 100
```

#### member

```
(member ELEM LIST)
```

Return true if `ELEM` is a member of `LIST`.

#### push

```
(push ELEM VARNAME)
```

Reassign `VARNAME` to a list with `ELEM` in it. Return `VARNAME`.

**Example:**

```lisp
(defvar mylist (range 5)) ; --> (1 2 3 4 5)
(push "foo" mylist) ; --> mylist

;; mylist is now ("foo" 1 2 3 4 5).
```

#### range

```
(range SIZE)
```

Return a list of size `SIZE` starting at 1 and ending at `SIZE`.

**Example:**

```lisp
(range 10) ; --> (1 2 3 4 5 ... 10)
(range 1)  ; --> (1)
(range 3)  ; --> (1 2 3)
```

#### reverse

```
(reverse LIST)
```

Return `LIST`, reversed.

**Example:**

```lisp
(reverse (range 5)) ; --> (5 4 3 2 1)
```

#### uniq

```
(uniq LIST)
```

Return `LIST` with all repeat elements removed.

**Example:**

```lisp
(uniq '(1 2 3 2 1 2 1 1 1 4 1 2 1 3)) ; --> (1 2 3 4)
```

#### filter

```
(filter TEST LIST)
```

Return a list that contains elements `LIST` ONLY if it can be applied to a 
one-argument predicate function `TEST` and return a truthy value.

**Example:**

```lisp
(filter odd? (range 10)) ; --> (1 3 5 7 9)
(filter even? (range 10)) ; --> (2 4 6 8 10)


(defun small? (X)
    (< X 5))
    
(filter small? (range 10)) ; --> (1 2 3 4)
```

#### assoc

```
(assoc KEY ALIST)
```

Search though an associative list `ALIST` and return a pair where its car 
matches `KEY`.

**Example:**

```lisp
(defvar myalist '((a . 1)
                  (b . 2)
                  (c . 3)))
                  
(assoc 'a myalist) ; --> (a . 1)
(assoc 'b myalist) ; --> (b . 2)
(assoc 'c myalist) ; --> (c . 3)

(assoc 'doesntexist myalist) ; --> nil                  
```

#### assocr

```
(assoc KEY ALIST)
```

Search though an associative list `ALIST` and return a pair where its cdr 
matches `KEY`.

**Example:**

```lisp
(defvar myalist '((a . 1)
                  (b . 2)
                  (c . 3)))
                  
(assocr 1 myalist) ; --> (a . 1)
(assocr 2 myalist) ; --> (b . 2)
(assocr 3 myalist) ; --> (c . 3)

(assocr 100 myalist) ; --> nil                  
```

#### akeys

```
(akeys ALIST)
```

Return a list of all keys from `ALIST`.

**Example:**

```lisp
(defvar myalist '((a . 1)
                  (b . 2)
                  (c . 3)))

(keys myalist) ; --> (a b c)
```

#### avalues

```
(avalues ALIST)
```

Return a list of all values from `ALIST`.

**Example:**

```lisp
(defvar myalist '((a . 1)
                  (b . 2)
                  (c . 3)))

(values myalist) ; --> (1 2 3)
```

#### zip

```
(zip KEYS VALUES)
```

Zip two values into an a-list.

**Example:**

```lisp
(zip '(a b c) '(1 2 3)) ; --> ((a . 1) (b . 2) (c . 3))
```

#### alist?

```
(alist? ALIST)
```

Return truthy if `ALIST` is a valid a-list.

### stdmath - standard math library.

#### +1

```
(+1 X)
```

Return `X` + 1.

#### -1

```
(-1 X)
```

Return `X` - 1.

#### sum

```
(sum LIST)
```

Sum all values from `LIST` and return the result.

#### avg

```
(avg LIST)
```

Return the arithmetic mean from `LIST`.

#### min

```
(min LIST)
```

Return the smallest number from `LIST`.

#### max

```
(max LIST)
```

Return the largest number from `LIST`.

#### odd?

```
(odd? NUM)
```

Return true if `NUM` is odd.

#### even?

```
(even? NUM)
```

Return true if `NUM` is even.

#### inc

```
(inc VARNAME)
```

Increment `VARNAME` by 1 and return its result.

**Example:**

```lisp
(defvar counter 0)
(until (>= counter 5)
    (println (inc counter)))
```

*Output:*

```
1
2
3
4
5
```

#### dec

```
(dec VARNAME)
```

Decrement `VARNAME` by 1 and return its result.

**Example:**

```lisp
(defvar counter 5)
(until (<= counter 0)
    (println (dec counter)))
```

*Output:*

```
4
3
2
1
0
```

### stdclass - standard object-oriented library.

*Note: this section is not exhaustive. Some functions intended for internal 
use have been left out.*

#### superclass

```
(superclass CLASS)
```

Return the class's superclass.

#### cslots

```
(cslots CLASS)
```

Return a class's list of slots.

**Example:**

```lisp
(defclass +Animal ()
    "An example animal class."
    (name legs)
    (describe (Self)
        (concat (getslot 'name Self) " has " (getslot 'legs Self) " legs."))
    (walk (Self)
        (concat (getslot 'name Self) " walked confidently.")))
        
(cslots +Animal) ; --> (name legs)
```

#### classof

```
(classof OBJECT)
```

Return the class of `OBJECT`.

#### defclass

```
(defclass CLASSNAME SUPERCLASS
    DOCSTRING
    SLOTS
    METHODS...)
```

Define a class named `CLASSNAME` inheriting `SUPERCLASS` described by 
`DOCSTRING` with its objects' slots `SLOTS` and has `METHODS`. 


**Example:**

```lisp
(defclass +Animal ()
    "An example animal class."
    (name legs)
    (describe (Self)
        (concat (getslot 'name Self) " has " (getslot 'legs Self) " legs."))
    (walk (Self)
        (concat (getslot 'name Self) " walked confidently.")))
```

#### call

```
(call METHODNAME OBJECT)
```

Call `METHODNAME` on `OBJECT`.


**Example:**

```lisp
(defclass +Animal ()
    "An example animal class."
    (name legs)
    (describe (Self)
        (concat (getslot 'name Self) " has " (getslot 'legs Self) " legs."))
    (walk (Self)
        (concat (getslot 'name Self) " walked confidently.")))
        
(defvar Fred (make-instance +Animal "Fred" 4))

(call 'describe Fred) ; --> "Fred has 4 legs."
```

#### make-instance

```
(make-instance CLASS SLOTVALS...)
```

Create an object from `CLASS` with `SLOTVALS` assigned to the class's slots.

**Example:**

```lisp
(defclass +Animal ()
    "An example animal class."
    (name legs)
    (describe (Self)
        (concat (getslot 'name Self) " has " (getslot 'legs Self) " legs."))
    (walk (Self)
        (concat (getslot 'name Self) " walked confidently.")))
        
(defvar Fred (make-instance +Animal "Fred" 4))
```