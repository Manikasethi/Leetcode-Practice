class Solution {

    int[][] graph;
    boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {

        ArrayList<Integer>[] adj = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            adj[a].add(b);
            adj[b].add(a);
        }

        visited = new boolean[n];

        int answer = 0;

        for(int i = 0; i < n; i++) {

            if(!visited[i]) {

                int[] component = dfs(i, adj);

                int vertices = component[0];
                int edgesCount = component[1] / 2;

                int requiredEdges = vertices * (vertices - 1) / 2;

                if(edgesCount == requiredEdges) {
                    answer++;
                }
            }
        }

        return answer;
    }


    private int[] dfs(int node, ArrayList<Integer>[] adj) {

        visited[node] = true;

        int vertices = 1;
        int edges = adj[node].size();


        for(int neighbor : adj[node]) {

            if(!visited[neighbor]) {

                int[] result = dfs(neighbor, adj);

                vertices += result[0];
                edges += result[1];
            }
        }

        return new int[]{vertices, edges};
    }
}