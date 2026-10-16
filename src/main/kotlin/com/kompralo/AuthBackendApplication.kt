package com.kompralo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.kompralo.repository"])
@EntityScan(basePackages = ["com.kompralo.model", "com.kompralo.entity", "com.kompralo.entities", "com.kompralo"])
class AuthBackendApplication

fun main(args: Array<String>) {
	runApplication<AuthBackendApplication>(*args)
}