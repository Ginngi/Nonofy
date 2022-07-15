package com.nonofy.game.impl.data.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.nonofy.game.impl.data.proto.GameEntity
import java.io.InputStream
import java.io.OutputStream

object NonogramEntitySerializer : Serializer<GameEntity.NonogramEntity> {
    override val defaultValue: GameEntity.NonogramEntity
        get() = GameEntity.NonogramEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): GameEntity.NonogramEntity {
        try {
            return GameEntity.NonogramEntity.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: GameEntity.NonogramEntity, output: OutputStream) {
        t.writeTo(output)
    }
}