package za.co.nofuss.justsomegel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import za.co.nofuss.justsomegel.opengl.surfaceview.CustomGLSurfaceView

class MainActivity : AppCompatActivity() {

    private var customGLSurfaceView: CustomGLSurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customGLSurfaceView = CustomGLSurfaceView(this)
        setContentView(customGLSurfaceView)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putFloat(STATE_ROTATION_ANGLE, customGLSurfaceView?.myRenderer?.angle ?: 0.0f)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        customGLSurfaceView?.myRenderer?.angle = savedInstanceState?.getFloat(STATE_ROTATION_ANGLE) as Float
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        private val STATE_ROTATION_ANGLE = "angle"
    }
}
