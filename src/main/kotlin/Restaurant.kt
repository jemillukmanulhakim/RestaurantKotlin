package org.example

import org.example.Menu.Drink
import org.example.Menu.Food
import org.example.Menu.ItemMenu
import org.example.Menu.MenuPack

class Restaurant(val nama: String) {
    val menuLists: List<ItemMenu> = listOf(
        Food("Spaghetti", 10, "Pasta lezat dengan saus tomat", 5),
        Food("Pizza", 12, "Pizza panggang dengan berbagai toping", 4),
        Food("Burger", 8, "Burger juicy dengan keju dan sayuran", 4),
        Food("Salad", 6, "Salad segar dari taman", 3),
        Food("Coca-Cola", 2, "Biasa", 10),
        Food("Air mineral", 2, "Biasa",  10),
        Food("Es Teh", 3, "Manis", 9)
    )

    val menuPack: List<MenuPack> = listOf(
        MenuPack("Paket BS", menuLists[2].harga + menuLists[3].harga, setOf(
            menuLists[2], menuLists[3]
        ))
    )

    private val customerLists: MutableList<Customer> = mutableListOf()

    fun displayMenu() {
        println("Menu $nama: ")
        for ((index, item) in menuLists.withIndex()) {
            println("${index + 1} ${item.nama} - RP${item.harga}")
        }

        for ((index, item) in menuPack.withIndex()) {
            println("${index + 1 + menuLists.size} ${item.nama} - Rp${item.harga}")
        }
    }

    fun takeOrder() {
        println("Selamat datang di $nama! Silahkan masukkan nama: ")
        val customerName = readLine()
        val customer = Customer(customerName ?: "Guests")

        var isOrdering = true
        var customerOrder = mutableListOf<ItemMenu>()

        println("Masukkan nomor item yang ingin dipesan (0 untuk keluar):")
        while (isOrdering) {
            val option = readLine()?.toInt() ?: 0
            if (option in 1..menuLists.size) {
                val orderedItem = menuLists[option - 1]
                if (orderedItem is Food && orderedItem.stock > 0) {
                    orderedItem.minusStock(1)
                    customerOrder.add(orderedItem)
                } else if (orderedItem is Drink && orderedItem.stock > 0) {
                    orderedItem.minusStock(1)
                    customerOrder.add(orderedItem)
                } else {
                    println("Maaf, stock item tidak mencukup atau item tidak tersedia.")
                }
            }

            else if(option in (menuLists.size + 1)..(menuLists.size + menuPack.size)) {
                val orderedPack = menuPack[option - menuLists.size - 1]
                if (orderedPack.items.all { it.stock > 0 }) {
                    for (item in orderedPack.items) {
                        item.minusStock(1)
                    }
                    customerOrder.add(orderedPack)
                } else {
                    println("Maaf, stok item dalam paket tidak mencukupi atau item tidak tersedia.")
                }
            } else if (option == 0) {
                isOrdering = false
            } else {
                println("Pilihan tidak valid. Silakan pilih item menu yang valid.")
            }
        }

        if (customerOrder.isNotEmpty()) {
            for (orderedItem in customerOrder) {
                if (orderedItem is ItemMenu) {
                    customer.order(orderedItem)
                } else if (orderedItem is MenuPack) {
                    customer.orderPack(orderedItem)
                }

                customer.trackOrder()
                val existingCustomer = customerLists.find {
                    it.nama.equals(customerName)
                }

                if (existingCustomer == null) {
                    customerLists.add(customer)
                }
            }
        } else {
            println("Pesanan kosong. Terima kasih!")
        }
    }

    fun showCustomerLists() {
        println("Daftar Pelanggan:")
        for ((index, customer) in customerLists.withIndex()) {
            println("${index + 1} ${customer.nama}")
        }
    }

    fun addStock() {
        println("Pilih opsi:")
        println("1. Tambah Stock Makanan")
        println("2. Tambah Stock Minuman")
        val option = readLine()?.toIntOrNull()

        when (option) {
            1 -> {
                println("Pilih Makanan:")
                for ((index, item) in menuLists.filterIsInstance<Food>().withIndex()) {
                    println("${index + 1} ${item.nama}")
                }
                val foodIndex = readLine()?.toIntOrNull()
                if (foodIndex != null && foodIndex in 1..menuLists.filterIsInstance<Food>().size) {
                    val makanan = menuLists.filterIsInstance<Food>()[foodIndex - 1]

                    println("Masukkan jumlah stok yang ingin ditambahkan:")
                    val jumlahStok = readLine()?.toIntOrNull()

                    if (jumlahStok != null && jumlahStok > 0) {
                        makanan.addStock(jumlahStok)
                    } else {
                        println("Input tidak valid. Masukkan jumlah stok yang valid.")
                    }
                } else {
                    println("Pilihan tidak valid. Silakan pilih Makanan yang valid.")
                }
            }
            2 -> {
                println("Pilih Minuman:")
                for ((index, item) in menuLists.filterIsInstance<Drink>().withIndex()) {
                    println("${index + 1} ${item.nama}")
                }
                val drinkIndex = readLine()?.toIntOrNull()

                if (drinkIndex != null && drinkIndex in 1..menuLists.filterIsInstance<Drink>().size) {
                    val minuman = menuLists.filterIsInstance<Drink>()[drinkIndex - 1]

                    println("Masukkan jumlah stok yang ingin ditambahkan:")
                    val jumlahStok = readLine()?.toIntOrNull()

                    if (jumlahStok != null && jumlahStok > 0) {
                        minuman.addStock(jumlahStok)
                    } else {
                        println("Input tidak valid, masukkan jumlah stok yang valid.")
                    }
                } else {
                    println("Pilihan tidak valid, silakan pilih Minuman yang valid.")
                }
            }
            else -> println("Pilihan tidak valid, silakan pilih opsi yang valid.")
        }
    }
}