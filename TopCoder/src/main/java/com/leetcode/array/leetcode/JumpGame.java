package com.leetcode.array.leetcode;

//https://leetcode.com/problems/jump-game-ii/description/
public class JumpGame {

    public int jump(int[] nums) {
        int step_count = 0;
        int last_jump_max = 0;
        int current_jump_max = 0;
        for(int i=0; i<nums.length-1; i++) {
            current_jump_max = Math.max(current_jump_max, i+nums[i]);
            if( i == last_jump_max ) {
                step_count++;
                last_jump_max = current_jump_max;
            }
        }
        return step_count;
    }

    public static void main(String[] args) {
        int arr[] = {2,3,1,1,4};
        int result = new JumpGame().jump(arr);
        System.out.println(result);
    }
}
