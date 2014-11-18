package main.iteration;

public class Function2Impl<T, U, R> implements Function2<T, U, R> {
    @Override
    public R apply(T t, U u) {
        return (R) ((String)t + (String)u);
    }
}
