package za.co.nofuss.justsomegel.opengl.surfaceview

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import za.co.nofuss.justsomegel.opengl.fractal.FractalRenderer

/**
 * Basic rewrite to make the custom GLSurfaceView a bit more modern and "Kotlin-like".
 */

class FractalGLSurfaceView(context: Context) : GLSurfaceView(context) {
    private val mRenderer: FractalRenderer? = null
    private val mDetector: ScaleGestureDetector? = null

    private class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mRenderer.zoom(detector.scaleFactor, detector.focusX, detector.focusY)
            return true
        }
    }
}