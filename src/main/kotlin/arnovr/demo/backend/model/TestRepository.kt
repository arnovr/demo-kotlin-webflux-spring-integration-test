package arnovr.demo.backend.model

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface TestRepository  : R2dbcRepository<Test, UUID> {
    fun findBy_id(id: UUID): Mono<Test>
}
