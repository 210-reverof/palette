package com.smilegate.Easel.domain

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.smilegate.Easel.R

fun isDoneAction(actionId: Int, event: KeyEvent?): Boolean {
    return actionId == EditorInfo.IME_ACTION_DONE ||
            (event != null && event.action == KeyEvent.ACTION_DOWN &&
                    event.keyCode == KeyEvent.KEYCODE_ENTER)
}

fun containsSpaceOrNewline(s: CharSequence?): Boolean {
    return s?.contains(" ") == true || s?.contains("\n") == true
}


fun handleBackPressed(activity: AppCompatActivity, doubleBackPressed: Boolean): Boolean {
    var doubleBackPressedVar = doubleBackPressed

    if (doubleBackPressedVar) {
        // 두 번째 눌림
        activity.finish()
    } else {
        // 2초 내에 두 번 누르지 않으면 초기화
        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackPressedVar = false
        }, 2000)
    }
    return true
}

// dp를 픽셀로 변환하는 함수
fun dpToPx(dp: Float, context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (dp * density).toInt()
}

fun createCustomToast(context: Context, message: String, backgroundColor: Int): Toast {
    val toast = Toast(context)
    val layout = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null)

    val textView = layout.findViewById<TextView>(R.id.custom_toast_text)
    textView.text = message
    textView.setBackgroundColor(backgroundColor)

    toast.view = layout
    toast.duration = Toast.LENGTH_SHORT

    return toast
}
