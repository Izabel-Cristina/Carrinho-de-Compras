package br.com.lojapesca.lojadepesca;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("br.com.lojapesca.lojadepesca")
class LojadepescaApplicationTests {

	@Test
	void main() {
		LojadepescaApplication.main(new String[] {});
	}
}
