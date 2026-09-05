package com.example.gpt_mobile.domain.parser

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ThinkingParserTest {

    private fun parseThinking(content: String): Pair<String?, String> {
        val thinkRegex = Regex("<think>([\\s\\S]*?)</think>", RegexOption.DOT_MATCHES_ALL)
        val match = thinkRegex.find(content)
        return if (match != null) {
            val thinking = match.groupValues[1].trim()
            val text = content.replace(match.value, "").trim()
            Pair(thinking.ifEmpty { null }, text)
        } else {
            Pair(null, content)
        }
    }

    @Test
    fun `parseThinking extracts think block and remaining content`() {
        val input = "<think>Let me calculate 2+2.</think>The answer is 4."
        val (thinking, text) = parseThinking(input)
        assertEquals("Let me calculate 2+2.", thinking)
        assertEquals("The answer is 4.", text)
    }

    @Test
    fun `parseThinking handles input without think block`() {
        val input = "Just regular response."
        val (thinking, text) = parseThinking(input)
        assertNull(thinking)
        assertEquals("Just regular response.", text)
    }

    @Test
    fun `parseThinking handles empty think block`() {
        val input = "<think></think>No thoughts."
        val (thinking, text) = parseThinking(input)
        assertNull(thinking)
        assertEquals("No thoughts.", text)
    }

    @Test
    fun `parseThinking handles multiline thinking`() {
        val input = """
            <think>
            Step 1: Check inputs.
            Step 2: Compute result.
            </think>
            Done.
        """.trimIndent()
        val (thinking, text) = parseThinking(input)
        assertEquals("Step 1: Check inputs.\nStep 2: Compute result.", thinking)
        assertEquals("Done.", text)
    }
}
