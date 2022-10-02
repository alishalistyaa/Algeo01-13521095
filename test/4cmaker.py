n = int(input())

import math

def f(x):
    return ((x**2) + (x ** 0.5))/(math.exp(x) + x)

dx = 2 / n
x = 0
for i in range (0, n + 1):
    print("%.3f" %x, end = " ")
    print("%.3f" %f(x))
    x += dx
