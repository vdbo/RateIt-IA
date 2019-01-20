package com.vdbo.rateitia.common

/**
 * This wrapper could be used for sending single events like navigation, show message to user, etc
 */
class SingleEvent<out T>(private val value: T) {

    private var consumed = false

    /**
     * Returned value only if it hasn't been consumed
     */
    fun consume(action: (value: T) -> Unit) {
        if (!consumed) {
            consumed = true
            action(value)
        }
    }

    fun getOrNull(): T? =
        if (!consumed) {
            consumed = true
            value
        } else {
            null
        }

    operator fun component1() = value

}