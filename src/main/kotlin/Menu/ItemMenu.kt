package org.example.Menu

open class ItemMenu(val nama: String, var harga: Int, open var stock: Int) {
    fun minusStock(sum: Int) {
        if (stock - sum >= 0) {
            stock -= sum
        } else {
            println("Stock tidak mencukupi.")
        }
    }

    open fun addStock(jumlah: Int) {

    }
}