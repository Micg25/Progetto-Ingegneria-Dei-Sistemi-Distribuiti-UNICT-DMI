import java.util.ArrayList;
import java.util.List;

/**
 * Class SetOfAuthRules (for pattern Reference Monitor) holds field rules
 * that lists access rules. A rule is an instance of Authorisation.
 */
public class SetOfAuthRules {

    private List<Authorisation> rules = new ArrayList<>();

    public boolean exist(Request request) {
        return rules.stream()
                .filter(a -> a.subject().equals(request.subject()))
                .peek(a -> System.out.println("[RulSet]: found Authorisation for subject " + a.subject()))
                .filter(a -> a.object().equals(request.protectionObject().getClass().getName()))
                .peek(a -> System.out.println("[RulSet]: found Authorisation for object " + a.object()))
                .filter(a -> a.accessType().equals(request.accessRequest()))
                .peek(a -> System.out.println("[RulSet]: found Authorisation for access type " + a.accessType()))
                .anyMatch(a -> a.exist());
    }

    public void fillAuthRules() {
        rules.add(new Authorisation("Smith", "Book", "read", true));
        rules.add(new Authorisation("Smith", "Book", "write", false));
        rules.add(new Authorisation("Jones", "Book", "write", true));
    }
}
