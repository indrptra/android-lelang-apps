package com.appschef.baseproject.view

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import com.appschef.baseproject.App
import com.appschef.baseproject.R
import kotlinx.android.synthetic.main.dialog_progress.view.*

/**
 * Created by Alvin Rusli on 4/6/2016.
 *
 * A customizable loading dialog.
 *
 * For global usage (that overrides onBackPressed() to do nothing),
 * use [com.appschef.androidarchitecturesample.util.Common.showProgressDialog].
 *
 * For customized usage that can handle back press,
 * use [com.appschef.androidarchitecturesample.util.Common.showProgressDialog] (Context, OnBackPressListener)}.
 */
class ProgressDialog(context: Context) {

    /** The dialog */
    private val dialog: Dialog

    /** The view binding */
    private var view: View? = null

    /** The custom back press listener when progress dialog is showing */
    var backPressListener: OnBackPressListener? = null

    /** Generate a [Dialog] with back press listener passed in it */
    private fun Dialog(context: Context, themeResId: Int): Dialog {
        return object : Dialog(context, themeResId) {
            override fun onBackPressed() {
                backPressListener?.onProgressBackPressed()
            }
        }
    }

    init {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
        dialog = Dialog(context, R.style.AppTheme_Dialog_Transparent)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.setContentView(view)
    }

    /**
     * Sets the progress dialog's progress bar indeterminate state.
     * @param isIndeterminate true to make progress bar indeterminate
     */
    fun setIndeterminate(isIndeterminate: Boolean) {
        view?.progress_bar?.isIndeterminate = isIndeterminate
    }

    /**
     * Sets the current progress of the progress dialog.
     * @param percent the progress percentage (0-100)
     */
    fun setProgress(percent: Int) {
        if (view?.progress_bar?.isIndeterminate!!) view?.progress_bar?.isIndeterminate = false
        view?.progress_bar?.progress = percent
    }

    /**
     * Sets the text for the progress dialog.
     * @param stringRes the string resource ID
     */
    fun setText(stringRes: Int) {
        try {
            val text = App.context.resources.getString(stringRes)
            setText(text)
        } catch (e: Resources.NotFoundException) {
            setText(null)
        }
    }

    /**
     * Sets the text for the progress dialog.
     * @param message the string message
     */
    fun setText(message: String?) {
        view?.txt_loading?.text = message
        when {
            message.isNullOrEmpty() -> view?.txt_loading?.visibility = View.GONE
            else -> view?.txt_loading?.visibility = View.VISIBLE
        }
    }

    /** Show the progress dialog */
    fun show() {
        dialog.show()
    }

    /** Dismisses the progress dialog */
    fun dismiss() {
        dialog.dismiss()
    }

    /** Determines if the progress dialog is currently showing */
    val isShowing: Boolean
        get() = dialog.isShowing

    /** The interface to handle back button press while loading is showing */
    interface OnBackPressListener {

        /** Called when back button is pressed when loading is showing */
        fun onProgressBackPressed()
    }

}