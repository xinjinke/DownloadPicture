package com.jhs.day20200110;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangxinhe
 * @date 2020/1/10 15:38
 */
@Getter
@Setter
public class ListNode {
    int val;

    ListNode next;

    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
