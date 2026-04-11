package com.acl.util;

import com.acl.constants.ErrorConstant;
import com.acl.constants.GraphQlConstants;
import com.acl.exceptions.InvalidInputException;
import com.acl.exceptions.InvalidSortParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class CommonUtil {

	public static final String CLASS_NAME = CommonUtil.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(CLASS_NAME);

	public static boolean isEmptyOrNull(String str) {
		return null == str || str.isBlank();
	}

	public static Date getDate(String date) {
		Date convertDate = null;
		if (isEmptyOrNull(date)) {
			return convertDate;
		}
		if (CommonUtil.isValidDateFormat(date)) {
			convertDate = CommonUtil.convertStringToDate(date);
		} else {
			logger.warn(ErrorConstant.INVALID_DATE_FORMAT + "{}", date);
			throw new InvalidInputException(ErrorConstant.INVALID_DATE_FORMAT + date);
		}
		return convertDate;
	}

	public static Pageable getPageable(int page, int size, String sortBy, SortingOrder sortOrder,
			String defaultSort, Class<?> entityClass) {
		String finalSortBy = CommonUtil.isEmptyOrNull(sortBy) ? defaultSort : sortBy;
		boolean isValidParameter = Arrays.stream(entityClass.getDeclaredFields())
				.anyMatch(f -> f.getName().equals(finalSortBy));
		if (!isValidParameter) {
			throw new InvalidSortParameterException(sortBy, entityClass);
		}
		Sort sort = getSortOrders(sortOrder, finalSortBy);
		return getPageSizeWithSorting(page, size, sort);
	}

	private static Pageable getPageSizeWithSorting(int page, int size, Sort sort) {
		int p = (page != 0) ? page : GraphQlConstants.PAGE;
		int s = (size != 0) ? size : GraphQlConstants.SIZE;
		return PageRequest.of(p, s, sort);
	}

	private static Sort getSortOrders(SortingOrder sortOrder, String finalSortBy) {
		Sort.Direction direction = (sortOrder == SortingOrder.ASC) ? Direction.ASC : Direction.DESC;
		return Sort.by(direction, finalSortBy);
	}

	public static Date getDate(String startOrEndDate, Date currentSnapShotDate) {
		return startOrEndDate != null ? getDate(startOrEndDate) : currentSnapShotDate;
	}

	private static Date convertStringToDate(String input) {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate = null;
		try {
			convertedDate = inputFormat.parse(input);
		} catch (Exception e) {
			e.getMessage();
		}
		return convertedDate;
	}

	private static boolean isValidDateFormat(String date) {
		boolean valid = false;
		String dateFormat = "yyyy-MM-dd";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
		try {
			LocalDate.parse(date, dateFormatter);
			valid = true;
		} catch (DateTimeParseException e) {
			e.getMessage();
			valid = false;
		}
		return valid;
	}

	public static <T> List<T> getPageFromList(List<T> sourceList, int page, int pageSize) {
		if (pageSize <= 0 || page < 0) {
			page = 0;
			pageSize = 20;
		}
		int fromIndex = (page) * pageSize;
		if (sourceList == null || sourceList.size() <= fromIndex) {
			return Collections.emptyList();
		}
		return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
	}
}
