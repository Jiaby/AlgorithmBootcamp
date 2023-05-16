package org.train.secondweek;

public class Test {
    //@link https://leetcode-cn.com/problems/count-number-of-nice-subarrays/
    // 奇数看做1，偶数看做0，连续子数组[l,r]中的奇数个数为s[r]-s[l-1]
    public int numberOfSubarrays(int[] nums, int k) {
        // 将nums中的每个元素mod2 问题转为了子段和为k的子段数量
        // 子段和=k sum(l,r)=k=s[r] - s[l-1]
        int n = nums.length;
        int[] s = new int[n + 1];
        s[0] = 0;
        // 记录前缀和数组
        for (int i = 1; i < s.length; i++) {
            s[i] = s[i - 1] + nums[i - 1] % 2;
        }
        int[] count = new int[s.length];
        count[s[0]] ++;
        int ans = 0;
        for (int i = 1; i < s.length; i ++) {
            // s[i]代表0~i的字段和的值，k为目标值，
            // 假设l~i的子段和为k，即s[i] - s[l-1] = k s[l-1]= s[i]-k
            // 所以下面的count[s[i]-k]即是s[l-1]的数量，而s[l-1]的数量在我们遍历s[]的时候已经通过count[s[i]]++记录到了count数组
            // 如果正好s[i]与k相等，也就是s[l-1] = 0即count[0]也符合题意，所以上面会有一步count[0]++;
            if (s[i] - k >= 0) ans +=count[s[i] - k];
            count[s[i]] ++;
        }
        return ans;
    }

    //@link https://leetcode-cn.com/problems/maximum-subarray/
    // 题目就是求某一段s[i]-s[j]的最大值
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        s[0] = 0;
        // 先对nums求前缀和数组
        for (int i = 1; i < n+1; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int[] preMin = new int[n + 1];
        preMin[0] = 0;
        // 再对s[]前缀和数组求最小值数组
        for (int i = 1; i < s.length; i++) {
            preMin[i] = Math.min(s[i], preMin[i - 1]);
        }
        int ans=0;
        // 最后要求s[i]-s[j]的最大值，则s[j]最小时，s[i]-s[j]最大
        for (int i = 1; i < s.length; i++) {
            ans = Math.max(ans, s[i] - preMin[i - 1]);
        }
        return ans;
    }




    public static void main(String[] args) {
        Test test = new Test();
        test.numberOfSubarrays(new int[]{1,1,2,1,1},3);
    }
}
