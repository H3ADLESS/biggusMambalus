import de.h3ad.mamba.math.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VectorComparisonTest {

    // from Timo dedicated to Norman
    @Test
    void shouldBeEquals() {
        var v = new Vector3(476.56757010146976, 103.63044999656267);
        Assertions.assertEquals(new Vector3(476.56757010146976, 103.63044999656267), v);
    }

}
