class Solution {
public:
    int maxActiveSectionsAfterTrade(string s) {
        int ones = 0;

        for (char c : s) {
            if (c == '1')
                ones++;
        }

        vector<pair<char, int>> runs;

        for (char c : s) {
            if (runs.empty() || runs.back().first != c) {
                runs.push_back({c, 1});
            } else {
                runs.back().second++;
            }
        }

        int ans = ones;

        for (int i = 1; i + 1 < runs.size(); i++) {
            // Looking for a block of 1s surrounded by 0s
            if (runs[i].first == '1' &&
                runs[i - 1].first == '0' &&
                runs[i + 1].first == '0') {

                int gain = runs[i - 1].second + runs[i + 1].second;
                ans = max(ans, ones + gain);
            }
        }

        return ans;
    }
};