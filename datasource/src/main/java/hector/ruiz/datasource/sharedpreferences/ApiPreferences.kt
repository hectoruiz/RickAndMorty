package hector.ruiz.datasource.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ApiPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferencesName = context.packageName + NAME
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

    companion object {
        const val NAME = "SharedPreferences"
    }
}
