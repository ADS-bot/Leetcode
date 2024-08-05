class Solution {

    public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
        NumArray count = new NumArray(new int[colors.length + 1]), sum = new NumArray(new int[colors.length + 1]);
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == colors[(i + 1) % colors.length]) {
                add(i, colors.length, set, count, sum);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                list.add(set.isEmpty() ? colors.length : sum.sumRange(query[1], colors.length) - (query[1] - 1) * count.sumRange(query[1], colors.length));
            } else if (colors[query[1]] != query[2]) {
                colors[query[1]] = query[2];
                if (query[2] == colors[(query[1] - 1 + colors.length) % colors.length]) {
                    add((query[1] - 1 + colors.length) % colors.length, colors.length, set, count, sum);
                } else {
                    remove((query[1] - 1 + colors.length) % colors.length, colors.length, set, count, sum);
                }
                if (query[2] == colors[(query[1] + 1) % colors.length]) {
                    add(query[1], colors.length, set, count, sum);
                } else {
                    remove(query[1], colors.length, set, count, sum);
                }
            }
        }
        return list;
    }

    private void add(int x, int n, TreeSet<Integer> set, NumArray count, NumArray sum) {
        set.add(x);
        if (set.size() == 1) {
            count.update(n, 1);
            sum.update(n, n);
        }
        int prev = set.lower(x) == null ? set.last() : set.lower(x), next = set.higher(x) == null ? set.first() : set.higher(x);
        count.update(set.size() == 2 ? n : (next - prev + n) % n, -1);
        sum.update(set.size() == 2 ? n : (next - prev + n) % n, set.size() == 2 ? -n : -(next - prev + n) % n);
        count.update((x - prev + n) % n, 1);
        sum.update((x - prev + n) % n, (x - prev + n) % n);
        count.update((next - x + n) % n, 1);
        sum.update((next - x + n) % n, (next - x + n) % n);
    }

    private void remove(int x, int n, TreeSet<Integer> set, NumArray count, NumArray sum) {
        if (set.size() == 1) {
            count.update(n, -1);
            sum.update(n, -n);
        }
        int prev = set.lower(x) == null ? set.last() : set.lower(x), next = set.higher(x) == null ? set.first() : set.higher(x);
        count.update(set.size() == 2 ? n : (next - prev + n) % n, 1);
        sum.update(set.size() == 2 ? n : (next - prev + n) % n, set.size() == 2 ? n : (next - prev + n) % n);
        count.update((x - prev + n) % n, -1);
        sum.update((x - prev + n) % n, -(x - prev + n) % n);
        count.update((next - x + n) % n, -1);
        sum.update((next - x + n) % n, -(next - x + n) % n);
        set.remove(x);
    }

    class NumArray {
        private int[] tree;
        private int[] nums;

        public NumArray(int[] nums) {
            this.tree = new int[nums.length + 1];
            this.nums = nums;
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            add(index + 1, val);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }

        private int lowBit(int x) {
            return x & -x;
        }

        private void add(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += lowBit(index);
            }
        }

        private int prefixSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= lowBit(index);
            }
            return sum;
        }
    }
}