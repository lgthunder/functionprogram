package com.lei.function;

import java.util.function.Function;
import java.util.function.Predicate;

public class CurryTest {
    public static void main(String[] args) {
        //1 乘法
        int result = multiply(10, 10);
        Function<Integer, Function<Integer, Integer>> multiply = x -> (Function<Integer, Integer>) y -> x * y;
        System.out.println(multiply.apply(10).apply(10));
        //2
        Function<Integer, Function<Integer, Predicate<Integer>>> compare = x -> (Function<Integer, Predicate<Integer>>) y -> target -> target >= x && target < y;
        Predicate small = Compare(10, 50);
        Predicate small1 = compare.apply(10).apply(50);
        System.out.println(small.test(20));
        System.out.println(small1.test(20));
        //3
        Function<Integer, Predicate<Integer>> min = min1 -> (Predicate<Integer>) target -> target >= min1;
        Function<Integer, Predicate<Integer>> max = max1 -> (Predicate<Integer>) target -> target < max1;
        min.apply(10).test(20);
        max.apply(50).test(20);
        Function<Boolean, Function<Boolean, Boolean>> and = flag1 -> (Function<Boolean, Boolean>) flag2 -> flag1 && flag2;
        System.out.println(and.apply(min.apply(10).test(20)).apply(max.apply(50).test(0)));
        min.apply(10).and(max.apply(50)).test(20);

        //4
        Function<Predicate<Integer>, Function<Predicate<Integer>, Predicate<Integer>>> andPredicate = min12 -> (Function<Predicate<Integer>, Predicate<Integer>>) max12 -> (Predicate<Integer>) target -> min12.test(target) && max12.test(target);
        boolean flag = andPredicate.apply(min.apply(10)).apply(max.apply(50)).test(20);
        //andPredicate 在 predicate 中已经有默认实现 and
        System.out.println(min.apply(10).and(max.apply(50)).test(20));

        //复杂情况 x>10&&y<10
        int x = 15;
        int y = 20;
        min.apply(10).test(x);
        max.apply(20).test(y);
        min.apply(10).and(max.apply(20)).test(x);
        boolean result2 = min.apply(10).and(max.apply(30)).test(x) && max.apply(20).and(min.apply(10)).test(y);
    }


    public static int multiply(int x, int y) {
        return x * y;
    }


    public static Predicate<Integer> Compare(int min, int bound) {
        return integer -> integer >= min && integer < bound;
    }
}
