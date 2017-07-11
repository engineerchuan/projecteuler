class UnionFind:
    def __init__(self, N):
        
        self.indices = []
        for i in range(N):
            self.indices.append(i)
        self.N = N
    def union(self, a, b):
        if a < 0 or a >= self.N:
            raise Exception("a larger than %d" % self.N)
        if b < 0 or b >= self.N:
            raise Exception("b larger than %d" % self.N)
        aid = self.indices[a]
        bid = self.indices[b]
        if not self.connected(a,b):
            for i in range(self.N):
                if self.indices[i] == bid:
                    self.indices[i] = aid
    def connected(self, a, b):
        return self.indices[a] == self.indices[b]


uf = UnionFind(10)
uf.union(4,5)
uf.union(3,4)
uf.union(3,2)
print(uf.connected(2,6))
