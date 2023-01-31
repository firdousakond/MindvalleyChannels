package com.mindvalley.mindvalleyapptest

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.mindvalley.mindvalleyapptest.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class ChannelScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shimmerLoadingTest() {
        composeTestRule.onNodeWithTag("ShimmerColumn").assertIsDisplayed()
    }

    @Test
    fun channelContentTest() {
        composeTestRule.waitUntilTimeout(3000)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.label_channel))[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.label_new_episodes))[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.label_browse_categories))[0].assertExists()
        composeTestRule.onAllNodesWithTag("episodeList")[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("channelList")[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("categoryList")[0].assertExists()
    }

}