package com.lei.function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Function2oc {

    public static void main(String[] args) {
        Predicate small = Compare(10, 50);
        Predicate normal = Compare(50, 1000);
        Predicate bigger = Compare(1000, 0);
        Consumer<Integer> sc = integer -> System.out.println("sc:" + integer);
        Consumer<Integer> nc = integer -> System.out.println("sc:" + integer);
        Consumer<Integer> bc = integer -> System.out.println("sc:" + integer);
        //1
        isBigger(10, 20);
        int mount = 10;
        if (mount >= 10 && mount < 50) {
            // do something
            sc.accept(mount);
        }
        if (mount >= 50 && mount < 1000) {
            //do something
            nc.accept(mount);
        }
        if (mount >= 1000) {
            //do something
            bc.accept(mount);
        }

        //2

        if (small.test(mount)) {
            // do something
            sc.accept(mount);
        }
        if (normal.test(mount)) {
            //do something
            nc.accept(mount);
        }
        if (bigger.test(mount)) {
            //do something
            bc.accept(mount);
        }

        //3
        handlerMount(mount, small, sc);

        handlerMount(mount, normal, nc);

        handlerMount(mount, bigger, bc);

        //4
        Chain chain = new Chain();
        chain.add(small, sc);
        chain.add(normal, nc);
        chain.add(bigger, bc);
        chain.handle(mount);

        //5
        chain(small, sc)
                .andThen(chain(normal, nc))
                .andThen(chain(bigger, bc))
                .apply(mount);


    }


    public static boolean isBigger(int x, int y) {
        return x > y;
    }

    public static Predicate<Integer> Compare(int min, int bound) {
        return integer -> integer >= min && integer < bound;
    }

    public static void handlerMount(int mount, Predicate p, Consumer c) {
        if (p.test(mount)) {
            c.accept(mount);
        }
    }


    static class Chain {
        Map<Predicate, Consumer> handler = new HashMap<>();

        public void add(Predicate p, Consumer c) {
            handler.put(p, c);
        }

        void handle(int mount) {
            Set<Predicate> keys = handler.keySet();
            keys.forEach(predicate -> {
                if (predicate.test(mount)) {
                    handler.get(predicate).accept(mount);
                }
            });
        }
    }

    public static Function<Integer, Integer> chain(Predicate p, Consumer c) {
        return integer -> {
            if (p.test(integer)) {
                c.accept(integer);
            }
            return integer;
        };
    }

}



