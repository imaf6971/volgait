package ru.tisbi.volgait.applications.request;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tisbi.volgait.applications.ApplicationService;

@Service
public class ApplicationRequestServiceImpl implements ApplicationRequestService {

	private final ApplicationService applicationService;

	private final RequestTypeRepository requestTypeRepository;

	private final RequestRepository requestRepository;

	public ApplicationRequestServiceImpl(ApplicationService applicationService,
			RequestTypeRepository requestTypeRepository, RequestRepository requestRepository) {
		this.applicationService = applicationService;
		this.requestTypeRepository = requestTypeRepository;
		this.requestRepository = requestRepository;
	}

	@Override
	@Transactional
	public void addRequest(UUID applicationUuid, String requestTitle, String additionalInfo) {
		var application = applicationService.getApplicationByUuid(applicationUuid);
		var request = createRequest(requestTitle, additionalInfo);
		application.addRequest(request);
		requestRepository.save(request);
	}

	private Request createRequest(String requestTitle, String additionalInfo) {
		var requestType = getTypeByTitleOrCreateNew(requestTitle);
		return new Request(requestType, additionalInfo);
	}

	private RequestType getTypeByTitleOrCreateNew(String requestTitle) {
		return requestTypeRepository.findByTitle(requestTitle).orElse(new RequestType(requestTitle));
	}

}
