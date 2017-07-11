class WeightedQuickUnion:
    def __init__(self, N):
        self.weights = [1] * N
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
        if self.weights[rootA] >= self.weights[rootB]:
            self.parent[rootB] = rootA
            self.weights[rootA] += self.weights[rootB]
        else:
            self.parent[rootA] = rootB
            self.weights[rootB] += self.weights[rootA]
    def connected(self, a, b):
        return self.root(a) == self.root(b)
    

uf = WeightedQuickUnion(10000)
uf.union(4,5)
uf.union(3,4)
uf.union(3,2)
print(uf.connected(2,5))
