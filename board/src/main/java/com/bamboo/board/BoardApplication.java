package com.bamboo.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bamboo.*")      
//해당 package 경로의 Component 들을 Scan한다는 의미. 그러니까... 해당 패키지의 @Controller, @Repository, @Service 등을 애플리케이션 시작 시에 탐색한다. 탐색을 왜 하냐? ㅎㅎ 해당 class 파일에 @Autowired 가 있을 경우 의존성도 주입시켜줘야 하고...
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
