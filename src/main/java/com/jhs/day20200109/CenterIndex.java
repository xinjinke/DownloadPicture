package com.jhs.day20200109;

/**
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。

 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。

 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * @author zhangxinhe
 * @date 2020/1/9 14:15
 */
public class CenterIndex {

    public static void main(String[] args){
        int[] nums = {-1,-1,1,0,1,0};
        System.out.println(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        int length = nums.length;
        for(int i = 0;i < length ; i++){
            int sum_right = sum(nums,i)- nums[i];
            int sum_left = sum(nums,0) - sum_right - nums[i];
            if(sum_left == sum_right){
                return i;
            }else {
                continue;
            }
        }
        return -1;
    }

    public static int sum(int[] nums,int m){
        int length = nums.length;
        int sum = 0;
        for(int i = m; i < length ; i++){
            sum += nums[i];
        }
        return sum;
    }
}
