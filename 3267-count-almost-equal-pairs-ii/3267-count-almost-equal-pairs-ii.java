class Solution {

    public int countPairs(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            count += map.getOrDefault(num, 0);
            HashSet<Integer> set = new HashSet<>(Set.of(num));
            for (int i = 0; i < ("" + num).length(); i++) {
                for (int j = i + 1; j < ("" + num).length(); j++) {
                    String s = ("" + num).substring(0, i) + ("" + num).charAt(j) + ("" + num).substring(i + 1, j) + ("" + num).charAt(i) + ("" + num).substring(j + 1);
                    if (!set.contains(Integer.valueOf(s))) {
                        set.add(Integer.valueOf(s));
                        count += map.getOrDefault(Integer.valueOf(s), 0);
                    }
                    for (int l = 0; l < s.length(); l++) {
                        for (int m = l + 1; m < s.length(); m++) {
                            int k = Integer.valueOf(s.substring(0, l) + s.charAt(m) + s.substring(l + 1, m) + s.charAt(l) + s.substring(m + 1));
                            if (!set.contains(k)) {
                                set.add(k);
                                count += map.getOrDefault(k, 0);
                            }
                        }
                    }
                }
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }
}
