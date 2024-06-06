package sparta.enumtype;

import java.util.function.Function;

public enum DiscountEvent {
    NONE((price) -> price),
    SUMMER((price) -> (int) (price * 0.1)),
    WINTER((price) -> (int) (price * 0.2)),
    BLACK_FRIDAY((price) -> (int) (price * 0.3)),
    NEW_YEAR((price) -> (int) (price * 0.5));

    private final Function<Integer, Integer> expression;

    DiscountEvent(Function<Integer, Integer> expression) {
        this.expression = expression;
    }

    public Integer calc(int price) {
        return this.expression.apply(price);
    }
}
