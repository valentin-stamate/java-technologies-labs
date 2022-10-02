# Lab 1

* [x] Compulsory
* [x] Homework
* [x] Bonus
  * Tested with string **abcdefghijklmnopqrst**, size **5**
  * 100 concurrent clients: the server can resolve the first requests but
after the ~20th request, it crashes with **java.lang.OutOfMemoryError: Java heap space**
due to parallel permutation generation and recursion
  * 1000 concurrent clients but small input: the server can handle the load