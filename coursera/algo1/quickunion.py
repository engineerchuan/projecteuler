class QuickUnion:
    def __init__(self, N):
        
        self.parent = []
        for i in range(N):
            self.parent.append(i)
        self.N = N
    def root(self, i):
        parent = self.parent[i]
        if parent == i:
            return parent
        else:
            return self.root(parent)
    def union(self, a, b):
        rootA = self.root(a)
        rootB = self.root(b)
        self.parent[rootA] = rootB
        
    def connected(self, a, b):
        return self.root(a) == self.root(b)
    

uf = QuickUnion(10000)
uf.union(4,5)
uf.union(3,4)
uf.union(3,2)
print(uf.connected(2,5))
