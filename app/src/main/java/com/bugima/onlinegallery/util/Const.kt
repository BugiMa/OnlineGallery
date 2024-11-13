package com.bugima.onlinegallery.util

object Const {
    // API
    const val INITIAL_PAGE = 0
    const val PAGE_SIZE = 20

    // Pagination Interceptor
    const val LINK_HEADER_REGEX = "<([^>]+)>; rel=\"next\"" // Regex to get next page url from 'Link' header
    const val LINK_HEADER = "Link"
    const val NEXT_PAGE_URL = "NextPageUrl"
}
