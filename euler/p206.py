def fit(k, patt):

    stringed = str(k)
    stringed = stringed[-1 * len(patt):]
    for i in range(len(patt)):
        if patt[i] == "x":
            continue
        if patt[i] != stringed[i]:
            return False
    return True

main_patt = "1x2x3x4x5x6x7x8x9x0"
candidates = [0,1,2,3,4,5,6,7,8,9]

for p in range(1, 10, 2):
    newcandidates = []
    for acandidate in candidates:
        for nDig in range(1,100):
            newCand = nDig * 10**p + acandidate
            if (newCand ** 2) > 1929394959697989990:
                continue
            if fit(newCand**2, main_patt[-1*p-2:]):
                newcandidates.append(newCand)
    candidates = newcandidates            
    print(len(candidates))


for acandidate in candidates:
    if fit(acandidate ** 2, main_patt):
        print(acandidate, acandidate **2, fit(acandidate **2, main_patt))
          
