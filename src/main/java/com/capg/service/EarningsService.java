package com.capg.service;

import com.capg.dto.EarningsInputDto;
import com.capg.dto.EarningsOutputDto;

public interface EarningsService {

	EarningsOutputDto generateEarningsReport(EarningsInputDto earningsInputDto);

}
