package sharedpart;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public record StatusDTO(List<String> cart, Set<String> recent, Set<String> result, List<Double> cartPrice)
        implements Serializable {

}
