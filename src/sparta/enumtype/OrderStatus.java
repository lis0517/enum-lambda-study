package sparta.enumtype;

import sparta.service.Order;

import java.util.function.Function;

public enum OrderStatus {
    ORDER_RECEIVED, // 주문 접수
    PRODUCT_PREPARING, // 상품 준비중
    PRODUCT_SHIPPED, // 상품 발송
    DELIVERED, // 배송 완료
    CANCELED, // 취소
    EXCHANGED, // 교환
    RETURN, // 반품
    REFUNDED, // 환불
    REDELIVERED, // 재배송
    PURCHASE_CONFIRMED; // 구매 결정

    private Function<OrderStatus, Boolean> expression;

    static{
        ORDER_RECEIVED.expression = (status -> status == PRODUCT_PREPARING);
        PRODUCT_PREPARING.expression = (status -> status == PRODUCT_SHIPPED || status == CANCELED);
        PRODUCT_SHIPPED.expression = (status -> status == DELIVERED);
        DELIVERED.expression = (status -> status == EXCHANGED || status == RETURN);
        EXCHANGED.expression = (status -> status == REDELIVERED);
        RETURN.expression = (status -> status == REFUNDED);

        //end point
        PURCHASE_CONFIRMED.expression = (status -> false);
        CANCELED.expression = (status -> false);
        REFUNDED.expression = (status -> false);
        REDELIVERED.expression = (status -> false);
    }


    public Boolean isChangable(OrderStatus nextStatus){
        return this.expression.apply(nextStatus);
    }
}
