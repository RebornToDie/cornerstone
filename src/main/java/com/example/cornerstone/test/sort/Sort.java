package com.example.cornerstone.test.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

    /**
     * 冒泡排序
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;

        boolean diswap = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    diswap = true;
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }

            }
            if (!diswap) {
                break;
            }
        }

        return array;
    }

    public static int[] selectSort(int[] array) {
        if (array.length == 0)
            return array;

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
//            int minValue = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    minIndex = j;
//                    array[i] = array[j];
                }
            }
            if (minIndex != i) {
                int mid = array[minIndex];
                array[minIndex] = array[i];
                array[i] = mid;
            }
        }

        return array;
    }


    public static int[] insertSort(int[] array) {
        if (array.length == 0)
            return array;
        List<Integer> list = Arrays.stream( array ).boxed().collect(Collectors.toList());
        for (int i = 1; i < list.size(); i++) {
            for (int j = i-1; j >= 0; j--) {
//                  if((array[i] > array[j] && j >0) || (j == 0 && array[i] < array[j])){
//                      int value = list.indexOf(i);
//                      list.remove(i);
//                      list.add(j,value);
//
//
//                  }else{
//                      break;
//                }
                if(array[i] < array[j] && j >0){
                    continue;
                }else if(array[i] > array[j]  && j>=0){
                    int value = list.indexOf(i);
                    list.remove(i);
                    list.add(j+1,value);
                }else{
                    int value = list.indexOf(i);
                    list.remove(i);
                    list.add(0,value);
                }
            }
        }

//        for (int i = 1; i < array.length; i++) {
//            int index = -1;
//            for (int j = i - 1; j >= 0; j--) {
//                if (array[i] < array[j]) {
//                    index = j;
//                }
//                if ((array[i] > array[j] || j == 0) && index != -1) {
//                    insertArray(array, i, index);
////                    int temp = array[index];
////                    array[index] = array[i];
////                    array[i] = temp;
//                    break;
//                }
//            }
//        }
        return array;
    }

    public static void jiaohuan(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void insertArray(int[] array, int i, int j) {
        int temp = array[i];
        for (int k = i; k > j; k--) {
            array[k] = array[k - 1];
        }
        array[j] = temp;
    }
}
