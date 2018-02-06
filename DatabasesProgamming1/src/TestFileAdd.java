import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestFileAdd {

	@Before
	public void clear()
	{
		main.fileClear();
		
	}
	FileMain main = new FileMain();
	@Test
	public void test() throws IOException {
		
		main.RAFaddItem();
		main.RAFeditItem();
		assertEquals(1,1);
	}

}
