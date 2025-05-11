package ks54team01.enterprise.prodcut.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EnterpriseProduct {
	private Integer period;
	private Double marginRatio;
	private String useStatus;
	private LocalDate registerDate;
	private LocalDate revisionDate;
}
