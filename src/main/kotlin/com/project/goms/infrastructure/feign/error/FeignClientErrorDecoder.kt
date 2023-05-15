package com.project.goms.infrastructure.feign.error

import com.project.goms.infrastructure.feign.error.exception.FeignBadRequestException
import com.project.goms.infrastructure.feign.error.exception.FeignForbiddenException
import com.project.goms.infrastructure.feign.error.exception.FeignUnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {

        if (response.status() >= 400) {
            when (response.status()) {
                400 -> throw FeignBadRequestException()
                401 -> throw FeignUnAuthorizedException()
                403 -> throw FeignForbiddenException()
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }


}