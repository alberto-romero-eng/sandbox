package localhost.CommandLineRun;



// import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;



/*
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
*/

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CommandLineApp1Test {
	
	private static Logger log = LogManager.getLogger(CommandLineApp1Test.class);

	@Test
	public void contextLoads() throws Exception {
		
		log.debug("test 1 debug simple, no exception!");
		log.info("test 1 info simple, no exception!");
		
		// throw new Exception("Alberto generated!!!");
		
	}

}