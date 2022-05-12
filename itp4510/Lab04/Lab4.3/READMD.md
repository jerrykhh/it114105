### Q1
Explain why we need to maintain a binary search tree in balance such as an AVL tree.
```
Only a balanced tree can guarantee search performance of O(logN).
and, AVL tree’s theoretical search performance is 1.44 log2N. (see lecture notes)
```

### Q2
Build a binary search tree with the following integers being inserted in the order from
left to right: 72, 48, 35, 80, 60, 85, 75, 21, 9, 28

```
           72        
           / \       
          /   \      
         /     \     
        /       \    
       48       80   
      / \       / \  
     /   \     /   \ 
    35   60   75   85
   /
  21
 / \
9  28
```

### Q3
With the integers in Q2, show the steps of building an AVL tree. Indicate also the
balance value of each node and the rotations so required to maintain the tree in
balance.

```
insert 72

    72

insert 48
 72 
 /  
48  

insert 35
   72          
   / 
  48 
 /
35

=>Right rotate at 72
  48
  / \
 /   \
35   72


insert 80
  48
  / \
 /   \
35   72
       \
       80

insert 60
  48
  / \
 /   \
35   72
     / \
    /   \
   60   80

insert 85
  48
  / \
 /   \
35   72
     / \
    /   \
   60   80
          \
          85

=> Left rotate at 48
     72
     / \
    /   \
   48   80
  / \     \
 /   \    85
35   60

insert 75
       72
       / \
      /   \
     /     \
    /       \
   48       80
  / \       / \
 /   \     /   \
35   60   75   85

insert 21
         72
         / \
        /   \
       /     \
      /       \
     48       80
    / \       / \
   /   \     /   \
  35   60   75   85
 /
21

insert 9
           72
           / \
          /   \
         /     \
        /       \
       48       80
      / \       / \
     /   \     /   \
    35   60   75   85
   /
  21
 /
9

=> Right rotate at 35
         72
         / \
        /   \
       /     \
      /       \
     48       80
    / \       / \
   /   \     /   \
  21   60   75   85
 / \
9  35

insert 28
         72
         / \
        /   \
       /     \
      /       \
     48       80
    / \       / \
   /   \     /   \
  21   60   75   85
 / \
9  35
   /
  28

=> double rotate: left rotate at 21
           72
           / \
          /   \
         /     \
        /       \
       48       80
      / \       / \
     /   \     /   \
    35   60   75   85
   /
  21
 / \
9  28

=> double rotate: right ritate at 48
         72
         / \
        /   \
       /     \
      /       \
     35       80
    / \       / \
   /   \     /   \
  21   48   75   85
 / \     \
9  28    60
```

### Q4
Show the steps of deleting the following nodes from the AVL tree in Q3. Indicate also the balance value of each node and the rotations so required to maintain the tree in balance.

60, 48

```
delete 60
         72
         / \
        /   \
       /     \
      /       \
     35       80
    / \       / \
   /   \     /   \
  21   48   75   85
 / \
9  28

delete 48
      72
      / \
     /   \
    35   80
   /     / \
  21    /   \
 / \   75   85
9  28

=> rotate at 35
     72
     / \
    /   \
   /     \
  21     80
 / \     / \
9  35   /   \
   /   75   85
  28
```