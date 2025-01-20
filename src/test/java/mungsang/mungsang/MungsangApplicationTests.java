package mungsang.mungsang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = "mungsang.mungsang.domain.entity")
class MungsangApplicationTests {

	@Test
	void contextLoads() {
	}

}
