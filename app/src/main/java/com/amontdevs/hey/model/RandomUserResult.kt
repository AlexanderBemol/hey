package com.amontdevs.hey.model

import java.util.Date

data class RandomUserResult(
    val results: List<RandomUser>
)

data class RandomUser(
    val name: RandomUserName,
    val location: RandomUserLocation,
    val dob: RandomUserDOB,
    val email: String,
    val cell: String,
    val picture: RandomUserPhotos
)

data class RandomUserName(
    val first: String,
    val last: String
)

data class RandomUserLocation(
    val street: RandomUserLocationStreet,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: RandomUserLocationCoordinates
)

data class RandomUserLocationStreet(
    val number: Int,
    val name: String
)

data class RandomUserLocationCoordinates(
    val latitude: String,
    val longitude: String
)

data class RandomUserDOB(
    val date: Date
)

data class RandomUserPhotos(
    val large: String,
    val medium: String,
    val thumbnail: String
)