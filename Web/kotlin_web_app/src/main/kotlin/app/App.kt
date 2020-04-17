package app

import components.*
import kotlinx.css.*
import models.*
import react.*
import react.dom.*
import styled.*

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        h1 {
            +"Kotlin and React"
        }
        div {
            h3 {
                +"Videos to Watch"
            }
            videoList {
                videos = unwatchedVideos
            }
            h3 {
                +"Videos watched"
            }
            videoList {
                videos = watchedVideos
            }
        }
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"John Doe: Building and breaking things"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}