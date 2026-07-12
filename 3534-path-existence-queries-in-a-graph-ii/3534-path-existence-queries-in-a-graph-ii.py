from typing import List

class Solution:
    def pathExistenceQueries(
        self, 
        n: int, 
        nums: List[int], 
        maxDiff: int, 
        queries: List[List[int]]
    ) -> List[int]:

        arr = sorted([(nums[i], i) for i in range(n)])

        values = []
        pos = [0] * n

        for i, (val, idx) in enumerate(arr):
            values.append(val)
            pos[idx] = i


        # farthest node reachable in one jump
        nxt = [0] * n
        right = 0

        for left in range(n):
            if right < left:
                right = left

            while right + 1 < n and values[right + 1] - values[left] <= maxDiff:
                right += 1

            nxt[left] = right


        # binary lifting
        LOG = n.bit_length()

        up = [nxt]

        for _ in range(1, LOG):
            prev = up[-1]
            curr = [0] * n

            for i in range(n):
                curr[i] = prev[prev[i]]

            up.append(curr)


        ans = []

        for u, v in queries:

            start = pos[u]
            end = pos[v]

            if start > end:
                start, end = end, start

            if start == end:
                ans.append(0)
                continue


            jumps = 0

            # take maximum jumps without crossing end
            for k in range(LOG - 1, -1, -1):
                if up[k][start] < end:
                    start = up[k][start]
                    jumps += (1 << k)


            # one last jump should reach end
            if nxt[start] >= end:
                ans.append(jumps + 1)
            else:
                ans.append(-1)


        return ans