## Exercise 1
```
r=Rectangle@19e1023e
c=Circle@3a4afd8d
# RAM address
```

## Exercise 2
```
r=Rectangle@27ddd392
area=2.0
r=Rectangle@2a098129
area=12.0
r=null
Exception in thread "main" java.lang.NullPointerException
```

## Exercise 3
```
Triangle@6504e3b2
Triangle@6504e3b2
# RAM Address
```

## Exercise 4
```
# (a)
[1/1/1970]

# (b)
Date@28a418fc
# RAM Address
```

## Exercise 5
```
Q1Main.java:17: cannot assign a value to final variable A
			q1.A = 12;
                    ^
Q1Main.java:19: c has private access in QuestionOne
			q1.c = 12;
                    ^
Q1Main.java:21: methodOne(int) has private access in QuestionOne
			q1.methodOne(12);
                    ^
Q1Main.java:22: methodOne(int) in QuestionOne cannot be applied to ()
			q1.methodOne();
                    ^
Q1Main.java:24: possible loss of precision
found   : float
required: int
			q1.b = q1.methodTwo();
                                     ^
5 errors

Tool completed with exit code 1

```

## Exercise 6

| Code | valid | invalid |
| ---- | ----- | ------- |
| System.out.println(f.s); | [x] | [] |
| f.smethod(); | [x] | [] |
| System.out.println(Foo.i); | [] | [x] |
| System.out.println(Foo.s); | [x] | [] |
| Foo.imethod(); | [] | [x] |
| Foo.smethod(); | [x] | [] |