harshads = dict()
def naive_prime(k):
    for i in range(2,int(k ** 0.5)+1):
        if k % i == 0:
            return False
    return True
harshads[1] = [1,2,3,4,5,6,7,8,9]

for nDigits in range(2, 15):
    harshads[nDigits] = []
    exists = harshads[nDigits-1]
    for aExist in exists:
        for addand in range(0, 10):
            combined = aExist * 10 + addand
            sumDigits = sum([int(x) for x in str(combined)])
            if combined % sumDigits == 0:
                harshads[nDigits].append(combined)

print("finished getting harshads")

# Filter down to only strong harshads

newharshads = []

for nDigits in range(2,14):
    print(nDigits)
    cands = harshads[nDigits]
    for aCand in cands:
        sumDigits = sum([int(x) for x in str(aCand)])
        if naive_prime(aCand/sumDigits):
            newharshads.append(aCand)

runningSum = 0
for aCand in newharshads:
    newlyFormed = [aCand*10+x for x in [1,3,7,9]]
    for aNew in newlyFormed:
        if naive_prime(aNew):
            print(aNew)
            runningSum += aNew

print(runningSum)
