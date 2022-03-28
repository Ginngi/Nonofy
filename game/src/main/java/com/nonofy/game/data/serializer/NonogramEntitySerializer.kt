package com.nonofy.game.data.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.nonofy.game.data.proto.Game
import java.io.InputStream
import java.io.OutputStream

object NonogramEntitySerializer : Serializer<Game.NonogramEntity> {
    override val defaultValue: Game.NonogramEntity
        get() = Game.NonogramEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Game.NonogramEntity {
        try {
            return Game.NonogramEntity.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Game.NonogramEntity, output: OutputStream) {
        t.writeTo(output)
    }
}