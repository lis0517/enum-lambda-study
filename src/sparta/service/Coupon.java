package sparta.service;

import sparta.enumtype.DiscountEvent;

public class Coupon {
    private final String name;
    private final int price;

    public Coupon(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int calcPrice(DiscountEvent event) {
        return price + event.calc(price);
    }

    public int calcPrice(DiscountEvent event, int price) {
        return price + event.calc(price);
    }
}
