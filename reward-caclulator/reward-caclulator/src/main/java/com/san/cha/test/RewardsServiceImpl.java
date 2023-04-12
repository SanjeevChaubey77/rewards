package com.san.cha.test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsServiceImpl implements RewardsService {

    public static final int days = 30;
    public static int firstLimit = 50;
    public static int secondLimit = 100;
	
	@Autowired
	TransRepository transactionRepository;

	public Rewards getRewardsByCustomerId(Long customerId) {

		Timestamp lastMonthTimestamp = Timestamp.valueOf(LocalDateTime.now().minusDays(days));
		Timestamp lastSecondMonthTimestamp = Timestamp.valueOf(LocalDateTime.now().minusDays(2*days));
		Timestamp lastThirdMonthTimestamp = Timestamp.valueOf(LocalDateTime.now().minusDays(3*days));

		List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
				customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
						lastSecondMonthTimestamp);

		Rewards custRewards = new Rewards();
		custRewards.setCustID(customerId);
		
		Long lMRPoints = getRewardsByMonth(lastMonthTransactions);
		Long lSMRPoints = getRewardsByMonth(lastSecondMonthTransactions);
		Long lTMRPoints = getRewardsByMonth(lastThirdMonthTransactions);


		custRewards.setLMRPoints(lMRPoints);
		custRewards.setLSMRPoints(lSMRPoints);
		custRewards.setLTMRPoints(lTMRPoints);
		custRewards.setTotalRewards(lMRPoints + lSMRPoints + lTMRPoints);

		return custRewards;

	}

	private Long getRewardsByMonth(List<Transaction> data) {
		return data.stream().map(transaction -> calculate(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}

	private Long calculate(Transaction t) {
		if (t.getTransactionAmount() > firstLimit && t.getTransactionAmount() <= secondLimit) {
			return Math.round(t.getTransactionAmount() - firstLimit);
		} else if (t.getTransactionAmount() > secondLimit) {
			return Math.round(t.getTransactionAmount() - secondLimit) * 2
					+ (secondLimit - firstLimit);
		} else
			return 0l;

	}

}
