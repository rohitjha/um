# um
An esoteric programming language made up of common English fillers

Version: 0.2

## About um
um is a brainfuck-derived language with a tape of size 64KB and the following supported operators:

|  um operator | Meaning |
|:------------:|:--------|
|  um  | increment the data pointer (to point to the next cell to the right) |
|  uh  | decrement the data pointer (to point to the next cell to the left) |
|  er  | increment (increase by one) the byte at the data pointer |
|  ah  | decrement (decrease by one) the byte at the data pointer |
|  ok  | output the byte at the data pointer |
|  so  | accept one byte of input, storing its value in the byte at the data pointer |
| well | if the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching 'like' command |
| like | if the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching 'well' command |


## um ... Hello World!
This is the um program to print "Hello World!":
```
er er er er er er er er er er well um er er er er er er er um
er er er er er er er er er er um er er er um er uh uh uh uh
ah like um er er ok um er ok er er er er er er er ok ok er er
er ok um er er ok uh uh er er er er er er er er er er er er
er er er ok um ok er er er ok ah ah ah ah ah ah ok ah ah ah
ah ah ah ah ah ok um er ok um ok 
```

## Running um
The Um Java class has the API for evaluating an um program from a file.

The Filler class uses this API to run the um program in helloworld.um:

```shell
$ cd src
$ javac Filler.java
$ java Filler ../examples/helloworld.um
Hello World!
```

## Todo
* Um ... Handle incorrect programs
* Er ... Better exception handling
* Uh ... Better documentation

