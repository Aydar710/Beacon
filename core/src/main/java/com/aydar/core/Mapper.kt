package com.aydar.core

interface Mapper<Input, Output> {

    fun map(input: Input): Output
}