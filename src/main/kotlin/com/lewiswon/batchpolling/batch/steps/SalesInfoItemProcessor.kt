package com.lewiswon.batchpolling.batch.steps

import com.lewiswon.batchpolling.batch.dto.SalesInfoDTO
import com.lewiswon.batchpolling.batch.mapper.SalesInfoMapper
import com.lewiswon.batchpolling.domain.SalesInfo
import org.springframework.batch.item.ItemProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SalesInfoItemProcessor : ItemProcessor<SalesInfoDTO, SalesInfo> {


    private lateinit var salesInfoMapper: SalesInfoMapper

    override fun process(item: SalesInfoDTO): SalesInfo? {
        return salesInfoMapper.mapToEntity(item)
    }

}