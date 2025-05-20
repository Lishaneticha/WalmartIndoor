package com.lishan.walmartindoor.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.entity.Shelf

data class SectionWithShelves(
    @Embedded val section: Section,

    @Relation(
        parentColumn = "sectionId",
        entityColumn = "sectionOwnerId"
    )
    val shelves: List<Shelf>
)