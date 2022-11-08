package com.reactive.servicereactive.base;

import reactor.core.publisher.Mono;

public interface BaseService <RESULT extends BaseResponse, REQUEST extends BaseRequest>{

    Mono<RESULT> execute(REQUEST request);
}
