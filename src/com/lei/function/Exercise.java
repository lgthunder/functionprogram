package com.lei.function;

import java.util.function.Function;

public class Exercise {

    public static <V, U, T> Function<U, T> compose(Function<U, V> f, Function<V, T> g) {
        return new Function<U, T>() {
            @Override
            public T apply(U u) {
                return g.apply(f.apply(u));
            }
        };
    }

    public static final Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> compose = new Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>>() {
        @Override
        public Function<Function<Integer, Integer>, Function<Integer, Integer>> apply(Function<Integer, Integer> f) {
            return new Function<Function<Integer, Integer>, Function<Integer, Integer>>() {
                @Override
                public Function<Integer, Integer> apply(Function<Integer, Integer> g) {
                    return new Function<Integer, Integer>() {
                        @Override
                        public Integer apply(Integer integer) {
                            return g.apply(f.apply(integer));
                        }
                    };
                }
            };
        }
    };

    static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
        return new Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>>() {
            @Override
            public Function<Function<T, U>, Function<T, V>> apply(Function<U, V> f) {
                return new Function<Function<T, U>, Function<T, V>>() {
                    @Override
                    public Function<T, V> apply(Function<T, U> g) {
                        return new Function<T, V>() {
                            @Override
                            public V apply(T t) {
                                return f.apply(g.apply(t));
                            }
                        };
                    }
                };
            }
        };
    }


    public static <A, B, C> Function<B, C> partialA(A a, Function<A, Function<B, C>> f) {
        return new Function<B, C>() {
            @Override
            public C apply(B b) {
                return f.apply(a).apply(b);
            }
        };
    }

    public static <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
        return new Function<A, C>() {
            @Override
            public C apply(A a) {
                return f.apply(a).apply(b);
            }
        };
    }

    public static <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> f() {
        return new Function<A, Function<B, Function<C, Function<D, String>>>>() {
            @Override
            public Function<B, Function<C, Function<D, String>>> apply(A a) {
                return new Function<B, Function<C, Function<D, String>>>() {
                    @Override
                    public Function<C, Function<D, String>> apply(B b) {
                        return new Function<C, Function<D, String>>() {
                            @Override
                            public Function<D, String> apply(C c) {
                                return new Function<D, String>() {
                                    @Override
                                    public String apply(D d) {
                                        return "" + a.toString() + b.toString() + c.toString() + d.toString();
                                    }
                                };
                            }
                        };
                    }
                };
            }
        };
    }

    public static <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> f) {
        return new Function<A, Function<B, C>>() {
            @Override
            public Function<B, C> apply(A a) {
                return new Function<B, C>() {
                    @Override
                    public C apply(B b) {
                        return f.apply(new Tuple<>(a, b));
                    }
                };
            }
        };
    }

    public static <T, U, V> Function<U, Function<T, V>> reverseArgs(Function<T, Function<U, V>> f) {
        return new Function<U, Function<T, V>>() {
            @Override
            public Function<T, V> apply(U u) {
                return new Function<T, V>() {
                    @Override
                    public V apply(T t) {
                        return f.apply(t).apply(u);
                    }
                };
            }
        };
    }

    static <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> higherAndThen() {
        return new Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>>() {
            @Override
            public Function<Function<U, V>, Function<T, V>> apply(Function<T, U> f) {
                return new Function<Function<U, V>, Function<T, V>>() {
                    @Override
                    public Function<T, V> apply(Function<U, V> g) {
                        return new Function<T, V>() {
                            @Override
                            public V apply(T t) {
                                return g.apply(f.apply(t));
                            }
                        };
                    }
                };
            }
        };
    }

}
