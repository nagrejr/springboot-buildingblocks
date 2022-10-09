package com.newlife.springbootbuildingblocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

//second commit
@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
//@EntityScan("com.newlife.springbootbuildingblocks.entities")
public class SpringbootBuildingblocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}

}
