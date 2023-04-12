package com.san.cha.test;

public class Rewards {
    private long custID;
	private long lMRPoints;
    private long lSMRPoints;
    private long lTMRPoints;
    private long totalRewards;

    public long getCustomerId() {
        return custID;
    }

    public long getLMRPoints() {
		return lMRPoints;
	}

	public void setLMRPoints(long lMRPoints) {
		this.lMRPoints = lMRPoints;
	}

	public long getLSMRPoints() {
		return lSMRPoints;
	}

	public void setLSMRPoints(long lSMRPoints) {
		this.lSMRPoints = lSMRPoints;
	}

	public long getLastThirdMonthRewardPoints() {
		return lTMRPoints;
	}

	public void setLTMRPoints(long lTMRPoints) {
		this.lTMRPoints = lTMRPoints;
	}

	public void setCustID(long custId) {
        this.custID = custId;
    }

    

    public long getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }
}
