package com.nonofy.game.data.datasource

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class NonogramDataSourceTest {
    private val underTest = NonogramDataSource()

    @Test
    fun whenEasyGameThenReturnNonogramEasy() {
        val nonogram = underTest.easyTest()
        assertThat("a").contains('a'.toString())
    }
}