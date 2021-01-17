package com.rumbl.rumbl_pt.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.rumbl.rumbl_pt.R

/**
 * Created by Mohamed Shalan on 10/5/20.
 */

object DialogsUtils {


    inline fun showBlockingDialog(
        context: Context,
        @LayoutRes view: Int,
        @StringRes messageRes: Int? = null,
        message: String? = null,
        crossinline retryAction: () -> Unit = {},
        crossinline cancelAction: () -> Unit = {}
    ): AlertDialog {
        val inflatedView: View
        return AlertDialog.Builder(context).apply {
            inflatedView = LayoutInflater.from(context).inflate(view, null)
            inflatedView.findViewById<TextView>(R.id.tv_dialog_message)?.apply {
                messageRes?.let {
                    text = context.getString(it)
                }
                message?.let {
                    text = it
                }
            }
            setView(inflatedView)
            setCancelable(false)
        }.create().apply {
            window?.decorView?.setBackgroundResource(android.R.color.transparent)
            inflatedView.findViewById<TextView>(R.id.btn_retry)?.setOnClickListener {
                dismiss()
                retryAction.invoke()
            }
            inflatedView.findViewById<TextView>(R.id.tv_dialog_cancel)?.setOnClickListener {
                dismiss()
                cancelAction.invoke()
            }
            show()
        }
    }

}
