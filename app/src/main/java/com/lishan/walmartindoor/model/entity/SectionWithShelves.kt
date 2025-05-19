package com.lishan.walmartindoor.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SectionWithShelves(
    @Embedded val section: Section,

    @Relation(
        parentColumn = "sectionId",
        entityColumn = "sectionOwnerId"
    )
    val shelves: List<Shelf>
)