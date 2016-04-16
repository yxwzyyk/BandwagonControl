package xyz.yxwzyyk.bandwagoncontrol.utils

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by yyk on 3/23/16.
 */
fun <T : Any> SelectQueryBuilder.parseList(
        parser: (Map<String, Any>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = parser(columns)
        })