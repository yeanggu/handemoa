package com.hande.moa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan

//예은님
@ComponentScan(basePackages = "index")
@MapperScan(basePackages = "index")
@ComponentScan(basePackages = "admin")
@MapperScan(basePackages = "admin")
@ComponentScan(basePackages = "notice")
@MapperScan(basePackages = "notice")
@ComponentScan(basePackages = "adminmember")
@MapperScan(basePackages = "adminmember")
@ComponentScan(basePackages = "postmanage")
@MapperScan(basePackages = "postmanage")
@ComponentScan(basePackages = "profile")
@MapperScan(basePackages = "profile")

//정년님
@ComponentScan(basePackages = "adminreport")
@MapperScan(basePackages = "adminreport")
@ComponentScan(basePackages = "admincomment")
@MapperScan(basePackages = "admincomment")
@ComponentScan(basePackages = "bookmark")
@MapperScan(basePackages = "bookmark")
@ComponentScan(basePackages = "search")
@MapperScan(basePackages = "search")
@ComponentScan(basePackages = "report")
@MapperScan(basePackages = "report")

//은택님
@ComponentScan(basePackages = "member")
@MapperScan(basePackages = "member")

//세윤님
@ComponentScan(basePackages = "rankpost")
@MapperScan(basePackageClasses = rankpost.RankPostDAO.class)
@ComponentScan(basePackages = "category1")
@MapperScan(basePackageClasses = category1.CategoryDAO.class)

//윤석님
@ComponentScan(basePackages = "commupost")
@MapperScan(basePackageClasses = commupost.CommuPostDAO.class)




public class HandemoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandemoaApplication.class, args);
	}

}
