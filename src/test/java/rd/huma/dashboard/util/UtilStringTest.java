package rd.huma.dashboard.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;

public class UtilStringTest {


	@Test
	public void probar(){
		Assert.assertTrue("20150812.16604.01".equals(UtilString.subStringDespues("/branches/20150812.16604.01", "/branches/")));
	}

	@Test
	public void probar2(){
		Assert.assertTrue(UtilString.noContiene("/branches/20150812.16604.01", "trunk"));
	}
	@Test

	public void bla(){
		System.out.println(Instant.now().minus(70, ChronoUnit.DAYS));

	}
}
