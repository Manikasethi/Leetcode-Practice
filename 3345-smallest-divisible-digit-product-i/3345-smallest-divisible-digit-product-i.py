class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        def product(x):
            p=1
            while x>0:
                p*=x%10
                x//=10
            return p
        while True:
            if product(n) % t == 0:
                return n
            n+=1

#Time Complexity: O((ans - n + 1) * d), where d is the number of digits.
#Space Complexity: O(1).


        