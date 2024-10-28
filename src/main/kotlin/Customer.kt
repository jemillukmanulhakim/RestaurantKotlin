package org.example

import org.example.Menu.Drink
import org.example.Menu.Food
import org.example.Menu.ItemMenu
import org.example.Menu.MenuPack

data class Customer(val nama: String) {
    var order: MutableList<Any?> = mutableListOf()
    var totalOrder: Int = 0

    fun orderPack(itemPack: MenuPack?) {
        order.add(itemPack)
        // If itemPack is null, it will output error NPE
        totalOrder += itemPack?.harga ?: 0
    }

    fun order(itemMenu: ItemMenu?) {
        order.add(itemMenu)
        // If itemPack is null, it will output error NPE
        totalOrder += itemMenu?.harga ?: 0
    }

    fun trackOrder() {
        println("Pesanan $nama: ")

        for (item in order) {
            if (item is MenuPack) {
                println("${item.nama} - Rp${item.harga}")
                println("Berisi: ")
                for (itemPack in item.items) {
                    println("${itemPack.nama} - Rp${itemPack.harga}")
                }
            } else {
                if (item is Food) {
                    println("${item.nama} (${item.desc}) - Rp${item.harga}")
                } else if (item is Drink) {
                    println("${item.nama} (${item.desc}) - Rp${item.harga}")
                }
            }
        }
        println("Total Tagihan: Rp$totalOrder")
    }
}