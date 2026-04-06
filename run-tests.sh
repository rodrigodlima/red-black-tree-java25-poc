#!/bin/bash
# Red-Black Tree Tests
javac -cp . red-black-tree/src/RedBlackTree.java red-black-tree/tests/RedBlackTreeTest.java -d red-black-tree/tests/out
java -cp red-black-tree/tests/out:red-black-tree/src RedBlackTreeTest
