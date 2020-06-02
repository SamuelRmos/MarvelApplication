package com.example.desafio_android_samuel_ramos.base

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.File

abstract class BaseTest {

    private lateinit var mMockServerInstance: MockWebServer
    private var mShouldStart = false

    open fun setUp() {
        starMockServer()
    }

    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
        mMockServerInstance.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    private fun starMockServer() {
        mMockServerInstance = MockWebServer()
        mMockServerInstance.start()
    }

    fun getMockWebServerUrl() = mMockServerInstance.url("/").toString()

    private fun stopMockServer() {
        if (mShouldStart)
            mMockServerInstance.shutdown()
    }

    open fun tearDown() {
        stopMockServer()
    }
}