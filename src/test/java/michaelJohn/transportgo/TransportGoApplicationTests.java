package michaelJohn.transportgo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransportGoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testDatabaseConnection(){
		DriverManagerDataSource dataSource =
				new DriverManagerDataSource("jdbc:mysql://127.0.0.1:3306", "root", "Mjoo741993_micolami");
		try {
			Connection connection = dataSource.getConnection();
			assertThat(connection).isNotNull();
		} catch (SQLException exception) {
			throw new RuntimeException(exception);
		}
	}
}
