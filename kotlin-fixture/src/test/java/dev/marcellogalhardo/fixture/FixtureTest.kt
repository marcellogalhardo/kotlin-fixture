package dev.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FixtureTest {

    @Test
    fun shouldGenerate() {
        // Arrange
        val expectedContent = "123"

        // Act
        val result = Fixture.generate { expectedContent }

        // Assert
        assertThat(result).isEqualTo(expectedContent)
    }

    @Test
    fun shouldGenerateList() {
        // Arrange & Act
        val result = Fixture.list(from = 10, until = 50) { index -> index }

        // Assert
        assertThat(result.count() >= 10).isTrue()
        assertThat(result.count() <= 50).isTrue()
    }

    @Test
    fun shouldGenerateMap() {
        // Arrange & Act
        val result = Fixture.map(from = 10, until = 50) {
            char() to string()
        }

        // Assert
        assertThat(result.count() >= 10).isTrue()
        assertThat(result.count() <= 50).isTrue()
    }
}
