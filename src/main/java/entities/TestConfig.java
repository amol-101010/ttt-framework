package entities;

import org.aeonbits.owner.Config;

public interface TestConfig extends Config {
    String env();
    String url();
    String browser();
    Long implicitWait();
    Long threadCount();
}
