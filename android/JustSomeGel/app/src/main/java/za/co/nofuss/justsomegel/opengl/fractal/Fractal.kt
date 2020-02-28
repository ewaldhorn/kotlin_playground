package za.co.nofuss.justsomegel.opengl.fractal

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class Fractal {
    private val vertexShaderCode = "attribute vec4 vPosition;" +
        "void main() {" +
        "  gl_Position = vPosition;" +
        "}"

    private val fragmentShaderCode = "precision highp float;" +
        "uniform mat4 uMVPMatrix;" +
        "void main() {" +  // Transform given position to coordinate space
        "  vec2 p = (uMVPMatrix * vec4(gl_PointCoord,0,1)).xy;" +
        "  vec2 c = p;" +  // Set default color to black in HSV
        "  vec3 color=vec3(0.0,0.0,0.0);" +  // Use 200 as an arbitrary limit. The higher the number, the slower and more detailed it will be
        "  for(int i=0;i<200;i++){" +  // Perform z = z^2 + c using p, which represents the real and imaginary parts of z
        "  	   p= vec2(p.x*p.x-p.y*p.y,2.0*p.x*p.y)+c;" +
        "      if (dot(p,p)>4.0){" +  // colorRegulator continuously increases smoothly by 1 for every additional step it takes to break
        "         float colorRegulator = float(i-1)-log(log(length(p)))/log(2.0);" +  // Set color to a cycling color scheme using the smooth number
        "         color = vec3(0.95 + .012*colorRegulator , 1.0, .2+.4*(1.0+sin(.3*colorRegulator)));" +
        "         break;" +
        "      }" +
        "  }" +  //Convert HSV to RGB. Algorithm from https://gist.github.com/patriciogonzalezvivo/114c1653de9e3da6e1e3
        "  vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);" +
        "  vec3 m = abs(fract(color.xxx + K.xyz) * 6.0 - K.www);" +
        "  gl_FragColor.rgb = color.z * mix(K.xxx, clamp(m - K.xxx, 0.0, 1.0), color.y);" +
        "  gl_FragColor.a=1.0;" +
        "}"

    private var vertexBuffer: FloatBuffer? = null
    private var drawListBuffer: ShortBuffer? = null
    private var mProgram = 0
    private var mPositionHandle = 0
    private var mMVPMatrixHandle = 0

    // number of coordinates per vertex in this array
    val COORDS_PER_VERTEX = 3

    var squareCoords = floatArrayOf(
        -1.0f, 1.0f, 0.0f,  // top left
        -1.0f, -1.0f, 0.0f,  // bottom left
        1.0f, -1.0f, 0.0f,  // bottom right
        1.0f, 1.0f, 0.0f
    ) // top right

    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3) // order to draw vertices

    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    init {
        // initialize vertex byte buffer for shape coordinates
        // initialize vertex byte buffer for shape coordinates
        val bb: ByteBuffer =
            ByteBuffer.allocateDirect( // (# of coordinate values * 4 bytes per float)
                squareCoords.size * 4
            )
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer?.let {
            it.put(squareCoords)
            it.position(0)
        }

        // initialize byte buffer for the draw list
        // initialize byte buffer for the draw list
        val dlb: ByteBuffer =
            ByteBuffer.allocateDirect( // (# of coordinate values * 2 bytes per short)
                drawOrder.size * 2
            )
        dlb.order(ByteOrder.nativeOrder())
        drawListBuffer = dlb.asShortBuffer()
        drawListBuffer?.let {
            it.put(drawOrder)
            it.position(0)
        }

        // prepare shaders and OpenGL program
        // prepare shaders and OpenGL program
        val vertexShader: Int = FractalRenderer.loadShader(
            GLES20.GL_VERTEX_SHADER,
            vertexShaderCode
        )
        val fragmentShader: Int = FractalRenderer.loadShader(
            GLES20.GL_FRAGMENT_SHADER,
            fragmentShaderCode
        )

        mProgram = GLES20.glCreateProgram() // create empty OpenGL Program

        GLES20.glAttachShader(mProgram, vertexShader) // add the vertex shader to program

        GLES20.glAttachShader(mProgram, fragmentShader) // add the fragment shader to program

        GLES20.glLinkProgram(mProgram) // create OpenGL program executables
    }

    fun draw(mvpMatrix: FloatArray?) { // Add program to OpenGL environment
        GLES20.glUseProgram(mProgram)
        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0)
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        GLES20.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer
        )
        // Draw the square
        GLES20.glDrawElements(
            GLES20.GL_TRIANGLES, drawOrder.size,
            GLES20.GL_UNSIGNED_SHORT, drawListBuffer
        )
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle)
    }
}