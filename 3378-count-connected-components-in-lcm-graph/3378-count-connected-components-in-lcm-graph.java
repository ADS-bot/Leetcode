class Solution {
     static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size){
            parent = new int[size];
            rank = new int[size];
            for(int i=0;i<size;i++) parent[i] = i;
        }

        public int find(int x){
            if(parent[x]!=x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) return;
            if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            }
            else{
                parent[rootY] = rootX;
                if(rank[rootX] == rank[rootY]){
                    rank[rootX]++;
                }
            }
        }
    }

    public int countComponents(int[] nums, int threshold){
        int countGreater =0;
        List<Integer> validNums = new ArrayList<>();
        for(int x: nums){
            if(x <= threshold){
                validNums.add(x);
            }
            else{
                countGreater++;
            }
        }

        int m = validNums.size();
        if(m ==0){
            return countGreater;
        }
        int[] present = new int[threshold +1];
        Arrays.fill(present, -1);
        for(int i=0;i<m;i++){
            int x = validNums.get(i);
            present[x] = i;
        }

        UnionFind uf = new UnionFind(m);

        int[] lastSeen = new int[threshold +1];
        Arrays.fill(lastSeen, -1);

        for(int d=1; d<=threshold; d++){
            if(present[d] != -1){
                for(int l =d; l <=threshold; l +=d){
                    if(lastSeen[l] == -1){
                        lastSeen[l] = present[d];
                    }
                    else{
                        uf.union(lastSeen[l], present[d]);
                    }
                }
            }
        }
        Set<Integer> uniqueRoots = new HashSet<>();
        for(int i=0;i<m;i++){
            uniqueRoots.add(uf.find(i));
        }

        return uniqueRoots.size() + countGreater;
    }
}