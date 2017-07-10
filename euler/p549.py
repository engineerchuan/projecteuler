cache = dict()


def prime_factorization(k, d):
    if k in cache:
        return cache[k]
    found = False
    for i in range(2,int(k ** 0.5)+1):
        if k % i == 0:
            if i in d:
                d[i] += 1
            else:
                d[i] = 1
            
            found=True
            return prime_factorization(k/i, d)
            
    if found == False:
        if k in d:
            d[k] += 1
        else:
            d[k] = 1
            
        return d


def compute_s_n(n):
    k = prime_factorization(n, dict())
    if n not in cache:
        cache[n] = k
    #print(k)
    m = 2
    while True:
        m_factorized = prime_factorization(m, dict())
        for factor in m_factorized:
            if factor in k:
                k[factor] -= m_factorized[factor]
        allAccounted = True
        for factor in k:
            if k[factor] > 0:
                allAccounted = False
                break
        if allAccounted:
            return m
        m+=1

runningSum = 0
for i in range(2, 100000+1):

    runningSum += compute_s_n(i)

print(runningSum)
