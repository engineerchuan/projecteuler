
def naive_prime(k):
    for i in range(2,int(k ** 0.5)+1):
        if k % i == 0:
            return False
    return True

last = 1
nPrime = 0
nCorners = 1
for l in range(2, 100000):
    nInLayer = 4 * (l * 2 -1) - 4
    topRight = 2 * l - 2 + last
    topLeft = 2 * l -2 + topRight
    bottomLeft = 2 * l - 2 + topLeft
    last += nInLayer
    if naive_prime(topRight):
        nPrime+=1
    if naive_prime(topLeft):
        nPrime+=1
    if naive_prime(bottomLeft):
        nPrime+=1
    nCorners += 4
    if l % 1000 == 0:
        print(l, nPrime, nCorners, nPrime/nCorners, 2*l-1)
    if nPrime/nCorners < 0.10:
        print(l, nPrime, nCorners, nPrime/nCorners, 2*l-1)
        break
    
