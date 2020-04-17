package components

import models.*
import react.*
import react.dom.*

class VideoList: RComponent<VideoListProps, RState>() {
    override fun RBuilder.render() {
        for (video in props.videos) {
            p {
                key = video.id.toString()
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

external interface VideoListProps: RProps {
    var videos: List<Video>
}