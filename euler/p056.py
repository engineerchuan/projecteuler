from operator import mul    # or mul=lambda x,y:x*y
from fractions import Fraction
from functools import reduce

def nCk(n,k): 
    return int( reduce(mul, (Fraction(n-i, i+1) for i in range(k)), 1) )

def sumdigits(k):
    stK = str(k)
    return sum([int(x) for x in stK])

n = 99
k = 99

i = 0
coeff = 0
while i < k+1:
  val = nCk(k, i+1) * 100 - nCk(k, i)
  coeff += sumdigits(val)
  i += 2

print(coeff)
  
