package com.lewiswon.batchpolling.batch.mapper

import com.lewiswon.batchpolling.batch.dto.SalesInfoDTO
import com.lewiswon.batchpolling.domain.SalesInfo
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SalesInfoMapper {

    fun mapToEntity(salesInfoDTO: SalesInfoDTO) : SalesInfo

}