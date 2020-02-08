package com.darapay.loanreferral.entities;

import java.util.Date;

public interface ListEntity {
	 int isDeleted = 0;
	 Date createdDateTime = new Date();
	 Date modifiedDateTime = new Date();
	 int getIsDeleted();
	 void setIsDeleted(int isDeleted);
	 Date getCreatedDateTime();
	 void setCreatedDateTime(Date createdDateTime);
	 Date getModifiedDateTime();
	 void setModifiedDateTime(Date modifiedDateTime) ;
}
