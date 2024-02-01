package com.example.mvvmrecyclerview

data class ApiResponse<T>(val success: T? = null , val error: String? = null)
