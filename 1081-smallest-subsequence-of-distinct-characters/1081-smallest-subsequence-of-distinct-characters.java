class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];
        boolean[] used = new boolean[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            freq[idx]--;

            if (used[idx]) continue;

            while (!stack.isEmpty()
                    && stack.peekLast() > c
                    && freq[stack.peekLast() - 'a'] > 0) {
                used[stack.removeLast() - 'a'] = false;
            }

            stack.addLast(c);
            used[idx] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.removeFirst());

        return sb.toString();
    }
}