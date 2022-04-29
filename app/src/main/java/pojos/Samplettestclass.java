package pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Samplettestclass {
    public int x = 9;
    public double y = 10;
    public void setDistance(int num){
        x = num;
        y = num * 2.0;
        System.out.println("double value >>> "+y);
    }

    List< Integer > data;
    public Samplettestclass() {
        data = new ArrayList();
    }

    public void addNum(int num) {
        int idx = Collections.binarySearch(data, num);
        if (idx >= 0) {
            data.add(idx, num);
        } else {
            data.add(-idx - 1, num);
        }
    }

    public double findMedian() {
        int len = data.size();
        int mid = data.get(len / 2);
        if (len % 2 == 1) {
            return mid;
        } else {
            return 1.0 * ((data.get(len / 2 - 1)) + mid) / 2;
        }
    }

    private int counter = 0;

    public void inc1() {
        counter++;
    }

    public void inc2() {
        counter++;
    }
}
