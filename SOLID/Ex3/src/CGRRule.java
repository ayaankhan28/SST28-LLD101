public class CGRRule implements EligibilityRule {
    private final RuleInput config;
    
    public CGRRule(RuleInput config) {
        this.config = config;
    }

    @Override
    public String evaluate(StudentProfile s) {
        if (s.cgr < config.minCgr) {
            return "CGR below " + config.minCgr;
        }
        return null;
    }
}
