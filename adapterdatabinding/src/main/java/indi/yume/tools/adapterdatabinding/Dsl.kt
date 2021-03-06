package indi.yume.tools.adapterdatabinding

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.View
import indi.yume.tools.dsladapter.renderers.LayoutRenderer

fun <T : Any, DB : ViewDataBinding> LayoutRenderer.Companion.dataBindingItem(
        count: Int = 1,
        @LayoutRes layout: Int,
        bindBinding: (View) -> DB,
        binder: (DB, T, Int) -> Unit = { _, _, _ -> },
        recycleFun: (DB) -> Unit = { }) =
        LayoutRenderer<T>(
                count = count,
                layout = layout,
                binder = { view, data, index ->
                    val binding = DataBindingUtil.getBinding(view)
                            ?: bindBinding(view)
                    binder(binding, data, index)
                },
                recycleFun =
                { view ->
                    val binding = DataBindingUtil.getBinding(view)
                            ?: bindBinding(view)
                    recycleFun(binding)
                })
