mymap = dict()
mymap[1] = 1
mymap[89] = 89
n89 = 0
for start in range(1, 10000000):
    temp = start
    #print(temp)
    while not (temp in mymap):
        stringed = str(temp)
        temp = sum([int(x) **2 for x in stringed])
    #print("- found cache for %d is %d from %d" % (start, mymap[temp], temp))
    mymap[start] = mymap[temp]
    if start % 100000 == 0:
        print(start)
    if mymap[temp] == 89:
        n89 += 1
print(n89)
