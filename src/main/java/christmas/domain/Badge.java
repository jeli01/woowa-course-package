package christmas.domain;

public enum Badge {
    STAR(5_000), TREE(10_000), SANTA(20_000);

    private final Integer min_range;

    Badge(Integer min_range) {
        this.min_range = min_range;
    }

    public static Badge obtainBadgeByPrice(Integer totalBenefitPrice) {
        if (totalBenefitPrice < STAR.min_range) {
            return null;
        }
        if (totalBenefitPrice < TREE.min_range) {
            return STAR;
        }
        if (totalBenefitPrice < SANTA.min_range) {
            return TREE;
        }
        return SANTA;
    }
}
