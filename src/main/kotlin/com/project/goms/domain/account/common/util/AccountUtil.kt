package com.project.goms.domain.account.common.util

import com.project.goms.domain.account.persistence.entity.Account

interface AccountUtil {

    fun getCurrentAccount(): Account

}