package com.jhs.day20200110;

import com.alibaba.fastjson.JSONObject;

/**
 *
 *
 * @author zhangxinhe
 * @date 2020/1/10 15:22
 */
public class Solution {

    public static void main(String[] args){

        int[] nums = {2,1,5,9};
        int target = 9;
        System.out.println(JSONObject.toJSONString(twoSum(nums,target)));

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1,l2);

        System.out.println(JSONObject.toJSONString(l1));
        System.out.println(JSONObject.toJSONString(l2));
        System.out.println(JSONObject.toJSONString(listNode));
    }

    /**
     * 获取数组中和为target的数的坐标
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int length = nums.length;
        for(int i = 0; i<length; i++){
            for(int j = i+1; j<length; j++){
                int sum = nums[i] + nums[j];
                if(sum == target){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }


    /**
     *   给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
         如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
         您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
         示例：
         输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
         输出：7 -> 0 -> 8
         原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);

        //进位
        int i = 0;
        while (l1 != null && l2 != null){
            int sum = (l1.val + l2.val) % 10 + i;
            if((l1.val + l2.val) >= 10){
                i = 1;
            }
            node.next = new ListNode(sum);


            l1 = l1.next;
            l2 = l2.next;

//            if(listNode.val == 0){
//                listNode.val = sum;
//            }else {
//                if(listNode.next == null){
//                    listNode.next = new ListNode(sum);
//                }
//                else {
//                    listNode.next.next = new ListNode(sum);
//                }
//            }
        }
        return l1;
    }




}
