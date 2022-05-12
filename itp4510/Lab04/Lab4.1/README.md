### Q1
For each of the following code snippets, identify the critical section(s), compute the time (T(n)) each one takes, and specify the asymptotic complexity using Big-O.

(i)
```
for(int k=0; k<n; k+=3) {
    for(int p=n; p>6; p--) {
        System.out.println(p%2);
    }
}

O(n^2)
```

(ii)
```
for(int k=0;k<=n/8;k++) {
    System.out.println(k);
}
System.out.println(“Next”);
for (int p=n; p>=1;p--) {
    System.out.println(p%2);
}

O(n)
```

(iii)
```
for (int k=0; k<n-1; k++) {
    for (int m=k+1; m<n; m++) {
        System.out.println(k*m);
    }
}

O(n^2)
```