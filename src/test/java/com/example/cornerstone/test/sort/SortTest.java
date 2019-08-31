package com.example.cornerstone.test.sort;

import org.junit.Test;

public class SortTest {

    @Test
    public void contextLoads() {

        int [] array = {8,9,1,4,6,2,5,3,7};

        Sort.insertSort(array);

        System.out.println(array);
    }

}
