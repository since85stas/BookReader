package stas.batura.bookreader.data

import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun setLastPage(page: Int)

    fun getLastPage(): Int

}