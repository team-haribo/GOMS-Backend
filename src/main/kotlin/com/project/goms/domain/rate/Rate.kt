package com.project.goms.domain.rate

import com.project.goms.domain.account.persistence.entity.Account
import javax.persistence.*

@Entity
@Table(name = "tbl_rate")
class Rate(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    val account: Account
)