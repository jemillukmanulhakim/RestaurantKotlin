package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val restaurant = Restaurant("Restoran")
    var running = true

    while (running) {println("Pilih opsi:")
        println("1. Buat pesanan")
        println("2. Tampilkan Daftar Pelanggan")
        println("3. Tambah Stok Makanan/Minuman")
        println("4. Keluar")
        val pilihan = try {
            readlnOrNull()?.toInt() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Pilihan tidak valid. Silakan pilih opsi yang valid.")
            continue
        }
        when (pilihan) {
            1 -> {
                restaurant.displayMenu()
                restaurant.takeOrder()
            }
            2 -> restaurant.showCustomerLists()
            3 -> restaurant.addStock()
            4 -> running = false
        }
    }
}