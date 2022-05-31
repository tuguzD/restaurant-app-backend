package io.github.tuguzd.restaurantapp.buildconfig.backend.dependency

object Spring {
    object Boot {
        const val configProcessor =
            "org.springframework.boot:spring-boot-configuration-processor"

        const val jpa = "org.springframework.boot:spring-boot-starter-data-jpa"
        const val web = "org.springframework.boot:spring-boot-starter-web"
        const val security = "org.springframework.boot:spring-boot-starter-security"

        const val test = "org.springframework.boot:spring-boot-starter-test"
    }

    object Doc {
        const val version = "1.6.8"

        const val ui = "org.springdoc:springdoc-openapi-ui:$version"
        const val security = "org.springdoc:springdoc-openapi-security:$version"
        const val kotlin = "org.springdoc:springdoc-openapi-kotlin:$version"
    }
}
