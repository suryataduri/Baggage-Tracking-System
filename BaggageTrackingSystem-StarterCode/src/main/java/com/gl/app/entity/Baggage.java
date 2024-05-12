package com.gl.app.entity;

import lombok.ToString;


@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Builder
@lombok.ToString
@lombok.EqualsAndHashCode
@lombok.Getter
@lombok.Setter
public class Baggage {
	private String claimId;
	private String location;
	private String status;
	private String userId;
	

}
