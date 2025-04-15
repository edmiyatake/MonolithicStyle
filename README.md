# TopWordFrequency Program - Monolithic Style

## Overview
--------
The `Four` program is a monolithic-style Java application that:

- Reads a text file containing the content to analyze.
- Loads a list of stopwords and removes them from the analysis.
- Tracks the frequency of each non-stop word using only basic Java constructs (no collections or libraries like HashMap or ArrayList).
- Displays the top 25 most frequent words from the input text file.

This program was developed under the following constraints:
- No named abstractions (no custom classes or high-level libraries)
- No use of built-in data structures like ArrayList or HashMap
- Only standard arrays and manual resizing

## Project Folder Structure
------------------------
Place the following files in the same folder:

- Four.java           // The Java source code file
- test.txt            // Input text files to be analyzed
- stopwords.txt       // File containing comma-separated stop words

Example stopwords.txt:
a,an,the,in,of,to,for,with,is,was,and,by,on,from,at

## How to Compile and Run
-----------------------

1. Open Command Prompt or Terminal

2. Navigate to the source folder where the Java file and text files are located.

   Example:
   cd path\to\your\folder
   (replace with actual path)

3. Compile the program:
   javac Four.java

4. Run the program:
   java Four test.txt stopwords.txt

   The program will output the top 25 most frequent words in the file, excluding stopwords.

## Expected Output Example
------------------------
Top 25 words:
love: 123
elizabeth: 87
darcy: 79
mr: 75
time: 63
...

## Constraints of the Monolithic Style
-----
- No named abstractions, my helper functions are technically abstractions but they do not introduce new classes or modular decomposition
- This program is useful for analyzing text frequency under constrained environments without using libraries (except for file input)
