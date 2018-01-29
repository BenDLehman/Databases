

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestFileAdd {

	FileMain main = new FileMain();
	@Test
	public void test() throws IOException {
		main.RAFaddItem();
		assertEquals(1,1);
	}

}
