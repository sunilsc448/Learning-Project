package DSAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class JavaSamples {
    static String reverseWords(String S) {
        StringBuilder sb = new StringBuilder();
        String[] strings = S.split("\\.");
        for(int i = strings.length-1; i >=0; i--){
            sb.append(strings[i]);
            if(i != 0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    static String longestCommonPrefix(String arr[], int n){
       int currentIndex = 0;
       StringBuilder sb = new StringBuilder();

       while(currentIndex < arr[0].length()){
           boolean isEqual = true;
           for (int i = 0; i < arr.length; i++){
               if(currentIndex >= arr[i].length() || arr[0].charAt(currentIndex) != arr[i].charAt(currentIndex)){
                   isEqual = false;
                   break;
               }
           }
           if(isEqual){
               sb.append(arr[0].charAt(currentIndex));
           }else{
               break;
           }
           currentIndex++;
       }
       if(sb.toString().isEmpty()){
           return "-1";
       }else{
           return sb.toString();
       }
    }
    //LIX > 59
    //MCDXCVIII > 1498
    //CMXVI > 1116
    static int romanToDecimal(String str) {
        int decimal = 0;
        for (int i = 0; i < str.length(); i++){
             int val1 = getValueForRomanChar(str.charAt(i));
             if(i == str.length() - 1) {
                 decimal += val1;
             }else{
                int val2 =  getValueForRomanChar(str.charAt(i + 1));
                if (val1 < val2) {
                    decimal += val2 - val1;
                    i++;
                } else {
                    decimal += val1;
                }
            }
        }
        return decimal;
    }

    static int getValueForRomanChar(char ch){
        switch (ch){
            case 'I' :
                return 1;
            case 'V' :
                return 5;
            case 'X' :
                return 10;
            case 'L' :
                return 50;
            case 'C' :
                return 100;
            case 'D' :
                return 500;
            case 'M' :
                return 1000;
            default: return -1;
        }
    }

    static String convertToRoman(int n) {
        final String[] ones = new String[]{"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        final String[] tens = new String[]{"", "X","XX", "XXX", "XC", "L", "LX", "LXX", "LXXX", "XC"};
        final String[] hunderds = new String[]{"","C","CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        final String[] thousands = new String[]{"", "M","MM", "MMM", "MMMM"};

        String output = thousands[n / 1000] + hunderds[(n % 1000) / 100] + tens[(n % 100) / 10] + ones[(n % 10)] ;
        return output;
    }

    //("the", "quick", "brown", "fox", "quick"), "the", "fox") > output : 3
    //("geeks", "for", "geeks", "contribute", "practice"), "geeks", "practice") > output : 2
    //("rn", "kl", "bk", "ypo", "bk"), "kl", "rn") > output : 1
    //("jd", "lzt", "kym", "ky", "gdf", "gdf", "jd"), "kym", "gdf") > output : 2
    //("axa", "ffn", "ty", "bs", "oin", "bs", "axa"), "bs", "axa") > output : 1
    static int shortestDistance(ArrayList<String> s, String word1, String word2) {
       int word1Index = -1;
       int word2Index = -1;
       int distance = Integer.MAX_VALUE;

       for (int i = 0; i < s.size(); i++){
           if(s.get(i).equals(word1)){
              word1Index = i;
           }
           if(s.get(i).equals(word2)){
               word2Index = i;
           }

           if(word1Index != -1 && word2Index != -1){
               distance = Math.min(distance, Math.abs(word1Index - word2Index));
           }
       }

       return distance;
    }

    //"8955795758" > 1
    //1000 > 0
    static  int isdivisible7(String num){
        int i,x,rem=0,n=num.length(),m;
        for(i=0;i<n;i++) {
            x=num.charAt(i)-'0';
            m=rem*10+x;
            rem=m%7;
        }
        if(rem==0)
            return 1;
        else return 0;
    }

    //str1 = aab , str2 = xxy > Output: 1 (because x =a, y = b is true for all)
    //str1 = aab , str2 = xyy > Output: 0 (because x =a, y = b is not true for all)
    static boolean areIsomorphic(String str1,String str2) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++){
            Character ch1 = str1.charAt(i);
            Character ch2 = str2.charAt(i);
            if(map.containsKey(ch1)){
                if(map.get(ch1) != ch2){
                    return false;
                }
            }else if(map.containsValue(ch2)){
                return false;
            }else{
                map.put(ch1, ch2);
            }
        }
        return true;
    }

//    Two strings are called K-anagrams if both of the below conditions are true.
//    1. Both have same number of characters.
//    2. Two strings can become anagram by changing at most K characters in a string.
//    Input:
//    str1 = "fodr", str2="gork"
//    k = 2
//    Output:1
//    Explanation: Can change fd to gk
    //input s1 = uovwhqfaemqodyksjj, s2 = swwhzsiowocjfyadvj, k = 18 > output > 1
    static boolean areKAnagrams(String s1, String s2, int k) {
      if(s1.length() != s2.length())
          return false;
      else if(s1.length() <= k){
          return true;
      }
      Map<Character, Integer> map = new HashMap<>();
      for (int i = 0; i < s1.length(); i++){
          Character key = s1.charAt(i);
          if(map.containsKey(key)){
              map.put(key, map.get(key) + 1);
          }else {
              map.put(key, 1);
          }
      }

      int counter = 0;
      for (int i = 0; i < s2.length(); i++){
         Character key = s2.charAt(i);
         if(map.containsKey(key)){
             int value = map.get(key);
             if(value == 1){
                 map.remove(key);
             }else{
                 map.put(key, value - 1);
             }
         }else{
             counter++;
         }
      }
      return counter <= k;
    }

    static boolean checkPangram  (String str) {
        Set<Character> set = new HashSet();
        String lowerCaseStr = str.toLowerCase();
        for(int i = 0; i < str.length(); i++){
            char ch = lowerCaseStr.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                set.add(ch);
            }
        }
        return set.size() == 26;
    }

    //"Hello" > 3(Hel)
    static public int lengthOfLongestSubstring(String str) {
        int n = str.length();
        int res = 0;

        for(int i = 0; i < n; i++) {
            boolean[] visited = new boolean[256];
            for(int j = i; j < n; j++)
            {
                if (visited[str.charAt(j)] == true)
                    break;
                else {
                    res = Math.max(res, j - i + 1);
                    visited[str.charAt(j)] = true;
                }
            }
            visited[str.charAt(i)] = false;
        }
        return res;
    }

    //"Hello" > 3(Hel)
    static public int lengthOfLongestSubstringOptimised(String s) {
        int[] chars = new int[128];
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;
            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    static int longestPalindromString_withMinimumDeletions(String S){
        int length_longest_palindrome = longestPalindromStringLength(S);
        return S.length() - length_longest_palindrome;
    }

//    Input: S = "aebcbda" Output: 2, Explanation: Remove characters 'e'and 'd'.
//    Input: S = "geeksforgeeks" Output: 8, Explanation: One of the possible resultstring can be "eefee", so 13 - 5 = 8.
    static int longestPalindromStringLength(String str) {
        int n = str.length();

        // Create a table to store
        // results of subproblems
        int bookmarkArray[][] = new int[n][n];

        // Strings of length 1
        // are palindrome of length 1
        for (int i = 0; i < n; i++)
            bookmarkArray[i][i] = 1;

        // Build the table. Note
        // that the lower diagonal
        // values of table are useless
        // and not filled in the process.
        // c1 is length of substring
        for (int k = 2; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                boolean areCharsEqual = str.charAt(i) == str.charAt(j);
                if (areCharsEqual && k == 2)
                    bookmarkArray[i][j] = 2;
                else if (areCharsEqual)
                    bookmarkArray[i][j] = bookmarkArray[i + 1][j - 1] + 2;
                else
                    bookmarkArray[i][j] = Math.max(bookmarkArray[i][j - 1], bookmarkArray[i + 1][j]);
            }
        }

        // length of longest
        // palindromic subsequence
        return bookmarkArray[0][n - 1];
    }

//    Input: s = "gfg" Output: 7
//    Explanation: The seven distinct subsequences are "", "g", "f", "gf", "fg","gg" and "gfg"
//    Input: s = "ggg" Output: 4
//    Explanation: The four distinct subsequences are "", "g", "gg", "ggg"
    static  int distinctSubsequences(String S) {
        // Create an array to store index of last
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int mod = 1000000007;

        // Length of input string
        int n = S.length();

        // dp[i] is going to store count of distinct subsequences of length i.
        int[] dp = new int[n + 1];

        // Empty substring has only one subsequence
        dp[0] = 1;

        // Traverse through all lengths from 1 to n.
        for (int i = 1; i <= n; i++) {
            // Number of subsequences with substring S[0..i-1]
            dp[i] = 2 * dp[i - 1];
            dp[i] %= mod;

            // If current character has appeared before, then remove all subsequences ending with previous occurrence.
            if (last[S.charAt(i - 1)-'a'] != -1)
                dp[i] = (dp[i] - dp[last[S.charAt(i - 1)-'a']] + mod) % mod;

            // Mark occurrence of current character
            last[S.charAt(i - 1)-'a'] = (i - 1);
        }

        return dp[n];
    }

//    Input: a = amazon b = azonam, Output: 1
//    Explanation: amazon can be rotated left shifted(anti clockwise) by two places, which will make it as azonam.
//    Input: a = amazon b = onamaz, Output: 1
//    Explanation: amazon can be rotated right shifted(anti clockwise) by two places, which will make it as onamaz.
//    Input: a = amazon b = anomaz, Output: 0
    static boolean isRotated(String str1, String str2) {
        String leftRotated = leftRotate(str1, 2);
        String rightRotated = rightRotate(str1, 2);
        return (str2.equals(leftRotated) || str2.equals(rightRotated));
    }

    static String leftRotate(String str, int d) {
        return str.substring(d) + str.substring(0, d);
    }

    static String rightRotate(String str, int d) {
        return leftRotate(str, str.length() - d);
    }

    static int atoi(String str) {
        if(str.isEmpty())
            return -1;
        boolean isNegative = false;
        int result = 0;
        int index = 0;
        if(str.charAt(0) == '-'){
            isNegative = true;
            index++;
        }else if(str.charAt(0) == '+'){
            index++;
        }
        for (;index < str.length(); index++) {
            char value = str.charAt(index);
            if(value >= '0' && value <= '9'){
                result = result * 10 + Integer.parseInt(value+"");
            }else{
               return -1;
            }
        }

        if(isNegative){
            result = -result;
        }

        return result;
    }

    static String findLongestWord(String S, List<String> d) {
        Collections.sort(d);//only requiren when order has to be maintained when multiple items qualify for output
        int maxLength = 0;
        String result = "";
        for (int i = 0; i < d.size(); i++){
            boolean isSubString  = isSubString(S, d.get(i));
            if(isSubString && maxLength < d.get(i).length()){
                maxLength = d.get(i).length();
                result = d.get(i);
            }
        }
        return result;
    }

    private static boolean isSubString(String s, String s1) {
        if(s1.length() > s.length())
            return false;

        int j = 0;

        for (int i = 0; i < s.length() && j < s1.length(); i++){
            if(s.charAt(i) == s.charAt(i)){
                j++;
            }
        }

        return j == s1.length();
    }

    static String findAndReplace(String S, int Q, int[] index, String[] sources, String[] targets) {
        String result = S;
        for (int i = 0; i < Q ; i++){
            result = modifyString(S, index[i], targets[i]);
        }
        return result;
    }

    private static String modifyString(String s, int index, String target) {
        String modified = s.substring(index+1);
        return "";
    }

    static  String findSum(String str1, String str2) {
        // Before proceeding further, make sure length
        // of str2 is larger.
        if (str1.length() > str2.length()){
            String t = str1;
            str1 = str2;
            str2 = t;
        }

        // Take an empty String for storing result
        String str = "";

        // Calculate length of both String
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both of Strings
        str1=new StringBuilder(str1).reverse().toString();
        str2=new StringBuilder(str2).reverse().toString();

        int carry = 0;
        for (int i = 0; i < n1; i++)
        {
            // Do school mathematics, compute sum of
            // current digits and carry
            int val1 = (int)(str1.charAt(i));
            int val2 = (int)(str2.charAt(i));
            int sum = (val1 - '0') + (val2 - '0') + carry;
            str += (char)(sum % 10 + '0');

            // Calculate carry for next step
            carry = sum / 10;
        }

        // Add remaining digits of larger number
        for (int i = n1; i < n2; i++)
        {
            int sum = ((int)(str2.charAt(i)) - '0') + carry;
            str += (char)(sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry > 0)
            str += (char)(carry + '0');

        // reverse resultant String
        StringBuilder sb = new StringBuilder(str);
        int j = sb.length() - 1;
        while(sb.charAt(j) == '0' && j > 0){
            sb.deleteCharAt(j--);
        }

        return sb.reverse().toString();
    }

//    Input : aabcdaabc, Output : 4
//    The string "aabc" is the longest, prefix which is also suffix.
//    Input : abcab, Output : 2
//    Input : aaaa, Output : 2
    static int longestPrefixSuffix(String s) {
        int n = s.length();

        // If n is less than 2
        if(n < 2) {
            return 0;
        }

        int len = 0;
        int i = (n + 1)/2;

        while(i < n) {
            if(s.charAt(i) == s.charAt(len)) {
                ++len;
                ++i;
            }
            else {
                i = i - len + 1;
                len = 0;
            }
        }

        // Return len
        return len;
    }

//    Input: abc, k = 2, Output: 2
//    Possible substrings are {"ab", "bc"}
//
//    Input: aba, k = 2, Output: 3
//    Possible substrings are {"ab", "ba", "aba"}
//
//    Input: aa, k = 1, Output: 3
//    Possible substrings are {"a", "a", "aa"}

//    Input: abcbaa, k = 3, Output: 8
//    Possible substrings are {"abc", "abb", "aba" , "aba", "bca", "bca", "cba", "cba"}
    int countkDist(String str, int k) {
        // Initialize result
        int res = 0;

        int n = str.length();

        // To store seen characters from 'a' to 'z'
        boolean seen[] = new boolean[26];

        // Consider all substrings beginning with
        // str[i]
        for (int i = 0; i < n; i++) {
            int distCount = 0;
            // mark all chars as unseen
            Arrays.fill(seen, false);
            // Consider all substrings between str[i..j]
            for (int j=i; j<n; j++) {
                // If this is a new character for this
                // substring, increment dist_count.
                if (!seen[str.charAt(j) - 'a'])
                    distCount++;

                // mark current char as seen
                seen[str.charAt(j) - 'a'] = true;

                // If distinct character count becomes k,
                // then increment result.
                if (distCount == k)
                    res++;
            }
        }

        return res;
    }


    public void printAllPathsFromRooToLeaf(TreeNode<Integer> root , int[] pathArr, Integer pathIndex){
        if(root == null)
            return;

        pathArr[pathIndex++] =  root.getData();

        if(root.getLeft() == null && root.getRight() == null){
            System.out.print("path ");
            for (int i = 0; i < pathArr.length; i++){
                System.out.print(pathArr[i]+" ");
            }
            System.out.println();
        }else{
            printAllPathsFromRooToLeaf(root.getLeft(), pathArr, pathIndex);
            printAllPathsFromRooToLeaf(root.getRight(), pathArr, pathIndex);
        }
    }
}
