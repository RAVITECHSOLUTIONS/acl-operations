package com.acl.service.impl;

import com.acl.constants.ErrorConstant;
import com.acl.datafilter.AccountMasterSearchCriteria;
import com.acl.datafilter.AccountMetricFilter;
import com.acl.exceptions.InvalidInputException;
import com.acl.exceptions.ResourceNotFoundException;
import com.acl.model.AccountMaster;
import com.acl.model.AccountMetric;
import com.acl.models.CreateAccountMetricInput;
import com.acl.repository.AccountMasterRepository;
import com.acl.repository.AccountMetricRepository;
import com.acl.repository.CustomQueryRepository;
import com.acl.repository.SnapShotControlRepository;
import com.acl.service.AccountMetricService;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class AccountMetricServiceImpl implements AccountMetricService {

	private static final String CLASS_NAME = AccountMetricServiceImpl.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(CLASS_NAME);

	private AccountMasterRepository accountMasterRepository;

	private CustomQueryRepository customQueryRepository;
	private SnapShotControlRepository snapShotControlRepository;
	private AccountMetricRepository accountMetricRepository;

//	@Override
//	public List<AccountMetricResponse> getAccountMetrics(AccountMetricFilter filter) {
//		logger.info("GQL call to get account metric data for filter: {}", filter);
//		validateAccountMetricFilter(filter);
//		AccountMasterSearchCriteria input = buildSearchCriteriaInput(filter);
//		List<AccountMaster> accountMasters = customQueryRepository.findAccountWithoutFees(input);
//		if (CollectionUtils.isEmpty(accountMasters)) {
//			logger.warn(ErrorConstant.ACCOUNT_NOT_FOUNT_FOR_INPUT + "{}", filter);
//			throw new ResourceNotFoundException(ErrorConstant.ACCOUNT_NOT_FOUNT_FOR_INPUT + filter);
//		}
//		Map<BigInteger, AccountMaster> accountMasterMap = accountMasters.stream()
//				.collect(Collectors.toMap(AccountMaster::getAccountKey, Function.identity()));
//		List<BigInteger> accountKeys = accountMasterMap.keySet().stream().toList();
//		Map<BigInteger, List<AccountMetric>> accountMetricsMap = loadAccountMetrics(accountKeys,
//				filter);
//		List<AccountMetricResponse> response = new ArrayList<>();
//		for (BigInteger key : accountKeys) {
//			AccountMetricResponse accountMetricResponse = new AccountMetricResponse();
//			accountMetricResponse.setAccountNumber(accountMasterMap.get(key).getAccountNumber());
//			accountMetricResponse.setTaxId(accountMetricResponse.getTaxId());
//			accountMetricResponse.setParentAccountNumber(
//					accountMasterMap.get(key).getParentAccountNumber());
//			accountMetricResponse.setAccountMetrics(accountMetricsMap.get(key));
//			response.add(accountMetricResponse);
//		}
//		return response;
//	}

	@Override
	public AccountMetric createAccountMetric(CreateAccountMetricInput input) {
		logger.info("GQL call to create account metric: {}", input.getAccountKey());

		AccountMetric accountMetric = AccountMetric.builder().accountKey(input.getAccountKey())
				.marketVal(input.getMarketVal()).principalFeeMtd(input.getPrincipalFeeMtd())
				.principalCash(input.getPrincipalCash()).loadDate(new Date()).snapShotDate(new Date())
				.build();
		return accountMetricRepository.save(accountMetric);
	}

	@Override
	public List<AccountMetric> getAccountMetricsByAccountNumber(AccountMetricFilter filter) {
		logger.info("GQL call to get account metrics data for filter: {}", filter);
		validateAccountMetricFilter(filter);
		AccountMasterSearchCriteria input = buildSearchCriteriaInput(filter);
		List<AccountMaster> accountMasters = customQueryRepository.findAccountWithoutFees(input);
		if (CollectionUtils.isEmpty(accountMasters)) {
			logger.warn(ErrorConstant.ACCOUNT_NOT_FOUNT_FOR_INPUT + "{}", filter);
			throw new ResourceNotFoundException(ErrorConstant.ACCOUNT_NOT_FOUNT_FOR_INPUT + filter);
		}
		Map<BigInteger, AccountMaster> accountMasterMap = accountMasters.stream()
				.collect(Collectors.toMap(AccountMaster::getAccountKey, Function.identity()));
		List<BigInteger> accountKeys = accountMasterMap.keySet().stream().toList();
		return accountMetricRepository.getAccountMetricsByAccountKeys(
				accountKeys);
	}

	private AccountMasterSearchCriteria buildSearchCriteriaInput(AccountMetricFilter filter) {
		AccountMasterSearchCriteria input = new AccountMasterSearchCriteria();
		input.setAccountNumbers(
				CollectionUtils.isEmpty(filter.getAccountNumber()) ? Collections.emptyList()
						: filter.getAccountNumber());
		input.setTaxIds(CollectionUtils.isEmpty(filter.getTaxId()) ? Collections.emptyList()
				: filter.getTaxId());
		input.setParentAccountNUmber(
				CollectionUtils.isEmpty(filter.getParentAccountNumber()) ? Collections.emptyList()
						: filter.getParentAccountNumber());
		return input;
	}

//	private Map<BigInteger, List<AccountMetric>> loadAccountMetrics(List<BigInteger> accountKeys,
//			AccountMetricFilter filter) {
//		Pageable pageable = getPageable(GraphQlConstants.PAGE, GraphQlConstants.CUSTOM_SIZE, null, null,
//				GraphQlConstants.SNAPSHOT_DATE_DEFAULT_SORTING, AccountMetric.class);
//		Date snapShotDate = snapShotControlRepository.findAll().get(0).getCurrentSnapShotDate();
//		List<AccountMetric> accountMetrics = accountMetricRepository.getAccountMetricsByAccountKeys(
//				accountKeys, CommonUtil.getDate(filter.getStartSnapshotDate(), snapShotDate),
//				CommonUtil.getDate(filter.getEndSnapshotDate(), snapShotDate), pageable);
//		return accountMetrics.stream().collect(Collectors.groupingBy(AccountMetric::getAccountKey))
//				.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
//						entry -> CommonUtil.getPageFromList(entry.getValue(), filter.getPage(),
//								filter.getSize() > 0 ? filter.getSize() : GraphQlConstants.SIZE)));
//	}

	private void validateAccountMetricFilter(AccountMetricFilter filter) {
		boolean hasSearchCriteria =
				filter != null && (!CollectionUtils.isEmpty(filter.getAccountNumber())
//						|| CollectionUtils.isEmpty(filter.getParentAccountNumber())
//						|| CollectionUtils.isEmpty(filter.getTaxId())
//						|| CollectionUtils.isEmpty(filter.getStartSnapshotDate()) || !CollectionUtils.isEmpty(
//						filter.getEndSnapshotDate())
				);
		if (!hasSearchCriteria) {
			logger.debug("Filter has search criteria: {}", hasSearchCriteria);
			logger.warn(ErrorConstant.ACCOUNT_METRIC_INVALID_INPUT + "{}", filter);
			throw new InvalidInputException(ErrorConstant.ACCOUNT_METRIC_INVALID_INPUT + filter);
		}
	}
}

