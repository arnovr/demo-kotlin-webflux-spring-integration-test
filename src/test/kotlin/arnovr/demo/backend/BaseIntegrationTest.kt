package arnovr.demo.backend

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("it")
abstract class BaseIntegrationTest {
    @Container
    private val postgres = PostgreSQLContainer("postgres:latest")
        .withPassword("password")
        .withUsername("backend")
        .withDatabaseName("backend")
        .withExposedPorts(5432)
        .waitingFor(Wait.defaultWaitStrategy())

    @BeforeEach
    fun beforeAll() {
        postgres.start()
    }

    @AfterAll
    fun afterAll() {
        postgres.stop()
    }
}
