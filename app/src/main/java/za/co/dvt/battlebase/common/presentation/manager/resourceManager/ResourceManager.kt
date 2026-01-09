package za.co.dvt.battlebase.common.presentation.manager.resourceManager

import android.app.Application
import androidx.annotation.StringRes

class ResourceManager(
    private val application: Application
) {
    fun getString(@StringRes stringResId: Int): String {
        return application.getString(stringResId)
    }
}