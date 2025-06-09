package ks54team01.admin.transferBoard.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTransferBoardMapper {

	int removeTransferBoard(String transferBoardNum);
}
