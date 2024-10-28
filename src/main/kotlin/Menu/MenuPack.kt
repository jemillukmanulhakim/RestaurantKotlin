package org.example.Menu

class MenuPack(nama: String, harga: Int, items: Set<ItemMenu>) : ItemMenu(nama, harga, 0) {
    val items: Set<ItemMenu> = items
}