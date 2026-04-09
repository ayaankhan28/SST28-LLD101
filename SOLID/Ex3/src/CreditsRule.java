public class CreditsRule implements EligibilityRule {
    private final RuleInput config;

    public CreditsRule(RuleInput config) {
        this.config = config;
    }

    @Override
    public String evaluate(StudentProfile s) {
        if (s.earnedCredits < config.minCredits) {
            return "credits below " + config.minCredits;
        }
        return null;
    }
}
