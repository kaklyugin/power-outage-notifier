package org.rostovenergoparser.mapper;

import org.mapstruct.Mapper;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.tgclient.dto.updates.UpdateResponseDto;

@Mapper(componentModel = "spring")
public interface UpdateResponseMapper {
   UpdateDto mapUpdateResponseToUpdateDto(UpdateResponseDto updateResponseDto);
}
