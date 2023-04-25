package com.project.goms.infrastructure.feign.error

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception =
        FeignException.errorStatus(methodKey, response)

}