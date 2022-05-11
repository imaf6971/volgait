
package ru.tisbi.volgait.applications;

/**
 * DateFilter
 */
public enum DateFilter {
	WEEK("Неделя"),
	MONTH("Месяц"),
	YEAR("Год");
	
	final String value;

    private DateFilter(String value) {
        this.value = value;
    }

}