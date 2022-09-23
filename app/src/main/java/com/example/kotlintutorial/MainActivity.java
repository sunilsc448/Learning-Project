package com.example.kotlintutorial;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import pojos.Samplettestclass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

//        isVersionEligible(null, null);

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
//        kthLargestNumber(new String[]{"2","21","12","1"}, 3);
//        removeOutsideRange(getBSTree(), -13, 13);

        firstFunction();
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



    //    [3,0,4,null,2,null,null,1]
    private TreeNodeJava getBSTree() {
        TreeNodeJava root = new TreeNodeJava(6);
        root = root.insertBSTNode(root,-13);
        root = root.insertBSTNode(root,14);
        root = root.insertBSTNode(root,-8);
        root = root.insertBSTNode(root,15);
        root = root.insertBSTNode(root,13);
        root = root.insertBSTNode(root,7);
        return root;
    }

    private TreeNodeJava removeOutsideRange(TreeNodeJava root, int min, int max) {
        // BASE CASE
        if(root == null) {
            return null;
        }

        root.left = removeOutsideRange(root.left, min, max);
        root.right = removeOutsideRange(root.right, min, max);

        if(root.key < min) {
            TreeNodeJava rchild = root.right;
            root = null;
            return rchild;
        }

        if(root.key > max) {
            TreeNodeJava lchild = root.left;
            root = null;
            return lchild;
        }

        return root;
    }

    private void firstFunction() {
//        TreeNode node = new TreeNode(5);
//        node.test(10,20);
////        KotlinTestFile file = new KotlinTestFile();
////        file.test1();
//        KotlinTestFile ktf = new KotlinTestFile();
//        ktf.add(2,3, 4);
//        KotlinTestFile.sub(2, 3);
//        KotlinTestFile.Companion.sub(2,3);
//        KotlinSampleFile file = new KotlinSampleFile();
    }

    static int count_Triplets(int[] A, int N) {
        int max_val = 0;
        for (int i = 0; i < N; i++)
            max_val = Math.max(max_val, A[i]);
        int[] freq = new int[max_val + 1];
        for (int i = 0; i < N; i++)
            freq[A[i]]++;

        int count = 0;

        count += freq[0] * (freq[0] - 1) * (freq[0] - 2) / 6;

        for (int i = 1; i <= max_val; i++)
            count += freq[0] * freq[i] * (freq[i] - 1) / 2;


        for (int i = 1; 2 * i <= max_val; i++)
            count += freq[i] * (freq[i] - 1) / 2 * freq[2 * i];

        for (int i = 1; i <= max_val; i++) {
            for (int j = i + 1; i + j <= max_val; j++)
                count += freq[i] * freq[j] * freq[i + j];
        }

        return count;
    }
}