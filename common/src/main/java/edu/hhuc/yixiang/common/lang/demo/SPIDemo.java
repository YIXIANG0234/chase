package edu.hhuc.yixiang.common.lang.demo;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/17 16:33:08
 */
public class SPIDemo {
    public static void main(String[] args) {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println(driver.getClass().getName());
        }

        int[] nums1 = {1, 3, 4, 7, 8, 12};
        int[] nums2 = {1, 4, 5, 9, 13, 14, 15};
        test(nums1, nums2);
    }

    public static double test(int[] nums1, int[] nums2) {
        int left = 0;
        int right = 0;
        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] <= nums2[right]) {
                System.out.print(nums1[left] + " ");
                left++;
            } else {
                System.out.print(nums2[right] + " ");
                right++;
            }
        }
        while (left < nums1.length) {
            System.out.print(nums1[left] + " ");
            left++;
        }
        while (right < nums2.length) {
            System.out.print(nums2[right] + " ");
            right++;
        }
        return 0;
    }
}
