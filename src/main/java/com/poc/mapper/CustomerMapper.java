package com.poc.mapper;

import com.poc.common.mapper.DtoMapper;
import com.poc.model.domain.Customer;
import com.poc.model.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper extends DtoMapper<CustomerDTO, Customer> {

}
