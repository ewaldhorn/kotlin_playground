import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.mediacapture.*
import kotlin.browser.*

fun main() {
    val r = RollIt()
    r.getVideo()
}

class RollIt {
    val video = document.querySelector(".player") as HTMLVideoElement
    val canvas = document.querySelector(".photo") as HTMLCanvasElement
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
    val strip = document.querySelector(".strip") as HTMLDivElement

    // val snap = document.querySelector(".snap") as HTMLAudioElement
    val mirrorButton = document.getElementById("mirrorButton") as HTMLButtonElement
    val photoButton = document.getElementById("photoButton") as HTMLButtonElement

    var isMirroring = false

    fun getVideo() {
        window.navigator.mediaDevices.getUserMedia(constraints = MediaStreamConstraints(video = true, audio = false))
            .then { lms ->
                video.srcObject = lms
                video.crossOrigin = "anonymous"

                mirrorButton.addEventListener("click", { isMirroring = !isMirroring })
                photoButton.addEventListener("click", { takePhoto() })

                video.play()
                paintToCanvas()
            }.catch { err ->
                console.error("Could not access camera: ${err.message}")
            }
    }

    private fun paintToCanvas() {
        val width = 640
        val height = 480

        canvas.width = width
        canvas.height = height

        window.setInterval({
            // ctx.setTransform(-1.0, 0.0, 0.0, 1.0, 0.0, 0.0);
            ctx.drawImage(video, 0.0, 0.0, 640.0, 480.0)
            var pixels = ctx.getImageData(0.0, 0.0, 640.0, 480.0)
            pixels = rgbSplit(pixels)
            ctx.putImageData(pixels, 0.0, 0.0)
            // ctx.setTransform(1.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        }, 100)
    }

    private fun rgbSplit(im: ImageData): ImageData {
        for (i in 0 until im.data.length step 4) {
            im.data[i - 150] = im.data[i + 0]
            im.data[i + 500] = im.data[i + 1]
            im.data[i - 550] = im.data[i + 2]
        }

        return im
    }

    private fun greenScreen(im: ImageData): ImageData {

        return im;
    }

    private fun takePhoto() {
        val data = canvas.toDataURL("image/jpeg")
        val link = document.createElement("a") as HTMLAnchorElement
        link.href = data
        link.setAttribute("download", "cammiweb")
        link.innerHTML = "<img src=\"$data\" alt=\"CammiWeb Snapshot\" />"
        strip.insertBefore(link, strip.firstChild);
    }
}