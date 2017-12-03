package bos_fore;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class StringUtils {

	@Test
	public void test() {
		String random = RandomStringUtils.random(36);
		random = RandomStringUtils.randomNumeric(36);
		System.out.println(random);
	}
}
