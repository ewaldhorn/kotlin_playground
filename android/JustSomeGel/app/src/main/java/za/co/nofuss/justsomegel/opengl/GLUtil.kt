package za.co.nofuss.justsomegel.opengl

import android.opengl.GLES20
import android.util.Log
import za.co.nofuss.justsomegel.opengl.shaders.baseFragmentShaderSource
import za.co.nofuss.justsomegel.opengl.shaders.baseVertexShaderSource

/** Wrapper data class for OpenGL Shading Language (GLSL) source code */
data class GLSLSource(val shaderCode: String)

/** Wrapper class for GLESPrograms */
data class GLESProgram(val programRef: Int)

fun createGLESProgram(): GLESProgram {
    return GLESProgram(GLES20.glCreateProgram())
}

/**
 * Parent class of my OpenGL objects
 */
abstract class OpenGLShape {
    protected val myProgram: GLESProgram

    constructor(
        program: GLESProgram,
        vertexShader: GLSLSource = GLSLSource(baseVertexShaderSource),
        fragmentShader: GLSLSource = GLSLSource(baseFragmentShaderSource)
    ) {

        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShader)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader)
        myProgram = program

        GLES20.glAttachShader(myProgram.programRef, vertexShader)
        GLES20.glAttachShader(myProgram.programRef, fragmentShader)
        GLES20.glLinkProgram(myProgram.programRef)
    }

    abstract fun draw(mMVPMatrix: FloatArray)
}

fun loadShader(shaderType: Int, shaderSource: GLSLSource): Int {
    val shader = GLES20.glCreateShader(shaderType)

    GLES20.glShaderSource(shader, shaderSource.shaderCode)
    GLES20.glCompileShader(shader)

    val compilationStatus = IntArray(1) // Only holds one int

    GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compilationStatus, 0)

    if (compilationStatus[0] == GLES20.GL_FALSE) {
        Log.e("OpenGL", "Unable to compile Shader program (Code $compilationStatus[0])")
    }

    return shader
}