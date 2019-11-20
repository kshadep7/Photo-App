package com.akash.flickrbrowser

import android.os.Parcel
import android.os.Parcelable


class Photo(
    var title: String?,
    var link: String?,
    var image: String?,
    var author: String?,
    var authorId: String?,
    var tags: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun toString(): String {
        return "Photo(title='$title', " +
                "link='$link', " +
                "image='$image', " +
                "author='$author', " +
                "authorId='$authorId', " +
                "tags='$tags')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeString(image)
        parcel.writeString(author)
        parcel.writeString(authorId)
        parcel.writeString(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }

    //If using SERIALIZABLE ============================================================================
/*
    companion object {
        private const val serialVersionUID = 1L
    }
*/
/*
    * Classes that require special handling during the serialization and
    * deserialization process must implement special methods with these exact
    * signatures:
    *
    * <PRE>
    * private void writeObject(java.io.ObjectOutputStream out)
    *     throws IOException
    * private void readObject(java.io.ObjectInputStream in)
    *     throws IOException, ClassNotFoundException;
    * private void readObjectNoData()
    *     throws ObjectStreamException;
    * </PRE>
*//*


    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream) {
        Log.d("TAG", "writeObject: called")
        out.writeUTF(title)
        out.writeUTF(link)
        out.writeUTF(image)
        out.writeUTF(author)
        out.writeUTF(authorId)
        out.writeUTF(tags)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inStream: java.io.ObjectInputStream) {
        Log.d("TAG", "readObject: called")
        title = inStream.readUTF()
        link = inStream.readUTF()
        image = inStream.readUTF()
        author = inStream.readUTF()
        authorId = inStream.readUTF()
        tags = inStream.readUTF()
    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData() {
        Log.d("TAG", "readObjectNoData: called")
    }
*/
    //============================================================================

}