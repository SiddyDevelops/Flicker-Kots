package com.siddydevelops.flicker_kots

class Photo(val title:String, val author:String, val authorId:String, val link:String, val tag:String, val image:String) {
    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorId='$authorId', link='$link', tag='$tag', image='$image')"
    }
}