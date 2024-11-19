package com.bugima.onlinegallery.util

object Const {
    // API
    const val INITIAL_PAGE = 0
    const val PAGE_SIZE = 20

    // Pagination Interceptor
    const val LINK_HEADER_REGEX = "<([^>]+)>; rel=\"next\""
    const val LINK_HEADER = "Link"
    const val NEXT_PAGE_URL = "NextPageUrl"

    // String
    const val TEXT_MAX_LINE_ONE = 1
    const val TEXT_MAX_LINE_TWO = 2


    // Animation
    const val SPIN_DEGREE = 1080f
    const val SPIN_DURATION = 3000
}
