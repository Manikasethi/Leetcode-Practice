class Solution {
public:
    vector<int> validSequence(string word1, string word2) {
        int n = word1.size(), m = word2.size();

        // suf[j] = earliest position in word1 that can start matching
        vector<int> suf(m + 1, n);
        int p = n - 1;
        suf[m] = n;

        for (int j = m - 1; j >= 0; j--) {
            while (p >= 0 && word1[p] != word2[j]) p--;
            if (p < 0) {
                suf[j] = -1;
            } else {
                suf[j] = p;
                p--;
            }
        }

        vector<int> ans;
        int i = 0;
        bool used = false;

        for (int j = 0; j < m; j++) {
            bool found = false;

            while (i < n) {
                if (word1[i] == word2[j]) {
                    ans.push_back(i);
                    i++;
                    found = true;
                    break;
                }

                if (!used) {
                    bool ok;

                    if (j == m - 1)
                        ok = true;
                    else
                        ok = (suf[j + 1] != -1 && suf[j + 1] > i);

                    if (ok) {
                        used = true;
                        ans.push_back(i);
                        i++;
                        found = true;
                        break;
                    }
                }

                i++;
            }

            if (!found) return {};
        }

        return ans;
    }
};