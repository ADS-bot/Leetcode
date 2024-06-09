class FrequencyTracker {
public:
    FrequencyTracker() {
    }

    void add(int number) {
        int f = cnt[number];
        if (f > 0) {
            freq[f]--;
        }
        cnt[number]++;
        freq[f + 1]++;
    }

    void deleteOne(int number) {
        int f = cnt[number];
        if (f == 0) {
            return;
        }
        freq[f]--;
        cnt[number]--;
        freq[f - 1]++;
    }

    bool hasFrequency(int frequency) {
        return freq[frequency] > 0;
    }

private:
    unordered_map<int, int> cnt;
    unordered_map<int, int> freq;
};