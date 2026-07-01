class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        // Step 1: Distance from nearest thief
        int[][] dist = new int[n][n];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];

            for (int[] d : dirs) {

                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n
                        && dist[nr][nc] == -1) {

                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Max heap for safest path
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);

        boolean[][] visited = new boolean[n][n];

        pq.offer(new int[]{
                dist[0][0], // safeness
                0,
                0
        });

        visited[0][0] = true;

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int safety = cur[0];
            int r = cur[1];
            int c = cur[2];

            if (r == n - 1 && c == n - 1) {
                return safety;
            }

            for (int[] d : dirs) {

                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n
                        && !visited[nr][nc]) {

                    visited[nr][nc] = true;

                    int newSafety =
                            Math.min(safety, dist[nr][nc]);

                    pq.offer(new int[]{
                            newSafety,
                            nr,
                            nc
                    });
                }
            }
        }

        return 0;
    }
}