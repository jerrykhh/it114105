## Exercise 1
```
int x = 1;
```
| Boolean expressions | Result |
| ------------------- | ------ |
| (true) && (4 < 3) | false|
| !(x < 0) && (x < 0) | false |
| (x < 0) || (x > 0) | true |
| (x != 0) || (x == 0)	 | true |
| (x != 0) || (x == 0)	 | true |
| (x != 0) == !(x == 0) | true |

## Exercise 2
| Expressions | legal | illegal |
| ----------- | ----- | ------- |
| x > y > 0	 | [] | [x] |
| x = y || y	| [] | [x] |
| x /= y | [x] | [x] |
| x or y | [] | [x] |
| x and y | [] | [x] |
| (x != 7) || (x = 7) | [] | [x] |

## Exercise 3
```
int x = 4;
int y = 4;
switch (x + 4) {
	case 8: y=1;
	default: y++;
}
# y is 2
```

## Exercise 4
```
(x >= 21 and x <= 30) || x < 0
```
