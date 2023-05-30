package com.lewiswon.batchpolling.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class SalesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id : Long? = null

    private lateinit var product : String
    private lateinit var seller : String
    private var sellerId : Int? = null
    private var price : Double? = null
    private lateinit var city : String
    private lateinit var category: String
}