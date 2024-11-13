package com.bugima.onlinegallery.data.remote.interceptor

import com.bugima.onlinegallery.util.Const.LINK_HEADER
import com.bugima.onlinegallery.util.Const.LINK_HEADER_REGEX
import com.bugima.onlinegallery.util.Const.NEXT_PAGE_URL
import okhttp3.Interceptor
import okhttp3.Response
import java.util.regex.Pattern

class PaginationInterceptor: Interceptor {

    private val linkHeaderPattern = Pattern.compile(LINK_HEADER_REGEX)

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        response.header(LINK_HEADER)?.let {
            linkHeaderPattern.matcher(it).takeIf { matcher -> matcher.find() }
                ?.group(1)
                ?.let { nextPageUrl ->
                    response.newBuilder()
                        .addHeader(NEXT_PAGE_URL, nextPageUrl)
                        .build()
                }
        }
        return response
    }
}
