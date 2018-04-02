import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

public class TelephoneTest {
	@Test
	public void test() {
		Scanner scanner=new Scanner(System.in);
		Telephone telephone=new Telephone(scanner);
		Connection connection=new Connection(new MailSystem(20),telephone);
		/*scanner.
		telephone.run(connection);*/
		
	}

}
