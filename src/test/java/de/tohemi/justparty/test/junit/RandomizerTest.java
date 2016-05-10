package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.util.IDGenerator;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by Heiko on 16.12.2015.
 */

public class RandomizerTest {
    @Test
    public void generateLength50() {
        Assert.isTrue(50 == IDGenerator.generateID(50).length());
    }
}
