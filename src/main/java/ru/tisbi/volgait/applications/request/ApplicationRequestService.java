package ru.tisbi.volgait.applications.request;

import java.util.UUID;

public interface ApplicationRequestService {

	void addRequest(UUID applicationUuid, String requestTitle, String additionalInfo);

}
