import sparta.SampleUsers;
import sparta.User;
import sparta.enumtype.DiscountEvent;
import sparta.enumtype.OrderStatus;
import sparta.service.Coupon;
import sparta.service.Order;
import sparta.service.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        lambdaTest();
        System.out.println("---------------------------------------------------------");
        enumTest();
        System.out.println("---------------------------------------------------------");
        orderTest();
    }

    private static void enumTest() {
        final Coupon coupon = new Coupon("특가쿠폰", 5000);
        final Product product = new Product("아이폰135 Super Ultra Pro Max", 20_000_000);

        final int couponPrice = coupon.calcPrice(DiscountEvent.SUMMER);
        final int productPrice = product.calcPrice(DiscountEvent.WINTER);

        System.out.println("couponPrice = " + couponPrice);
        System.out.println("productPrice = " + productPrice);
    }

    private static void orderTest(){
        final Order order1 = new Order("램 16GB", OrderStatus.ORDER_RECEIVED);

        System.out.println(order1.getProductName() + ", isChangable? PRODUCT_SHIPPED = " + order1.isChangable(OrderStatus.PRODUCT_SHIPPED)); // 주문 -> 발송으로 변경
        System.out.println(order1.getProductName() + ", isChangable? DELIVERED  = " + order1.isChangable(OrderStatus.DELIVERED)); // 주문 -> 배송 완료로 변경
        System.out.println(order1.getProductName() + ", isChangable? PRODUCT_PREPARING  = " + order1.isChangable(OrderStatus.PRODUCT_PREPARING)); // 주문 -> 접수로 변경

        System.out.println("---------------------------------------------------------");

        final Order order2 = new Order("RTX 4090", OrderStatus.PRODUCT_PREPARING);

        System.out.println(order2.getProductName() + ", isChangable? CANCELED = " + order2.isChangable(OrderStatus.CANCELED)); // 접수 -> 취소로 변경
        System.out.println(order2.getProductName() + ", isChangable? PRODUCT_SHIPPED  = " + order2.isChangable(OrderStatus.PRODUCT_SHIPPED)); // 접수 -> 발송으로 변경
        System.out.println(order2.getProductName() + ", isChangable? ORDER_RECEIVED  = " + order2.isChangable(OrderStatus.ORDER_RECEIVED)); // 접수 ->  주문으로 변경
        System.out.println(order2.getProductName() + ", isChangable? DELIVERED  = " + order2.isChangable(OrderStatus.DELIVERED)); // 접수 ->  배송완료로 변경
    }

    private static void lambdaTest() {
        final SampleUsers users = new SampleUsers();
        users.init();

        List<User> users1 = users.ageGreaterThan(30);
        List<User> users2 = users.ageEquals(30);
        List<User> users3 = users.ageBetween(10, 20);
        List<User> users4 = users.genderEquals("여성");
        List<User> users5 = users.burgerEquals("맥도날드");

        List<User> lUser1 = users.getUsersBy((user) -> user.getAge() > 30);
        List<User> lUser2 = users.getUsersBy((user) -> user.getAge() == 30);
        List<User> lUser3 = users.getUsersBy((user) -> user.getAge() >= 10 && user.getAge() <= 20);
        List<User> lUser4 = users.getUsersBy((user) -> "여성".equalsIgnoreCase(user.getGender()));
        List<User> lUser5 = users.getUsersBy((user) -> "맥도날드".equalsIgnoreCase(user.getBurger()));

        // 30세 초과
        System.out.println("#####   30세 초과   #####");
        System.out.println("01: " + users1);
        System.out.println("L1: " + lUser1);
        System.out.println();

        // 30세
        System.out.println("#####   30세   #####");
        System.out.println("02: " + users2);
        System.out.println("L2: " + lUser2);
        System.out.println();

        // 10 ~ 20세 사이
        System.out.println("#####   10 ~ 20세 사이   #####");
        System.out.println("03: " + users3);
        System.out.println("L3: " + lUser3);
        System.out.println();

        // 여성
        System.out.println("#####   여성   #####");
        System.out.println("04: " + users4);
        System.out.println("L4: " + lUser4);
        System.out.println();

        // 맥도날드
        System.out.println("#####   맥도날드   #####");
        System.out.println("04: " + users5);
        System.out.println("L4: " + lUser5);
        System.out.println();

        /*
        // 국적
        System.out.println("#####   국적   #####");
        */

        /*
        // 반민초(이단)
        System.out.println("#####   이단   #####");*/
    }
}
