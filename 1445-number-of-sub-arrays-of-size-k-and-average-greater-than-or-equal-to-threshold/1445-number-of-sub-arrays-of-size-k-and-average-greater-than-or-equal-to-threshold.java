class Solution {
  public int numOfSubarrays(int[] arr, int k, int threshold) {
    int ans = 0;
    int windowSum = 0;

    for (int i = 0; i < arr.length; ++i) {
      windowSum += arr[i];
      if (i >= k)
        windowSum -= arr[i - k];
      if (i >= k - 1 && windowSum / k >= threshold)
        ++ans;
    }

    return ans;
  }
}
