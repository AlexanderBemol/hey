package com.amontdevs.hey.model

class NetworkException() : Exception()
class MissingPhotoException(val filename: String) : Exception()
class UnknownException() : Exception()
