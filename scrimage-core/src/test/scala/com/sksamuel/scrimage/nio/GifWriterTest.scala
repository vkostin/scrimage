package com.sksamuel.scrimage.nio

import com.sksamuel.scrimage.Image
import org.scalatest.{BeforeAndAfter, FunSuite, OneInstancePerTest}

class GifWriterTest extends FunSuite with BeforeAndAfter with OneInstancePerTest {

  val original: Image = Image.fromStream(getClass.getResourceAsStream("/com/sksamuel/scrimage/bird.jpg")).scaleTo(300, 200)

  test("GIF output happy path") {
    val actual = Image(original.bytes(GifWriter.Default))
    val expected = Image.fromStream(getClass.getResourceAsStream("/com/sksamuel/scrimage/io/bird_compressed.gif"))
    assert(expected.pixels.length === actual.pixels.length)
    assert(expected == actual)
  }

  test("GIF progressive output happy path") {
    val actual = Image(original.bytes(GifWriter.Progressive))
    val expected = Image.fromStream(getClass.getResourceAsStream("/com/sksamuel/scrimage/io/bird_progressive.gif"))
    assert(expected.pixels.length === actual.pixels.length)
    assert(expected == actual)
  }
}
