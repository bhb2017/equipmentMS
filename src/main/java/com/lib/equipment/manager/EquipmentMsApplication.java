package com.lib.equipment.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.lib.equipment.manager.*")
public class EquipmentMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentMsApplication.class, args);
	}

}
