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
    val btn = document.getElementById("playVideo") as HTMLButtonElement
    val photoButton = document.getElementById("photoButton") as HTMLButtonElement

    fun getVideo() {
        window.navigator.mediaDevices.getUserMedia(constraints = MediaStreamConstraints(video = true, audio = false))
            .then { lms ->
                video.srcObject = lms
                video.crossOrigin = "anonymous"

                btn.addEventListener("click", { video.play() })
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

        window.setInterval({ ctx.drawImage(video, 0.0, 0.0, 640.0, 480.0) }, 100)
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