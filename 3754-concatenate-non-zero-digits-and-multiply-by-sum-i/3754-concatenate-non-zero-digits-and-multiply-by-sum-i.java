class Solution {
    public long sumAndMultiply(int n) {
        int sum=0;
        String temp="";
        
        String s = String.valueOf(n);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0') continue;
            else{
                temp=temp+s.charAt(i);
                int digit= s.charAt(i)-'0';
                sum+=digit;
            }
        }
        long x; 
        if(temp.equals("")){
            x=0;
        }
        else{
            x = Integer.parseInt(temp);
        }
        return x*sum;
    }
}