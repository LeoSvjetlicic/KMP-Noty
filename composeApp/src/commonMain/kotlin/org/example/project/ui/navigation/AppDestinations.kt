package org.example.project.ui.navigation

import org.example.project.utils.Constants.CHAT_WITH_ID
import org.example.project.utils.Constants.NOTE_ROUTE

sealed class AppRoute(
    open val route: String,
)

object DetailsRoute : AppRoute(CHAT_WITH_ID) {
    fun createNavigationRoute(id: Long): String = "${NOTE_ROUTE}/$id"
}
