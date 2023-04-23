package com.project.goms.domain.outing

import com.project.goms.domain.account.Account
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
class Outing(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "outing_idx", columnDefinition = "BINARY(16)", nullable = false)
    val idx: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    val account: Account
)