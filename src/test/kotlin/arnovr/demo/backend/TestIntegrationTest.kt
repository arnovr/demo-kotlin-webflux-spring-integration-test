package arnovr.demo.backend

import arnovr.demo.backend.config.WebSecurityConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.util.*

@AutoConfigureWebTestClient
@SpringBootTest
@Import(WebSecurityConfig::class)
class TestIntegrationTest(
    @Autowired private var client: WebTestClient
) : BaseIntegrationTest() {
    @Test
    fun shouldCreate() {
        client.post()
            .uri("/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                BodyInserters.fromValue(
                    """{
                          "title": "Test Entity"
                        }"""
                        .trimIndent()
                )
            )
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
    }

    @Test
    fun shouldGet() {
        client.get()
            .uri("/d407cc5f-e6c2-4857-a52e-d7bbc8a8333d")
            .exchange()
            .expectStatus().isOk
            .expectBody().jsonPath("$.title").isEqualTo("Title")
    }
}
