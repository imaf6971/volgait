package ru.tisbi.volgait.applications;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AnalythicsServiceImpl implements AnalythicsService {

	private final ApplicationService applicationService;

	public AnalythicsServiceImpl(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	public Map<String, Long> getRequestsByApplicationUuid(UUID uuid, DateFilter filter) {
		Application app = applicationService.getApplicationByUuid(uuid);
		return app.getRequests().stream()
				.filter(request -> request.getDateTime().isAfter(getDateByFilter(filter)))
				.collect(Collectors.groupingBy(request -> request.getType().getTitle(), Collectors.counting()));
	}

	private LocalDateTime getDateByFilter(DateFilter filter) {
		if (filter == DateFilter.WEEK) {
			return LocalDateTime.now().minusWeeks(1);
		}
		if (filter == DateFilter.MONTH) {
			return LocalDateTime.now().minusMonths(1);
		}
		if (filter == DateFilter.YEAR) {
			return LocalDateTime.now().minusYears(1);
		}
		throw new IllegalArgumentException();
	}

}
