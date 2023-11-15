package christmas.domain;

public enum Gift {
    CHAMPAGNE("샴페인", 25_000, 120_000);

    private final String name;
    private final Integer price;
    private final Integer orderPriceToGet;

    Gift(String name, Integer price, Integer orderPriceToGet) {
        this.name = name;
        this.price = price;
        this.orderPriceToGet = orderPriceToGet;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getOrderPriceToGet() {
        return orderPriceToGet;
    }
}
