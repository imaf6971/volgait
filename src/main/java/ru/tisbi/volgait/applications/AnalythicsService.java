

package ru.tisbi.volgait.applications;

import java.util.Map;
import java.util.UUID;

/**
 * AnalythicsService
 */
public interface AnalythicsService {

	Map<String, Long> getRequestsByApplicationUuid(UUID uuid, DateFilter filter);

}