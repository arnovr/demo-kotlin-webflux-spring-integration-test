package arnovr.demo.backend

import arnovr.demo.backend.model.Test
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.core.io.Resource
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Component
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.util.*

@Component
class InitialData(
    private val environment: Environment,
    @Value("classpath:schema.sql") private val initSql: Resource,
    private val entityTemplate: R2dbcEntityTemplate
) {
    @PostConstruct
    fun init() {
        if (!environment.activeProfiles.contains("dev") && !environment.activeProfiles.contains("it")) {
            return
        }
        val query: String = StreamUtils.copyToString(initSql.inputStream, StandardCharsets.UTF_8)
        entityTemplate
            .databaseClient
            .sql(query)
            .then()
            .subscribe()

        insertEntity(
            Test(
                UUID.fromString("d407cc5f-e6c2-4857-a52e-d7bbc8a8333d"),
                "Title",
                LocalDateTime.now()
            )
        )
    }

    private fun insertEntity(entity: Any) {
        entityTemplate.insert(
            entity
        ).then().subscribe();
    }
}


