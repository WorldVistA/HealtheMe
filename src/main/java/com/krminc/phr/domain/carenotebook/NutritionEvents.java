package com.krminc.phr.domain.carenotebook;

import java.util.List;
import java.util.Vector;

/**
 * @author cmccall
 */
public class NutritionEvents {
	private Vector<NutritionEvent> nutritionEvents;
	private Long healthRecordId;

	public Long getHealthRecordId() {
		return healthRecordId;
	}

	public NutritionEvents(Vector<NutritionEvent> nutritionEvents, Long healthRecordId) {
		if (nutritionEvents.isEmpty()) {
			nutritionEvents.add(new NutritionEvent(healthRecordId));
		}

		this.nutritionEvents = nutritionEvents;
		this.healthRecordId = healthRecordId;
	}

	public List<NutritionEvent> getNutritionEvents() {
		return nutritionEvents;
	}

	public void setNutritionEvents(Vector<NutritionEvent> nutritionEvents) {
		this.nutritionEvents = nutritionEvents;
	}
}
