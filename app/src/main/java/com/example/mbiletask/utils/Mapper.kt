package com.example.mbiletask.utils

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}