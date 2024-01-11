package com.openspace.Model.Dto;

import java.time.LocalDate;

public class Sessions {

	private Integer totalSessions;
	
	private Integer completeSessions;
	
	private Integer awaitingSessions;
	
	private Integer cancelledSessions;
	
	private Integer paidSessions;
	
	private Integer unPaidSessions;
	
	private Integer duration;
	
   private String lastPaymentDate;

public Integer getTotalSessions() {
	return totalSessions;
}

public void setTotalSessions(Integer totalSessions) {
	this.totalSessions = totalSessions;
}

public Integer getCompleteSessions() {
	return completeSessions;
}

public void setCompleteSessions(Integer completeSessions) {
	this.completeSessions = completeSessions;
}

public Integer getAwaitingSessions() {
	return awaitingSessions;
}

public void setAwaitingSessions(Integer awaitingSessions) {
	this.awaitingSessions = awaitingSessions;
}

public Integer getCancelledSessions() {
	return cancelledSessions;
}

public void setCancelledSessions(Integer cancelledSessions) {
	this.cancelledSessions = cancelledSessions;
}

public Integer getPaidSessions() {
	return paidSessions;
}

public void setPaidSessions(Integer paidSessions) {
	this.paidSessions = paidSessions;
}

public Integer getUnPaidSessions() {
	return unPaidSessions;
}

public void setUnPaidSessions(Integer unPaidSessions) {
	this.unPaidSessions = unPaidSessions;
}

public Integer getDuration() {
	return duration;
}

public void setDuration(Integer duration) {
	this.duration = duration;
}

public String getLastPaymentDate() {
	return lastPaymentDate;
}

public void setLastPaymentDate(String lastPaymentDate) {
	this.lastPaymentDate = lastPaymentDate;
}

/*public LocalDate getLastPaymentDate() {
	return lastPaymentDate;
}

public void setLastPaymentDate(LocalDate lastPaymentDate) {
	this.lastPaymentDate = lastPaymentDate;
}*/

}
