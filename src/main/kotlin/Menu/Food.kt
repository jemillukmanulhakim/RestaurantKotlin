package org.example.Menu

class Food(nama: String, harga: Int, val desc: String, override var stock: Int) : ItemMenu(nama, harga, stock) {
    override fun addStock(jumlah: Int) {
        super.addStock(jumlah)
        stock += jumlah
        print("Stock $nama ditambah sebanyak $jumlah\n")
    }
}