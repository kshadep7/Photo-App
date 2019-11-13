package com.akash.flickrbrowser

class Photo(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val authorId: String,
    val tags: String
) {
    override fun toString(): String {
        return "Photo(title='$title', " +
                "link='$link', " +
                "image='$image', " +
                "author='$author', " +
                "authorId='$authorId', " +
                "tags='$tags')"
    }
}