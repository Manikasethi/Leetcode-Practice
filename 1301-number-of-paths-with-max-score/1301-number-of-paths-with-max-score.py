class Solution:
    def pathsWithMaxScore(self, board):
        MOD = 10 ** 9 + 7
        n = len(board)

        best = [[-1] * n for _ in range(n)]
        ways = [[0] * n for _ in range(n)]

        best[n - 1][n - 1] = 0
        ways[n - 1][n - 1] = 1

        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if board[i][j] == 'X':
                    continue
                if i == n - 1 and j == n - 1:
                    continue

                mx = -1
                cnt = 0

                for ni, nj in ((i + 1, j), (i, j + 1), (i + 1, j + 1)):
                    if ni >= n or nj >= n:
                        continue
                    if best[ni][nj] == -1:
                        continue

                    if best[ni][nj] > mx:
                        mx = best[ni][nj]
                        cnt = ways[ni][nj]
                    elif best[ni][nj] == mx:
                        cnt = (cnt + ways[ni][nj]) % MOD

                if mx == -1:
                    continue

                val = 0
                if board[i][j].isdigit():
                    val = int(board[i][j])

                best[i][j] = mx + val
                ways[i][j] = cnt

        if ways[0][0] == 0:
            return [0, 0]
        return [best[0][0], ways[0][0]]