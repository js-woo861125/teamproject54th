package ks54team01.customer.member.mapper;



import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CustomerMemberMapper {
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);
	

}
