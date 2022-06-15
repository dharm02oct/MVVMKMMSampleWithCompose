package com.harman.mvvmkmmsample.android.ui

import android.util.Patterns
import java.util.regex.Pattern

object Validators {
    @JvmStatic
    fun required(input: String?): ValidationError? {
        return if (input.isNullOrBlank()) ValidationError.REQUIRED else null
    }

    @JvmStatic
    fun pattern(pattern: Pattern, input: String?, required: Boolean): ValidationError? {
        if (input.isNullOrBlank()) {
            return if (required) ValidationError.REQUIRED else null
        }
        if (!pattern.matcher(input).matches()) {
            return ValidationError.PATTERN
        }
        return null
    }

    @JvmStatic
    fun phonePattern(pattern: Pattern, input: Long?, required: Boolean): ValidationError? {
        if (input == null) {
            return if (required) ValidationError.REQUIRED else null
        }
        if (!pattern.matcher(""+input).matches()) {
            return ValidationError.PATTERN
        }
        return null
    }

    @JvmStatic
    fun email(input: String?, required: Boolean) = pattern(Patterns.EMAIL_ADDRESS, input, required)

    @JvmStatic
    fun phone(input: Long?, required: Boolean) = phonePattern(Patterns.PHONE, input, required)

    enum class ValidationError {
        REQUIRED, PATTERN
    }
}