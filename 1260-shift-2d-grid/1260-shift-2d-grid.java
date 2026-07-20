class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        List<Integer> arr = new ArrayList<>();

        // Step 1: Flatten grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr.add(grid[i][j]);
            }
        }

        int total = m * n;

        // Avoid unnecessary shifts
        k = k % total;

        // Step 2: Rotate right by k
        List<Integer> shifted = new ArrayList<>();

        for (int i = total - k; i < total; i++) {
            shifted.add(arr.get(i));
        }

        for (int i = 0; i < total - k; i++) {
            shifted.add(arr.get(i));
        }


        // Step 3: Convert back to 2D list
        List<List<Integer>> result = new ArrayList<>();

        int index = 0;

        for (int i = 0; i < m; i++) {

            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                row.add(shifted.get(index));
                index++;
            }

            result.add(row);
        }

        return result;
    }
}