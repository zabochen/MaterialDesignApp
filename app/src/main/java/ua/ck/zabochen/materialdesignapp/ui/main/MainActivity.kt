package ua.ck.zabochen.materialdesignapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_main.*
import ua.ck.zabochen.materialdesignapp.R
import ua.ck.zabochen.materialdesignapp.ui.dialog.MyBottomSheetDialogFragment

class MainActivity : AppCompatActivity() {

    private val tag: String = "MainActivity"

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_main)

        // Toolbar
        setSupportActionBar(activityMain_toolbar)

        // BottomSheet
        this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet_layout_linearLayout).apply {
            setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> Log.i(tag, "STATE_HIDDEN")
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            activityMain_button_bottomSheetAction.text = "Close"
                            Log.i(tag, "STATE_EXPANDED")
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            activityMain_button_bottomSheetAction.text = "Open"
                            Log.i(tag, "STATE_COLLAPSED")
                        }
                        BottomSheetBehavior.STATE_DRAGGING -> Log.i(tag, "STATE_DRAGGING")
                        BottomSheetBehavior.STATE_SETTLING -> Log.i(tag, "STATE_SETTLING")
                        else -> Log.i(tag, "WITHOUT_STATE")
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    Log.i(tag, "onSlide()")
                }
            })
        }

        // Buttons
        activityMain_button_bottomSheetAction.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                activityMain_button_bottomSheetAction.text = "Open"
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                activityMain_button_bottomSheetAction.text = "Close"
            }
        }

        activityMain_button_bottomSheetDialog.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        }

        activityMain_button_bottomSheetFragment.setOnClickListener {
            val bottomSheetDialogFragment = MyBottomSheetDialogFragment()
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
    }
}