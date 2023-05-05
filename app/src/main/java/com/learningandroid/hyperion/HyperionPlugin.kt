package com.learningandroid.hyperion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.auto.service.AutoService
import com.learningandroid.R
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
internal class HyperionPlugin : Plugin() {
    override fun createPluginModule(): PluginModule {
        return HyperionPluginModule()
    }
}

internal class HyperionPluginModule : PluginModule() {
    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View? {
        val view = layoutInflater.inflate(R.layout.hyperion_plugin, parent, false)?.apply {
            setOnClickListener {
                // TODO debug menu画面を追加
            }
        }
        return view
    }
}
