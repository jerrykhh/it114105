# Extra Lab - Algorithm Complexity

### Q1
```
for (int i = 0; i < n; i++) {
  for (int j = 0; j < n / 2; j++) {
    for (int k = 0; k < n / 1000; k++) {
      //something to run
    }
  }
}
```
### Q2
```
for (int x = n; x > 10; x /= 2) {
  //something to run
}
```

### Q3
```
int g = 1;
while (g < n) {
  for (int v = 1; v < n - 9; v++) {
    //something to run
  }
  g *= 3;
}
```

### Q4
```
int i = 1;
while (i < n) {
  i *= 2;
  method1(n);
}

public static void method1(int n) {
  for (int i = 0; i < n / 4; i++) {
    //something to run
  }
}
```

### Q5
```
public static void method2(int n) {
  //something to run
  if (n > 10)
    method2(n / 5);
}
```

### Q6
```
public static int method3(int n) {
  //something to run
  if (n > 10) {
    return (method3(n - 6));
  } else
    return 1;
  }
```

### Q7
```
public static int method4(int n) {
  //something to run
  if (n > 1) {
    return (method4(n - 1) + method4(n - 1));
  } else
    return 1;
}
```

## Answer
```
q1 O(n^3)

q2 O(log n)

q3 O(n log n)

q4 O(n log n)

q5 O(log n)

q6 O(n)

q7 O(2^n)
```