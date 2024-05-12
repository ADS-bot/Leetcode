class Solution {
public:
    
    vector<int> elements, optimalPermutation, permutationInProgress, chosen;
    int size;
    int lowestScore;

    void search(int depth, int currentScore, int previous) {
        if (depth == size) {
            currentScore += abs(permutationInProgress.back() - elements[permutationInProgress[0]]);
            if (currentScore < lowestScore) {
                lowestScore = currentScore;
                optimalPermutation = permutationInProgress;
            }
            return;
        }

        for (int idx = 0; idx < size; ++idx) {
            if (!chosen[idx]) {
                int addScore = (depth == 0) ? 0 : abs(permutationInProgress[depth - 1] - elements[idx]);
                if (depth == 0 || currentScore + addScore < lowestScore) {
                    chosen[idx] = true;
                    permutationInProgress.push_back(idx);
                    search(depth + 1, currentScore + addScore, idx);
                    permutationInProgress.pop_back();
                    chosen[idx] = false;
                }
            }
        }
    }

    vector<int> findPermutation(vector<int>& inputElements) {
        this->elements = inputElements;
        size = elements.size();
        lowestScore = numeric_limits<int>::max();
        chosen.assign(size, false);
        permutationInProgress.reserve(size);
        optimalPermutation.reserve(size);

        search(0, 0, -1);
        return optimalPermutation;
    }
};