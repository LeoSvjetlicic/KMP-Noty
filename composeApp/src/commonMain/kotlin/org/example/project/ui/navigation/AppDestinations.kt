package org.example.project.ui.navigation

import org.ls.notey.utils.Constants.CHAT_WITH_ID
import org.ls.notey.utils.Constants.NOTE_ROUTE

sealed class AppRoute(
    open val route: String,
)

object DetailsRoute : AppRoute(CHAT_WITH_ID) {
    fun createNavigationRoute(id: Long): String = "${NOTE_ROUTE}/$id"
}
