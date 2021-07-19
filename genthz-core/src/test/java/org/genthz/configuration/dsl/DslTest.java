package org.genthz.configuration.dsl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

public class DslTest {

    @Test
    public void test() {
        final Configuration configuration = new DefaultConfiguration() {
            {
                name("myconfig");

                reg(
                        path("name").filler((c, v) -> RandomStringUtils.random(5))
                );

                reg(
                        path("middleName").filler((c, v) -> RandomStringUtils.random(5)),
                        path("lastName").filler((c, v) -> RandomStringUtils.random(5))
                );
            }
        }
                .reg(c -> Arrays.asList(
                        c.path("birthDate").strict(Date.class).instance(ctx -> new Date()),
                        c.path("sex").strict(String.class).instance(ctx -> RandomUtils.nextBoolean() ? "M" : "F")
                        )
                )
                .reg(c -> Arrays.asList((c.path("code").strict(String.class).instance(ctx -> "ONE"))));
    }
}
