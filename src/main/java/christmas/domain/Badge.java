package christmas.domain;

public enum Badge {
    STAR(5_000), TREE(10_000), SANTA(20_000);

    private final Integer minRange;

    Badge(Integer minRange) {
        this.minRange = minRange;
    }

    public static Badge obtainBadge(Integer totalBenefitPrice) {
        if (totalBenefitPrice < STAR.minRange) {
            return null;
        }
        if (totalBenefitPrice < TREE.minRange) {
            return STAR;
        }
        if (totalBenefitPrice < SANTA.minRange) {
            return TREE;
        }
        return SANTA;
    }
}