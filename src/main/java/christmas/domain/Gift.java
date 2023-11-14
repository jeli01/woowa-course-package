package christmas.domain;

public enum Gift {
    CHAMPAGNE(25_000, 120_000);

    private final Integer price;
    private final Integer orderPriceToGet;

    Gift(Integer price, Integer orderPriceToGet) {
        this.price = price;
        this.orderPriceToGet = orderPriceToGet;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getOrderPriceToGet() {
        return orderPriceToGet;
    }
}
