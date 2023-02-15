package arnovr.demo.backend.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.util.*


@EnableWebFlux
@Configuration
class WebfluxConfig: WebFluxConfigurer {
    @PostConstruct
    fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "*"
            ).allowedMethods("PUT", "GET", "POST", "OPTIONS", "DELETE").allowedHeaders("*")
    }


    @Bean
    fun jackson2JsonEncoder(mapper: ObjectMapper?): Jackson2JsonEncoder {
        return Jackson2JsonEncoder(mapper!!)
    }

    @Bean
    fun jackson2JsonDecoder(mapper: ObjectMapper?): Jackson2JsonDecoder {
        return Jackson2JsonDecoder(mapper!!)
    }
}
