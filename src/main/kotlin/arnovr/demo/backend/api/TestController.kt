package arnovr.demo.backend.api

import arnovr.demo.backend.model.Test
import arnovr.demo.backend.model.TestRepository
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/")
class TestController(
    val testRepository: TestRepository
)  {
    @GetMapping("{id}")
    fun get(
        @PathVariable id: UUID
    ): Mono<Test> {
        return testRepository.findBy_id(id)
    }

    @PostMapping("")
    fun create(@RequestBody ar: CreateTest, response: ServerHttpResponse): Mono<ResponseEntity<TestResponse>> {
        return testRepository.save(
            Test(UUID.randomUUID(), ar.title, LocalDateTime.now()).setAsNew()
        ).map {
            ResponseEntity.ok(TestResponse(it.id))
        }
    }
}

data class CreateTest(
    val title: String
)

data class TestResponse(val id: UUID)
