package com.saiferwp.swplanetviewerdemo.core.utils

import java.util.Locale

fun String.capitalizeLocalised() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

fun String.getLastPathElementOrEmpty() =
    split("/").let {
        it.getOrNull(it.lastIndex - 1) ?: ""
    }
