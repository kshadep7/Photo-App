package com.akash.flickrbrowser

class Photo(
    private val title: String,
    private val link: String,
    private val image: String,
    private val author: String,
    private val authorId: String,
    private val tags: String
) {
    override fun toString(): String {
        return "Photo(title='$title', link='$link', image='$image', author='$author', authorId='$authorId', tags='$tags')"
    }
}