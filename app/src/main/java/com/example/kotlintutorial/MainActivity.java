package com.example.kotlintutorial;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import pojos.Samplettestclass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        isVersionEligible(null, null);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaggerSampleActivity.class));
            }
        });

//        solution(2014, "April", "May", "Wednesday");

//        isPhoneNumbersConsistent();

//        validPhoneNumberProblem();

//        MedianProblem();
//        solution();
//        int[] input = {1, 4, 2, 10, 2, 3, 1, 0, 20 };
//        printMedian(input, 3);
//        threeSumMulti(input, 5);
//        int result = longestUniqueSubsttr("geeksforgeeks");
//        int result =  maximumSumSlidingProblemBetterApproach(input, 4);
//        System.out.println("Result of maximumSumSlidingProblem is >>> "+result);
//        int[][] arr = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
//        gameOfLife(arr);
//        String[] input = {"683339452288515879","7846081062003424420","4805719838","4840666580043","83598933472122816064","522940572025909479","615832818268861533","65439878015","499305616484085","97704358112880133","23861207501102","919346676","60618091901581","5914766072","426842450882100996","914353682223943129","97","241413975523149135","8594929955620533","55257775478129","528","5110809","7930848872563942788","758","4","38272299275037314530","9567700","28449892665","2846386557790827231","53222591365177739","703029","3280920242869904137","87236929298425799136","3103886291279"};
        kthLargestNumber(new String[]{"2","21","12","1"}, 3);
    }

    public int threeSumMulti(int[] arr, int target) {
        HashMap<Integer, Integer> hm=new HashMap<>();
        int mod= 1000000007;
        hm.put(arr[0], 1);
        int ans=0;
        for(int i=1;i<arr.length;i++) {
            for(int j=i+1;j<arr.length;j++) {
                if(arr[i]+arr[j]>target)
                    continue;
                else {
                    int re=target-(arr[i]+arr[j]);
                    if(hm.containsKey(re))
                        ans=(ans%mod+hm.get(re)%mod)%mod;
                }
            }
            if(hm.containsKey(arr[i]))
                hm.put(arr[i], hm.get(arr[i])+1);

            else
                hm.put(arr[i], 1);
        }
        return ans;
    }

    public static Boolean areDistinct(String str, int i, int j) {

        // Note : Default values in visited are false
        boolean[] visited = new boolean[26];

        for(int k = i; k <= j; k++)
        {
            if (visited[str.charAt(k) - 'a'] == true)
                return false;

            visited[str.charAt(k) - 'a'] = true;
        }
        return true;
    }

    void printMedian(int arr[], int n)
    {
        int[] output = new int[arr.length];
        int outputCount = 0;
        int i, j, pos, num;
        int count = 1;
        System.out.println("Median after reading "+arr[0]+" is "+count+" \n");
        output[outputCount++] = count;
        for (i = 1; i < n; i++) {
            float median;
            j = i - 1;
            num = arr[i];

            // find position to insert current element in sorted
            // part of array
            pos = binarySearch(arr, num, 0, j);

            // move elements to right to create space to insert
            // the current element
            while (j >= pos) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = num;

            // increment count of sorted elements in array
            count++;

            // if odd number of integers are read from stream
            // then middle element in sorted order is median
            // else average of middle elements is median
            if (count % 2 != 0) {
                median = arr[count / 2];
            }
            else {
                median = (arr[(count / 2) - 1] + arr[count / 2]) / 2;
            }
            output[outputCount++] = (int)median;
            System.out.println("Median after reading "+(i + 1)+" is "+median+" \n");
        }
    }

    int binarySearch(int arr[], int item, int low, int high)
    {
        if (low >= high) {
            return (item > arr[low]) ? (low + 1) : low;
        }

        int mid = (low + high) / 2;

        if (item == arr[mid])
            return mid + 1;

        if (item > arr[mid])
            return binarySearch(arr, item, mid + 1, high);

        return binarySearch(arr, item, low, mid - 1);
    }

    private void MedianProblem(int[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <T> Object[] convertStreamToArray(Stream<T> stream) {
        return stream.toArray();
    }

    int solution() {
        String doc = "Alex:(486)-619-9812, Maria: 632 831 1993, Donald: 232-618-93200, John: 621) 198 1082";
        Pattern pattern = Pattern.compile("^(\\+\\d{1,2}\\s)?((\\(\\d{3}\\))|(\\d{3}))[\\s.-]\\d{3}[\\s.-]\\d{4}$");
        Matcher matches = pattern.matcher(doc);
        int s = 0;
        while (matches.find()) {
            s++;
        }
        return s;
    }


    private int validPhoneNumberProblem() {
        int count = 0;
        String[] phoneNumbers = {"(486)-619-9812", "632 831 1993", "232-618-93200", "621) 198 1082"};
        for (int i = 0; i < phoneNumbers.length; i++) {
            if(isValidPhoneNumber(phoneNumbers[i])){
                count++;
            }
        }
        System.out.println("count of valid phone numbers is >>>  "+count);
        return count;
    }

    private boolean isPhoneNumbersConsistent() {
        String[] numbers = {"911", "9876543", "9112345"};
        Arrays.sort(numbers);
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i].startsWith(numbers[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPhoneNumber(String input){
        String regex = "^(\\+\\d{1,2}\\s)?((\\(\\d{3}\\))|(\\d{3}))[\\s.-]\\d{3}[\\s.-]\\d{4}$";
        return input.matches(regex);
    }

    private void test() {
        Samplettestclass samplettestclass = new Samplettestclass();
        samplettestclass.setDistance(2);
    }

    private boolean isVersionEligible(String currentVersion, String targetedVersion) {
        if(TextUtils.isEmpty(currentVersion) || TextUtils.isEmpty(targetedVersion)){
            return false;
        }
        String[] cvSplit = currentVersion.split("\\.");
        String[] tvSplit = targetedVersion.split("\\.");
        int length = Math.max(cvSplit.length, tvSplit.length);
        boolean result = true;
        for (int i = 0; i < length; i++){
            int cv = i < cvSplit.length ? Integer.parseInt(cvSplit[i]) : 0;
            int tv = i < tvSplit.length ? Integer.parseInt(tvSplit[i]) : 0;
            if(cv < tv){
                result = false;
                break;
            }else if(cv > tv){
                break;
            }
        }
        return result;
    }

    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] days1 = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
    int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int solution(int year, String startMonth, String endMonth, String startDayofTheYear){
        //set february total days as 29 if it is leap year
        if(year % 4 == 0){
            monthDays[1]++;
        }

        int monthStartIndex = 0; int monthEndIndex = 0;
        //to get month start and month end indices
        for (int i = 0; i < months.length; i++) {
            if(months[i].equals(startMonth)){
                monthStartIndex = i;
            }else if(months[i].equals(endMonth)){
                monthEndIndex = i;
            }
        }

        //get total days from start of the year to start of the month
        int totalDays = getTotalDaysTillStartMonth(monthStartIndex);

        //get start day index
        int yearDayIndex = getDayIndex(startDayofTheYear);

        //get day index of starting month
        for (int i = 0; i < totalDays-1; i++) {
            if(yearDayIndex == 7)
                yearDayIndex = 0;

            yearDayIndex++;
        }

        //get total days in between the months
        int totalDaysBetweenMonths = getTotalDaysBetweenMonths(monthStartIndex, monthEndIndex);

        int weeks = 0;
        int counter = yearDayIndex;
        for (int i = 0; i < totalDaysBetweenMonths-yearDayIndex; i++) {
            if(counter == 7){
                weeks++;
                counter = 0;
            }
            counter++;
        }

        if(counter < 6)
            weeks = weeks - 1;

        return weeks;
    }



    private int getDayIndex(String startDayofTheYear) {
        int retIndex = 0;
        for (int i = 0; i < days.length; i++) {
            if(days[i].equals(startDayofTheYear)){
                retIndex = i;
            }
        }
        return retIndex;
    }

    private int getTotalDaysTillStartMonth(int monthStartIndex){
        int totalDays = 0;
        for (int i = 0; i < months.length; i++) {
            if(i == monthStartIndex){
                break;
            }else{
                totalDays += monthDays[i];
            }
        }
        return totalDays;
    }

    private int getTotalDaysBetweenMonths(int monthStartIndex, int monthEndIndex){
        int totalDays = 0;
        for (int i = 0; i < months.length; i++) {
            if(i == monthStartIndex){
                totalDays = monthDays[i];
            }else{
                totalDays += monthDays[i];
            }
            if(i == monthEndIndex){
                break;
            }
        }
        return totalDays;
    }

    public static int longestUniqueSubsttr(String str) {
        int n = str.length();

        // Result
        int res = 0;

        for(int i = 0; i < n; i++)
        {

            // Note : Default values in visited are false
            boolean[] visited = new boolean[256];

            for(int j = i; j < n; j++)
            {

                // If current character is visited
                // Break the loop
                if (visited[str.charAt(j)] == true)
                    break;

                    // Else update the result if
                    // this window is larger, and mark
                    // current character as visited.
                else
                {
                    res = Math.max(res, j - i + 1);
                    visited[str.charAt(j)] = true;
                }
            }

            // Remove the first character of previous
            // window
            visited[str.charAt(i)] = false;
        }
        return res;
    }

    private int maximumSumSlidingProblem(int[] arr, int m){
        int maxSum = 0;
        for (int i = 0; i < arr.length-m; i++) {
            int sum = 0;
            for (int j = i; j < i+m; j++) {
                sum += arr[j];
            }
            if(sum > maxSum){
                maxSum = sum;
            }
        }
        return maxSum;
    }

    private int maximumSumSlidingProblemBetterApproach(int[] arr, int m){
        int maxSum = 0;
        int windowSum = 0;
        for (int i = 0; i < m; i++) {
            maxSum += arr[i];
        }

        windowSum = maxSum;
        for (int i = m; i < arr.length; i++) {
           windowSum = windowSum + (arr[i] - arr[i-m]);
           maxSum = Math.max(windowSum, maxSum);
        }

        return maxSum;
    }

    void gameOfLife(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        boolean[][] changed = new boolean[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int currentVal = arr[i][j];
                int countOfLives= count(arr,i,j,m,n,changed); //count live neighbours
                if(currentVal == 0){
                    if(countOfLives == 3){
                        changed[i][j] = true;
                        currentVal = 1;
                    }
                }else{
                    if(countOfLives < 2){
                        changed[i][j] = true;
                        currentVal = 0;
                    }else if(countOfLives > 3){
                        changed[i][j] = true;
                        currentVal = 0;
                    }
                }
                arr[i][j] = currentVal;
            }
        }
    }

    //function for counting the live neighbours of current cell
    int count(int[][] arr,int i,int j,int m,int n, boolean[][] changed){
        int[][] dis = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int cnt=0;
        for(int k=0;k<8;k++){
            int x= i+dis[k][0];
            int y= j+dis[k][1];
            if(x>=0 && y>=0 && x<m && y<n && arr[x][y] == 1){
                cnt++;
            }
        }
        return cnt;
    }

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, new Comparator<String>() {
            public int compare(String num1, String num2) {
                int len1 = num1.length();
                int len2 = num2.length();

                if (len1 > len2) {
                    return 1;
                } else if (len1 < len2) {
                    return -1;
                } else {
                    for (int i = 0; i < len1; i++) {
                        int c1 = num1.charAt(i) - '0';
                        int c2 = num2.charAt(i) - '0';

                        if (c1 > c2) {
                            return 1;
                        } else if (c1 < c2) {
                            return -1;
                        }
                    }
                    return 0;
                }
            }
        });

        return nums[nums.length - k];
    }
}