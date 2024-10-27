class Solution {
    private static class Frame {
         int node;
         boolean isExit;
         int prevLastSeen;

         Frame(int node, boolean isExit, int prevLastSeen) {
             this.node = node;
             this.isExit = isExit;
             this.prevLastSeen = prevLastSeen;
         }
     }

     public int[] findSubtreeSizes(int[] parent, String s) {
         int n = parent.length;

         List<List<Integer>> tree = new ArrayList<>(n);
         for (int i = 0; i < n; i++) {
             tree.add(new ArrayList<>());
         }
         for (int i = 1; i < n; i++) {
             tree.get(parent[i]).add(i);
         }

         int[] newParent = new int[n];
         System.arraycopy(parent, 0, newParent, 0, n);

         int[] lastSeen = new int[26];
         Arrays.fill(lastSeen, -1);

         Deque<Frame> stack = new ArrayDeque<>();
         stack.push(new Frame(0, false, -1)); 

         while (!stack.isEmpty()) {
             Frame current = stack.pop();
             int node = current.node;

             if (!current.isExit) {
                 char c = s.charAt(node);
                 int cIdx = c - 'a';
                 int y = lastSeen[cIdx];

                 if (y != -1) {
                     newParent[node] = y;
                 }

                 
                 stack.push(new Frame(node, true, lastSeen[cIdx]));

                
                 lastSeen[cIdx] = node;

                 
                 List<Integer> children = tree.get(node);
                 for (int i = children.size() - 1; i >= 0; i--) {
                     stack.push(new Frame(children.get(i), false, -1));
                 }
             } else {
                 
                 char c = s.charAt(node);
                 int cIdx = c - 'a';
                 lastSeen[cIdx] = current.prevLastSeen;
             }
         }


         List<List<Integer>> newTree = new ArrayList<>(n);
         for (int i = 0; i < n; i++) {
             newTree.add(new ArrayList<>());
         }
         for (int i = 1; i < n; i++) {
             newTree.get(newParent[i]).add(i);
         }

       
         int[] size = new int[n];
         stack.push(new Frame(0, false, -1)); // Start with root node

         while (!stack.isEmpty()) {
             Frame current = stack.pop();
             int node = current.node;

             if (!current.isExit) {
                 
                 stack.push(new Frame(node, true, -1));

               
                 List<Integer> children = newTree.get(node);
                 for (int i = children.size() - 1; i >= 0; i--) {
                     stack.push(new Frame(children.get(i), false, -1));
                 }
             } else {
                
                 size[node] = 1; // Count the node itself
                 for (int child : newTree.get(node)) {
                     size[node] += size[child];
                 }
             }
         }

         return size;
    }
}