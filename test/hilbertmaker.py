n = int(input())

for i in range(1, n + 1):
    for j in range(1, n + 1):
        float = 1/(i + j - 1)
        print("%.03f" %float, end =" ")
    print()