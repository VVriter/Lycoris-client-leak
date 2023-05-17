package ua.lycoris.client.ui.clickgui.listen.util

import java.util.function.Consumer

/**
 * @author PunCakeCat/Kristofer
 */
class LinkedProcess<T> {
    private val list: ArrayList<Processor<T>> = ArrayList()

    private lateinit var result: Consumer<T>

    fun apply(obj: T): LinkedProcess<T> {
        if(list.stream().map { processor -> processor.accept(obj) }.noneMatch(Boolean::not))
            result.accept(obj)
        return this
    }

    fun accept(result: Consumer<T>): LinkedProcess<T> {
        this.result = result
        return this
    }

    fun append(processor: Processor<T>): LinkedProcess<T> {
        list.add(processor)
        return this
    }

    infix fun withAccepted(result: Consumer<T>) = accept(result)

    infix fun withAppended(processor: Processor<T>) = append(processor)
}