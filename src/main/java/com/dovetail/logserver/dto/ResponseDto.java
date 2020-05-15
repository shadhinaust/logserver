package com.dovetail.logserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
    private Object data;
    private boolean hasNext;
    private int totalPages;
}
