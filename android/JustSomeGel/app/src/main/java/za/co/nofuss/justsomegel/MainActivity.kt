package za.co.nofuss.justsomegel

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import za.co.nofuss.justsomegel.opengl.surfaceview.FractalGLSurfaceView

class MainActivity : AppCompatActivity() {
    private var mGLView: GLSurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Create and set GLSurfaceView
        mGLView = FractalGLSurfaceView(this)
        setContentView(mGLView)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE.or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                    .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                    .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                    .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    override fun onPause() {
        super.onPause()
        mGLView!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        mGLView!!.onResume()
    }
}

// class MainActivity : AppCompatActivity() {
//
//     private var customGLSurfaceView: CustomGLSurfaceView? = null
//
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         customGLSurfaceView = CustomGLSurfaceView(this)
//         setContentView(customGLSurfaceView)
//     }
//
//     override fun onSaveInstanceState(outState: Bundle?) {
//         outState?.putFloat(STATE_ROTATION_ANGLE, customGLSurfaceView?.myRenderer?.angle ?: 0.0f)
//         super.onSaveInstanceState(outState)
//     }
//
//     override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//         customGLSurfaceView?.myRenderer?.angle = savedInstanceState?.getFloat(STATE_ROTATION_ANGLE) as Float
//         super.onRestoreInstanceState(savedInstanceState)
//     }
//
//     companion object {
//         private val STATE_ROTATION_ANGLE = "angle"
//     }
// }
