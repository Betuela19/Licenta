package com.masterandroid.doamneaimila

data class GetDiseaseResponse(val id: Int, val organId: Int,
                              val organ: String, val name: String,
                              val description: String, val causes: String,
                              val symptoms: String, val diagnostic: String,
                              val category: Int, val importance: Int)
