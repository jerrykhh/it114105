### Q2(c)

Now, perform some experiments to test the searching performance in the two approaches. Record the results in the table below. You are free to try some more words.

| Word to search for | Result (found/ not found) | Time needed (BST) | Time needed (linked list)
| --- | --- | --- | --- |
| water | Found | 2500 | 7100 |
| ever | Found | 4800 | 77600 |
| snail | Not Found | 4500 | 74200 |
| better | Not Found | 3900 | 80700 |
| apple | Found | 5700 | 49700 |
| door | Found | 3300 | 7300 |
| foolish | Found | 4000 | 52000 |

### Q4
A proper binary tree contains N nodes. What is the number of leaf nodes in the tree? What is the
number of non-leaf nodes in the tree?

```
Leaf nodes = N/2+1 (N must be an odd number, so take the integer of N/2.)
Non-leaf nodes = N/2
```

### Q5
A complete binary tree has a depth of 8. What is the total number of nodes in the tree? Give the
relationship of the depth and the total number of nodes of a complete binary tree in a mathematic
expression.
```
Total nodes = 2^(8+1) – 1 = 511
```

### Q6a
Draw the array of the binary tree if it is implemented in an
array.
```
            [Q]
        /         \
    [B]             [U]
      \             / \
       [G]         [R][W]
      /   \
    [E]    [J]
      \    / \
      [F] [I] [P]
          /
         [H]
```
```
0:Q 1:B 2:U 4:G 5:R 6:W 9:E 10:J 20:F 21:I 22:P 43:H
```

### Q6(b)
(b)List the node sequence by

(i) pre-order traversal
```
Q B G E F J I H P U R W
```
(ii) in-order traversal
```
B E F G H I J P Q R U W
```
(iii) post-order traversal
```
F E H I P J G B R W U Q
```

### Q7
Follow the algorithm outlined in the lecture notes, depict the Binary Search Trees created by inserting the items below from left to right. Perform an in-order traversal to check if the nodes are visited in ascending order.

(a) M J W S G L K A B P Z X Y R T
```
         M
        / \
       /   \
      /     \
     /       \
    J         W
   / \       / \
  G   L     /   \
 /   /     /     \
A   K     S       Z
 \       / \     /
  B     /   \   X
       P    T   \
        \        Y
        R
```

(b) P W J Y B L S G K M A Z X T R
```
               P
              / \
             /   \
            /     \
           /       \
          /         \
         /           \
        /             \
       /               \
      J                 W
     / \               / \
    /   \             /   \
   /     \           /     \
  B       L         /       \
 / \     / \       S         Y
A   G   /   \     / \       / \
       K     M   /   \     /   \
                R     T   X     Z
```

(c) P W J Y B L S G K M A R
```
            P
           / \
          /   \
         /     \
        /       \
       /         \
      J           W
     / \         / \
    /   \       S   Y
   /     \     /
  B       L   R
 / \     / \
A   G   /   \
       K    M
```

(d) P W J Y B L S G K M A
```
            P
           / \
          /   \
         /     \
        /       \
       /         \
      J           W
     / \         / \
    /   \       S   Y
   /     \     
  B       L   
 / \     / \
A   G   /   \
       K    M
```

(e) A B C D E F G H I J K L
```
A
 \
  B
   \
    C
     \
      D
       \
        E
         \
          F
           \
            G
             \
              H
               \
                I
                 \
                  J
                   \
                    K
                     \
                      L
```

(f) L K J I H G F E D C B A
```
                      L
                     /
                    K
                   /
                  J
                 /
                I
               /
              H
             /
            G
           /
          F
         /
        E
       /
      D
     /
    C
   /
  B
 /
A
```

### Q8
Outline an algorithm of searching an item in a Binary Search Tree.
```
BinaryNode search (BinaryNode t, key x)
begin
if t is null
  return null; 
if (x is less than t.data.key)
  return search(t.left, x);
else if (x is greater than t.data.key)
  return search(t.right, x); 
else
  return t;
end
```

### Q10
Give the postfix and prefix expressions as well as the expression tree.

((A + B) * C) / (D + E) * F

```
          [*]
          / \
        [/]   [F]
        / \
       /   \
      /     \
    [*]     [+]
    / \     / \
  [+]  [C] [D] [E]
  / \
 /   \
[A]   [B]

pre-order traversal on the expression tree to yields the prefix expression

Prefix expression: * / * + A B C + D E F
```