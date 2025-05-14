package ks54team01.customer.register.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonMember {
	// 공통정보 (members 테이블)
	private String memberId;
	private String memberPw;
	private String memberType;
	private String memberStateTransitionDate;
}

