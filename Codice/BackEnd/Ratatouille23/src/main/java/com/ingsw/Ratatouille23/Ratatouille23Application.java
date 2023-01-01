package com.ingsw.Ratatouille23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ingsw.Ratatouille23.DAO.*;

import java.sql.SQLException;

@SpringBootApplication
public class Ratatouille23Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Ratatouille23Application.class, args);
	}

}
