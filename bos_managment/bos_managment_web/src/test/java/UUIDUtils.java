import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class UUIDUtils {
	
	public static String getId() {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		return id;
	}
	
	@Test
	public void test() {
		String id = UUID.randomUUID().toString();
		Date time = Calendar.getInstance().getTime();
		Date date = new Date();
		System.out.println(date);
		
	}
}
