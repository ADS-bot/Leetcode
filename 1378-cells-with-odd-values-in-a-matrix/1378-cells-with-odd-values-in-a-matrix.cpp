class Solution {
 public:
  int oddCells(int m, int n, vector<vector<int>>& indices) {
    // rows[i] and cols[i] :=
    //   true (flipped even times) / false (flipped odd times)
    vector<bool> rows(m);
    vector<bool> cols(n);

    for (const vector<int>& index : indices) {
      rows[index[0]] = rows[index[0]] ^ true;
      cols[index[1]] = cols[index[1]] ^ true;
    }

    const int oddRowsCount = count(rows.begin(), rows.end(), true);
    const int oddColsCount = count(cols.begin(), cols.end(), true);
    const int evenRowsCount = m - oddRowsCount;
    const int evenColsCount = n - oddColsCount;
    return oddRowsCount * evenColsCount + oddColsCount * evenRowsCount;
  }
};
