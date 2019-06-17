package com.sandeep.scannertest.services

interface Constants {
    enum class SERVICETYPES {
        SCANNER_INFO
    }
    enum class RequestType {
        GET, POST
    }
    companion object {
        val PLAIN_TEXT_TYPE = "plain_text"
        val VARIABLE_TYPE = "variable"
    }
}