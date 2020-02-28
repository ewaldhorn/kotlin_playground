package za.co.nofuss.justsomegel.opengl.fractal

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class FractalRenderer : GLSurfaceView.Renderer {
    private var mFractal: Fractal? = null

    private var mHeight = 0
    private var mWidth = 0

    //Store all values as doubles, and truncate for use as floats.
    private var mRatio = 0.0
    private var mY = 1.0
    private var mX = 1.0
    private var mZoom = 0.5
    private val zoomIncrease = 1.5

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        val mMVPMatrix = floatArrayOf(
            (-1.0 / mZoom).toFloat(), 0.0f, 0.0f, 0.0f,
            0.0f, (1.0 / (mZoom * mRatio)).toFloat(), 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            (-mX).toFloat(), (-mY).toFloat(), 0.0f, 1.0f
        )

        mFractal?.draw(mMVPMatrix)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        mWidth = width
        mHeight = height

        //Set viewport to fullscreen
        //Set viewport to fullscreen
        GLES20.glViewport(0, 0, width, height)

        mRatio = width.toDouble() / height
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        mFractal = Fractal()
    }

    fun add(
        dx: Double,
        dy: Double
    ) { //Both are scaled by mHeight, because the ratio is taken into account by the translation matrix
        mX -= dx / (mZoom * mHeight)
        mY -= dy / (mZoom * mHeight)
    }

    fun zoom(scaleFactor: Double, x: Double, y: Double) {
        var scaleFactor = scaleFactor
        var x = x
        var y = y
        scaleFactor = (scaleFactor - 1) * zoomIncrease + 1
        // Default zoom is to top corner of the screen. Thus, changes should be zeroed at that point
        x -= mWidth / 2.toDouble()
        y -= mHeight / 2.toDouble()
        //Note that, because mZoom changes in the add method, there is an implicit division by log(2) hidden through limit discrete summation/integration
        val scale = Math.log(scaleFactor)
        //Move towards focus
        add(-scale * x, -scale * y)
        //add(scale)
        mZoom *= scaleFactor
    }

    companion object {
        private val TAG = "FractalRenderer"

        fun loadShader(type: Int, shaderCode: String?): Int {
            val shader = GLES20.glCreateShader(type)
            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }

        fun checkGlError(glOperation: String) {
            var error: Int
            while (GLES20.glGetError().also { error = it } != GLES20.GL_NO_ERROR) {
                Log.e(TAG, "$glOperation: glError $error")
                throw RuntimeException("$glOperation: glError $error")
            }
        }
    }
}