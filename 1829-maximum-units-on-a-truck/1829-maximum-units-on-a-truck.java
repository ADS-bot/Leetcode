class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        if (boxTypes == null || boxTypes.length == 0) {
            return 0;
        }
        
        int totalUnits = 0;
        Arrays.sort(boxTypes, (box1, box2) -> Integer.compare(box2[1], box1[1]));
        
        for (int[] box : boxTypes) {
            if (truckSize >= box[0]) {
                totalUnits += box[0] * box[1];
                truckSize -= box[0];
            } else {
                totalUnits += truckSize * box[1];
                break;
            }
        }
        
        return totalUnits;
    }
}