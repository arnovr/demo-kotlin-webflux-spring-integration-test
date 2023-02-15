package arnovr.demo.backend.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*


@Table("test")
data class Test (
    @Id
    @Column("id")
    private var _id: UUID,

    var title: String,

    @Column("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    var createdAt: LocalDateTime? = null,
) : Persistable<UUID> {
    @Transient
    private var new = false

    override fun getId(): UUID {
        return _id;
    }

    @JsonIgnore
    override fun isNew(): Boolean {
        return new || _id == null
    }

    fun setAsNew(): Test {
        new = true
        return this
    }
}
